package com.log.delivery.domain.service;

import org.springframework.stereotype.Service;

import com.log.delivery.domain.exception.EntityNotFoundException;
import com.log.delivery.domain.model.Delivery;
import com.log.delivery.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FetchDeliveryService {

	private DeliveryRepository deliveryRepository;
	
	public Delivery fetch(Long deliveryId) {
		return deliveryRepository.findById(deliveryId)
				.orElseThrow(() -> new EntityNotFoundException("Delivery not found."));
	}
}
