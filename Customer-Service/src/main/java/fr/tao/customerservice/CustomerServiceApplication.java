package fr.tao.customerservice;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fr.tao.customerservice.entity.Customer;
import fr.tao.customerservice.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * The main application class for the Customer Service application.
 * This class initializes and runs the Spring Boot application.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
@Slf4j
@SpringBootApplication
public class CustomerServiceApplication {
	
	/**
     * The main method to start the Customer Service application.
     *
     * @param args The command-line arguments passed to the application.
     */
	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	
	/**
     * Configures a CommandLineRunner bean to initialize customer data.
     * 
     * @param customerRepository The repository for managing customer data.
     * @return A CommandLineRunner bean to execute initialization logic.
     */
 	@Bean
	CommandLineRunner start(CustomerRepository customerRepository) {
		return args -> {
			log.info("########### Create customers #############");
			Stream.of("Mohamed", "Zineb", "Ali").forEach(name -> {
				customerRepository.save(
						Customer.builder()
						.name(name)
						.email(name.toLowerCase() + "@gmail.com")
						.build()
				);
			});
		};
	}

}
