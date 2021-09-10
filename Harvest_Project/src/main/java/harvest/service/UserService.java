package harvest.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	Logger logger = LoggerFactory.getLogger(UserService.class);

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
    	logger.trace("Getting user by email \"" + email + "\" from database...");

		return userRepository.findByEmail(email);
	}

	public User findById(Integer id) {
    	logger.trace("Getting user by id=" + id + " from database...");

		return userRepository.findById(id).get();
	}

	public List<User> findAll() {
    	logger.trace("Getting all users from database...");

		return userRepository.findAll();
	}
	
    public boolean checkIfExists(User user) {
    	logger.trace("Checking if stored user already exists in database...");

    	User userFromDb = userRepository.findByEmail(user.getEmail());

    	if (userFromDb != null && user.getId() != userFromDb.getId()) {
    		return true;
    	}
    	return false;
    }
    
	public boolean addUser(User user) {
    	logger.trace("Adding new user to database...");

		if (checkIfExists(user))
			return false;
		
        logger.trace("Encoding recieved user's password...");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setActive(false);
		user.setAccessLevels(Collections.singleton(AccessLevel.USER));
        logger.trace("Generating activation code...");
        user.setActivationCode(UUID.randomUUID().toString());

        logger.trace("Saving new user in database...");
        userRepository.save(user);
		sendActivationCode(user);
		return true;
	}

	public void sendActivationCode(User user) {
		logger.trace("Sending activation code to user's email...");

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
    	logger.trace("Activating user's account...");

    	logger.trace("Getting user by activation code \"" + code + "\" from database...");
		User user = userRepository.findByActivationCode(code);

		if (user == null) {
        	logger.warn("There is no any user with activation code \"" + code + "\" in database...");
			return false;
		}

        logger.trace("Setting user's account active and clearing activation code...");
        user.setActive(true);
		user.setActivationCode(null);

        logger.trace("Saving activated user in database...");
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
    	logger.trace("Updating user's account...");

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
		
		logger.trace("Saving updated user in database...");
		userRepository.save(user);
	}

	public boolean updateProfile(User user, String firstName, String lastName, String email, String password) {
		logger.trace("Updating user's profile...");

		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(passwordEncoder.encode(password));

		logger.trace("Checking user's email for being changed...");
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

		logger.trace("Saving updated user's profile in database...");
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
