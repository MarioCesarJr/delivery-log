package com.log.delivery.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.log.delivery.api.assembler.DeliveryAssembler;
import com.log.delivery.api.model.DeliveryModel;
import com.log.delivery.api.model.input.DeliveryInput;
import com.log.delivery.domain.model.Delivery;
import com.log.delivery.domain.repository.DeliveryRepository;
import com.log.delivery.domain.service.DeliveryCompletedService;
import com.log.delivery.domain.service.RequestDeliveryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

	private DeliveryRepository deliveryRepository;
	private RequestDeliveryService requestDeliveryService;
	private DeliveryCompletedService deliveryCompletedService;
	private DeliveryAssembler deliveryAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DeliveryModel request(@Valid @RequestBody DeliveryInput deliveryInput) {
		Delivery newDelivery = deliveryAssembler.toEntity(deliveryInput);
		Delivery requestedDelivery = requestDeliveryService.request(newDelivery);
		
		return deliveryAssembler.toModel(requestedDelivery);
	}
	
	@PutMapping("/{deliveryId}/completed")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finish(@PathVariable Long deliveryId) {
		deliveryCompletedService.finish(deliveryId);
	}
	
	@GetMapping
	public List<DeliveryModel> list() {
		return deliveryAssembler.toCollectionModel(deliveryRepository.findAll());
	}
	
	@GetMapping("/{deliveryId}")
	public ResponseEntity<DeliveryModel> fetch(@PathVariable Long deliveryId) {
		return deliveryRepository.findById(deliveryId)
				.map(delivery -> ResponseEntity.ok(deliveryAssembler.toModel(delivery)))
				.orElse(ResponseEntity.notFound().build());
	}
	
}

