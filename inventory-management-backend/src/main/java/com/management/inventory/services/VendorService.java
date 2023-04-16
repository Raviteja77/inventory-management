package com.management.inventory.services;

import java.util.List;

import com.management.inventory.payloads.VendorDto;

public interface VendorService {
	
	VendorDto createVendor(VendorDto vendor);
	VendorDto updateVendor(VendorDto vendor, Integer vendorId);
	VendorDto getVendor(Integer vendorId);
	List<VendorDto> getAllVendors();
	void deleteVendor(Integer vendorId);

}
