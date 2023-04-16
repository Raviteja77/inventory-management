package com.management.inventory.controllers;


import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.inventory.entities.Manager;
import com.management.inventory.exceptions.ApiException;
import com.management.inventory.payloads.JwtAuthRequest;
import com.management.inventory.payloads.JwtAuthResponse;
import com.management.inventory.payloads.ManagerDto;
import com.management.inventory.repositories.ManagerRepository;
import com.management.inventory.security.JwtTokenHelper;
import com.management.inventory.security.SecureManagerService;
import com.management.inventory.services.ManagerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private SecureManagerService secureManagerService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private ManagerService managerService;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails managerDetails = this.secureManagerService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(managerDetails);

		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		response.setManager(this.mapper.map((Manager) managerDetails, ManagerDto.class));
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String email, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
				password);

		try {

			this.authenticationManager.authenticate(authenticationToken);

		} catch (BadCredentialsException e) {
			System.out.println("Invalid Detials !!");
			throw new ApiException("Invalid username or password !!");
		}

	}

	// register new user api
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody ManagerDto managerDto) {
		if(this.managerRepository.findByEmail(managerDto.getEmail()).isEmpty()) {
			ManagerDto registeredUser = this.managerService.registerNewManager(managerDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(HttpStatus.CREATED.value());
	    }
	    return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(HttpStatus.ALREADY_REPORTED.value());
	}

	// get loggedin user data
	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private ModelMapper mapper;

	@GetMapping("/current-user/")
	public ResponseEntity<ManagerDto> getUser(Principal principal) {
		Manager manager = this.managerRepository.findByEmail(principal.getName()).get();
		return new ResponseEntity<ManagerDto>(this.mapper.map(manager, ManagerDto.class), HttpStatus.OK);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<JwtAuthResponse> deleteToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        if (auth != null){      
           new SecurityContextLogoutHandler().logout(request, response, auth);  
        }
        
        JwtAuthResponse returnResponse = new JwtAuthResponse();
		
		returnResponse.setToken(null);
		
		return new ResponseEntity<JwtAuthResponse>(returnResponse, HttpStatus.OK);
	}

}