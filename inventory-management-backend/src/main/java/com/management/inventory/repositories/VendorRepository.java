package com.management.inventory.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.inventory.entities.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
	
	Optional<Vendor> findByEmail(String email);

}
