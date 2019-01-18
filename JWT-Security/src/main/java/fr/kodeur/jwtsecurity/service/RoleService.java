package fr.kodeur.jwtsecurity.service;

import java.util.List;

import fr.kodeur.jwtsecurity.model.Role;
import fr.kodeur.jwtsecurity.model.User;

public interface RoleService {

	public Role saveRole(String rolename);
	
	public Role getRoleById(Long id);
	
	public Role getRoleByRolename(String rolename);
	
	public List<Role> getAllRoles();
	
	public List<Role> getRolesByUser(User user);
	
	public Role updateRole(Role role);
	
	public void deleteRole(Long id);
}
