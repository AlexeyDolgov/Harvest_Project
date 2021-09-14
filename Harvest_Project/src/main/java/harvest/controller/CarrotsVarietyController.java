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

import harvest.domain.CarrotsVariety;
import harvest.service.CarrotsVarietyService;

@Controller
@RequestMapping("/variety/carrots")
@PreAuthorize("hasAuthority('ADMIN')")
public class CarrotsVarietyController {
	@Autowired
	private CarrotsVarietyService carrotsVarietyService;
	
	@GetMapping
	public String viewCarrotsVarietyList(Model model) {
		List<CarrotsVariety> carrotsVarietiesList = carrotsVarietyService.findAll();
		model.addAttribute("carrotsVarieties", carrotsVarietiesList);

		return "carrotsVarietyList";
	}
	
	@GetMapping("/create")
	public String viewCreationForm(@RequestParam(name = "superRefererURI", required = false) String superRefererURI,
			HttpServletRequest request, Model model) throws URISyntaxException {
		model.addAttribute("refererURI", new URI(request.getHeader("referer")).getPath());
		
		if (superRefererURI != null) {
			model.addAttribute("superRefererURI", superRefererURI);
		}
		
		return "carrotsVarietyCreator";
	}

	@PostMapping("/create")
	public String createCarrotsVariety(String refererURI, String superRefererURI, @Valid CarrotsVariety carrotsVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("refererURI", refererURI);
            
            if (superRefererURI != "") {
    			model.addAttribute("superRefererURI", superRefererURI);
    		}
            
            return "carrotsVarietyCreator";
        }
        		
		boolean carrotsVarietyExists = !carrotsVarietyService.createCarrotsVariety(carrotsVariety);
		
		if (carrotsVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("refererURI", refererURI);
			
			if (superRefererURI != "") {
				model.addAttribute("superRefererURI", superRefererURI);
			}
			
			return "carrotsVarietyCreator";
		}
		
		if (superRefererURI != "") {
			return "redirect:" + refererURI + "?superRefererURI=" + superRefererURI;
		}
		
		return "redirect:" + refererURI;
	}
	
	@GetMapping("/edit")
	public String viewEditForm(@RequestParam("id") CarrotsVariety carrotsVariety, Model model) {
		model.addAttribute("carrotsVariety", carrotsVariety);
		
		return "carrotsVarietyEditor";
	}

	@PostMapping("/edit")
	public String saveCarrotsVariety(@RequestParam("id") CarrotsVariety carrotsVariety, @Valid CarrotsVariety updatedCarrotsVariety, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
			model.mergeAttributes(errors);
			model.addAttribute("carrotsVariety", carrotsVariety);
			
			return "carrotsVarietyEditor";
		}
		
		boolean carrotsVarietyExists = !carrotsVarietyService.updateCarrotsVariety(updatedCarrotsVariety);
		
		if (carrotsVarietyExists) {
			model.addAttribute("varietyExistsMessage", "Такой сорт уже существует!");
			model.addAttribute("carrotsVariety", carrotsVariety);

			return "carrotsVarietyEditor";
		}

		return "redirect:/variety/carrots";
	}
	
	@GetMapping("/delete")
	public String deleteCarrotsVariety(@RequestParam("id") CarrotsVariety carrotsVariety) {
		if (!carrotsVariety.getCarrots().isEmpty()) {
			return "redirect:/403";
		}
		
		carrotsVarietyService.deleteCarrotsVariety(carrotsVariety);

		return "redirect:/variety/carrots";
	}
}