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

import harvest.domain.BerriesVariety;
import harvest.dto.BerriesVarietyImportForm;
import harvest.dto.ImportVarietyFields;
import harvest.service.BerriesVarietyService;
import harvest.service.ExcelReader;

@Controller
@RequestMapping("/variety/berries")
@PreAuthorize("hasAuthority('ADMIN')")
public class BerriesVarietyController {
	@Autowired
	private BerriesVarietyService berriesVarietyService;
	@Autowired
	private ExcelReader excelReader;
	
	@GetMapping
	public String viewBerriesVarietyList(Model model) {
		List<BerriesVariety> berriesVarietiesList = berriesVarietyService.findAll();
		model.addAttribute("berriesVarieties", berriesVarietiesList);

		return "berriesVarietyList";
	}
	
	@GetMapping("/create")
	public String viewCreationForm(@RequestParam(name = "superRefererURI", required = false) String superRefererURI,
			HttpServletRequest request, Model model) throws URISyntaxException {
		model.addAttribute("refererURI", new URI(request.getHeader("referer")).getPath());
		
		if (superRefererURI != null) {
			model.addAttribute("superRefererURI", superRefererURI);
		}
		
		return "berriesVarietyCreator";
	}

	@PostMapping("/create")
	public String createBerriesVariety(String refererURI, String superRefererURI, @Valid BerriesVariety berriesVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("refererURI", refererURI);
            
            if (superRefererURI != "") {
    			model.addAttribute("superRefererURI", superRefererURI);
    		}
            
            return "berriesVarietyCreator";
        }
        		
		boolean berriesVarietyExists = !berriesVarietyService.createBerriesVariety(berriesVariety);
		
		if (berriesVarietyExists) {
			model.addAttribute("varietyExistsMessage", "?????????? ???????? ?????? ????????????????????!");
			model.addAttribute("refererURI", refererURI);
			
			if (superRefererURI != "") {
				model.addAttribute("superRefererURI", superRefererURI);
			}
			
			return "berriesVarietyCreator";
		}
		
		if (superRefererURI != "") {
			return "redirect:" + refererURI + "?superRefererURI=" + superRefererURI;
		}
		
		return "redirect:" + refererURI;
	}
	
	@GetMapping("/edit")
	public String viewEditForm(@RequestParam("id") BerriesVariety berriesVariety, Model model) {
		model.addAttribute("berriesVariety", berriesVariety);
		
		return "berriesVarietyEditor";
	}

	@PostMapping("/edit")
	public String saveBerriesVariety(@RequestParam("id") BerriesVariety berriesVariety, @Valid BerriesVariety updatedBerriesVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
			model.mergeAttributes(errors);
			model.addAttribute("berriesVariety", berriesVariety);
			
			return "berriesVarietyEditor";
		}
		
		boolean berriesVarietyExists = !berriesVarietyService.updateBerriesVariety(updatedBerriesVariety);
		
		if (berriesVarietyExists) {
			model.addAttribute("varietyExistsMessage", "?????????? ???????? ?????? ????????????????????!");
			model.addAttribute("berriesVariety", berriesVariety);

			return "berriesVarietyEditor";
		}

		return "redirect:/variety/berries";
	}
	
	@GetMapping("/delete")
	public String deleteBerriesVariety(@RequestParam("id") BerriesVariety berriesVariety) {
		if (!berriesVariety.getBerries().isEmpty()) {
			return "redirect:/403";
		}
		
		berriesVarietyService.deleteBerriesVariety(berriesVariety);

		return "redirect:/variety/berries";
	}
	
	@GetMapping("/import")
	public String viewBerriesVarietyImportForm() {
		return "berriesVarietyImport";
	}
	
	@PostMapping("/import")
	public RedirectView getBerriesImportVarietyFields(ImportVarietyFields importVarietyFields, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws IOException {
		redirectAttributes.addFlashAttribute("importVarietyFields", importVarietyFields);
		
		return new RedirectView("/variety/berries/importConfirm");
	}
	
	@GetMapping("/importConfirm")
	public String viewBerriesVarietyImportConfirmList(@ModelAttribute("importVarietyFields") ImportVarietyFields importVarietyFields, Model model) throws IOException {
		List<Map<Integer, String>> list = excelReader.readFromCertainRangeInExcelFile(importVarietyFields);
		List<BerriesVariety> berriesVarietyList = berriesVarietyService.mapBerriesVarietyFromExcelList(list, importVarietyFields);
		
		Map<BerriesVariety, Boolean> berriesVarietyMap = new HashMap<BerriesVariety, Boolean>();
		Boolean areAnyExistingVarieties = false;
		Boolean areAnyNewVarieties = false;

		for (BerriesVariety berriesVariety : berriesVarietyList) {
			boolean doesExist = berriesVarietyService.checkIfExists(berriesVariety);
			
			berriesVarietyMap.put(berriesVariety, doesExist);
			
			if (doesExist) {
				areAnyExistingVarieties = true;
			} else if (!(berriesVariety.getName() == null)) {
				areAnyNewVarieties = true;
			}			
		}
		
		model.addAttribute("berriesVarietyMap", berriesVarietyMap);
		model.addAttribute("areAnyExistingVarieties", areAnyExistingVarieties);
		model.addAttribute("areAnyNewVarieties", areAnyNewVarieties);
		
		return "berriesVarietyImportConfirm";
	}

	@PostMapping("/importConfirm")
	public String saveBerriesVarietiesList(@ModelAttribute("berriesVarietyImportForm") BerriesVarietyImportForm berriesVarietyImportForm, Model model) {
		List<BerriesVariety> berriesVarieties = berriesVarietyImportForm.getBerriesVarieties();

		for (BerriesVariety berriesVariety : berriesVarieties) {
			berriesVarietyService.createBerriesVariety(berriesVariety);
		}
		
		return "redirect:/variety/berries";
	}
}