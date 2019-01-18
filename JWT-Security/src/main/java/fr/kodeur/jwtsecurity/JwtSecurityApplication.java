package fr.kodeur.jwtsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fr.kodeur.jwtsecurity.service.RoleService;
import fr.kodeur.jwtsecurity.service.UserService;

@SpringBootApplication
public class JwtSecurityApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	public static void main(String[] args) {
		SpringApplication.run(JwtSecurityApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		
		roleService.saveRole("ADMIN");
		roleService.saveRole("USER");
		
		userService.saveUser("admin", "admin", "admin");
		userService.saveUser("user_1", "123", "123");
		userService.saveUser("user_2", "123", "123");
		userService.saveUser("user_3", "123", "123");
		
		userService.addRoleToUser(1L, "ADMIN");
		
	}

}

