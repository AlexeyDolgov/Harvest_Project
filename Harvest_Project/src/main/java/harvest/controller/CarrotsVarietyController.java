package harvest.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import harvest.domain.CarrotsVariety;
import harvest.dto.CarrotsVarietyImportForm;
import harvest.dto.ImportVarietyFields;
import harvest.service.CarrotsVarietyService;
import harvest.service.ExcelReader;

@Controller
@RequestMapping("/variety/carrots")
@PreAuthorize("hasAuthority('ADMIN')")
public class CarrotsVarietyController {
	@Autowired
	private CarrotsVarietyService carrotsVarietyService;
	@Autowired
	private ExcelReader excelReader;
	
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
	
	@GetMapping("/import")
	public String viewCarrotsVarietyImportForm() {
		return "carrotsVarietyImport";
	}
	
	@PostMapping("/import")
	public RedirectView getCarrotsImportVarietyFields(ImportVarietyFields importVarietyFields, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws IOException {
		redirectAttributes.addFlashAttribute("importVarietyFields", importVarietyFields);
		
		return new RedirectView("/variety/carrots/importConfirm");
	}
	
	@GetMapping("/importConfirm")
	public String viewCarrotsVarietyImportConfirmList(@ModelAttribute("importVarietyFields") ImportVarietyFields importVarietyFields, Model model) throws IOException {
		List<Map<Integer, String>> list = excelReader.readFromCertainRangeInExcelFile(importVarietyFields);
		List<CarrotsVariety> carrotsVarietyList = carrotsVarietyService.mapCarrotsVarietyFromExcelList(list, importVarietyFields);
		
		Map<CarrotsVariety, Boolean> carrotsVarietyMap = new HashMap<CarrotsVariety, Boolean>();
		Boolean areAnyExistingVarieties = false;
		Boolean areAnyNewVarieties = false;

		for (CarrotsVariety carrotsVariety : carrotsVarietyList) {
			boolean doesExist = carrotsVarietyService.checkIfExists(carrotsVariety);
			
			carrotsVarietyMap.put(carrotsVariety, doesExist);
			
			if (doesExist) {
				areAnyExistingVarieties = true;
			} else if (!(carrotsVariety.getName() == null)) {
				areAnyNewVarieties = true;
			}			
		}
		
		model.addAttribute("carrotsVarietyMap", carrotsVarietyMap);
		model.addAttribute("areAnyExistingVarieties", areAnyExistingVarieties);
		model.addAttribute("areAnyNewVarieties", areAnyNewVarieties);
		
		return "carrotsVarietyImportConfirm";
	}

	@PostMapping("/importConfirm")
	public String saveCarrotsVarietiesList(@ModelAttribute("carrotsVarietyImportForm") CarrotsVarietyImportForm carrotsVarietyImportForm, Model model) {
		List<CarrotsVariety> carrotsVarieties = carrotsVarietyImportForm.getCarrotsVarieties();

		for (CarrotsVariety carrotsVariety : carrotsVarieties) {
			carrotsVarietyService.createCarrotsVariety(carrotsVariety);
		}
		
		return "redirect:/variety/carrots";
	}
}