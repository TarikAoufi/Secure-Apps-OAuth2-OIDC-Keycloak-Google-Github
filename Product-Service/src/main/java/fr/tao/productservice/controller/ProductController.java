package fr.tao.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.tao.productservice.entity.Product;
import fr.tao.productservice.exception.ProductNotFoundException;
import fr.tao.productservice.repository.ProductRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * REST controller for handling product-related HTTP requests.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
	
	/**
	 * Repository for managing product data.
	 */
	@NonNull
	private final ProductRepository productRepository;
	
	/**
	 * Retrieves all products.
	 * 
	 * @return List of all products.
	 */
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
	
	/**
	 * Retrieves a product by its ID.
	 * 
	 * @param id The ID of the product to retrieve.
	 * @return ResponseEntity containing the retrieved product.
	 * @throws ProductNotFoundException if the product with the given ID does not
	 *                                  exist.
	 */
	@GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return ResponseEntity.ok(product);
    }
	
	/**
	 * Creates a new product.
	 * 
	 * @param product The product to create.
	 * @return ResponseEntity containing the created product.
	 */
	@PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }
	
	/**
	 * Updates an existing product.
	 * 
	 * @param id      The ID of the product to update.
	 * @param product The updated product data.
	 * @return ResponseEntity containing the updated product.
	 * @throws ProductNotFoundException if the product with the given ID does not
	 *                                  exist.
	 */
	@PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        product.setId(id); // Ensure the correct ID is set for update
        Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }
	
	/**
	 * Deletes a product by its ID.
	 * 
	 * @param id The ID of the product to delete.
	 * @return ResponseEntity indicating successful deletion.
	 * @throws ProductNotFoundException if the product with the given ID does not
	 *                                  exist.
	 */
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
