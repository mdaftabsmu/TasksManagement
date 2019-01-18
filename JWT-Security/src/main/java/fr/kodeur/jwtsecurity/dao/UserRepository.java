package fr.kodeur.jwtsecurity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.kodeur.jwtsecurity.model.User;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);
}
