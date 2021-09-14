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

import harvest.domain.BeansVariety;
import harvest.service.BeansVarietyService;

@Controller
@RequestMapping("/variety/beans")
@PreAuthorize("hasAuthority('ADMIN')")
public class BeansVarietyController {
	@Autowired
	private BeansVarietyService beansVarietyService;
	
	@GetMapping
	public String viewBeansVarietyList(Model model) {
		List<BeansVariety> beansVarietiesList = beansVarietyService.findAll();
		model.addAttribute("beansVarieties", beansVarietiesList);

		return "beansVarietyList";
	}
	
	@GetMapping("/create")
	public String viewCreationForm(@RequestParam(name = "superRefererURI", required = false) String superRefererURI,
			HttpServletRequest request, Model model) throws URISyntaxException {
		model.addAttribute("refererURI", new URI(request.getHeader("referer")).getPath());
		
		if (superRefererURI != null) {
			model.addAttribute("superRefererURI", superRefererURI);
		}
		
		return "beansVarietyCreator";
	}

	@PostMapping("/create")
	public String createBeansVariety(String refererURI, String superRefererURI, @Valid BeansVariety beansVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("refererURI", refererURI);
            
            if (superRefererURI != "") {
    			model.addAttribute("superRefererURI", superRefererURI);
    		}
            
            return "beansVarietyCreator";
        }
        		
		boolean beansVarietyExists = !beansVarietyService.createBeansVariety(beansVariety);
		
		if (beansVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("refererURI", refererURI);
			
			if (superRefererURI != "") {
				model.addAttribute("superRefererURI", superRefererURI);
			}
			
			return "beansVarietyCreator";
		}
		
		if (superRefererURI != "") {
			return "redirect:" + refererURI + "?superRefererURI=" + superRefererURI;
		}
		
		return "redirect:" + refererURI;
	}
	
	@GetMapping("/edit")
	public String viewEditForm(@RequestParam("id") BeansVariety beansVariety, Model model) {
		model.addAttribute("beansVariety", beansVariety);
		
		return "beansVarietyEditor";
	}

	@PostMapping("/edit")
	public String saveBeansVariety(@RequestParam("id") BeansVariety beansVariety, @Valid BeansVariety updatedBeansVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
			model.mergeAttributes(errors);
			model.addAttribute("beansVariety", beansVariety);
			
			return "beansVarietyEditor";
		}
		
		boolean beansVarietyExists = !beansVarietyService.updateBeansVariety(updatedBeansVariety);
		
		if (beansVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("beansVariety", beansVariety);

			return "beansVarietyEditor";
		}

		return "redirect:/variety/beans";
	}
	
	@GetMapping("/delete")
	public String deleteBeansVariety(@RequestParam("id") BeansVariety beansVariety) {
		if (!beansVariety.getBeans().isEmpty()) {
			return "redirect:/403";
		}
		
		beansVarietyService.deleteBeansVariety(beansVariety);

		return "redirect:/variety/beans";
	}
}