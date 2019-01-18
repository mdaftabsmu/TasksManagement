package fr.kodeur.jwtsecurity.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import fr.kodeur.jwtsecurity.model.util.AbstractPersistent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends AbstractPersistent{

	@Column(unique = true)
	private String username;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	@Email
	private String email;
	
	private boolean actived;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles")
	private List<Role> roles = new ArrayList<>();

	public User(String username, String password, @Email String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
