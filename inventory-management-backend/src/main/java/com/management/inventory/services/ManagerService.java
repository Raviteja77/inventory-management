package com.management.inventory.services;

import java.util.List;

import com.management.inventory.payloads.ManagerDto;

public interface ManagerService {
	
	ManagerDto registerNewManager(ManagerDto manager);
	
	ManagerDto createManager(ManagerDto manager);
	ManagerDto updateManager(ManagerDto manager, Integer managerId);
	ManagerDto getManager(Integer managerId);
	List<ManagerDto> getAllManagers();
	void deleteManager(Integer managerId);
}
