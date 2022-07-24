package com.log.delivery.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.log.delivery.domain.exception.DomainExceptionHandler;
import com.log.delivery.domain.model.Customer;
import com.log.delivery.domain.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomerService {

	private CustomerRepository customerRepository;
	
	public Customer fetch(Long customerId) {
		return customerRepository.findById(customerId)
				.orElseThrow(() -> new DomainExceptionHandler("Customer not found"));
	}
	
	@Transactional
	public Customer save(Customer customer) {
		boolean hasEmail = customerRepository.findByEmail(customer.getEmail())
				.stream()
				.anyMatch(existCustomer -> !existCustomer.equals(customer));
		
		if (hasEmail) {
			throw new DomainExceptionHandler("There is already a customer registered with this email.");
		}
		
		return customerRepository.save(customer);
	}
	
	@Transactional
	public void delete(Long customerId) {
		customerRepository.deleteById(customerId);
	}
	
}

