package co.edu.devSenior.controller;

import co.edu.devSenior.model.Course;
import co.edu.devSenior.service.CourseService;
import co.edu.devSenior.view.CourseView;

import java.util.List;

/**
 * Controller for course management.
 * Handles user interactions, delegates business logic to the service layer,
 * and coordinates the view for displaying information.
 * */
public class CourseController {
    private final CourseService courseService;
    private final CourseView courseView;

    /**
     * Constructs a CourseControllet with the specified service and view.
     * @param courseService The service handling course business logic.
     * @param courseView The view for user interaction.
     * */
    public CourseController(CourseService courseService, CourseView courseView) {
        this.courseService = courseService;
        this.courseView = courseView;
    }

    /**
     * Handles the creation  of a new course by requesting input from the user,
     * delegating creation to the service, and displaying the result.
     * */
    public void createCourse() {
        String id = courseView.getInput("Ingrese el código del curso: ");
        String name = courseView.getInput("Ingresa el nombre del curso: ");
        Integer capacity = Integer.parseInt(courseView.getInput("Ingresa la capacidad del curso: "));

        try {
            Course course = courseService.createCourse(id, name, capacity);
            courseView.displayMessage(String.format("Curso creado exitosamente: %s", course));
        } catch (IllegalArgumentException error) {
            courseView.displayMessage(String.format("Error al crear el curso: %s", error.getMessage()));
        }
    }

    /**
     * Displays all registered courses to the user.
     * */
    public void displayAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        courseView.displayCourses(courses);
    }

    /**
     * Searches for a course by its código, displaying the result or an error message.
     * */
    public void searchCourseById() {
        String id = courseView.getInput("Ingresa el código del curso a buscar: ");

        try {
            Course course = courseService.searchCourseById(id);
            courseView.displayCourse(course);
        } catch (IllegalArgumentException error) {
            courseView.displayMessage(String.format("Error: %s", error.getMessage()));
        }
    }
}