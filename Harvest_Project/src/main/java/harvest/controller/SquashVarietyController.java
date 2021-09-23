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

import harvest.domain.SquashVariety;
import harvest.dto.ImportVarietyFields;
import harvest.dto.SquashVarietyImportForm;
import harvest.service.ExcelReader;
import harvest.service.SquashVarietyService;

@Controller
@RequestMapping("/variety/squash")
@PreAuthorize("hasAuthority('ADMIN')")
public class SquashVarietyController {
	@Autowired
	private SquashVarietyService squashVarietyService;
	@Autowired
	private ExcelReader excelReader;
	
	@GetMapping
	public String viewSquashVarietyList(Model model) {
		List<SquashVariety> squashVarietiesList = squashVarietyService.findAll();
		model.addAttribute("squashVarieties", squashVarietiesList);

		return "squashVarietyList";
	}
	
	@GetMapping("/create")
	public String viewCreationForm(@RequestParam(name = "superRefererURI", required = false) String superRefererURI,
			HttpServletRequest request, Model model) throws URISyntaxException {
		model.addAttribute("refererURI", new URI(request.getHeader("referer")).getPath());
		
		if (superRefererURI != null) {
			model.addAttribute("superRefererURI", superRefererURI);
		}
		
		return "squashVarietyCreator";
	}

	@PostMapping("/create")
	public String createSquashVariety(String refererURI, String superRefererURI, @Valid SquashVariety squashVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("refererURI", refererURI);
            
            if (superRefererURI != "") {
    			model.addAttribute("superRefererURI", superRefererURI);
    		}
            
            return "squashVarietyCreator";
        }
        		
		boolean squashVarietyExists = !squashVarietyService.createSquashVariety(squashVariety);
		
		if (squashVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("refererURI", refererURI);
			
			if (superRefererURI != "") {
				model.addAttribute("superRefererURI", superRefererURI);
			}
			
			return "squashVarietyCreator";
		}
		
		if (superRefererURI != "") {
			return "redirect:" + refererURI + "?superRefererURI=" + superRefererURI;
		}
		
		return "redirect:" + refererURI;
	}
	
	@GetMapping("/edit")
	public String viewEditForm(@RequestParam("id") SquashVariety squashVariety, Model model) {
		model.addAttribute("squashVariety", squashVariety);
		
		return "squashVarietyEditor";
	}

	@PostMapping("/edit")
	public String saveSquashVariety(@RequestParam("id") SquashVariety squashVariety, @Valid SquashVariety updatedSquashVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
			model.mergeAttributes(errors);
			model.addAttribute("squashVariety", squashVariety);
			
			return "squashVarietyEditor";
		}
		
		boolean squashVarietyExists = !squashVarietyService.updateSquashVariety(updatedSquashVariety);
		
		if (squashVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("squashVariety", squashVariety);

			return "squashVarietyEditor";
		}

		return "redirect:/variety/squash";
	}
	
	@GetMapping("/delete")
	public String deleteSquashVariety(@RequestParam("id") SquashVariety squashVariety) {
		if (!squashVariety.getSquash().isEmpty()) {
			return "redirect:/403";
		}
		
		squashVarietyService.deleteSquashVariety(squashVariety);

		return "redirect:/variety/squash";
	}
	
	@GetMapping("/import")
	public String viewSquashVarietyImportForm() {
		return "squashVarietyImport";
	}
	
	@PostMapping("/import")
	public RedirectView getSquashImportVarietyFields(ImportVarietyFields importVarietyFields, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws IOException {
		redirectAttributes.addFlashAttribute("importVarietyFields", importVarietyFields);
		
		return new RedirectView("/variety/squash/importConfirm");
	}
	
	@GetMapping("/importConfirm")
	public String viewSquashVarietyImportConfirmList(@ModelAttribute("importVarietyFields") ImportVarietyFields importVarietyFields, Model model) throws IOException {
		List<Map<Integer, String>> list = excelReader.readFromCertainRangeInExcelFile(importVarietyFields);
		List<SquashVariety> squashVarietyList = squashVarietyService.mapSquashVarietyFromExcelList(list, importVarietyFields);
		
		Map<SquashVariety, Boolean> squashVarietyMap = new HashMap<SquashVariety, Boolean>();
		Boolean areAnyExistingVarieties = false;
		Boolean areAnyNewVarieties = false;

		for (SquashVariety squashVariety : squashVarietyList) {
			boolean doesExist = squashVarietyService.checkIfExists(squashVariety);
			
			squashVarietyMap.put(squashVariety, doesExist);
			
			if (doesExist) {
				areAnyExistingVarieties = true;
			} else if (!(squashVariety.getName() == null)) {
				areAnyNewVarieties = true;
			}			
		}
		
		model.addAttribute("squashVarietyMap", squashVarietyMap);
		model.addAttribute("areAnyExistingVarieties", areAnyExistingVarieties);
		model.addAttribute("areAnyNewVarieties", areAnyNewVarieties);
		
		return "squashVarietyImportConfirm";
	}

	@PostMapping("/importConfirm")
	public String saveSquashVarietiesList(@ModelAttribute("squashVarietyImportForm") SquashVarietyImportForm squashVarietyImportForm, Model model) {
		List<SquashVariety> squashVarieties = squashVarietyImportForm.getSquashVarieties();

		for (SquashVariety squashVariety : squashVarieties) {
			squashVarietyService.createSquashVariety(squashVariety);
		}
		
		return "redirect:/variety/squash";
	}
}