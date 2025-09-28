package co.edu.devSenior.controller;

import co.edu.devSenior.model.Course;
import co.edu.devSenior.service.CourseService;
import co.edu.devSenior.view.CourseView;

import java.util.List;

/**
 *
 * */
public class CourseController {
    private final CourseService courseService;
    private final CourseView courseView;

    public CourseController(CourseService courseService, CourseView courseView) {
        this.courseService = courseService;
        this.courseView = courseView;
    }

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

    public void displayAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        courseView.displayCourses(courses);
    }

    public void searchCourseById() {
        String id = courseView.getInput("Ingresa el ID del curso a buscar: ");

        try {
            Course course = courseService.searchCourseById(id);
            courseView.displayCourse(course);
        } catch (IllegalArgumentException error) {
            courseView.displayMessage(String.format("Error: %s", error.getMessage()));
        }
    }
}