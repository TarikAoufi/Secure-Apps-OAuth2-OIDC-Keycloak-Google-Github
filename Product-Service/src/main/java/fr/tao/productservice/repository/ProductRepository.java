package fr.tao.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.tao.productservice.entity.Product;

/**
 * Repository interface for managing products in the database. Extends
 * JpaRepository for CRUD operations on Product entities with String as the type
 * of the primary key.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}
