package com.sk.sample.delivery.domain.restaurant.model;

import java.util.Date;

import javax.persistence.Embeddable;

import com.sk.sample.delivery.domain.base.ValueObject;

import lombok.Data;

@Data
@Embeddable
public class RestaurantDetail implements ValueObject {
	private String information;
	private String guidance;
	private Date openTime;
	private Date closeTime;
	private String location;

}
