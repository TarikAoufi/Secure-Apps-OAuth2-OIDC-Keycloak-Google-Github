package fr.tao.productservice.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

/**
 * Configuration class for security settings.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
	
	/**
     * JwtAuthConverter instance for JWT authentication.
     */
	@Nonnull
	private final JwtAuthConverter jwtAuthConverter;
	
	/**
     * Configures the security filter chain.
     * 
     * @param http The HttpSecurity object to configure security.
     * @return SecurityFilterChain instance.
     * @throws Exception If an error occurs during configuration.
     */
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
        		.cors(Customizer.withDefaults())
        		.authorizeHttpRequests(ar -> ar.anyRequest().authenticated())
        		.oauth2ResourceServer(o2 -> o2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)))
        		.headers(h -> h.frameOptions(fo -> fo.disable()))
				.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
        		.build();
    }

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("*"));
	    configuration.setAllowedMethods(Arrays.asList("*"));
	    configuration.setAllowedHeaders(Arrays.asList("*"));
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
	
}
