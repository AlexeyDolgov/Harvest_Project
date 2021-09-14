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

import harvest.domain.PeppersVariety;
import harvest.service.PeppersVarietyService;

@Controller
@RequestMapping("/variety/peppers")
@PreAuthorize("hasAuthority('ADMIN')")
public class PeppersVarietyController {
	@Autowired
	private PeppersVarietyService peppersVarietyService;
	
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
}