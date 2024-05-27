package fr.tao.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a product is not found.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
	
	/**
     * Constructs a new ProductNotFoundException with the specified ID.
     * @param id The ID of the product that was not found.
     */
	public ProductNotFoundException(String id) {
		super("Product not found with ID : " + id);
	}

}
