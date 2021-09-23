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

import harvest.domain.PotatoesVariety;
import harvest.dto.ImportVarietyFields;
import harvest.dto.PotatoesVarietyImportForm;
import harvest.service.ExcelReader;
import harvest.service.PotatoesVarietyService;

@Controller
@RequestMapping("/variety/potatoes")
@PreAuthorize("hasAuthority('ADMIN')")
public class PotatoesVarietyController {
	@Autowired
	private PotatoesVarietyService potatoesVarietyService;
	@Autowired
	private ExcelReader excelReader;
	
	@GetMapping
	public String viewPotatoesVarietyList(Model model) {
		List<PotatoesVariety> potatoesVarietiesList = potatoesVarietyService.findAll();
		model.addAttribute("potatoesVarieties", potatoesVarietiesList);

		return "potatoesVarietyList";
	}
	
	@GetMapping("/create")
	public String viewCreationForm(@RequestParam(name = "superRefererURI", required = false) String superRefererURI,
			HttpServletRequest request, Model model) throws URISyntaxException {
		model.addAttribute("refererURI", new URI(request.getHeader("referer")).getPath());
		
		if (superRefererURI != null) {
			model.addAttribute("superRefererURI", superRefererURI);
		}
		
		return "potatoesVarietyCreator";
	}

	@PostMapping("/create")
	public String createPotatoesVariety(String refererURI, String superRefererURI, @Valid PotatoesVariety potatoesVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("refererURI", refererURI);
            
            if (superRefererURI != "") {
    			model.addAttribute("superRefererURI", superRefererURI);
    		}
            
            return "potatoesVarietyCreator";
        }
        		
		boolean potatoesVarietyExists = !potatoesVarietyService.createPotatoesVariety(potatoesVariety);
		
		if (potatoesVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("refererURI", refererURI);
			
			if (superRefererURI != "") {
				model.addAttribute("superRefererURI", superRefererURI);
			}
			
			return "potatoesVarietyCreator";
		}
		
		if (superRefererURI != "") {
			return "redirect:" + refererURI + "?superRefererURI=" + superRefererURI;
		}
		
		return "redirect:" + refererURI;
	}
	
	@GetMapping("/edit")
	public String viewEditForm(@RequestParam("id") PotatoesVariety potatoesVariety, Model model) {
		model.addAttribute("potatoesVariety", potatoesVariety);
		
		return "potatoesVarietyEditor";
	}

	@PostMapping("/edit")
	public String savePotatoesVariety(@RequestParam("id") PotatoesVariety potatoesVariety, @Valid PotatoesVariety updatedPotatoesVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
			model.mergeAttributes(errors);
			model.addAttribute("potatoesVariety", potatoesVariety);
			
			return "potatoesVarietyEditor";
		}
		
		boolean potatoesVarietyExists = !potatoesVarietyService.updatePotatoesVariety(updatedPotatoesVariety);
		
		if (potatoesVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("potatoesVariety", potatoesVariety);

			return "potatoesVarietyEditor";
		}

		return "redirect:/variety/potatoes";
	}
	
	@GetMapping("/delete")
	public String deletePotatoesVariety(@RequestParam("id") PotatoesVariety potatoesVariety) {
		if (!potatoesVariety.getPotatoes().isEmpty()) {
			return "redirect:/403";
		}
		
		potatoesVarietyService.deletePotatoesVariety(potatoesVariety);

		return "redirect:/variety/potatoes";
	}
	
	@GetMapping("/import")
	public String viewPotatoesVarietyImportForm() {
		return "potatoesVarietyImport";
	}
	
	@PostMapping("/import")
	public RedirectView getPotatoesImportVarietyFields(ImportVarietyFields importVarietyFields, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws IOException {
		redirectAttributes.addFlashAttribute("importVarietyFields", importVarietyFields);
		
		return new RedirectView("/variety/potatoes/importConfirm");
	}
	
	@GetMapping("/importConfirm")
	public String viewPotatoesVarietyImportConfirmList(@ModelAttribute("importVarietyFields") ImportVarietyFields importVarietyFields, Model model) throws IOException {
		List<Map<Integer, String>> list = excelReader.readFromCertainRangeInExcelFile(importVarietyFields);
		List<PotatoesVariety> potatoesVarietyList = potatoesVarietyService.mapPotatoesVarietyFromExcelList(list, importVarietyFields);
		
		Map<PotatoesVariety, Boolean> potatoesVarietyMap = new HashMap<PotatoesVariety, Boolean>();
		Boolean areAnyExistingVarieties = false;
		Boolean areAnyNewVarieties = false;

		for (PotatoesVariety potatoesVariety : potatoesVarietyList) {
			boolean doesExist = potatoesVarietyService.checkIfExists(potatoesVariety);
			
			potatoesVarietyMap.put(potatoesVariety, doesExist);
			
			if (doesExist) {
				areAnyExistingVarieties = true;
			} else if (!(potatoesVariety.getName() == null)) {
				areAnyNewVarieties = true;
			}			
		}
		
		model.addAttribute("potatoesVarietyMap", potatoesVarietyMap);
		model.addAttribute("areAnyExistingVarieties", areAnyExistingVarieties);
		model.addAttribute("areAnyNewVarieties", areAnyNewVarieties);
		
		return "potatoesVarietyImportConfirm";
	}

	@PostMapping("/importConfirm")
	public String savePotatoesVarietiesList(@ModelAttribute("potatoesVarietyImportForm") PotatoesVarietyImportForm potatoesVarietyImportForm, Model model) {
		List<PotatoesVariety> potatoesVarieties = potatoesVarietyImportForm.getPotatoesVarieties();

		for (PotatoesVariety potatoesVariety : potatoesVarieties) {
			potatoesVarietyService.createPotatoesVariety(potatoesVariety);
		}
		
		return "redirect:/variety/potatoes";
	}
}