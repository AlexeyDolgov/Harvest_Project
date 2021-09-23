package harvest.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import harvest.domain.FruitsVariety;
import harvest.dto.FruitsVarietyImportForm;
import harvest.dto.ImportVarietyFields;
import harvest.service.ExcelReader;
import harvest.service.FruitsVarietyService;

@Controller
@RequestMapping("/variety/fruits")
@PreAuthorize("hasAuthority('ADMIN')")
public class FruitsVarietyController {
	@Autowired
	private FruitsVarietyService fruitsVarietyService;
	@Autowired
	private ExcelReader excelReader;
	
	@GetMapping
	public String viewFruitsVarietyList(Model model) {
		List<FruitsVariety> fruitsVarietiesList = fruitsVarietyService.findAll();
		model.addAttribute("fruitsVarieties", fruitsVarietiesList);

		return "fruitsVarietyList";
	}
	
	@GetMapping("/create")
	public String viewCreationForm(@RequestParam(name = "superRefererURI", required = false) String superRefererURI,
			HttpServletRequest request, Model model) throws URISyntaxException {
		model.addAttribute("refererURI", new URI(request.getHeader("referer")).getPath());
		
		if (superRefererURI != null) {
			model.addAttribute("superRefererURI", superRefererURI);
		}
		
		return "fruitsVarietyCreator";
	}

	@PostMapping("/create")
	public String createFruitsVariety(String refererURI, String superRefererURI, @Valid FruitsVariety fruitsVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("refererURI", refererURI);
            
            if (superRefererURI != "") {
    			model.addAttribute("superRefererURI", superRefererURI);
    		}
            
            return "fruitsVarietyCreator";
        }
        		
		boolean fruitsVarietyExists = !fruitsVarietyService.createFruitsVariety(fruitsVariety);
		
		if (fruitsVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("refererURI", refererURI);
			
			if (superRefererURI != "") {
				model.addAttribute("superRefererURI", superRefererURI);
			}
			
			return "fruitsVarietyCreator";
		}
		
		if (superRefererURI != "") {
			return "redirect:" + refererURI + "?superRefererURI=" + superRefererURI;
		}
		
		return "redirect:" + refererURI;
	}
	
	@GetMapping("/edit")
	public String viewEditForm(@RequestParam("id") FruitsVariety fruitsVariety, Model model) {
		model.addAttribute("fruitsVariety", fruitsVariety);
		
		return "fruitsVarietyEditor";
	}

	@PostMapping("/edit")
	public String saveFruitsVariety(@RequestParam("id") FruitsVariety fruitsVariety, @Valid FruitsVariety updatedFruitsVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
			model.mergeAttributes(errors);
			model.addAttribute("fruitsVariety", fruitsVariety);
			
			return "fruitsVarietyEditor";
		}
		
		boolean fruitsVarietyExists = !fruitsVarietyService.updateFruitsVariety(updatedFruitsVariety);
		
		if (fruitsVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("fruitsVariety", fruitsVariety);

			return "fruitsVarietyEditor";
		}

		return "redirect:/variety/fruits";
	}
	
	@GetMapping("/delete")
	public String deleteFruitsVariety(@RequestParam("id") FruitsVariety fruitsVariety) {
		if (!fruitsVariety.getFruits().isEmpty()) {
			return "redirect:/403";
		}
		
		fruitsVarietyService.deleteFruitsVariety(fruitsVariety);

		return "redirect:/variety/fruits";
	}
	
	@GetMapping("/import")
	public String viewFruitsVarietyImportForm() {
		return "fruitsVarietyImport";
	}
	
	@PostMapping("/import")
	public RedirectView getFruitsImportVarietyFields(ImportVarietyFields importVarietyFields, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws IOException {
		redirectAttributes.addFlashAttribute("importVarietyFields", importVarietyFields);
		
		return new RedirectView("/variety/fruits/importConfirm");
	}
	
	@GetMapping("/importConfirm")
	public String viewFruitsVarietyImportConfirmList(@ModelAttribute("importVarietyFields") ImportVarietyFields importVarietyFields, Model model) throws IOException {
		List<Map<Integer, String>> list = excelReader.readFromCertainRangeInExcelFile(importVarietyFields);
		List<FruitsVariety> fruitsVarietyList = fruitsVarietyService.mapFruitsVarietyFromExcelList(list, importVarietyFields);
		
		Map<FruitsVariety, Boolean> fruitsVarietyMap = new HashMap<FruitsVariety, Boolean>();
		Boolean areAnyExistingVarieties = false;
		Boolean areAnyNewVarieties = false;

		for (FruitsVariety fruitsVariety : fruitsVarietyList) {
			boolean doesExist = fruitsVarietyService.checkIfExists(fruitsVariety);
			
			fruitsVarietyMap.put(fruitsVariety, doesExist);
			
			if (doesExist) {
				areAnyExistingVarieties = true;
			} else if (!(fruitsVariety.getName() == null)) {
				areAnyNewVarieties = true;
			}			
		}
		
		model.addAttribute("fruitsVarietyMap", fruitsVarietyMap);
		model.addAttribute("areAnyExistingVarieties", areAnyExistingVarieties);
		model.addAttribute("areAnyNewVarieties", areAnyNewVarieties);
		
		return "fruitsVarietyImportConfirm";
	}

	@PostMapping("/importConfirm")
	public String saveFruitsVarietiesList(@ModelAttribute("fruitsVarietyImportForm") FruitsVarietyImportForm fruitsVarietyImportForm, Model model) {
		List<FruitsVariety> fruitsVarieties = fruitsVarietyImportForm.getFruitsVarieties();

		for (FruitsVariety fruitsVariety : fruitsVarieties) {
			fruitsVarietyService.createFruitsVariety(fruitsVariety);
		}
		
		return "redirect:/variety/fruits";
	}
}