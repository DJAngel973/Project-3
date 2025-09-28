package co.edu.devSenior;

import co.edu.devSenior.controller.AppController;

/**
 * Main application entry point.
 * Initializes and starts the course management system.
 * */
public class App {
    public static void main(String[] args) {
        AppController appController = new AppController();
        appController.run();
    }
}