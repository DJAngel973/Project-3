package co.edu.poli.exception;

/**
 * Exception thrown when a student already exists.
 * */
public class StudentAlreadyExistsException extends RuntimeException {
    public StudentAlreadyExistsException(String message) {
        super(message);
    }
}