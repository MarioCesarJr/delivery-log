package com.log.delivery.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.log.delivery.domain.model.Customer;
import com.log.delivery.domain.repository.CustomerRepository;
import com.log.delivery.domain.service.CustomerService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerRepository customerRepository;
	private CustomerService customerService;

	@GetMapping
	public List<Customer> list() {
		return customerRepository.findAll();
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> fetch(@PathVariable Long customerId) {
		return customerRepository.findById(customerId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Customer add(@Valid @RequestBody Customer customer) {
		return customerService.save(customer);
	}
	
	@PutMapping("/{customerId}")
	public ResponseEntity<Customer> update(@PathVariable Long customerId, 
			@Valid @RequestBody Customer customer) {
		if (!customerRepository.existsById(customerId)) {
			return ResponseEntity.notFound().build();
		}
		
		customer.setId(customerId);
		customer = customerService.save(customer);
		
		return ResponseEntity.ok(customer);
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<Void> remove(@PathVariable Long customerId) {
		if (!customerRepository.existsById(customerId)) {
			return ResponseEntity.notFound().build();
		}
		
		customerService.delete(customerId);
		
		return ResponseEntity.noContent().build();
	}
	
}

