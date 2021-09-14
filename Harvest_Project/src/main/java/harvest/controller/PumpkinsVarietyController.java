package harvest.controller;

import java.net.URI;
import java.net.URISyntaxException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import harvest.domain.PumpkinsVariety;
import harvest.service.PumpkinsVarietyService;

@Controller
@RequestMapping("/variety/pumpkins")
@PreAuthorize("hasAuthority('ADMIN')")
public class PumpkinsVarietyController {
	@Autowired
	private PumpkinsVarietyService pumpkinsVarietyService;
	
	@GetMapping
	public String viewPumpkinsVarietyList(Model model) {
		List<PumpkinsVariety> pumpkinsVarietiesList = pumpkinsVarietyService.findAll();
		model.addAttribute("pumpkinsVarieties", pumpkinsVarietiesList);

		return "pumpkinsVarietyList";
	}
	
	@GetMapping("/create")
	public String viewCreationForm(@RequestParam(name = "superRefererURI", required = false) String superRefererURI,
			HttpServletRequest request, Model model) throws URISyntaxException {
		model.addAttribute("refererURI", new URI(request.getHeader("referer")).getPath());
		
		if (superRefererURI != null) {
			model.addAttribute("superRefererURI", superRefererURI);
		}
		
		return "pumpkinsVarietyCreator";
	}

	@PostMapping("/create")
	public String createPumpkinsVariety(String refererURI, String superRefererURI, @Valid PumpkinsVariety pumpkinsVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("refererURI", refererURI);
            
            if (superRefererURI != "") {
    			model.addAttribute("superRefererURI", superRefererURI);
    		}
            
            return "pumpkinsVarietyCreator";
        }
        		
		boolean pumpkinsVarietyExists = !pumpkinsVarietyService.createPumpkinsVariety(pumpkinsVariety);
		
		if (pumpkinsVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("refererURI", refererURI);
			
			if (superRefererURI != "") {
				model.addAttribute("superRefererURI", superRefererURI);
			}
			
			return "pumpkinsVarietyCreator";
		}
		
		if (superRefererURI != "") {
			return "redirect:" + refererURI + "?superRefererURI=" + superRefererURI;
		}
		
		return "redirect:" + refererURI;
	}
	
	@GetMapping("/edit")
	public String viewEditForm(@RequestParam("id") PumpkinsVariety pumpkinsVariety, Model model) {
		model.addAttribute("pumpkinsVariety", pumpkinsVariety);
		
		return "pumpkinsVarietyEditor";
	}

	@PostMapping("/edit")
	public String savePumpkinsVariety(@RequestParam("id") PumpkinsVariety pumpkinsVariety, @Valid PumpkinsVariety updatedPumpkinsVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
			model.mergeAttributes(errors);
			model.addAttribute("pumpkinsVariety", pumpkinsVariety);
			
			return "pumpkinsVarietyEditor";
		}
		
		boolean pumpkinsVarietyExists = !pumpkinsVarietyService.updatePumpkinsVariety(updatedPumpkinsVariety);
		
		if (pumpkinsVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("pumpkinsVariety", pumpkinsVariety);

			return "pumpkinsVarietyEditor";
		}

		return "redirect:/variety/pumpkins";
	}
	
	@GetMapping("/delete")
	public String deletePumpkinsVariety(@RequestParam("id") PumpkinsVariety pumpkinsVariety) {
		if (!pumpkinsVariety.getPumpkins().isEmpty()) {
			return "redirect:/403";
		}
		
		pumpkinsVarietyService.deletePumpkinsVariety(pumpkinsVariety);

		return "redirect:/variety/pumpkins";
	}
}