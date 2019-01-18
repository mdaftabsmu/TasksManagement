package fr.kodeur.jwtsecurity.service;

import java.util.List;

import fr.kodeur.jwtsecurity.model.Role;
import fr.kodeur.jwtsecurity.model.User;

public interface UserService {

	public User saveUser(String username, String password, String confirmedPassword);
	
	public User getUserById(Long id);
	
	public User getUserByUsername(String username);
	
	public List<User> getAllUsers();
	
	public List<User> getUsersByRole(Role role);
	
	public User updateUser(User user);
	
	public void deleteUser(Long id);
	
	public User addRoleToUser(Long idUser, String rolename);
	
	public User removeRoleToUser(Long idUser, String rolename);
}
