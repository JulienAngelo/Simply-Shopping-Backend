package com.devcrawlers.simply.shopping.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.devcrawlers.simply.shopping.domain.User;
import com.devcrawlers.simply.shopping.resources.JwtResponseResource;
import com.devcrawlers.simply.shopping.resources.LoginRequestResource;
import com.devcrawlers.simply.shopping.resources.SignupRequestResource;
@Service
public interface AuthService {

	public JwtResponseResource authenticateUser(LoginRequestResource loginRequest);

	public User registerUser(SignupRequestResource signUpRequest);

}
