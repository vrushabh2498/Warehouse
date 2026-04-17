package com.wms.temp.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wms.temp.dto.ProductRequestDto;
import com.wms.temp.dto.ProductResponseDto;
import com.wms.temp.entity.Product;
import com.wms.temp.exception.ProductAlreadyExits;
import com.wms.temp.mapper.ProductMapper;
import com.wms.temp.repository.ProductRepository;
import com.wms.temp.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository=productRepository;
	}

	@Override
	public ProductResponseDto createProduct(ProductRequestDto product) {
		Optional<Product> getproduct=productRepository.findBySku(product.getSku());
		if(getproduct.isPresent()) {
		 throw new ProductAlreadyExits("product with this sku already exists");
			
		}
		 Product entity = ProductMapper.toEntity(product);
	        Product saved = productRepository.save(entity);

	        return ProductMapper.toDTO(saved);
		
	}

	@Override
	public Product getProductById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> listgetAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product deleteProduct(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
