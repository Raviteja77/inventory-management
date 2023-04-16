package com.management.inventory.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.inventory.entities.Vendor;
import com.management.inventory.exceptions.ResourceNotFoundException;
import com.management.inventory.payloads.VendorDto;
import com.management.inventory.repositories.VendorRepository;
import com.management.inventory.services.VendorService;

@Service
public class VendorServiceImplementation implements VendorService {
	
	@Autowired
	private VendorRepository vendorRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public VendorDto createVendor(VendorDto vendorDto) {
		Vendor vendor = this.dtoToVendor(vendorDto);
		Vendor savedVendor = this.vendorRepository.save(vendor);
		return this.vendorToDto(savedVendor);
	}

	@Override
	public VendorDto updateVendor(VendorDto vendorDto, Integer vendorId) {
		Vendor vendor = this.vendorRepository.findById(vendorId)
				.orElseThrow(() -> new ResourceNotFoundException("Vendor", "Id", vendorId));
		
		vendor.setVendorId(vendorDto.getVendorId());
		vendor.setFirstName(vendorDto.getFirstName());
		vendor.setLastName(vendorDto.getLastName());
		vendor.setEmail(vendorDto.getEmail());
		vendor.setMobile(vendorDto.getMobile());
		
		Vendor updatedVendor = this.vendorRepository.save(vendor);
		VendorDto updatedVendorDto = this.vendorToDto(updatedVendor);
				
		return updatedVendorDto;
	}

	@Override
	public VendorDto getVendor(Integer vendorId) {
		Vendor vendor = this.vendorRepository.findById(vendorId)
				.orElseThrow(() -> new ResourceNotFoundException("Vendor", "Id", vendorId));
		
		return this.vendorToDto(vendor);
	}

	@Override
	public List<VendorDto> getAllVendors() {
		List<Vendor> vendors = this.vendorRepository.findAll();
		
		List<VendorDto> vendorDtos = vendors.stream().map(vendor -> this.vendorToDto(vendor)).collect(Collectors.toList());
		return vendorDtos;
	}

	@Override
	public void deleteVendor(Integer vendorId) {
		Vendor vendor = this.vendorRepository.findById(vendorId)
				.orElseThrow(() -> new ResourceNotFoundException("Vendor", "Id", vendorId));
		
		vendor.setStatus(false);
		this.vendorRepository.save(vendor);

	}
	
	private Vendor dtoToVendor(VendorDto vendorDto) {
		Vendor vendor = this.modelMapper.map(vendorDto, Vendor.class);
		return vendor;
	}
	
	private VendorDto vendorToDto(Vendor vendor) {
		VendorDto vendorDto = this.modelMapper.map(vendor, VendorDto.class);
		return vendorDto;
	}

}
