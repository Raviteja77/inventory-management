package com.management.inventory.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="item")
@NoArgsConstructor
@Getter
@Setter
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int itemId;
	
	private String name;
	
	private int vendorId;
	
	private String description;
	
	private double purchasePrice;
	
	private double salesPrice;
	
	private int itemThreshold;
	
	private String expirationDate;
	
	private int quantity;
	
	private int soldQuantity;
	
	private boolean status;


}
