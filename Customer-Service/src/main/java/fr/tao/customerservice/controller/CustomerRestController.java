package fr.tao.customerservice.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.tao.customerservice.entity.Customer;
import fr.tao.customerservice.repository.CustomerRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * REST controller for managing customer operations.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
@RestController
@RequestMapping(produces = "application/json", value = "/api/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerRestController {
	
	/**
     * The customer repository.
     */
	@Nonnull
	private final CustomerRepository customerRepository;
	
	/**
     * Retrieves all customers.
     *
     * @return List of all customers.
     */
	@GetMapping
    public List<Customer> getAllCustomers() {
		log.info("Invoking getCustomers - CustomerRestAPI");
        return customerRepository.findAll();
    }
	
	/**
     * Retrieves a customer by ID.
     *
     * @param id The ID of the customer to retrieve.
     * @return The customer with the specified ID.
     * @throws IllegalArgumentException if the customer ID is invalid.
     */
	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable Long id) {
		log.info("Calling getCustomerById - CustomerRestAPI");
		var customer = customerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));
		return customer;
	}
	
	/**
     * Adds a new customer.
     *
     * @param customer The customer to add.
     * @return The added customer.
     */
	@PostMapping("/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Customer addCustomer(@RequestBody Customer customer) {
		log.info("addCustomer - REST request: Saving new customer: {}", customer);
		var createdCustomer = customerRepository.save(customer);
		return createdCustomer;
	}
	
	/**
     * Updates an existing customer.
     *
     * @param customerId The ID of the customer to update.
     * @param customer   The updated customer data.
     * @return The updated customer.
     * @throws IllegalArgumentException if the customer ID is invalid.
     */
	@PutMapping("/{id}/edit")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Customer updateCustomer(@PathVariable("id") Long customerId, @RequestBody Customer customer) {
		log.info("updateCustomer - REST request: Updating customer with ID: {} - New data: {}", customerId, customer);
		Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));
		existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer= customerRepository.save(existingCustomer);
        return existingCustomer;
	}
	
	/**
     * Deletes a customer by ID.
     *
     * @param customerId The ID of the customer to delete.
     * @throws IllegalArgumentException if the customer ID is invalid.
     */
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteCustomer(@PathVariable("id") Long customerId) {
		log.info("deleteCustomer - REST request: Deleting customer with ID: {}", customerId);
		Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));
		customerRepository.delete(customer);
	}
	

}
