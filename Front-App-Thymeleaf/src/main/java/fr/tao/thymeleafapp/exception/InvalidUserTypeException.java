package fr.tao.thymeleafapp.exception;

/**
 * Exception thrown when encountering an invalid user type during
 * authentication.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 26/05/2024
 */
@SuppressWarnings("serial")
public class InvalidUserTypeException extends RuntimeException {
	
	/**
     * Constructs a new InvalidUserTypeException with the specified detail message.
     *
     * @param message The detail message.
     */
	public InvalidUserTypeException(String message) {
		super(message);
	}
}
