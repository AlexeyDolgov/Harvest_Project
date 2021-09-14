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

import harvest.domain.GarlicVariety;
import harvest.service.GarlicVarietyService;

@Controller
@RequestMapping("/variety/garlic")
@PreAuthorize("hasAuthority('ADMIN')")
public class GarlicVarietyController {
	@Autowired
	private GarlicVarietyService garlicVarietyService;
	
	@GetMapping
	public String viewGarlicVarietyList(Model model) {
		List<GarlicVariety> garlicVarietiesList = garlicVarietyService.findAll();
		model.addAttribute("garlicVarieties", garlicVarietiesList);

		return "garlicVarietyList";
	}
	
	@GetMapping("/create")
	public String viewCreationForm(@RequestParam(name = "superRefererURI", required = false) String superRefererURI,
			HttpServletRequest request, Model model) throws URISyntaxException {
		model.addAttribute("refererURI", new URI(request.getHeader("referer")).getPath());
		
		if (superRefererURI != null) {
			model.addAttribute("superRefererURI", superRefererURI);
		}
		
		return "garlicVarietyCreator";
	}

	@PostMapping("/create")
	public String createGarlicVariety(String refererURI, String superRefererURI, @Valid GarlicVariety garlicVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("refererURI", refererURI);
            
            if (superRefererURI != "") {
    			model.addAttribute("superRefererURI", superRefererURI);
    		}
            
            return "garlicVarietyCreator";
        }
        		
		boolean garlicVarietyExists = !garlicVarietyService.createGarlicVariety(garlicVariety);
		
		if (garlicVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("refererURI", refererURI);
			
			if (superRefererURI != "") {
				model.addAttribute("superRefererURI", superRefererURI);
			}
			
			return "garlicVarietyCreator";
		}
		
		if (superRefererURI != "") {
			return "redirect:" + refererURI + "?superRefererURI=" + superRefererURI;
		}
		
		return "redirect:" + refererURI;
	}
	
	@GetMapping("/edit")
	public String viewEditForm(@RequestParam("id") GarlicVariety garlicVariety, Model model) {
		model.addAttribute("garlicVariety", garlicVariety);
		
		return "garlicVarietyEditor";
	}

	@PostMapping("/edit")
	public String saveGarlicVariety(@RequestParam("id") GarlicVariety garlicVariety, @Valid GarlicVariety updatedGarlicVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
			model.mergeAttributes(errors);
			model.addAttribute("garlicVariety", garlicVariety);
			
			return "garlicVarietyEditor";
		}
		
		boolean garlicVarietyExists = !garlicVarietyService.updateGarlicVariety(updatedGarlicVariety);
		
		if (garlicVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("garlicVariety", garlicVariety);

			return "garlicVarietyEditor";
		}

		return "redirect:/variety/garlic";
	}
	
	@GetMapping("/delete")
	public String deleteGarlicVariety(@RequestParam("id") GarlicVariety garlicVariety) {
		if (!garlicVariety.getGarlic().isEmpty()) {
			return "redirect:/403";
		}
		
		garlicVarietyService.deleteGarlicVariety(garlicVariety);

		return "redirect:/variety/garlic";
	}
}