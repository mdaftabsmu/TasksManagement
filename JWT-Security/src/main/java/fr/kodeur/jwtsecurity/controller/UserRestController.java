package fr.kodeur.jwtsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.kodeur.jwtsecurity.model.User;
import fr.kodeur.jwtsecurity.model.util.UserRegister;
import fr.kodeur.jwtsecurity.service.UserService;

@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> users(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public User user(@PathVariable("id") Long id) {
		return userService.getUserById(id);
	}
	
	@PostMapping("/register")
	public User register(@RequestBody UserRegister userRegister) {
		return userService.saveUser(userRegister.getUsername(), userRegister.getPassword(), userRegister.getConfirmedPassword());
	}
	
	@PutMapping
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
	}
	
	@PostMapping("/addRole/{id}")
	public User addRoleToUser(@PathVariable("id") Long idUser, @RequestParam String rolename) {
		System.out.println("id : " + idUser + " rolename : " + rolename);
		return userService.addRoleToUser(idUser, rolename);
	}
	
	@PostMapping("/removeRole/{id}")
	public User removeRoleToUser(@PathVariable("id") Long idUser, @RequestParam String rolename) {
		return userService.removeRoleToUser(idUser, rolename);
	}
}
