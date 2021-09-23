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

import harvest.domain.PeppersVariety;
import harvest.dto.ImportVarietyFields;
import harvest.dto.PeppersVarietyImportForm;
import harvest.service.ExcelReader;
import harvest.service.PeppersVarietyService;

@Controller
@RequestMapping("/variety/peppers")
@PreAuthorize("hasAuthority('ADMIN')")
public class PeppersVarietyController {
	@Autowired
	private PeppersVarietyService peppersVarietyService;
	@Autowired
	private ExcelReader excelReader;
	
	@GetMapping
	public String viewPeppersVarietyList(Model model) {
		List<PeppersVariety> peppersVarietiesList = peppersVarietyService.findAll();
		model.addAttribute("peppersVarieties", peppersVarietiesList);

		return "peppersVarietyList";
	}
	
	@GetMapping("/create")
	public String viewCreationForm(@RequestParam(name = "superRefererURI", required = false) String superRefererURI,
			HttpServletRequest request, Model model) throws URISyntaxException {
		model.addAttribute("refererURI", new URI(request.getHeader("referer")).getPath());
		
		if (superRefererURI != null) {
			model.addAttribute("superRefererURI", superRefererURI);
		}
		
		return "peppersVarietyCreator";
	}

	@PostMapping("/create")
	public String createPeppersVariety(String refererURI, String superRefererURI, @Valid PeppersVariety peppersVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("refererURI", refererURI);
            
            if (superRefererURI != "") {
    			model.addAttribute("superRefererURI", superRefererURI);
    		}
            
            return "peppersVarietyCreator";
        }
        		
		boolean peppersVarietyExists = !peppersVarietyService.createPeppersVariety(peppersVariety);
		
		if (peppersVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("refererURI", refererURI);
			
			if (superRefererURI != "") {
				model.addAttribute("superRefererURI", superRefererURI);
			}
			
			return "peppersVarietyCreator";
		}
		
		if (superRefererURI != "") {
			return "redirect:" + refererURI + "?superRefererURI=" + superRefererURI;
		}
		
		return "redirect:" + refererURI;
	}
	
	@GetMapping("/edit")
	public String viewEditForm(@RequestParam("id") PeppersVariety peppersVariety, Model model) {
		model.addAttribute("peppersVariety", peppersVariety);
		
		return "peppersVarietyEditor";
	}

	@PostMapping("/edit")
	public String savePeppersVariety(@RequestParam("id") PeppersVariety peppersVariety, @Valid PeppersVariety updatedPeppersVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
			model.mergeAttributes(errors);
			model.addAttribute("peppersVariety", peppersVariety);
			
			return "peppersVarietyEditor";
		}
		
		boolean peppersVarietyExists = !peppersVarietyService.updatePeppersVariety(updatedPeppersVariety);
		
		if (peppersVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("peppersVariety", peppersVariety);

			return "peppersVarietyEditor";
		}

		return "redirect:/variety/peppers";
	}
	
	@GetMapping("/delete")
	public String deletePeppersVariety(@RequestParam("id") PeppersVariety peppersVariety) {
		if (!peppersVariety.getPeppers().isEmpty()) {
			return "redirect:/403";
		}
		
		peppersVarietyService.deletePeppersVariety(peppersVariety);

		return "redirect:/variety/peppers";
	}
	
	@GetMapping("/import")
	public String viewPeppersVarietyImportForm() {
		return "peppersVarietyImport";
	}
	
	@PostMapping("/import")
	public RedirectView getPeppersImportVarietyFields(ImportVarietyFields importVarietyFields, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws IOException {
		redirectAttributes.addFlashAttribute("importVarietyFields", importVarietyFields);
		
		return new RedirectView("/variety/peppers/importConfirm");
	}
	
	@GetMapping("/importConfirm")
	public String viewPeppersVarietyImportConfirmList(@ModelAttribute("importVarietyFields") ImportVarietyFields importVarietyFields, Model model) throws IOException {
		List<Map<Integer, String>> list = excelReader.readFromCertainRangeInExcelFile(importVarietyFields);
		List<PeppersVariety> peppersVarietyList = peppersVarietyService.mapPeppersVarietyFromExcelList(list, importVarietyFields);
		
		Map<PeppersVariety, Boolean> peppersVarietyMap = new HashMap<PeppersVariety, Boolean>();
		Boolean areAnyExistingVarieties = false;
		Boolean areAnyNewVarieties = false;

		for (PeppersVariety peppersVariety : peppersVarietyList) {
			boolean doesExist = peppersVarietyService.checkIfExists(peppersVariety);
			
			peppersVarietyMap.put(peppersVariety, doesExist);
			
			if (doesExist) {
				areAnyExistingVarieties = true;
			} else if (!(peppersVariety.getName() == null)) {
				areAnyNewVarieties = true;
			}			
		}
		
		model.addAttribute("peppersVarietyMap", peppersVarietyMap);
		model.addAttribute("areAnyExistingVarieties", areAnyExistingVarieties);
		model.addAttribute("areAnyNewVarieties", areAnyNewVarieties);
		
		return "peppersVarietyImportConfirm";
	}

	@PostMapping("/importConfirm")
	public String savePeppersVarietiesList(@ModelAttribute("peppersVarietyImportForm") PeppersVarietyImportForm peppersVarietyImportForm, Model model) {
		List<PeppersVariety> peppersVarieties = peppersVarietyImportForm.getPeppersVarieties();

		for (PeppersVariety peppersVariety : peppersVarieties) {
			peppersVarietyService.createPeppersVariety(peppersVariety);
		}
		
		return "redirect:/variety/peppers";
	}
}