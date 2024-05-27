package fr.tao.thymeleafapp;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fr.tao.thymeleafapp.entity.Customer;
import fr.tao.thymeleafapp.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

/**
 * Main application class for the FrontAppThymeleaf application.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
@Slf4j
@SpringBootApplication
public class FrontAppThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontAppThymeleafApplication.class, args);
	}
	
	/**
	 * Bean declaration for LayoutDialect.
	 * @return LayoutDialect instance
	 */
	@Bean
	LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}

	/**
     * Bean declaration for CommandLineRunner to initialize customers.
     * @param customerRepository Repository for managing Customer entities
     * @return CommandLineRunner instance
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
