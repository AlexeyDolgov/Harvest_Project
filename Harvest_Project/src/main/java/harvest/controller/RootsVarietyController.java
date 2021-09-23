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

import harvest.domain.RootsVariety;
import harvest.dto.ImportVarietyFields;
import harvest.dto.RootsVarietyImportForm;
import harvest.service.ExcelReader;
import harvest.service.RootsVarietyService;

@Controller
@RequestMapping("/variety/roots")
@PreAuthorize("hasAuthority('ADMIN')")
public class RootsVarietyController {
	@Autowired
	private RootsVarietyService rootsVarietyService;
	@Autowired
	private ExcelReader excelReader;
	
	@GetMapping
	public String viewRootsVarietyList(Model model) {
		List<RootsVariety> rootsVarietiesList = rootsVarietyService.findAll();
		model.addAttribute("rootsVarieties", rootsVarietiesList);

		return "rootsVarietyList";
	}
	
	@GetMapping("/create")
	public String viewCreationForm(@RequestParam(name = "superRefererURI", required = false) String superRefererURI,
			HttpServletRequest request, Model model) throws URISyntaxException {
		model.addAttribute("refererURI", new URI(request.getHeader("referer")).getPath());
		
		if (superRefererURI != null) {
			model.addAttribute("superRefererURI", superRefererURI);
		}
		
		return "rootsVarietyCreator";
	}

	@PostMapping("/create")
	public String createRootsVariety(String refererURI, String superRefererURI, @Valid RootsVariety rootsVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("refererURI", refererURI);
            
            if (superRefererURI != "") {
    			model.addAttribute("superRefererURI", superRefererURI);
    		}
            
            return "rootsVarietyCreator";
        }
        		
		boolean rootsVarietyExists = !rootsVarietyService.createRootsVariety(rootsVariety);
		
		if (rootsVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("refererURI", refererURI);
			
			if (superRefererURI != "") {
				model.addAttribute("superRefererURI", superRefererURI);
			}
			
			return "rootsVarietyCreator";
		}
		
		if (superRefererURI != "") {
			return "redirect:" + refererURI + "?superRefererURI=" + superRefererURI;
		}
		
		return "redirect:" + refererURI;
	}
	
	@GetMapping("/edit")
	public String viewEditForm(@RequestParam("id") RootsVariety rootsVariety, Model model) {
		model.addAttribute("rootsVariety", rootsVariety);
		
		return "rootsVarietyEditor";
	}

	@PostMapping("/edit")
	public String saveRootsVariety(@RequestParam("id") RootsVariety rootsVariety, @Valid RootsVariety updatedRootsVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
			model.mergeAttributes(errors);
			model.addAttribute("rootsVariety", rootsVariety);
			
			return "rootsVarietyEditor";
		}
		
		boolean rootsVarietyExists = !rootsVarietyService.updateRootsVariety(updatedRootsVariety);
		
		if (rootsVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("rootsVariety", rootsVariety);

			return "rootsVarietyEditor";
		}

		return "redirect:/variety/roots";
	}
	
	@GetMapping("/delete")
	public String deleteRootsVariety(@RequestParam("id") RootsVariety rootsVariety) {
		if (!rootsVariety.getRoots().isEmpty()) {
			return "redirect:/403";
		}
		
		rootsVarietyService.deleteRootsVariety(rootsVariety);

		return "redirect:/variety/roots";
	}
	
	@GetMapping("/import")
	public String viewRootsVarietyImportForm() {
		return "rootsVarietyImport";
	}
	
	@PostMapping("/import")
	public RedirectView getRootsImportVarietyFields(ImportVarietyFields importVarietyFields, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws IOException {
		redirectAttributes.addFlashAttribute("importVarietyFields", importVarietyFields);
		
		return new RedirectView("/variety/roots/importConfirm");
	}
	
	@GetMapping("/importConfirm")
	public String viewRootsVarietyImportConfirmList(@ModelAttribute("importVarietyFields") ImportVarietyFields importVarietyFields, Model model) throws IOException {
		List<Map<Integer, String>> list = excelReader.readFromCertainRangeInExcelFile(importVarietyFields);
		List<RootsVariety> rootsVarietyList = rootsVarietyService.mapRootsVarietyFromExcelList(list, importVarietyFields);
		
		Map<RootsVariety, Boolean> rootsVarietyMap = new HashMap<RootsVariety, Boolean>();
		Boolean areAnyExistingVarieties = false;
		Boolean areAnyNewVarieties = false;

		for (RootsVariety rootsVariety : rootsVarietyList) {
			boolean doesExist = rootsVarietyService.checkIfExists(rootsVariety);
			
			rootsVarietyMap.put(rootsVariety, doesExist);
			
			if (doesExist) {
				areAnyExistingVarieties = true;
			} else if (!(rootsVariety.getName() == null)) {
				areAnyNewVarieties = true;
			}			
		}
		
		model.addAttribute("rootsVarietyMap", rootsVarietyMap);
		model.addAttribute("areAnyExistingVarieties", areAnyExistingVarieties);
		model.addAttribute("areAnyNewVarieties", areAnyNewVarieties);
		
		return "rootsVarietyImportConfirm";
	}

	@PostMapping("/importConfirm")
	public String saveRootsVarietiesList(@ModelAttribute("rootsVarietyImportForm") RootsVarietyImportForm rootsVarietyImportForm, Model model) {
		List<RootsVariety> rootsVarieties = rootsVarietyImportForm.getRootsVarieties();

		for (RootsVariety rootsVariety : rootsVarieties) {
			rootsVarietyService.createRootsVariety(rootsVariety);
		}
		
		return "redirect:/variety/roots";
	}
}