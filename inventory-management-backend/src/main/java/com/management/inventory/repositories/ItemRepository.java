package com.management.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.inventory.entities.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
