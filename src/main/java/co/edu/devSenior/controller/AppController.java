package co.edu.devSenior.controller;

import co.edu.devSenior.view.CourseView;
import co.edu.devSenior.view.EnrollmentView;
import co.edu.devSenior.service.CourseService;
import co.edu.devSenior.service.EnrollmentService;
import co.edu.devSenior.service.StudentService;

import java.util.Scanner;

/**
 * Main controller for the application.
 * Manages the main menu, user input, and delegates actions to the course controller.
 * */
public class AppController {
    private final CourseController courseController;
    private final EnrollmentController enrollmentController;
    private final CourseView courseView;

    /**
     * Constructs the AppController and initializes its dependencies.
     * */
    public AppController() {
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService();
        StudentService studentService = new StudentService();
        EnrollmentView enrollmentView = new EnrollmentView();
        this.courseView = new CourseView();
        this.courseController = new CourseController(courseService, courseView);
        this.enrollmentController = new EnrollmentController(enrollmentService, enrollmentView, courseService, studentService);
    }

    /**
     * Runs the main application loop, displaying the menu and handling user actions.
     * */
    public void run() {
        boolean running = true;

        while (running) {
            courseView.displayMessage("\n======= Sistema de Gestión de cursos =======");
            courseView.displayMessage("1. Crear curso.");
            courseView.displayMessage("2. Ver todos los cursos.");
            courseView.displayMessage("3. Buscar curso por código del curso.");
            courseView.displayMessage("4. Inscribir estudiante en un curso.");
            courseView.displayMessage("5. Salir.");
            String option = courseView.getInput("Seleccione una opción: ");

            switch (option) {
                case "1":
                    courseController.createCourse();
                    break;
                case "2":
                    courseController.displayAllCourses();
                    break;
                case "3":
                    courseController.searchCourseById();
                    break;
                case "4":
                    enrollmentController.enrollStudentInCourse();
                    break;
                case "5":
                    running = false;
                    courseView.displayMessage("Gracias, saliendo del sistema...");
                    break;
                default:
                    courseView.displayMessage("Opción no válida. Intenta de nuevo.");
            }
        }
    }
}