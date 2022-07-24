package com.log.delivery.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.log.delivery.domain.model.Delivery;
import com.log.delivery.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DeliveryCompletedService {

	private DeliveryRepository deliveryRepository;
	private FetchDeliveryService fetchDeliveryService;
	
	@Transactional
	public void finish(Long deliveryId) {
		Delivery delivery = fetchDeliveryService.fetch(deliveryId);
		
		delivery.finish();
		
		deliveryRepository.save(delivery);
	}
}
