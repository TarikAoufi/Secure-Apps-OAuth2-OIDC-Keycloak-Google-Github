package fr.tao.thymeleafapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClient;

import fr.tao.thymeleafapp.model.Product;

/**
 * Controller class to handle product-related requests.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
@Controller
public class ProductController {

	/**
     * Base URL of the backend service.
     */
	@Value("${product.service.base.uri}")
    private String productServiceBaseUri;
    
	/**
	 * Retrieves a list of products from the backend service and populates the model
	 * with the retrieved products.
	 * 
	 * Requires authentication via OAuth 2.0 bearer token.
	 * 
	 * @param model the Spring MVC model to which the products list will be added
	 * @return the name of the view to render, typically "products", or redirects to
	 *         access denied page if an exception occurs
	 */
    @GetMapping("/products")
    public String products(Model model) {
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();
            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            DefaultOidcUser oidcUser = (DefaultOidcUser) oAuth2AuthenticationToken.getPrincipal();
            String jwtTokenValue = oidcUser.getIdToken().getTokenValue();
            RestClient restClient = RestClient.create(productServiceBaseUri);
            List<Product> products = restClient.get()
                    .uri("/products")
                    .headers(httpHeaders -> httpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer " + jwtTokenValue))
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            model.addAttribute("products", products);
            return "products";
        }
        catch (Exception e){
            return "redirect:/accessDenied";
        }
    }
	
}
