package com.management.inventory.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.management.inventory.entities.Manager;
import com.management.inventory.exceptions.ResourceNotFoundException;
import com.management.inventory.repositories.ManagerRepository;


@Service
public class SecureManagerService implements UserDetailsService {

	@Autowired
	private ManagerRepository managerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Manager manager = this.managerRepository.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("User ", " email : " + username, 0));

		return manager;
	}

}