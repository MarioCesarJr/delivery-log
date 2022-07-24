package com.log.delivery.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.log.delivery.domain.model.Customer;
import com.log.delivery.domain.model.Delivery;
import com.log.delivery.domain.model.OrderStatus;
import com.log.delivery.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RequestDeliveryService {

	private CustomerService customerService;
	private DeliveryRepository deliveryRepository;
	
	@Transactional
	public Delivery request(Delivery delivery) {
		Customer customer = customerService.fetch(delivery.getCustomer().getId());
		
		delivery.setCustomer(customer);
		delivery.setStatus(OrderStatus.PENDING);
		delivery.setOrderDate(OffsetDateTime.now());
		
		return deliveryRepository.save(delivery);
	}	
}
