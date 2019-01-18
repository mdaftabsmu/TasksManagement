package fr.kodeur.jwtsecurity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.kodeur.jwtsecurity.model.Role;

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role findByRolename(String rolename);
}
