package fr.kodeur.jwtsecurity.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.kodeur.jwtsecurity.dao.RoleRepository;
import fr.kodeur.jwtsecurity.dao.UserRepository;
import fr.kodeur.jwtsecurity.model.Role;
import fr.kodeur.jwtsecurity.model.User;
import fr.kodeur.jwtsecurity.service.RoleService;
import fr.kodeur.jwtsecurity.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User saveUser(String username, String password, String confirmedPassword) {
		User user = userRepository.findByUsername(username);
		
		if(user != null) throw new RuntimeException("User already exist !");
		if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password !");
		
		user = new User();
		user.setUsername(username);
		user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setActived(true);
		userRepository.save(user);
		
		addRoleToUser(user.getId(), "USER");
		
		return user;
	}
	
	@Override
	public User getUserById(Long id) {
		User user = userRepository.getOne(id);
		if(user == null) throw new RuntimeException("User not exist !");
		return user;
	}

	@Override
	public User getUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if(user == null) throw new RuntimeException("User not exist !");
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<User> getUsersByRole(Role role) {
		List<User> users = new ArrayList<>();
		getAllUsers().forEach(user -> {
			user.getRoles().forEach(r -> {
				if(role.getRolename().equals(r.getRolename())) {
					users.add(user);
				}
			});
		});
		return users;
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		User user = getUserById(id);
		userRepository.delete(user);
	}

	@Override
	public User addRoleToUser(Long idUser, String rolename) {
		User user = getUserById(idUser);
		Role role = roleRepository.findByRolename(rolename);
		
		if(role == null) {
			role = new Role(rolename);
			roleService.saveRole(rolename);
		}
		if(user .getRoles().contains(role))
			throw new RuntimeException("This user already has this role !");
		
		user.getRoles().add(role);
		return user;
	}

	@Override
	public User removeRoleToUser(Long idUser, String rolename) {
		User user = getUserById(idUser);
		Role role = roleService.getRoleByRolename(rolename);
		user.getRoles().remove(role);
		return user;
	}

}
