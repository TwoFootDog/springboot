package com.sk.sample.mall.product.domain.model;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
public class ProductDescription {
	@Enumerated
	private ColorType colorType;
	
	@Enumerated(EnumType.STRING)
	private SizeType sizeType;
	
	public ProductDescription(ColorType colorType, SizeType sizeType) {
		this.colorType = colorType;
		this.sizeType = sizeType;
	}
	
	public ProductDescription(ColorType colorType) {
		this.colorType = colorType;
	}
	
	public ProductDescription(SizeType sizeType) {
		this.sizeType = sizeType;
	}
}
