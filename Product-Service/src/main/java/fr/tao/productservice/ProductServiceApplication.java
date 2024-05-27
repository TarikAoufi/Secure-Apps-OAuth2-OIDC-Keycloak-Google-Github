package fr.tao.productservice;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import fr.tao.productservice.entity.Product;
import fr.tao.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * Main application class for Product Service.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
@Slf4j
@SpringBootApplication
@EnableWebSecurity
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}
	
	/**
     * Bean to initialize data in the product repository.
     * @param productRepository The product repository to initialize.
     * @return CommandLineRunner instance for initializing data.
     */
	@Bean
	CommandLineRunner initializeData(ProductRepository productRepository) {
		log.info("########### Create products #############");
		Supplier<String> uuidSupplier = () -> UUID.randomUUID().toString();
		return args -> List.of(
				new Product(uuidSupplier.get(), "Bike", 100.99, 100), 
				new Product(uuidSupplier.get(), "Watch", 20.49, 50),
				new Product(uuidSupplier.get(), "Coat", 5.99, 200),
				new Product(uuidSupplier.get(), "TV", 10.0, 400),
				new Product(uuidSupplier.get(), "Phone", 20.0, 200),
				new Product(uuidSupplier.get(), "PC", 30.0, 300)
			).forEach(productRepository::save);
	};

}
