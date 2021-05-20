package com.devcrawlers.simply.shopping.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.devcrawlers.simply.shopping.domain.Role;
import com.devcrawlers.simply.shopping.domain.User;
import com.devcrawlers.simply.shopping.enums.UserRoles;
import com.devcrawlers.simply.shopping.exception.NoRecordFoundException;
import com.devcrawlers.simply.shopping.exception.ValidateRecordException;
import com.devcrawlers.simply.shopping.repository.RoleRepository;
import com.devcrawlers.simply.shopping.repository.UserRepository;
import com.devcrawlers.simply.shopping.resources.JwtResponseResource;
import com.devcrawlers.simply.shopping.resources.LoginRequestResource;
import com.devcrawlers.simply.shopping.resources.SignupRequestResource;
import com.devcrawlers.simply.shopping.security.jwt.JwtUtils;
import com.devcrawlers.simply.shopping.service.AuthService;
@Component
@Transactional(rollbackFor=Exception.class)
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Override
	public JwtResponseResource authenticateUser(LoginRequestResource loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return new JwtResponseResource(jwt, 
				 userDetails.getId(), 
				 userDetails.getUsername(), 
				 userDetails.getEmail(), 
				 roles);
	}

	@Override
	public User registerUser(SignupRequestResource signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername()))
			throw new ValidateRecordException(environment.getProperty("user.username-exists"), "message");

		if (userRepository.existsByEmail(signUpRequest.getEmail()))
			throw new ValidateRecordException(environment.getProperty("user.email-exists"), "message");

		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(UserRoles.ROLE_USER)
					.orElseThrow(() -> new NoRecordFoundException(environment.getProperty("role-not-found")));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(UserRoles.ROLE_ADMIN)
							.orElseThrow(() -> new NoRecordFoundException(environment.getProperty("role-not-found")));
					roles.add(adminRole);

					break;
				default:
					Role userRole = roleRepository.findByName(UserRoles.ROLE_USER)
							.orElseThrow(() -> new NoRecordFoundException(environment.getProperty("role-not-found")));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		return userRepository.save(user);
	}

}
