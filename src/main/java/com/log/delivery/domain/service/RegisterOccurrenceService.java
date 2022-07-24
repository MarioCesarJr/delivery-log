package com.log.delivery.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.log.delivery.domain.model.Delivery;
import com.log.delivery.domain.model.Occurrence;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegisterOccurrenceService {

	private FetchDeliveryService fetchDeliveryService;
	
	@Transactional
	public Occurrence register(Long deliveryId, String description) {
		Delivery delivery = fetchDeliveryService.fetch(deliveryId);
		
		return delivery.addOccurrence(description);
	}
}
