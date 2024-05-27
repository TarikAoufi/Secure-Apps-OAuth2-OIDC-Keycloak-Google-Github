package fr.tao.thymeleafapp.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import lombok.extern.slf4j.Slf4j;

/**
 * Global exception handler to handle exceptions across the application.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	/**
     * Handles WebClientResponseException by redirecting to the accessDenied page.
     *
     * @param ex The WebClientResponseException.
     * @return The redirect URL to the accessDenied page.
     */
    @ExceptionHandler(WebClientResponseException.class)
    public String handleAccessDeniedException(WebClientResponseException ex) {
    	log.error("WebClientResponseException handled: " + ex.getMessage());
        return "redirect:/accessDenied";
    }
    
    /**
     * Handles InvalidUserTypeException by redirecting to the accessDenied page.
     *
     * @param ex The InvalidUserTypeException.
     * @return The redirect URL to the accessDenied page.
     */
    @ExceptionHandler(InvalidUserTypeException.class)
    public String handleInvalidUserTypeException(InvalidUserTypeException ex) {
    	log.error("InvalidUserTypeException handled: " + ex.getMessage());
        return "redirect:/accessDenied";
    }
}