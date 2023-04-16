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
import com.management.inventory.payloads.ItemDto;
import com.management.inventory.services.ItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@PostMapping("/add")
	public ResponseEntity<ItemDto> createitem(@Valid @RequestBody ItemDto itemDto) {
		ItemDto createdItemDto = this.itemService.createItem(itemDto);
		return new ResponseEntity<>(createdItemDto, HttpStatus.CREATED);
	}

	@PutMapping("/{itemId}")
	public ResponseEntity<ItemDto> updateItem(@Valid @RequestBody ItemDto itemDto, @PathVariable("itemId") Integer id) {
		ItemDto updatedItem = this.itemService.updateItem(itemDto, id);
		return ResponseEntity.ok(updatedItem);
	}

	@PostMapping("/{itemId}")
	public ResponseEntity<ApiResponse> deleteitem(@PathVariable("itemId") Integer id) {
		this.itemService.deleteItem(id);
		return new ResponseEntity<>(new ApiResponse("item Deleted successfully", true), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<ItemDto>> getAllItems() {
		return ResponseEntity.ok(this.itemService.getAllItems());
	}

	@GetMapping("/{itemId}")
	public ResponseEntity<ItemDto> getitem(@PathVariable Integer itemId) {
		return ResponseEntity.ok(this.itemService.getItem(itemId));
	}

}
