package co.edu.devSenior.exception;

/**
 * Exception thrown when a student already exists.
 * */
public class StudentAlreadyExistsException extends RuntimeException {
    public StudentAlreadyExistsException(String message) {
        super(message);
    }
}