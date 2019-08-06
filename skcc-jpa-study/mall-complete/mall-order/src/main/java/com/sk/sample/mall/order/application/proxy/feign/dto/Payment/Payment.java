package com.sk.sample.mall.order.application.proxy.feign.dto.Payment;

import java.util.Date;

import com.sk.sample.mall.order.domain.model.CreditCard;

import lombok.Data;

@Data
public class Payment  {
	private CreditCard creditCard;
	private Integer purchasedAmount;
	private Boolean successed;
	private Date purchasedDate;
	
	public Payment(CreditCard creditCard, Integer purchasedAmount) {
		this.creditCard = creditCard;
		this.purchasedAmount = purchasedAmount;
		this.successed = false;
		this.purchasedDate = new Date();
	}
}
