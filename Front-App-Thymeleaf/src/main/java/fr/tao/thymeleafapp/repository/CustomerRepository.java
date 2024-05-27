package fr.tao.thymeleafapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.tao.thymeleafapp.entity.Customer;

/**
 * Interface for accessing and managing Customer entities in the database.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
