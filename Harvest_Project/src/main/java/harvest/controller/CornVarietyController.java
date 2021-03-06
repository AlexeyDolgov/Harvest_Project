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

import harvest.domain.CornVariety;
import harvest.dto.CornVarietyImportForm;
import harvest.dto.ImportVarietyFields;
import harvest.service.CornVarietyService;
import harvest.service.ExcelReader;

@Controller
@RequestMapping("/variety/corn")
@PreAuthorize("hasAuthority('ADMIN')")
public class CornVarietyController {
	@Autowired
	private CornVarietyService cornVarietyService;
	@Autowired
	private ExcelReader excelReader;
	
	@GetMapping
	public String viewCornVarietyList(Model model) {
		List<CornVariety> cornVarietiesList = cornVarietyService.findAll();
		model.addAttribute("cornVarieties", cornVarietiesList);

		return "cornVarietyList";
	}
	
	@GetMapping("/create")
	public String viewCreationForm(@RequestParam(name = "superRefererURI", required = false) String superRefererURI,
			HttpServletRequest request, Model model) throws URISyntaxException {
		model.addAttribute("refererURI", new URI(request.getHeader("referer")).getPath());
		
		if (superRefererURI != null) {
			model.addAttribute("superRefererURI", superRefererURI);
		}
		
		return "cornVarietyCreator";
	}

	@PostMapping("/create")
	public String createCornVariety(String refererURI, String superRefererURI, @Valid CornVariety cornVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("refererURI", refererURI);
            
            if (superRefererURI != "") {
    			model.addAttribute("superRefererURI", superRefererURI);
    		}
            
            return "cornVarietyCreator";
        }
        		
		boolean cornVarietyExists = !cornVarietyService.createCornVariety(cornVariety);
		
		if (cornVarietyExists) {
			model.addAttribute("varietyExistsMessage", "?????????? ???????? ?????? ????????????????????!");
			model.addAttribute("refererURI", refererURI);
			
			if (superRefererURI != "") {
				model.addAttribute("superRefererURI", superRefererURI);
			}
			
			return "cornVarietyCreator";
		}
		
		if (superRefererURI != "") {
			return "redirect:" + refererURI + "?superRefererURI=" + superRefererURI;
		}
		
		return "redirect:" + refererURI;
	}
	
	@GetMapping("/edit")
	public String viewEditForm(@RequestParam("id") CornVariety cornVariety, Model model) {
		model.addAttribute("cornVariety", cornVariety);
		
		return "cornVarietyEditor";
	}

	@PostMapping("/edit")
	public String saveCornVariety(@RequestParam("id") CornVariety cornVariety, @Valid CornVariety updatedCornVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
			model.mergeAttributes(errors);
			model.addAttribute("cornVariety", cornVariety);
			
			return "cornVarietyEditor";
		}
		
		boolean cornVarietyExists = !cornVarietyService.updateCornVariety(updatedCornVariety);
		
		if (cornVarietyExists) {
			model.addAttribute("varietyExistsMessage", "?????????? ???????? ?????? ????????????????????!");
			model.addAttribute("cornVariety", cornVariety);

			return "cornVarietyEditor";
		}

		return "redirect:/variety/corn";
	}
	
	@GetMapping("/delete")
	public String deleteCornVariety(@RequestParam("id") CornVariety cornVariety) {
		if (!cornVariety.getCorn().isEmpty()) {
			return "redirect:/403";
		}
		
		cornVarietyService.deleteCornVariety(cornVariety);

		return "redirect:/variety/corn";
	}
	
	@GetMapping("/import")
	public String viewCornVarietyImportForm() {
		return "cornVarietyImport";
	}
	
	@PostMapping("/import")
	public RedirectView getCornImportVarietyFields(ImportVarietyFields importVarietyFields, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws IOException {
		redirectAttributes.addFlashAttribute("importVarietyFields", importVarietyFields);
		
		return new RedirectView("/variety/corn/importConfirm");
	}
	
	@GetMapping("/importConfirm")
	public String viewCornVarietyImportConfirmList(@ModelAttribute("importVarietyFields") ImportVarietyFields importVarietyFields, Model model) throws IOException {
		List<Map<Integer, String>> list = excelReader.readFromCertainRangeInExcelFile(importVarietyFields);
		List<CornVariety> cornVarietyList = cornVarietyService.mapCornVarietyFromExcelList(list, importVarietyFields);
		
		Map<CornVariety, Boolean> cornVarietyMap = new HashMap<CornVariety, Boolean>();
		Boolean areAnyExistingVarieties = false;
		Boolean areAnyNewVarieties = false;

		for (CornVariety cornVariety : cornVarietyList) {
			boolean doesExist = cornVarietyService.checkIfExists(cornVariety);
			
			cornVarietyMap.put(cornVariety, doesExist);
			
			if (doesExist) {
				areAnyExistingVarieties = true;
			} else if (!(cornVariety.getName() == null)) {
				areAnyNewVarieties = true;
			}			
		}
		
		model.addAttribute("cornVarietyMap", cornVarietyMap);
		model.addAttribute("areAnyExistingVarieties", areAnyExistingVarieties);
		model.addAttribute("areAnyNewVarieties", areAnyNewVarieties);
		
		return "cornVarietyImportConfirm";
	}

	@PostMapping("/importConfirm")
	public String saveCornVarietiesList(@ModelAttribute("cornVarietyImportForm") CornVarietyImportForm cornVarietyImportForm, Model model) {
		List<CornVariety> cornVarieties = cornVarietyImportForm.getCornVarieties();

		for (CornVariety cornVariety : cornVarieties) {
			cornVarietyService.createCornVariety(cornVariety);
		}
		
		return "redirect:/variety/corn";
	}
}