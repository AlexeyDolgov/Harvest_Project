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

import harvest.domain.CucumbersVariety;
import harvest.service.CucumbersVarietyService;

@Controller
@RequestMapping("/variety/cucumbers")
@PreAuthorize("hasAuthority('ADMIN')")
public class CucumbersVarietyController {
	@Autowired
	private CucumbersVarietyService cucumbersVarietyService;
	
	@GetMapping
	public String viewCucumbersVarietyList(Model model) {
		List<CucumbersVariety> cucumbersVarietiesList = cucumbersVarietyService.findAll();
		model.addAttribute("cucumbersVarieties", cucumbersVarietiesList);

		return "cucumbersVarietyList";
	}
	
	@GetMapping("/create")
	public String viewCreationForm(@RequestParam(name = "superRefererURI", required = false) String superRefererURI,
			HttpServletRequest request, Model model) throws URISyntaxException {
		model.addAttribute("refererURI", new URI(request.getHeader("referer")).getPath());
		
		if (superRefererURI != null) {
			model.addAttribute("superRefererURI", superRefererURI);
		}
		
		return "cucumbersVarietyCreator";
	}

	@PostMapping("/create")
	public String createCucumbersVariety(String refererURI, String superRefererURI, @Valid CucumbersVariety cucumbersVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("refererURI", refererURI);
            
            if (superRefererURI != "") {
    			model.addAttribute("superRefererURI", superRefererURI);
    		}
            
            return "cucumbersVarietyCreator";
        }
        		
		boolean cucumbersVarietyExists = !cucumbersVarietyService.createCucumbersVariety(cucumbersVariety);
		
		if (cucumbersVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("refererURI", refererURI);
			
			if (superRefererURI != "") {
				model.addAttribute("superRefererURI", superRefererURI);
			}
			
			return "cucumbersVarietyCreator";
		}
		
		if (superRefererURI != "") {
			return "redirect:" + refererURI + "?superRefererURI=" + superRefererURI;
		}
		
		return "redirect:" + refererURI;
	}
	
	@GetMapping("/edit")
	public String viewEditForm(@RequestParam("id") CucumbersVariety cucumbersVariety, Model model) {
		model.addAttribute("cucumbersVariety", cucumbersVariety);
		
		return "cucumbersVarietyEditor";
	}

	@PostMapping("/edit")
	public String saveCucumbersVariety(@RequestParam("id") CucumbersVariety cucumbersVariety, @Valid CucumbersVariety updatedCucumbersVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
			model.mergeAttributes(errors);
			model.addAttribute("cucumbersVariety", cucumbersVariety);
			
			return "cucumbersVarietyEditor";
		}
		
		boolean cucumbersVarietyExists = !cucumbersVarietyService.updateCucumbersVariety(updatedCucumbersVariety);
		
		if (cucumbersVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("cucumbersVariety", cucumbersVariety);

			return "cucumbersVarietyEditor";
		}

		return "redirect:/variety/cucumbers";
	}
	
	@GetMapping("/delete")
	public String deleteCucumbersVariety(@RequestParam("id") CucumbersVariety cucumbersVariety) {
		if (!cucumbersVariety.getCucumbers().isEmpty()) {
			return "redirect:/403";
		}
		
		cucumbersVarietyService.deleteCucumbersVariety(cucumbersVariety);

		return "redirect:/variety/cucumbers";
	}
}