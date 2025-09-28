package co.edu.devSenior.view;

import co.edu.devSenior.model.Course;

import java.util.Scanner;
import java.util.List;

/**
 * View for course management.
 * Provides methods to interact with the user via the console,
 * displaying messages, requesting input, and presenting course information.
 * */
public class CourseView {
    private final Scanner input;

    /**
     * Creates a new CourseView instance with a Scanner for standard input.
     * */
    public CourseView() {
        this.input = new Scanner(System.in);
    }

    /**
     * Displays a message to the user.
     * @param message The message to display.
     * */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prompt the user for input with a message.
     * @param prompt The prompt message.
     * @return the user's input.
     * */
    public String getInput(String prompt) {
        System.out.println(prompt);
        return input.nextLine();
    }

    /**
     * Displays a list of courses to the user.
     * @param courses the list of courses to the user.
     * */
    public void displayCourses(List<Course> courses) {
        if (courses.isEmpty()) {
            System.out.println("No hay cursos registrados.");
        } else {
            System.out.println("Lista de cursos:");
            for (Course course : courses) {
                System.out.println(course);
            }
        }
    }

    /**
     * Displays information about a specific course.
     * @param course The course to display.
     * */
    public void displayCourse(Course course) {
        System.out.printf(String.format("Curso encontrado: %s", course));
    }
}