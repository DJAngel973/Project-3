package co.edu.devSenior.exception;

/**
 * Exception thrown when a student is not found in the system.
 */
public class EstudianteNoEncontradoException extends RuntimeException {

    /**
     * Constructs a new EstudianteNoEncontradoException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public EstudianteNoEncontradoException(String message) {
        super(message);
    }
}