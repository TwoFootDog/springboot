package com.sk.sample.mall.order.domain.model;

import javax.persistence.Entity;

import com.sk.sample.mall.shared.base.AbstractEntity;
import com.sk.sample.mall.shared.base.AggregateRoot;
import com.sk.sample.mall.shared.domain.Address;

import lombok.Data;

@Data
@Entity(name = "ordering")
public class Order extends AbstractEntity implements AggregateRoot {
	private Long buyerAccountId;
	private Long productId;
	
	private Integer productCount;
	private Integer totlaPrice;
	private Boolean purchased = false;
	
	private CreditCard creditCard;
	private Address shippingAddress;
	
	public Order(Long buyerAccountId, Long productId, Integer productCount) {
		this.buyerAccountId = buyerAccountId;
		this.productId = productId;
		this.productCount = productCount;
	}
	
	
	
}
