package fr.tao.thymeleafapp.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.tao.thymeleafapp.entity.Customer;
import fr.tao.thymeleafapp.repository.CustomerRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

/**
 * Controller class responsible for handling customer-related HTTP requests.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
@Controller
@RequiredArgsConstructor
public class CustomerController {
	
	/**
     * Repository for managing customer data.
     */
	@Nonnull
	private final CustomerRepository customerRepository;
	
	/**
     * Retrieves all customers and renders them on the view.
     *
     * @param model The model to add customer data to.
     * @return The view name for displaying all customers.
     */
	@GetMapping("/customers")
	public String getAllCustomer(Model model) {
		List<Customer> customerList = customerRepository.findAll();
		model.addAttribute("customers", customerList);
		return "customers";
	}
	
	/**
     * Renders the form for editing a specific customer.
     *
     * @param id    The ID of the customer to edit.
     * @param model The model to add customer data to.
     * @return The view name for editing a customer.
     */
	@GetMapping("/customers/{id}/edit")
	public String showEditCustomerForm(@PathVariable Long id, Model model) {
	    Customer customer = customerRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));
	    model.addAttribute("customer", customer);
	    model.addAttribute("formTitle", "Edit Customer");
	    model.addAttribute("submitButtonLabel", "Update Customer");
	    model.addAttribute("action", "edit");
	    model.addAttribute("actionUrl", "/customers/" + id + "/edit");
	    return "customer-form";
	}
	
	/**
     * Updates an existing customer's information.
     *
     * @param id       The ID of the customer to update.
     * @param customer The updated customer data.
     * @return The redirect URL after updating the customer.
     */
	@PostMapping("/customers/{id}/edit")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer customer) {
	    Customer existingCustomer = customerRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));
	    existingCustomer.setName(customer.getName());
	    existingCustomer.setEmail(customer.getEmail());
	    customerRepository.save(existingCustomer);
	    return "redirect:/customers";
	}
	
	/**
	 * Renders the form for adding a new customer.
	 *
	 * @param model The model to add form attributes to.
	 * @return The view name for adding a new customer.
	 */
	@GetMapping("/customers/add")
	public String showAddCustomerForm(Model model) {
	    model.addAttribute("customer", new Customer());
	    model.addAttribute("formTitle", "Add Customer");
	    model.addAttribute("submitButtonLabel", "Add Customer");
	    model.addAttribute("action", "add");
	    model.addAttribute("actionUrl", "/customers/add");
	    return "customer-form";
	}

	/**
	 * Handles the submission of adding a new customer.
	 *
	 * @param customer The customer object containing data to add.
	 * @return The redirect URL after adding the customer.
	 */
	@PostMapping("/customers/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addCustomer(@ModelAttribute Customer customer) {
	    customerRepository.save(customer);
	    return "redirect:/customers";
	}
	
	/**
	 * Deletes a customer with the specified ID.
	 *
	 * @param id The ID of the customer to delete.
	 * @return The redirect URL after deleting the customer.
	 */
	@DeleteMapping("/customers/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteCustomer(@PathVariable Long id) {
		customerRepository.deleteById(id);
		return "redirect:/customers";
	}

}
