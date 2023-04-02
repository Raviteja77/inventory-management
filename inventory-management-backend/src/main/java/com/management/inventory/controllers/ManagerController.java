package com.management.inventory.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.inventory.payloads.ApiResponse;
import com.management.inventory.payloads.ManagerDto;
import com.management.inventory.services.ManagerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/managers")
@CrossOrigin(origins = "http://localhost:4200")
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;
	
	//POST - Create manager
	@PostMapping("/")
	public ResponseEntity<ManagerDto> createManager(@Valid @RequestBody ManagerDto managerDto) {
		ManagerDto createdManagerDto = this.managerService.createManager(managerDto);
		return new ResponseEntity<>(createdManagerDto, HttpStatus.CREATED);
	}
	
	//PUT - Update manager
	@PutMapping("/{managerId}")
	public ResponseEntity<ManagerDto> updateManager(@Valid @RequestBody ManagerDto managerDto, @PathVariable("managerId") Integer Id) {
		ManagerDto updatedManager = this.managerService.updateManager(managerDto, Id);
		return ResponseEntity.ok(updatedManager);
	}
	
	//DELETE - Delete manager
	@DeleteMapping("/{managerId}")
	public ResponseEntity<ApiResponse> deleteManager(@PathVariable("managerId") Integer id) {
		this.managerService.deleteManager(id);
		return new ResponseEntity<>(new ApiResponse("Manager Deleted successfully", true), HttpStatus.OK);
	}
	
	//GET - Get manager
	@GetMapping("/allManagers")
	public ResponseEntity<List<ManagerDto>> getAllManagers() {
		return ResponseEntity.ok(this.managerService.getAllManagers());
	}
	
	@GetMapping("/{managerId}")
	public ResponseEntity<ManagerDto> getManager(@PathVariable Integer managerId) {
		return ResponseEntity.ok(this.managerService.getManager(managerId));
	}

}
