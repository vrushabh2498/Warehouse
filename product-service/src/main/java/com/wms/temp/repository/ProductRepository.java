package com.wms.temp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.temp.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	public Optional<Product> findBySku(String sku);

}
