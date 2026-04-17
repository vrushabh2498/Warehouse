package com.wms.temp.service;

import java.util.List;

import com.wms.temp.dto.ProductRequestDto;
import com.wms.temp.dto.ProductResponseDto;
import com.wms.temp.entity.Product;

public interface ProductService {

	ProductResponseDto createProduct(ProductRequestDto product);
	
	Product getProductById(Long id);
	
	List<Product> listgetAllProducts();
	
	Product deleteProduct(Long id);

}
