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

import harvest.domain.CabbageVariety;
import harvest.dto.CabbageVarietyImportForm;
import harvest.dto.ImportVarietyFields;
import harvest.service.CabbageVarietyService;
import harvest.service.ExcelReader;

@Controller
@RequestMapping("/variety/cabbage")
@PreAuthorize("hasAuthority('ADMIN')")
public class CabbageVarietyController {
	@Autowired
	private CabbageVarietyService cabbageVarietyService;
	@Autowired
	private ExcelReader excelReader;
	
	@GetMapping
	public String viewCabbageVarietyList(Model model) {
		List<CabbageVariety> cabbageVarietiesList = cabbageVarietyService.findAll();
		model.addAttribute("cabbageVarieties", cabbageVarietiesList);

		return "cabbageVarietyList";
	}
	
	@GetMapping("/create")
	public String viewCreationForm(@RequestParam(name = "superRefererURI", required = false) String superRefererURI,
			HttpServletRequest request, Model model) throws URISyntaxException {
		model.addAttribute("refererURI", new URI(request.getHeader("referer")).getPath());
		
		if (superRefererURI != null) {
			model.addAttribute("superRefererURI", superRefererURI);
		}
		
		return "cabbageVarietyCreator";
	}

	@PostMapping("/create")
	public String createCabbageVariety(String refererURI, String superRefererURI, @Valid CabbageVariety cabbageVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("refererURI", refererURI);
            
            if (superRefererURI != "") {
    			model.addAttribute("superRefererURI", superRefererURI);
    		}
            
            return "cabbageVarietyCreator";
        }
        		
		boolean cabbageVarietyExists = !cabbageVarietyService.createCabbageVariety(cabbageVariety);
		
		if (cabbageVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("refererURI", refererURI);
			
			if (superRefererURI != "") {
				model.addAttribute("superRefererURI", superRefererURI);
			}
			
			return "cabbageVarietyCreator";
		}
		
		if (superRefererURI != "") {
			return "redirect:" + refererURI + "?superRefererURI=" + superRefererURI;
		}
		
		return "redirect:" + refererURI;
	}
	
	@GetMapping("/edit")
	public String viewEditForm(@RequestParam("id") CabbageVariety cabbageVariety, Model model) {
		model.addAttribute("cabbageVariety", cabbageVariety);
		
		return "cabbageVarietyEditor";
	}

	@PostMapping("/edit")
	public String saveCabbageVariety(@RequestParam("id") CabbageVariety cabbageVariety, @Valid CabbageVariety updatedCabbageVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
			model.mergeAttributes(errors);
			model.addAttribute("cabbageVariety", cabbageVariety);
			
			return "cabbageVarietyEditor";
		}
		
		boolean cabbageVarietyExists = !cabbageVarietyService.updateCabbageVariety(updatedCabbageVariety);
		
		if (cabbageVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("cabbageVariety", cabbageVariety);

			return "cabbageVarietyEditor";
		}

		return "redirect:/variety/cabbage";
	}
	
	@GetMapping("/delete")
	public String deleteCabbageVariety(@RequestParam("id") CabbageVariety cabbageVariety) {
		if (!cabbageVariety.getCabbage().isEmpty()) {
			return "redirect:/403";
		}
		
		cabbageVarietyService.deleteCabbageVariety(cabbageVariety);

		return "redirect:/variety/cabbage";
	}
	
	@GetMapping("/import")
	public String viewCabbageVarietyImportForm() {
		return "cabbageVarietyImport";
	}
	
	@PostMapping("/import")
	public RedirectView getCabbageImportVarietyFields(ImportVarietyFields importVarietyFields, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws IOException {
		redirectAttributes.addFlashAttribute("importVarietyFields", importVarietyFields);
		
		return new RedirectView("/variety/cabbage/importConfirm");
	}
	
	@GetMapping("/importConfirm")
	public String viewCabbageVarietyImportConfirmList(@ModelAttribute("importVarietyFields") ImportVarietyFields importVarietyFields, Model model) throws IOException {
		List<Map<Integer, String>> list = excelReader.readFromCertainRangeInExcelFile(importVarietyFields);
		List<CabbageVariety> cabbageVarietyList = cabbageVarietyService.mapCabbageVarietyFromExcelList(list, importVarietyFields);
		
		Map<CabbageVariety, Boolean> cabbageVarietyMap = new HashMap<CabbageVariety, Boolean>();
		Boolean areAnyExistingVarieties = false;
		Boolean areAnyNewVarieties = false;

		for (CabbageVariety cabbageVariety : cabbageVarietyList) {
			boolean doesExist = cabbageVarietyService.checkIfExists(cabbageVariety);
			
			cabbageVarietyMap.put(cabbageVariety, doesExist);
			
			if (doesExist) {
				areAnyExistingVarieties = true;
			} else if (!(cabbageVariety.getName() == null)) {
				areAnyNewVarieties = true;
			}			
		}
		
		model.addAttribute("cabbageVarietyMap", cabbageVarietyMap);
		model.addAttribute("areAnyExistingVarieties", areAnyExistingVarieties);
		model.addAttribute("areAnyNewVarieties", areAnyNewVarieties);
		
		return "cabbageVarietyImportConfirm";
	}

	@PostMapping("/importConfirm")
	public String saveCabbageVarietiesList(@ModelAttribute("cabbageVarietyImportForm") CabbageVarietyImportForm cabbageVarietyImportForm, Model model) {
		List<CabbageVariety> cabbageVarieties = cabbageVarietyImportForm.getCabbageVarieties();

		for (CabbageVariety cabbageVariety : cabbageVarieties) {
			cabbageVarietyService.createCabbageVariety(cabbageVariety);
		}
		
		return "redirect:/variety/cabbage";
	}
}