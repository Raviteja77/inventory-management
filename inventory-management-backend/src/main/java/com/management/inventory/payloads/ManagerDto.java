package com.management.inventory.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ManagerDto {
	
	private int managerId;
	
	@NotEmpty(message = "Please enter first name")
	@Size(min = 3, max = 20, message = "First name must be in between 3 and 20 characters long")
	private String firstName;
	
	@NotEmpty(message = "Please enter last name")
	@Size(min = 3, max = 20, message = "Last name must be in between 3 and 20 characters long")
	private String lastName;
	
	@Email
	private String email;

	@NotEmpty(message = "Please enter password")
	@Size(min = 6, max = 15, message = "Password must be in between 6 and 15 characters long")
	private String password;
	
	private boolean status;

}
