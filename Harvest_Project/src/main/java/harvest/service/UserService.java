package harvest.service;

import java.util.Collections;
import java.util.UUID;

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
	
    public boolean addUser(User user) {
        User userFromDb = userRepository.findByEmail(user.getEmail());

        if (userFromDb != null) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(false);
        user.setAccessLevels(Collections.singleton(AccessLevel.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        userRepository.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
            		"Доброго времени суток, %s %s! \n\n" +
            				"Добро пожаловать в приложение \"Урожай©\".\n" +
            				"Для продолжения регистрации и активации своего аккаунта перейдите, пожалуйста, по ссылке:\n" +
            				applicationPath + "/activate/%s",
                    user.getFirstName(),
                    user.getLastName(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Код активации аккаунта", message);
        }

        return true;
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
}
