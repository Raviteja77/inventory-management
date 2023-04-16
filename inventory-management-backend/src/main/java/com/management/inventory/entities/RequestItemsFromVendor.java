package com.management.inventory.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "requestItemsFromVendor")
@NoArgsConstructor
@Getter
@Setter
public class RequestItemsFromVendor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int requestId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long batchNumber;
    
    private String dateOfRequest;

    private String vendorId;

    @ManyToMany
    @JoinTable(
        name = "request_item",
        joinColumns = @JoinColumn(name = "request_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items = new ArrayList<>();
    
    private boolean status;
}
