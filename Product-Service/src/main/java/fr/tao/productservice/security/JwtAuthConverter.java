package fr.tao.productservice.security;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

/**
 * Converter implementation to convert a Jwt token into an
 * AbstractAuthenticationToken.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken>  {
	
	private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
	
	/**
     * Converts a Jwt token into an AbstractAuthenticationToken.
     *
     * @param jwt The Jwt token to convert.
     * @return An AbstractAuthenticationToken representing the Jwt token.
     */
	@Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream.concat(
        		jwtGrantedAuthoritiesConverter.convert(jwt).stream(), 
        		extractAuthorities(jwt).stream()
        ).collect(Collectors.toSet());
        return new JwtAuthenticationToken(jwt, authorities, jwt.getClaimAsString("preferred_username"));
    }
	
	/**
     * Extracts authorities from the Jwt token.
     *
     * @param jwt The Jwt token.
     * @return A collection of GrantedAuthority extracted from the Jwt token.
     */
    @SuppressWarnings("unchecked")
	private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");        
        if (realmAccess != null && realmAccess.containsKey("roles")) {
            Collection<String> roles = (Collection<String>) realmAccess.get("roles");
            return roles.stream()
                    .map(role -> new SimpleGrantedAuthority(role))
                    .collect(Collectors.toSet());
        }
        return Set.of();
    }

}
