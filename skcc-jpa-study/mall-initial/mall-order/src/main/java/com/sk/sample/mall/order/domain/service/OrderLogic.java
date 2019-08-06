package com.sk.sample.mall.order.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.sample.mall.order.application.proxy.feign.AccountProxy;
import com.sk.sample.mall.order.application.proxy.feign.ProductProxy;
import com.sk.sample.mall.order.application.proxy.feign.dto.account.Account;
import com.sk.sample.mall.order.application.proxy.feign.dto.product.Product;
import com.sk.sample.mall.order.domain.model.Order;
import com.sk.sample.mall.order.domain.repository.OrderRepository;

@Service("orderLogic")
public class OrderLogic implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductProxy productProxy;
	
	@Autowired
	private AccountProxy accountProxy;
	
	@Override
	public void purchase(Long orderId) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findOne(orderId);
		
		if (order == null) {
			System.out.println("주문 정보가 없습니다>>>>>>");
			return;
		}
		
		if (order.getPurchased() == true) {
			System.out.println("이미 결재된 주문입니다>>>>>>>");
			return;
		}
		
		Product product = productProxy.findProduct(order.getProductId());
		System.out.println("PRODUCT >>>>>>>>" + product);
		
		order.setTotlaPrice(product.getPrice().getValue() * order.getProductCount());
		System.out.println("ORDER >>>>>>>>>>>" + order);
		
		if (order.getCreditCard() == null) {
			System.out.println("카드정보가 없습니다>>>>>>>>>>>");
			return;
		}
		
		Account account = accountProxy.findAccount(order.getBuyerAccountId());
			order.setShippingAddress(account.getAddress());
		
		if (order.getShippingAddress() == null) {
			System.out.println("배송지 정보가 없습니다>>>>>>>>>");
			return;
		}
		
		order.setPurchased(true);
		Order resultOrder = orderRepository.save(order);
		System.out.println("RESULT ORDER >>>>>>>" + resultOrder);
		
	}

}
