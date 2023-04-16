package com.management.inventory.payloads;

import java.util.List;

import com.management.inventory.entities.Item;
import com.management.inventory.entities.Vendor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RequestItemsFromVendorDto {
	
	private int requestId;

    private long batchNumber;
    
    private String dateOfRequest;

    private String vendorId;

    private List<Item> items;
    
    private boolean status;

}
