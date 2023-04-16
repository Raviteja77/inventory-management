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
import com.management.inventory.payloads.VendorDto;
import com.management.inventory.repositories.VendorRepository;
import com.management.inventory.services.VendorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vendors")
@CrossOrigin(origins = "http://localhost:4200")
public class VendorController {
	
	@Autowired
	private VendorService vendorService;
	
	@Autowired
	private VendorRepository vendorRepository;
	
	//POST - Create vendor
	@PostMapping("/add")
	public ResponseEntity<?> createVendor(@Valid @RequestBody VendorDto vendorDto) {
	    if (this.vendorRepository.findByEmail(vendorDto.getEmail()).isEmpty()) {
	        VendorDto createdVendorDto = this.vendorService.createVendor(vendorDto);
	        return ResponseEntity.status(HttpStatus.CREATED).body(HttpStatus.CREATED.value());
	    }
	    return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(HttpStatus.ALREADY_REPORTED.value());
	}

	
	//PUT - Update vendor
	@PutMapping("/{vendorId}")
	public ResponseEntity<VendorDto> updateVendor(@Valid @RequestBody VendorDto vendorDto, @PathVariable("vendorId") Integer Id) {
		VendorDto updatedVendor = this.vendorService.updateVendor(vendorDto, Id);
		return ResponseEntity.ok(updatedVendor);
	}
	
	//DELETE - Delete vendor
	@PostMapping("/{vendorId}")
	public ResponseEntity<ApiResponse> deleteVendor(@PathVariable("vendorId") Integer id) {
		this.vendorService.deleteVendor(id);
		return new ResponseEntity<>(new ApiResponse("Vendor Deleted successfully", true), HttpStatus.OK);
	}
	
	//GET - Get all vendors
	@GetMapping("/allVendors")
	public ResponseEntity<List<VendorDto>> getAllVendors() {
		return ResponseEntity.ok(this.vendorService.getAllVendors());
	}
	
	@GetMapping("/{vendorId}")
	public ResponseEntity<VendorDto> getVendor(@PathVariable Integer vendorId) {
		return ResponseEntity.ok(this.vendorService.getVendor(vendorId));
	}

}
