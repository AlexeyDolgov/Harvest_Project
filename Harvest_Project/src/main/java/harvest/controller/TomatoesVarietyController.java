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

import harvest.domain.TomatoesVariety;
import harvest.dto.ImportVarietyFields;
import harvest.dto.TomatoesVarietyImportForm;
import harvest.service.ExcelReader;
import harvest.service.TomatoesVarietyService;

@Controller
@RequestMapping("/variety/tomatoes")
@PreAuthorize("hasAuthority('ADMIN')")
public class TomatoesVarietyController {
	@Autowired
	private TomatoesVarietyService tomatoesVarietyService;
	@Autowired
	private ExcelReader excelReader;
	
	@GetMapping
	public String viewTomatoesVarietyList(Model model) {
		List<TomatoesVariety> tomatoesVarietiesList = tomatoesVarietyService.findAll();
		model.addAttribute("tomatoesVarieties", tomatoesVarietiesList);

		return "tomatoesVarietyList";
	}
	
	@GetMapping("/create")
	public String viewCreationForm(@RequestParam(name = "superRefererURI", required = false) String superRefererURI,
			HttpServletRequest request, Model model) throws URISyntaxException {
		model.addAttribute("refererURI", new URI(request.getHeader("referer")).getPath());
		
		if (superRefererURI != null) {
			model.addAttribute("superRefererURI", superRefererURI);
		}
		
		return "tomatoesVarietyCreator";
	}

	@PostMapping("/create")
	public String createTomatoesVariety(String refererURI, String superRefererURI, @Valid TomatoesVariety tomatoesVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("refererURI", refererURI);
            
            if (superRefererURI != "") {
    			model.addAttribute("superRefererURI", superRefererURI);
    		}
            
            return "tomatoesVarietyCreator";
        }
        		
		boolean tomatoesVarietyExists = !tomatoesVarietyService.createTomatoesVariety(tomatoesVariety);
		
		if (tomatoesVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("refererURI", refererURI);
			
			if (superRefererURI != "") {
				model.addAttribute("superRefererURI", superRefererURI);
			}
			
			return "tomatoesVarietyCreator";
		}
		
		if (superRefererURI != "") {
			return "redirect:" + refererURI + "?superRefererURI=" + superRefererURI;
		}
		
		return "redirect:" + refererURI;
	}
	
	@GetMapping("/edit")
	public String viewEditForm(@RequestParam("id") TomatoesVariety tomatoesVariety, Model model) {
		model.addAttribute("tomatoesVariety", tomatoesVariety);
		
		return "tomatoesVarietyEditor";
	}

	@PostMapping("/edit")
	public String saveTomatoesVariety(@RequestParam("id") TomatoesVariety tomatoesVariety, @Valid TomatoesVariety updatedTomatoesVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
			model.mergeAttributes(errors);
			model.addAttribute("tomatoesVariety", tomatoesVariety);
			
			return "tomatoesVarietyEditor";
		}
		
		boolean tomatoesVarietyExists = !tomatoesVarietyService.updateTomatoesVariety(updatedTomatoesVariety);
		
		if (tomatoesVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("tomatoesVariety", tomatoesVariety);

			return "tomatoesVarietyEditor";
		}

		return "redirect:/variety/tomatoes";
	}
	
	@GetMapping("/delete")
	public String deleteTomatoesVariety(@RequestParam("id") TomatoesVariety tomatoesVariety) {
		if (!tomatoesVariety.getTomatoes().isEmpty()) {
			return "redirect:/403";
		}
		
		tomatoesVarietyService.deleteTomatoesVariety(tomatoesVariety);

		return "redirect:/variety/tomatoes";
	}
	
	@GetMapping("/import")
	public String viewTomatoesVarietyImportForm() {
		return "tomatoesVarietyImport";
	}
	
	@PostMapping("/import")
	public RedirectView getTomatoesImportVarietyFields(ImportVarietyFields importVarietyFields, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws IOException {
		redirectAttributes.addFlashAttribute("importVarietyFields", importVarietyFields);
		
		return new RedirectView("/variety/tomatoes/importConfirm");
	}
	
	@GetMapping("/importConfirm")
	public String viewTomatoesVarietyImportConfirmList(@ModelAttribute("importVarietyFields") ImportVarietyFields importVarietyFields, Model model) throws IOException {
		List<Map<Integer, String>> list = excelReader.readFromCertainRangeInExcelFile(importVarietyFields);
		List<TomatoesVariety> tomatoesVarietyList = tomatoesVarietyService.mapTomatoesVarietyFromExcelList(list, importVarietyFields);
		
		Map<TomatoesVariety, Boolean> tomatoesVarietyMap = new HashMap<TomatoesVariety, Boolean>();
		Boolean areAnyExistingVarieties = false;
		Boolean areAnyNewVarieties = false;

		for (TomatoesVariety tomatoesVariety : tomatoesVarietyList) {
			boolean doesExist = tomatoesVarietyService.checkIfExists(tomatoesVariety);
			
			tomatoesVarietyMap.put(tomatoesVariety, doesExist);
			
			if (doesExist) {
				areAnyExistingVarieties = true;
			} else if (!(tomatoesVariety.getName() == null)) {
				areAnyNewVarieties = true;
			}			
		}
		
		model.addAttribute("tomatoesVarietyMap", tomatoesVarietyMap);
		model.addAttribute("areAnyExistingVarieties", areAnyExistingVarieties);
		model.addAttribute("areAnyNewVarieties", areAnyNewVarieties);
		
		return "tomatoesVarietyImportConfirm";
	}

	@PostMapping("/importConfirm")
	public String saveTomatoesVarietiesList(@ModelAttribute("tomatoesVarietyImportForm") TomatoesVarietyImportForm tomatoesVarietyImportForm, Model model) {
		List<TomatoesVariety> tomatoesVarieties = tomatoesVarietyImportForm.getTomatoesVarieties();

		for (TomatoesVariety tomatoesVariety : tomatoesVarieties) {
			tomatoesVarietyService.createTomatoesVariety(tomatoesVariety);
		}
		
		return "redirect:/variety/tomatoes";
	}
}