package fr.tao.productservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a product entity.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Product {
	
	/**
     * The unique identifier for the product.
     */
	@Id
	private String id;
	
	/**
     * The name of the product.
     */
	private String name;
	
	/**
     * The price of the product.
     */
	private Double price;
	
	 /**
     * The quantity of the product available.
     */
	private Integer quantity; 
	

}
