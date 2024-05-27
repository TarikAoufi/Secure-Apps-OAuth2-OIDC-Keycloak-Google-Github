package fr.tao.thymeleafapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Controller class for handling authentication-related endpoints.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
@Controller
@RequiredArgsConstructor @Getter
public class AuthController {
	
	/**
	 * Repository for managing client registrations.
	 */
	@NonNull
	private ClientRegistrationRepository clientRegistrationRepository;
	
	/**
     * Handler method for the login endpoint.
     * @return The view name for the login page
     */
	@GetMapping("/login")
    public String login() {
        return "login";
    }
	
	/**
     * Handler method for the logout endpoint.
     * @param request HttpServletRequest object
     * @return Redirects to the login page after session invalidation
     */
	@GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Invalidate session
        request.getSession().invalidate();
        // Clear security context
        SecurityContextHolder.clearContext();
        // Redirect to the home page
        return "redirect:/login";
    }
	
	/**
	 * Retrieves the username of the currently authenticated user.
	 *
	 * @return The username of the authenticated user, or "Anonymous" if not authenticated.
	 */
	@GetMapping("/profile")
    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getName();
        }
        return "Anonymous";
    }
	
	/**
	 * Method to retrieve the currently authenticated Authentication object from the
	 * Security Context
	 * 
	 * @return
	 */
	@GetMapping("/authentication")
	@ResponseBody
    public Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }
	
	/**
	 * Method to handle requests to the home page
	 * @return
	 */
    @GetMapping("/")
    public String home() {
        return "home"; 
    }
    
    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied"; 
    }
	    
}