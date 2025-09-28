package co.edu.devSenior.view;

import co.edu.devSenior.model.Course;

import java.util.Scanner;
import java.util.List;

/**
 *
 * */
public class CourseView {
    private final Scanner input;

    public CourseView() {
        this.input = new Scanner(System.in);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public String getInput(String prompt) {
        System.out.println(prompt);
        return input.nextLine();
    }

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


    public void displayCourse(Course course) {
        System.out.printf(String.format("Curso encontrado: %s", course));
    }
}
