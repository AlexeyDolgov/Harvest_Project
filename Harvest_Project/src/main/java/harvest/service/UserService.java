package harvest.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import harvest.dao.UserRepository;
import harvest.domain.AccessLevel;
import harvest.domain.User;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MailSender mailSender;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("${spring.application.path}")
	private String applicationPath;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email);
	}

	public User findById(Integer id) {
		return userRepository.findById(id).get();
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}
	
    public boolean checkIfExists(User user) {
    	User userFromDb = userRepository.findByEmail(user.getEmail());

    	if (userFromDb != null && user.getId() != userFromDb.getId()) {
    		return true;
    	}
    	return false;
    }
    
	public boolean addUser(User user) {
		if (checkIfExists(user))
			return false;
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setActive(false);
		user.setAccessLevels(Collections.singleton(AccessLevel.USER));
		user.setActivationCode(UUID.randomUUID().toString());

		userRepository.save(user);
		sendActivationCode(user);
		return true;
	}

	public void sendActivationCode(User user) {
		if (!StringUtils.isEmpty(user.getEmail())) {
			String message = String.format("Доброго времени суток, %s %s! \n\n"
					+ "Добро пожаловать в приложение \"Урожай©\".\n"
					+ "Для продолжения регистрации и активации своего аккаунта перейдите, пожалуйста, по ссылке:\n"
					+ applicationPath + "/activate/%s", user.getFirstName(), user.getLastName(),
					user.getActivationCode());

			mailSender.send(user.getEmail(), "Код активации аккаунта", message);
		}
	}

	public boolean activateUser(String code) {
		User user = userRepository.findByActivationCode(code);

		if (user == null) {
			return false;
		}

		user.setActive(true);
		user.setActivationCode(null);

		userRepository.save(user);

		return true;
	}
	
    public Map<String, String> getUserErrors(Map<String, String> form) {
    	Map<String, String> errors = new HashMap<>();
    	if (StringUtils.isEmpty(form.get("firstName"))) {
    		errors.put("firstNameError", "Имя пользователя не может быть пустым!");			
    	}

    	if (StringUtils.isEmpty(form.get("lastName"))) {
    		errors.put("lastNameError", "Фамилия пользователя не может быть пустым!");
    	}
    	return errors;
    }
    
    public void saveUser(User user, Map<String, String> form) {
		user.setFirstName(form.get("firstName"));
		user.setLastName(form.get("lastName"));
		user.setEmail(form.get("email"));

		if (form.keySet().contains("active")) {
			user.setActive(true);
		} else {
			user.setActive(false);
		}

		user.getAccessLevels().clear();

		Set<String> accessLevels = Arrays.stream(AccessLevel.values()).map(AccessLevel::name).collect(Collectors.toSet());

		for (String key : form.keySet()) {
			if (accessLevels.contains(key)) {
				user.getAccessLevels().add(AccessLevel.valueOf(key));
			}
		}
	}

	public boolean updateProfile(User user, String firstName, String lastName, String email, String password) {
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(passwordEncoder.encode(password));

		String userEmail = user.getEmail();
		boolean isEmailChanged = (email != null && !email.equals(userEmail))
				|| (userEmail != null && !userEmail.equals(email));

		if (isEmailChanged) {
			if (checkIfExists(user))
				return false;
			
			user.setEmail(email);
			user.setActive(false);
			user.setActivationCode(UUID.randomUUID().toString());
			sendActivationCode(user);			
		}

		userRepository.save(user);
		return true;
	}

	public Map<String, String> getProfileErrors(String firstName, String lastName, String email, String password, String confirmPassword) {
		Map<String, String> errors = new HashMap<>();
		if (StringUtils.isEmpty(firstName)) {
			errors.put("firstNameError", "Имя пользователя не может быть пустым!");			
		}
	
		if (StringUtils.isEmpty(lastName)) {
			errors.put("lastNameError", "Фамилия пользователя не может быть пустым!");
		}
	
		if (StringUtils.isEmpty(email)) {
			errors.put("emailError", "Email пользователя не может быть пустым!");
		}
	
		if (password.length() < 6) {
			errors.put("passwordError", "Пароль пользователя должен быть не менее 6 символов!");
		}
	
		if (confirmPassword.length() < 6) {
			errors.put("confirmPasswordError", "Пароль пользователя должен быть не менее 6 символов!");
		}
	
		if (password != "" && confirmPassword != "" && !password.equals(confirmPassword)) {
			errors.put("confirmPasswordError", "Введённые пароли не совпадают!");
	    }
		return errors;
	}
}
