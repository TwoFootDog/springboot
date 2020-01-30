package com.sk.sample.mall.order;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.sk.sample.mall.order.domain.model.CreditCard;
import com.sk.sample.mall.order.domain.model.Order;
import com.sk.sample.mall.order.domain.repository.OrderRepository;
import com.sk.sample.mall.order.domain.service.OrderLogic;
import com.sk.sample.mall.shared.domain.Address;

@SpringBootApplication
@EnableFeignClients
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
    public CommandLineRunner test(OrderRepository orderRepository, OrderLogic orderLogic) {
        return (args) -> {
            Order order = new Order(1L, 3L, 2);
            orderRepository.save(order);
            order.setCreditCard(new CreditCard("1234", "08/01"));
//			order.setShippingAddress(new Address(12345, "경기도 성남시"));
            orderRepository.save(order);

            orderLogic.purchase(1L);
//			insertProduct(productRepository);
//			
//			executeExample1(productRepository);
//			executeExample2(productRepository);
//			executeExample3(productRepository);
//			executeExample4(productRepository);
//			executeExample5(productRepository);
//			executeExample6(productRepository);
//			executeExample7(productRepository);
        };
    }
}
