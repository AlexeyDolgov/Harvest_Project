package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmail(String email);

	User findByActivationCode(String code);
}
