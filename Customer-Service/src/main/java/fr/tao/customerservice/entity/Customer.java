package fr.tao.customerservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a customer entity.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Customer {
	/**
     * Unique identifier for the customer.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
     * The name of the customer.
     */
	private String name;
	
	/**
     * The email address of the customer.
     */
	private String email;

}