package com.log.delivery.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.log.delivery.api.assembler.OccurrenceAssembler;
import com.log.delivery.api.model.OccurrenceModel;
import com.log.delivery.api.model.input.OccurrenceInput;
import com.log.delivery.domain.model.Delivery;
import com.log.delivery.domain.model.Occurrence;
import com.log.delivery.domain.service.FetchDeliveryService;
import com.log.delivery.domain.service.RegisterOccurrenceService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/deliveries/{deliveryId}/occurrences")
public class OccurrenceController {

	private FetchDeliveryService fetchDeliveryService;
	private RegisterOccurrenceService registerOccurrenceService;
	private OccurrenceAssembler occurrenceAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OccurrenceModel register(@PathVariable Long deliveryId,
			@Valid @RequestBody OccurrenceInput occurrenceInput) {
		
		Occurrence registerOccurrence = registerOccurrenceService
				.register(deliveryId, occurrenceInput.getDescription());
		
		return occurrenceAssembler.toModel(registerOccurrence);
		
	}
	
	@GetMapping
	public List<OccurrenceModel> list(@PathVariable Long deliveryId) {
		Delivery delivery = fetchDeliveryService.fetch(deliveryId);
		
		return occurrenceAssembler.toCollectionModel(delivery.getOccurrences());
	}
	
}

