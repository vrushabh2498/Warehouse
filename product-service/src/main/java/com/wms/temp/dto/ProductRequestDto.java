package com.wms.temp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ProductRequestDto {
	@NotBlank
	private String name;

	@NotBlank
	private String sku;

	@NotNull
	@Min(0)
	private Integer quantity;

	@NotNull
	private Double price;

	@NotNull
	private Long warehouseId;

}
