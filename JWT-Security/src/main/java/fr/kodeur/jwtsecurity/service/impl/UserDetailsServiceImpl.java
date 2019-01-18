package fr.kodeur.jwtsecurity.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.kodeur.jwtsecurity.model.User;
import fr.kodeur.jwtsecurity.service.UserService;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userService.getUserByUsername(username);
		if(user == null) throw new UsernameNotFoundException("Invalid user");
		
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getRolename()));
		});
		
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), authorities);
	}

}
