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

import harvest.domain.FruitsVariety;
import harvest.service.FruitsVarietyService;

@Controller
@RequestMapping("/variety/fruits")
@PreAuthorize("hasAuthority('ADMIN')")
public class FruitsVarietyController {
	@Autowired
	private FruitsVarietyService fruitsVarietyService;
	
	@GetMapping
	public String viewFruitsVarietyList(Model model) {
		List<FruitsVariety> fruitsVarietiesList = fruitsVarietyService.findAll();
		model.addAttribute("fruitsVarieties", fruitsVarietiesList);

		return "fruitsVarietyList";
	}
	
	@GetMapping("/create")
	public String viewCreationForm(@RequestParam(name = "superRefererURI", required = false) String superRefererURI,
			HttpServletRequest request, Model model) throws URISyntaxException {
		model.addAttribute("refererURI", new URI(request.getHeader("referer")).getPath());
		
		if (superRefererURI != null) {
			model.addAttribute("superRefererURI", superRefererURI);
		}
		
		return "fruitsVarietyCreator";
	}

	@PostMapping("/create")
	public String createFruitsVariety(String refererURI, String superRefererURI, @Valid FruitsVariety fruitsVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("refererURI", refererURI);
            
            if (superRefererURI != "") {
    			model.addAttribute("superRefererURI", superRefererURI);
    		}
            
            return "fruitsVarietyCreator";
        }
        		
		boolean fruitsVarietyExists = !fruitsVarietyService.createFruitsVariety(fruitsVariety);
		
		if (fruitsVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("refererURI", refererURI);
			
			if (superRefererURI != "") {
				model.addAttribute("superRefererURI", superRefererURI);
			}
			
			return "fruitsVarietyCreator";
		}
		
		if (superRefererURI != "") {
			return "redirect:" + refererURI + "?superRefererURI=" + superRefererURI;
		}
		
		return "redirect:" + refererURI;
	}
	
	@GetMapping("/edit")
	public String viewEditForm(@RequestParam("id") FruitsVariety fruitsVariety, Model model) {
		model.addAttribute("fruitsVariety", fruitsVariety);
		
		return "fruitsVarietyEditor";
	}

	@PostMapping("/edit")
	public String saveFruitsVariety(@RequestParam("id") FruitsVariety fruitsVariety, @Valid FruitsVariety updatedFruitsVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
			model.mergeAttributes(errors);
			model.addAttribute("fruitsVariety", fruitsVariety);
			
			return "fruitsVarietyEditor";
		}
		
		boolean fruitsVarietyExists = !fruitsVarietyService.updateFruitsVariety(updatedFruitsVariety);
		
		if (fruitsVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("fruitsVariety", fruitsVariety);

			return "fruitsVarietyEditor";
		}

		return "redirect:/variety/fruits";
	}
	
	@GetMapping("/delete")
	public String deleteFruitsVariety(@RequestParam("id") FruitsVariety fruitsVariety) {
		if (!fruitsVariety.getFruits().isEmpty()) {
			return "redirect:/403";
		}
		
		fruitsVarietyService.deleteFruitsVariety(fruitsVariety);

		return "redirect:/variety/fruits";
	}
}