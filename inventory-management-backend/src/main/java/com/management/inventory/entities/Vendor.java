package com.management.inventory.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="vendor")
@NoArgsConstructor
@Getter
@Setter
public class Vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int vendorId;
	
	@Column(name = "firstName", nullable = false, length = 20)
	private String firstName;
	
	@Column(name = "lastName", nullable = false, length = 20)
	private String lastName;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "mobile", nullable = false, unique = true)
	private String mobile;
	
	private boolean status;
}
