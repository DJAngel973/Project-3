package co.edu.devSenior.controller;

import co.edu.devSenior.view.CourseView;
import co.edu.devSenior.service.CourseService;

import java.util.Scanner;

/**
 * Main controller for the application.
 * Manages the main menu, user input, and delegates actions to the course controller.
 * */
public class AppController {
    private final CourseController courseController;
    private final CourseView courseView;
    private final Scanner input;

    /**
     * Constructs the AppController and initializes its dependencies.
     * */
    public AppController() {
        CourseService courseService = new CourseService();
        this.courseView = new CourseView();
        this.courseController = new CourseController(courseService, courseView);
        this.input = new Scanner(System.in);
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
            courseView.displayMessage("4. Salir.");
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
                    running = false;
                    courseView.displayMessage("Gracias, saliendo del sistema...");
                    break;
                default:
                    courseView.displayMessage("Opción no válida. Intenta de nuevo.");
            }
        }
    }
}