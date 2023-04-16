package com.management.inventory.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ItemDto {
	
	private int itemId;
	
	@NotEmpty(message = "Please enter the item name")
	private String name;
	
	@NotEmpty(message = "Please enter the item description")
	private String description;
	
	private int vendorId;
	
	private double purchasePrice;
	
	private double salesPrice;
	
	private int itemThreshold;
	
	private String expirationDate;
	
	private int quantity;
	
	private int soldQuantity;
	
	private boolean status;

}
