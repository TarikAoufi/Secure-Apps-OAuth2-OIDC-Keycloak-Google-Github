package fr.tao.thymeleafapp.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Configuration class for defining security settings.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	/**
	 * Configures the security filter chain.
	 *
	 * @param http The HttpSecurity to configure.
	 * @return The configured SecurityFilterChain.
	 * @throws Exception If an error occurs while configuring the security.
	 */
    @Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.cors(Customizer.withDefaults())
				.csrf(Customizer.withDefaults())
				.authorizeHttpRequests(ar -> ar
						// Allow these URLs without authentication
						.requestMatchers("/", "/login", "/logout", "/webjars/**", "/h2-console/**").permitAll()
						// Require authentication for any other request
				        .anyRequest().authenticated())
				.headers(h -> h.frameOptions(fo -> fo.disable()))
				.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
				.oauth2Login(al -> 
					al.loginPage("/login")
					.defaultSuccessUrl("/")
				)
				.logout(logout -> logout
	                    .logoutSuccessUrl("/login")
	                    .deleteCookies("JSESSIONID") // Delete session cookie
	                    .clearAuthentication(true)
	                    .invalidateHttpSession(true)
	            )
				.exceptionHandling(eh -> eh.accessDeniedPage("/accessDenied"))
				.build();
	}
    
	/**
	 * Provides a GrantedAuthoritiesMapper to map authorities for a user. This
	 * mapper functionally processes the authorities retrieved from the
	 * authentication token, extracting necessary information and mapping them to a
	 * set of GrantedAuthority objects. It handles authorities from both OIDC
	 * (OpenID Connect) and OAuth2 protocols.
	 *
	 * @return A GrantedAuthoritiesMapper instance that maps authorities for a user.
	 */
	@Bean
	GrantedAuthoritiesMapper userAuthoritiesMapper() {
		return (authorities) -> {
			Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
			authorities.forEach(authority -> {
				if (authority instanceof OidcUserAuthority oidcUserAuth) {
					OidcIdToken idToken = oidcUserAuth.getIdToken();
					mappedAuthorities.addAll(mapAuthorities(idToken.getClaims()));
					System.out.println(oidcUserAuth.getAttributes());
				} else if (authority instanceof OAuth2UserAuthority oauth2UserAuth) {
					mappedAuthorities.addAll(mapAuthorities(oauth2UserAuth.getAttributes()));
				}
			});
			return mappedAuthorities;
		};
	}
	
	/**
     * Maps authorities from the user attributes.
     *
     * @param attributes The user attributes.
     * @return The list of mapped authorities.
     */
	@SuppressWarnings("unchecked")
	private List<SimpleGrantedAuthority> mapAuthorities(Map<String, Object> attributes) {
		Map<String, Object> realmAccess = (Map<String, Object>) attributes.getOrDefault("realm_access",
				Collections.emptyMap());
		List<String> roles = (List<String>) realmAccess.getOrDefault("roles", Collections.emptyList());
	    return roles.stream()
	            .map(SimpleGrantedAuthority::new)
	            .toList();
	}
	
	/**
     * Configures the CORS configuration source.
     *
     * @return The configured CorsConfigurationSource.
     */
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
