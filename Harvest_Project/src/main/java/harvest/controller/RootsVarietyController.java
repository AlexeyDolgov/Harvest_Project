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

import harvest.domain.RootsVariety;
import harvest.service.RootsVarietyService;

@Controller
@RequestMapping("/variety/roots")
@PreAuthorize("hasAuthority('ADMIN')")
public class RootsVarietyController {
	@Autowired
	private RootsVarietyService rootsVarietyService;
	
	@GetMapping
	public String viewRootsVarietyList(Model model) {
		List<RootsVariety> rootsVarietiesList = rootsVarietyService.findAll();
		model.addAttribute("rootsVarieties", rootsVarietiesList);

		return "rootsVarietyList";
	}
	
	@GetMapping("/create")
	public String viewCreationForm(@RequestParam(name = "superRefererURI", required = false) String superRefererURI,
			HttpServletRequest request, Model model) throws URISyntaxException {
		model.addAttribute("refererURI", new URI(request.getHeader("referer")).getPath());
		
		if (superRefererURI != null) {
			model.addAttribute("superRefererURI", superRefererURI);
		}
		
		return "rootsVarietyCreator";
	}

	@PostMapping("/create")
	public String createRootsVariety(String refererURI, String superRefererURI, @Valid RootsVariety rootsVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("refererURI", refererURI);
            
            if (superRefererURI != "") {
    			model.addAttribute("superRefererURI", superRefererURI);
    		}
            
            return "rootsVarietyCreator";
        }
        		
		boolean rootsVarietyExists = !rootsVarietyService.createRootsVariety(rootsVariety);
		
		if (rootsVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("refererURI", refererURI);
			
			if (superRefererURI != "") {
				model.addAttribute("superRefererURI", superRefererURI);
			}
			
			return "rootsVarietyCreator";
		}
		
		if (superRefererURI != "") {
			return "redirect:" + refererURI + "?superRefererURI=" + superRefererURI;
		}
		
		return "redirect:" + refererURI;
	}
	
	@GetMapping("/edit")
	public String viewEditForm(@RequestParam("id") RootsVariety rootsVariety, Model model) {
		model.addAttribute("rootsVariety", rootsVariety);
		
		return "rootsVarietyEditor";
	}

	@PostMapping("/edit")
	public String saveRootsVariety(@RequestParam("id") RootsVariety rootsVariety, @Valid RootsVariety updatedRootsVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
			model.mergeAttributes(errors);
			model.addAttribute("rootsVariety", rootsVariety);
			
			return "rootsVarietyEditor";
		}
		
		boolean rootsVarietyExists = !rootsVarietyService.updateRootsVariety(updatedRootsVariety);
		
		if (rootsVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("rootsVariety", rootsVariety);

			return "rootsVarietyEditor";
		}

		return "redirect:/variety/roots";
	}
	
	@GetMapping("/delete")
	public String deleteRootsVariety(@RequestParam("id") RootsVariety rootsVariety) {
		if (!rootsVariety.getRoots().isEmpty()) {
			return "redirect:/403";
		}
		
		rootsVarietyService.deleteRootsVariety(rootsVariety);

		return "redirect:/variety/roots";
	}
}