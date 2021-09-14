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

import harvest.domain.PotatoesVariety;
import harvest.service.PotatoesVarietyService;

@Controller
@RequestMapping("/variety/potatoes")
@PreAuthorize("hasAuthority('ADMIN')")
public class PotatoesVarietyController {
	@Autowired
	private PotatoesVarietyService potatoesVarietyService;
	
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
}