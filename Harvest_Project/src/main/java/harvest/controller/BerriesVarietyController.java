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

import harvest.domain.BerriesVariety;
import harvest.service.BerriesVarietyService;

@Controller
@RequestMapping("/variety/berries")
@PreAuthorize("hasAuthority('ADMIN')")
public class BerriesVarietyController {
	@Autowired
	private BerriesVarietyService berriesVarietyService;
	
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
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
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
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
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
}