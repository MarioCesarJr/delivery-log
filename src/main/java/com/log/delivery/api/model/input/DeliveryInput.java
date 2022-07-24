package com.log.delivery.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryInput {

	@Valid
	@NotNull
	private CustomerIdInput customer;
	
	@Valid
	@NotNull
	private RecipientInput recipient;
	
	@NotNull
	private BigDecimal fee;
	
}
