package fr.tao.thymeleafapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a customer.
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
     * The unique identifier for the customer.
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
