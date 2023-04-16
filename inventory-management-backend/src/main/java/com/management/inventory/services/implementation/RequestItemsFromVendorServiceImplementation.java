package com.management.inventory.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.inventory.entities.RequestItemsFromVendor;
import com.management.inventory.exceptions.ResourceNotFoundException;
import com.management.inventory.payloads.RequestItemsFromVendorDto;
import com.management.inventory.repositories.RequestItemsFromVendorRepository;
import com.management.inventory.services.RequestItemsFromVendorService;

@Service
public class RequestItemsFromVendorServiceImplementation implements RequestItemsFromVendorService {
	
	@Autowired
	private RequestItemsFromVendorRepository requestItemsRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public RequestItemsFromVendorDto createRequest(RequestItemsFromVendorDto requestItemsFromVendorDto) {
		RequestItemsFromVendor requestItems = this.dtoToRequestItemsFromVendor(requestItemsFromVendorDto);
		RequestItemsFromVendor savedRequestItemsFromVendor = this.requestItemsRepository.save(requestItems);
		return this.RequestItemsFromVendorToDto(savedRequestItemsFromVendor);
	}

	@Override
	public RequestItemsFromVendorDto updateRequest(RequestItemsFromVendorDto requestItemsFromVendorDto, Integer requestId) {
		RequestItemsFromVendor request = this.requestItemsRepository.findById(requestId)
				.orElseThrow(() -> new ResourceNotFoundException("RequestItemsFromVendor", "Id", requestId));
		
		request.setRequestId(requestItemsFromVendorDto.getRequestId());
		request.setBatchNumber(requestItemsFromVendorDto.getBatchNumber());
		request.setDateOfRequest(requestItemsFromVendorDto.getDateOfRequest());
		request.setItems(requestItemsFromVendorDto.getItems());
		request.setVendorId(requestItemsFromVendorDto.getVendorId());
		
		RequestItemsFromVendor updatedRequestItemsFromVendor = this.requestItemsRepository.save(request);
		RequestItemsFromVendorDto updatedRequestItemsFromVendorDto = this.RequestItemsFromVendorToDto(updatedRequestItemsFromVendor);
				
		return updatedRequestItemsFromVendorDto;
	}

	@Override
	public RequestItemsFromVendorDto getRequest(Integer requestId) {
		RequestItemsFromVendor request = this.requestItemsRepository.findById(requestId)
				.orElseThrow(() -> new ResourceNotFoundException("RequestItemsFromVendor", "Id", requestId));
		
		return this.RequestItemsFromVendorToDto(request);
	}

	@Override
	public List<RequestItemsFromVendorDto> getAllRequests() {
		List<RequestItemsFromVendor> requests = this.requestItemsRepository.findAll();
		
		List<RequestItemsFromVendorDto> requestDtos = requests.stream().map(item -> this.RequestItemsFromVendorToDto(item)).collect(Collectors.toList());
		return requestDtos;
	}

	@Override
	public void deleteRequest(Integer requestId) {
		RequestItemsFromVendor request = this.requestItemsRepository.findById(requestId)
				.orElseThrow(() -> new ResourceNotFoundException("RequestItemsFromVendor", "Id", requestId));
		
		request.setStatus(false);
		this.requestItemsRepository.save(request);

	}
	
	private RequestItemsFromVendor dtoToRequestItemsFromVendor(RequestItemsFromVendorDto requestItemsDto) {
		RequestItemsFromVendor request = this.modelMapper.map(requestItemsDto, RequestItemsFromVendor.class);
		return request;
	}
	
	private RequestItemsFromVendorDto RequestItemsFromVendorToDto(RequestItemsFromVendor requestItems) {
		RequestItemsFromVendorDto requestDto = this.modelMapper.map(requestItems, RequestItemsFromVendorDto.class);
		return requestDto;
	}

}
