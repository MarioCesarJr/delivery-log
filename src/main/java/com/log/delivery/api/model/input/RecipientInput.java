package com.log.delivery.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RecipientInput {

	@NotBlank
	private String name;
	
	@NotBlank
	private String street;
	
	@NotBlank
	private String number;
	
	@NotBlank
	private String district;
	
	@NotBlank
	private String note;
}
