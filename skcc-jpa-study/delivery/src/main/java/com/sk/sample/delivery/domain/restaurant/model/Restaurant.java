package com.sk.sample.delivery.domain.restaurant.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.sk.sample.delivery.domain.base.AbstractEntity;
import com.sk.sample.delivery.domain.base.AggregateRoot;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
public class Restaurant extends AbstractEntity implements AggregateRoot {
	private String name;
	private Float starAverage;
	
	private RestaurantDetail restaurantDetail;
	
	private FoodCategory foodCategory;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Menu> menus;
	
	public Restaurant(String name, Float starAverage, FoodCategory foodCategory) {
		this.name = name;
		this.starAverage = starAverage;
		this.foodCategory = foodCategory;
	}

}
