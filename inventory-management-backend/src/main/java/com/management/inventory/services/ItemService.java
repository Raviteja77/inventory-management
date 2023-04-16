package com.management.inventory.services;

import java.util.List;

import com.management.inventory.payloads.ItemDto;

public interface ItemService {
	
	ItemDto createItem(ItemDto item);
	ItemDto updateItem(ItemDto item, Integer itemId);
	ItemDto getItem(Integer itemId);
	List<ItemDto> getAllItems();
	void deleteItem(Integer itemId);

}
