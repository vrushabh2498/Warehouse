package com.wms.temp.dto;
import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ProductResponseDto {
	private Long id;
	private String name;
	private String sku;
	private Integer quantity;
	private double price;
	private Long warehouseId;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	
}
