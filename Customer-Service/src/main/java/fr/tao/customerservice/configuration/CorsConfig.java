package fr.tao.customerservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuration class for Cross-Origin Resource Sharing (CORS) configuration.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
@Configuration
public class CorsConfig {
    
	/**
     * Creates and configures a CORS filter bean.
     * 
     * @return CorsFilter bean configured with CORS settings.
     */
    @Bean
    CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    
}