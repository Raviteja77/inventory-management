package com.management.inventory.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.management.inventory.entities.Manager;
import com.management.inventory.exceptions.ResourceNotFoundException;
import com.management.inventory.payloads.ManagerDto;
import com.management.inventory.repositories.ManagerRepository;
import com.management.inventory.services.ManagerService;

@Service
public class ManagerServiceImplementation implements ManagerService {
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ManagerDto createManager(ManagerDto managerDto) {
		Manager manager = this.dtoToManager(managerDto);
		Manager savedManager = this.managerRepository.save(manager);
		return this.managerToDto(savedManager);
	}

	@Override
	public ManagerDto updateManager(ManagerDto managerDto, Integer managerId) {
		Manager manager = this.managerRepository.findById(managerId)
				.orElseThrow(() -> new ResourceNotFoundException("Manager", "Id", managerId));
		
		manager.setManagerId(managerDto.getManagerId());
		manager.setFirstName(managerDto.getFirstName());
		manager.setLastName(managerDto.getLastName());
		manager.setEmail(managerDto.getEmail());
		manager.setPassword(managerDto.getPassword());
		
		Manager updatedManager = this.managerRepository.save(manager);
		ManagerDto updatedManagerDto = this.managerToDto(updatedManager);
				
		return updatedManagerDto;
	}

	@Override
	public ManagerDto getManager(Integer managerId) {
		Manager manager = this.managerRepository.findById(managerId)
				.orElseThrow(() -> new ResourceNotFoundException("Manager", "Id", managerId));
		
		return this.managerToDto(manager);
	}

	@Override
	public List<ManagerDto> getAllManagers() {
		List<Manager> managers = this.managerRepository.findAll();
		
		List<ManagerDto> managerDtos = managers.stream().map(manager -> this.managerToDto(manager)).collect(Collectors.toList());
		return managerDtos;
	}

	@Override
	public void deleteManager(Integer managerId) {
		Manager manager = this.managerRepository.findById(managerId)
				.orElseThrow(() -> new ResourceNotFoundException("Manager", "Id", managerId));
		
		manager.setStatus(false);
		this.managerRepository.save(manager);

	}
	
	@Override
	public ManagerDto registerNewManager(ManagerDto managerDto) {

		Manager manager = this.modelMapper.map(managerDto, Manager.class);

		// encoded the password
		manager.setPassword(this.passwordEncoder.encode(manager.getPassword()));

		Manager newUser = this.managerRepository.save(manager);

		return this.modelMapper.map(newUser, ManagerDto.class);
	}
	
	private Manager dtoToManager(ManagerDto managerDto) {
		Manager manager = this.modelMapper.map(managerDto, Manager.class);
		return manager;
	}
	
	private ManagerDto managerToDto(Manager manager) {
		ManagerDto managerDto = this.modelMapper.map(manager, ManagerDto.class);
		return managerDto;
	}

}
