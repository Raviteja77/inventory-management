package com.management.inventory.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.inventory.entities.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
	
	Optional<Manager> findByEmail(String email);

}
