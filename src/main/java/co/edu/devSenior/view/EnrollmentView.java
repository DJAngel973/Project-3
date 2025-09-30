package co.edu.devSenior.view;

import java.util.Scanner;

/**
 * View for managing user interactions related to enrollments.
 * Handles input and output for enrollment-related operations.
 */
public class EnrollmentView {
    private final Scanner input;

    public EnrollmentView() {
        this.input = new Scanner(System.in);
    }

    /**
     * Prompts the user for input and returns the entered value.
     * @param prompt The message to display to the user.
     * @return The user's input as a String.
     */
    public String getInput(String prompt) {
        System.out.println(prompt);
        return input.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }
}