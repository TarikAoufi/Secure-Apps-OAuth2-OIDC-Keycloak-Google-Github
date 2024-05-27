package fr.tao.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.tao.customerservice.entity.Customer;

/**
 * Repository interface for managing customer entities.
 * Extends JpaRepository to inherit basic CRUD operations.
 * 
 * @param <Customer> the type of entity managed by this repository
 * @param <Long> the type of the entity's identifier
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
