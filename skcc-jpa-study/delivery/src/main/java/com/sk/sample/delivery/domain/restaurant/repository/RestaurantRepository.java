package com.sk.sample.delivery.domain.restaurant.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sk.sample.delivery.domain.restaurant.model.Restaurant;

@RepositoryRestResource
public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, Long>, 
															  QueryDslPredicateExecutor<Restaurant> {

}
