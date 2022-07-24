package com.log.delivery.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.log.delivery.domain.model.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryModel {

	private Long id;
	private CustomerSimpleModel customer;
	private RecipientModel recipient;
	private BigDecimal fee;
	private OrderStatus status;
	private OffsetDateTime orderDate;
	private OffsetDateTime finishedDate;
	
}
