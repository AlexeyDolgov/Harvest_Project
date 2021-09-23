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

import harvest.domain.BeetrootsVariety;
import harvest.dto.BeetrootsVarietyImportForm;
import harvest.dto.ImportVarietyFields;
import harvest.service.BeetrootsVarietyService;
import harvest.service.ExcelReader;

@Controller
@RequestMapping("/variety/beetroots")
@PreAuthorize("hasAuthority('ADMIN')")
public class BeetrootsVarietyController {
	@Autowired
	private BeetrootsVarietyService beetrootsVarietyService;
	@Autowired
	private ExcelReader excelReader;
	
	@GetMapping
	public String viewBeetrootsVarietyList(Model model) {
		List<BeetrootsVariety> beetrootsVarietiesList = beetrootsVarietyService.findAll();
		model.addAttribute("beetrootsVarieties", beetrootsVarietiesList);

		return "beetrootsVarietyList";
	}
	
	@GetMapping("/create")
	public String viewCreationForm(@RequestParam(name = "superRefererURI", required = false) String superRefererURI,
			HttpServletRequest request, Model model) throws URISyntaxException {
		model.addAttribute("refererURI", new URI(request.getHeader("referer")).getPath());
		
		if (superRefererURI != null) {
			model.addAttribute("superRefererURI", superRefererURI);
		}
		
		return "beetrootsVarietyCreator";
	}

	@PostMapping("/create")
	public String createBeetrootsVariety(String refererURI, String superRefererURI, @Valid BeetrootsVariety beetrootsVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("refererURI", refererURI);
            
            if (superRefererURI != "") {
    			model.addAttribute("superRefererURI", superRefererURI);
    		}
            
            return "beetrootsVarietyCreator";
        }
        		
		boolean beetrootsVarietyExists = !beetrootsVarietyService.createBeetrootsVariety(beetrootsVariety);
		
		if (beetrootsVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("refererURI", refererURI);
			
			if (superRefererURI != "") {
				model.addAttribute("superRefererURI", superRefererURI);
			}
			
			return "beetrootsVarietyCreator";
		}
		
		if (superRefererURI != "") {
			return "redirect:" + refererURI + "?superRefererURI=" + superRefererURI;
		}
		
		return "redirect:" + refererURI;
	}
	
	@GetMapping("/edit")
	public String viewEditForm(@RequestParam("id") BeetrootsVariety beetrootsVariety, Model model) {
		model.addAttribute("beetrootsVariety", beetrootsVariety);
		
		return "beetrootsVarietyEditor";
	}

	@PostMapping("/edit")
	public String saveBeetrootsVariety(@RequestParam("id") BeetrootsVariety beetrootsVariety, @Valid BeetrootsVariety updatedBeetrootsVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
			model.mergeAttributes(errors);
			model.addAttribute("beetrootsVariety", beetrootsVariety);
			
			return "beetrootsVarietyEditor";
		}
		
		boolean beetrootsVarietyExists = !beetrootsVarietyService.updateBeetrootsVariety(updatedBeetrootsVariety);
		
		if (beetrootsVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("beetrootsVariety", beetrootsVariety);

			return "beetrootsVarietyEditor";
		}

		return "redirect:/variety/beetroots";
	}
	
	@GetMapping("/delete")
	public String deleteBeetrootsVariety(@RequestParam("id") BeetrootsVariety beetrootsVariety) {
		if (!beetrootsVariety.getBeetroots().isEmpty()) {
			return "redirect:/403";
		}
		
		beetrootsVarietyService.deleteBeetrootsVariety(beetrootsVariety);

		return "redirect:/variety/beetroots";
	}
	
	@GetMapping("/import")
	public String viewBeetrootsVarietyImportForm() {
		return "beetrootsVarietyImport";
	}
	
	@PostMapping("/import")
	public RedirectView getBeetrootsImportVarietyFields(ImportVarietyFields importVarietyFields, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws IOException {
		redirectAttributes.addFlashAttribute("importVarietyFields", importVarietyFields);
		
		return new RedirectView("/variety/beetroots/importConfirm");
	}
	
	@GetMapping("/importConfirm")
	public String viewBeetrootsVarietyImportConfirmList(@ModelAttribute("importVarietyFields") ImportVarietyFields importVarietyFields, Model model) throws IOException {
		List<Map<Integer, String>> list = excelReader.readFromCertainRangeInExcelFile(importVarietyFields);
		List<BeetrootsVariety> beetrootsVarietyList = beetrootsVarietyService.mapBeetrootsVarietyFromExcelList(list, importVarietyFields);
		
		Map<BeetrootsVariety, Boolean> beetrootsVarietyMap = new HashMap<BeetrootsVariety, Boolean>();
		Boolean areAnyExistingVarieties = false;
		Boolean areAnyNewVarieties = false;

		for (BeetrootsVariety beetrootsVariety : beetrootsVarietyList) {
			boolean doesExist = beetrootsVarietyService.checkIfExists(beetrootsVariety);
			
			beetrootsVarietyMap.put(beetrootsVariety, doesExist);
			
			if (doesExist) {
				areAnyExistingVarieties = true;
			} else if (!(beetrootsVariety.getName() == null)) {
				areAnyNewVarieties = true;
			}			
		}
		
		model.addAttribute("beetrootsVarietyMap", beetrootsVarietyMap);
		model.addAttribute("areAnyExistingVarieties", areAnyExistingVarieties);
		model.addAttribute("areAnyNewVarieties", areAnyNewVarieties);
		
		return "beetrootsVarietyImportConfirm";
	}

	@PostMapping("/importConfirm")
	public String saveBeetrootsVarietiesList(@ModelAttribute("beetrootsVarietyImportForm") BeetrootsVarietyImportForm beetrootsVarietyImportForm, Model model) {
		List<BeetrootsVariety> beetrootsVarieties = beetrootsVarietyImportForm.getBeetrootsVarieties();

		for (BeetrootsVariety beetrootsVariety : beetrootsVarieties) {
			beetrootsVarietyService.createBeetrootsVariety(beetrootsVariety);
		}
		
		return "redirect:/variety/beetroots";
	}
}