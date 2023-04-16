package com.management.inventory.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.inventory.payloads.ApiResponse;
import com.management.inventory.payloads.RequestItemsFromVendorDto;
import com.management.inventory.services.RequestItemsFromVendorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin(origins = "http://localhost:4200")
public class RequestItemsFromVendorController {
	
	@Autowired
	private RequestItemsFromVendorService requestService;

	@PostMapping("/add")
	public ResponseEntity<RequestItemsFromVendorDto> createRequest(@Valid @RequestBody RequestItemsFromVendorDto requestDto) {
		System.out.println("test" + requestDto.getVendorId());
		RequestItemsFromVendorDto createdRequestDto = this.requestService.createRequest(requestDto);
		return new ResponseEntity<>(createdRequestDto, HttpStatus.CREATED);
	}

	@PutMapping("/{requestId}")
	public ResponseEntity<RequestItemsFromVendorDto> updateRequest(@Valid @RequestBody RequestItemsFromVendorDto itemDto, @PathVariable("requestId") Integer id) {
		RequestItemsFromVendorDto updatedRequest = this.requestService.updateRequest(itemDto, id);
		return ResponseEntity.ok(updatedRequest);
	}

	@PostMapping("/{requestId}")
	public ResponseEntity<ApiResponse> deleteitem(@PathVariable("requestId") Integer id) {
		this.requestService.deleteRequest(id);
		return new ResponseEntity<>(new ApiResponse("Request Deleted successfully", true), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<RequestItemsFromVendorDto>> getAllRequests() {
		return ResponseEntity.ok(this.requestService.getAllRequests());
	}

	@GetMapping("/{requestId}")
	public ResponseEntity<RequestItemsFromVendorDto> getitem(@PathVariable Integer requestId) {
		return ResponseEntity.ok(this.requestService.getRequest(requestId));
	}

}
