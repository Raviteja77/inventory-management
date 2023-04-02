package com.management.inventory.payloads;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;
	
	private ManagerDto manager;
}