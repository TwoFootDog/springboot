package com.sk.sample.mall.order.application.proxy.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.FeignClientProperties.FeignClientConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.sk.sample.mall.order.application.proxy.feign.dto.Payment.Payment;

@Service
public class PaymentProxy {
    @Autowired
    private PaymentClient paymentClient;

    public Payment pay(Payment payment) {
        return paymentClient.pay(payment);
    }

    @FeignClient(name = "payments", url = "http://localhost:11005", configuration = FeignClientConfiguration.class)
    interface PaymentClient {
        @PostMapping("v1/payments")
        Payment pay(Payment payment);

    }
}

