package harvest.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import harvest.domain.User;
import harvest.service.UserService;

@Controller
public class RegistrationController {
	@Autowired
	private UserService userService;

	@GetMapping("/registration")
	public String viewRegistrationForm() {
		return "registration";
	}

	@PostMapping("/registration")
	public String registerUser(
			@RequestParam String confirmPassword,
			@Valid User user,
			BindingResult bindingResult,
			Model model,
			RedirectAttributes redir) {

		if (StringUtils.isEmpty(confirmPassword) || bindingResult.hasErrors()) {
			Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
			model.mergeAttributes(errors);
			model.addAttribute("confirmPasswordError", "Пароль пользователя должен быть не менее 6 символов!");
			return "registration";
		}

		if (user.getPassword() != null && !user.getPassword().equals(confirmPassword)) {
			model.addAttribute("confirmPasswordError2", "Введённые пароли не совпадают!");
			return "registration";
		}

		if (!userService.addUser(user)) {
			model.addAttribute("userExistsMessage", "Такой пользователь уже существует!");
			return "registration";
		}

		redir.addFlashAttribute("activationMessage",
				"Для активации пользователя перейдите по ссылке в письме, отправленном на указанный Вами электронный ящик!");
		return "activationMessage";
	}

	@GetMapping("/activate/{code}")
	public String activate(@PathVariable String code, Model model) {
		boolean isActivated = userService.activateUser(code);

		if (isActivated) {
			model.addAttribute("activationSucceedMessage", "Пользователь успешно активирован!");
		} else {
			model.addAttribute("activationFailedMessage", "Код активации не найден!");
		}

		return "login";
	}
}