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

import fr.kodeur.jwtsecurity.model.Role;
import fr.kodeur.jwtsecurity.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleRestController {

	@Autowired
	private RoleService roleService;
	
	@GetMapping
	public List<Role> roles(){
		return roleService.getAllRoles();
	}
	
	@GetMapping("/{id}")
	public Role role(@PathVariable("id") Long id) {
		return roleService.getRoleById(id);
	}
	
	@PostMapping
	public Role createRole(@RequestParam String rolename) {
		return roleService.saveRole(rolename);
	}
	
	@PutMapping
	public Role updateRole(@RequestBody Role role) {
		return roleService.updateRole(role);
	}
	
	@DeleteMapping("/{id}")
	public void deleteRole(@PathVariable("id") Long id) {
		roleService.deleteRole(id);
	}
}
