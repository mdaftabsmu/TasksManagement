package fr.kodeur.jwtsecurity.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.kodeur.jwtsecurity.model.User;
import fr.kodeur.jwtsecurity.security.util.SecurityParams;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	@Autowired
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
			
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
											HttpServletResponse response, FilterChain chain,
											Authentication authResult) throws IOException, ServletException {
		
		org.springframework.security.core.userdetails.User user = 
				(org.springframework.security.core.userdetails.User) authResult.getPrincipal();
		
		List<String> roles = new ArrayList<>();
		authResult.getAuthorities().forEach(authority -> {
			roles.add(authority.getAuthority());
		});
		
		String jwt = JWT.create()
				.withIssuer(request.getRequestURI())
				.withSubject(user.getUsername())
				.withArrayClaim("roles", (String[]) roles.toArray(new String[roles.size()]))
				.withExpiresAt(new Date(System.currentTimeMillis() + SecurityParams.JWT_EXPIRATION_TIME))
				.sign(Algorithm.HMAC256(SecurityParams.JWT_SECRET));
		
		response.addHeader(SecurityParams.JWT_HEADER_NAME, jwt);
	}
}
