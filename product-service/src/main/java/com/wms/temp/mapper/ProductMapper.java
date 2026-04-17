package com.wms.temp.mapper;

import com.wms.temp.dto.ProductRequestDto;
import com.wms.temp.dto.ProductResponseDto;
import com.wms.temp.entity.Product;

public class ProductMapper {
	
	public static Product toEntity(ProductRequestDto dto) {
        return Product.builder()
                .name(dto.getName())
                .sku(dto.getSku())
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .warehouseId(dto.getWarehouseId())
                .build();
    }
	
	 public static ProductResponseDto toDTO(Product entity) {
	        return ProductResponseDto.builder()
	                .id(entity.getId())
	                .name(entity.getName())
	                .sku(entity.getSku())
	                .quantity(entity.getQuantity())
	                .price(entity.getPrice())
	                .warehouseId(entity.getWarehouseId())
	                .createdAt(entity.getCreatedAt())
	                .updatedAt(entity.getUpdatedAt())
	                .build();
}
}
