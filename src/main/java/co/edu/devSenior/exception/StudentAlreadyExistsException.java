package co.edu.devSenior.exception;

/**
 * Exception thrown when a student already exists.
 * */
public class StudentAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new StudentAlreadyExistsException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public StudentAlreadyExistsException(String message) {
        super(message);
    }
}