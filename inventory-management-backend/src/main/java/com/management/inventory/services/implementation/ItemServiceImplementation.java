package com.management.inventory.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.inventory.entities.Item;
import com.management.inventory.exceptions.ResourceNotFoundException;
import com.management.inventory.payloads.ItemDto;
import com.management.inventory.repositories.ItemRepository;
import com.management.inventory.services.ItemService;

@Service
public class ItemServiceImplementation implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ItemDto createItem(ItemDto ItemDto) {
		Item Item = this.dtoToItem(ItemDto);
		Item savedItem = this.itemRepository.save(Item);
		return this.ItemToDto(savedItem);
	}

	@Override
	public ItemDto updateItem(ItemDto itemDto, Integer itemId) {
		Item item = this.itemRepository.findById(itemId)
				.orElseThrow(() -> new ResourceNotFoundException("Item", "Id", itemId));
		
		item.setItemId(itemDto.getItemId());
		item.setName(itemDto.getName());
		item.setVendorId(itemDto.getVendorId());
		item.setDescription(itemDto.getDescription());
		item.setPurchasePrice(itemDto.getPurchasePrice());
		item.setSalesPrice(itemDto.getSalesPrice());
		item.setItemThreshold(itemDto.getItemThreshold());
		item.setExpirationDate(itemDto.getExpirationDate());
		item.setQuantity(itemDto.getQuantity());
		item.setSoldQuantity(itemDto.getSoldQuantity());
		
		Item updatedItem = this.itemRepository.save(item);
		ItemDto updatedItemDto = this.ItemToDto(updatedItem);
				
		return updatedItemDto;
	}

	@Override
	public ItemDto getItem(Integer itemId) {
		Item item = this.itemRepository.findById(itemId)
				.orElseThrow(() -> new ResourceNotFoundException("Item", "Id", itemId));
		
		return this.ItemToDto(item);
	}

	@Override
	public List<ItemDto> getAllItems() {
		List<Item> items = this.itemRepository.findAll();
		
		List<ItemDto> itemDtos = items.stream().map(item -> this.ItemToDto(item)).collect(Collectors.toList());
		return itemDtos;
	}

	@Override
	public void deleteItem(Integer itemId) {
		Item item = this.itemRepository.findById(itemId)
				.orElseThrow(() -> new ResourceNotFoundException("Item", "Id", itemId));
		
		item.setStatus(false);
		this.itemRepository.save(item);

	}
	
	private Item dtoToItem(ItemDto itemDto) {
		Item item = this.modelMapper.map(itemDto, Item.class);
		return item;
	}
	
	private ItemDto ItemToDto(Item item) {
		ItemDto itemDto = this.modelMapper.map(item, ItemDto.class);
		return itemDto;
	}

}
