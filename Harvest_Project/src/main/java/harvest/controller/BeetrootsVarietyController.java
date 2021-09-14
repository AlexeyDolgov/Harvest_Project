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

import harvest.domain.BeetrootsVariety;
import harvest.service.BeetrootsVarietyService;

@Controller
@RequestMapping("/variety/beetroots")
@PreAuthorize("hasAuthority('ADMIN')")
public class BeetrootsVarietyController {
	@Autowired
	private BeetrootsVarietyService beetrootsVarietyService;
	
	@GetMapping
	public String viewBeetrootsVarietyList(Model model) {
		List<BeetrootsVariety> beetrootsVarietiesList = beetrootsVarietyService.findAll();
		model.addAttribute("beetrootsVarieties", beetrootsVarietiesList);

		return "beetrootsVarietyList";
	}
	
	@GetMapping("/create")
	public String viewCreationForm(@RequestParam(name = "superRefererURI", required = false) String superRefererURI,
			HttpServletRequest request, Model model) throws URISyntaxException {
		model.addAttribute("refererURI", new URI(request.getHeader("referer")).getPath());
		
		if (superRefererURI != null) {
			model.addAttribute("superRefererURI", superRefererURI);
		}
		
		return "beetrootsVarietyCreator";
	}

	@PostMapping("/create")
	public String createBeetrootsVariety(String refererURI, String superRefererURI, @Valid BeetrootsVariety beetrootsVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("refererURI", refererURI);
            
            if (superRefererURI != "") {
    			model.addAttribute("superRefererURI", superRefererURI);
    		}
            
            return "beetrootsVarietyCreator";
        }
        		
		boolean beetrootsVarietyExists = !beetrootsVarietyService.createBeetrootsVariety(beetrootsVariety);
		
		if (beetrootsVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("refererURI", refererURI);
			
			if (superRefererURI != "") {
				model.addAttribute("superRefererURI", superRefererURI);
			}
			
			return "beetrootsVarietyCreator";
		}
		
		if (superRefererURI != "") {
			return "redirect:" + refererURI + "?superRefererURI=" + superRefererURI;
		}
		
		return "redirect:" + refererURI;
	}
	
	@GetMapping("/edit")
	public String viewEditForm(@RequestParam("id") BeetrootsVariety beetrootsVariety, Model model) {
		model.addAttribute("beetrootsVariety", beetrootsVariety);
		
		return "beetrootsVarietyEditor";
	}

	@PostMapping("/edit")
	public String saveBeetrootsVariety(@RequestParam("id") BeetrootsVariety beetrootsVariety, @Valid BeetrootsVariety updatedBeetrootsVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
			model.mergeAttributes(errors);
			model.addAttribute("beetrootsVariety", beetrootsVariety);
			
			return "beetrootsVarietyEditor";
		}
		
		boolean beetrootsVarietyExists = !beetrootsVarietyService.updateBeetrootsVariety(updatedBeetrootsVariety);
		
		if (beetrootsVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("beetrootsVariety", beetrootsVariety);

			return "beetrootsVarietyEditor";
		}

		return "redirect:/variety/beetroots";
	}
	
	@GetMapping("/delete")
	public String deleteBeetrootsVariety(@RequestParam("id") BeetrootsVariety beetrootsVariety) {
		if (!beetrootsVariety.getBeetroots().isEmpty()) {
			return "redirect:/403";
		}
		
		beetrootsVarietyService.deleteBeetrootsVariety(beetrootsVariety);

		return "redirect:/variety/beetroots";
	}
}