package com.management.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.inventory.entities.RequestItemsFromVendor;

public interface RequestItemsFromVendorRepository extends JpaRepository<RequestItemsFromVendor, Integer> {

}
