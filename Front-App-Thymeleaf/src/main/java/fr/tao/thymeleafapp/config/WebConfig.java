package fr.tao.thymeleafapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

/**
 * Configuration class for Spring MVC settings.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
@Configuration
public class WebConfig {
	
	/**
     * Creates and configures a {@link HiddenHttpMethodFilter} bean.
     * This filter allows Spring MVC to handle HTTP methods such as PUT, PATCH, and DELETE
     * by using a hidden form field '_method' in a POST request.
     *
     * @return The configured {@link HiddenHttpMethodFilter} bean.
     */
    @Bean
    HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}