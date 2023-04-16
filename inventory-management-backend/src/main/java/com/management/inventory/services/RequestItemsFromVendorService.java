package com.management.inventory.services;

import java.util.List;

import com.management.inventory.payloads.RequestItemsFromVendorDto;

public interface RequestItemsFromVendorService {
	
	RequestItemsFromVendorDto createRequest(RequestItemsFromVendorDto request);
	RequestItemsFromVendorDto updateRequest(RequestItemsFromVendorDto request, Integer requestId);
	RequestItemsFromVendorDto getRequest(Integer requestId);
	List<RequestItemsFromVendorDto> getAllRequests();
	void deleteRequest(Integer requestId);

}
