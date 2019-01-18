package fr.kodeur.jwtsecurity.model.util;

import lombok.Data;

@Data
public class UserRegister {

	private String username;
	
	private String password;
	
	private String confirmedPassword;
}
