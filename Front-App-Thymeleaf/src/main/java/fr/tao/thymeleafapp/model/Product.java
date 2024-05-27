package fr.tao.thymeleafapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing a product.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Product {
	
	/**
     * The unique identifier for the product.
     */
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
     * The quantity of the product.
     */
	private Integer quantity; 
	

}
