package fr.kodeur.jwtsecurity.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.kodeur.jwtsecurity.dao.RoleRepository;
import fr.kodeur.jwtsecurity.model.Role;
import fr.kodeur.jwtsecurity.model.User;
import fr.kodeur.jwtsecurity.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role saveRole(String rolename) {
		Role role = roleRepository.findByRolename(rolename);
		if(role != null) throw new RuntimeException("Role already exist !");
		role = new Role(rolename);
		return roleRepository.save(role);
	}
	
	@Override
	public Role getRoleById(Long id) {
		Role Role = roleRepository.getOne(id);
		if(Role == null) throw new RuntimeException("Role not exist !");
		return Role;
	}

	@Override
	public Role getRoleByRolename(String rolename) {
		Role role = roleRepository.findByRolename(rolename);
		if(role == null) throw new RuntimeException("Role not exist !");
		return role;
	}

	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public List<Role> getRolesByUser(User user) {
		return user.getRoles();
	}

	@Override
	public Role updateRole(Role Role) {
		return roleRepository.save(Role);
	}

	@Override
	public void deleteRole(Long id) {
		Role role = getRoleById(id);
		roleRepository.delete(role);
	}
}
