package com.log.delivery.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Recipient {

	@NotBlank
	@Column(name = "recipient_name")
	private String name;
	
	@NotBlank
	@Column(name = "recipient_street")
	private String street;
	
	@NotBlank
	@Column(name = "recipient_number")
	private String number;
	
	@NotBlank
	@Column(name = "recipient_district")
	private String district;
	
	@NotBlank
	@Column(name = "recipient_note")
	private String note;
}

