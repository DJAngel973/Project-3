package co.edu.devSenior.controller;

import co.edu.devSenior.exception.StudentNotFoundException;
import co.edu.devSenior.model.Course;
import co.edu.devSenior.service.CourseService;
import co.edu.devSenior.service.EnrollmentService;
import co.edu.devSenior.service.StudentService;
import co.edu.devSenior.view.EnrollmentView;
import java.util.List;
import co.edu.devSenior.model.Student;;

/**
 * Controller for managing student enrollments in courses.
 * Acts as an intermediary between the EnrollmentService, CourseService, StudentService, end EnrollmentView.
 * */
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    private final EnrollmentView enrollmentView;
    private final CourseService courseService;
    private final StudentService studentService;

    /**
     * Constructs an EnrollmentController with the required services and view.
     * @param enrolmmentService The service for managing enrollments.
     * @param enrollmentView The view for interacting with the user.
     * @param courseService The service for managing courses.
     * @param studentService The service for managing students.
     * */
    public EnrollmentController(EnrollmentService enrollmentService, EnrollmentView enrollmentView, CourseService courseService, StudentService studentService) {
        this.enrollmentService = enrollmentService;
        this.enrollmentView = enrollmentView;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    /**
     * Handles the process of enrolling a student in a course.
     * Prompts the user for course and student details, and performs the enrollment.
     */
    public void enrollStudentInCourse() {
        try {
            String courseId = enrollmentView.getInput("Ingresa el código del curso: ");
            Course course = courseService.searchCourseById(courseId);
            String studentId = enrollmentView.getInput("Ingresa el ID del estudiante: ");
            Student student;
            try {
                student = studentService.getStudentById(studentId);
            } catch (IllegalArgumentException error) {
                String studentName = enrollmentView.getInput("Ingresa el nombre del estudiante: ");
                String studentEmail = enrollmentView.getInput("Ingresa el correo del estudiante: ");
                student = studentService.createStudent(studentId, studentName, studentEmail);
            }
            enrollmentService.enrollStudentInCourse(course, student);
            enrollmentView.displayMessage(String.format("Estudiante inscrito exitosamente al curso %s.", course.getName()));
        } catch (IllegalArgumentException | NullPointerException error) {
            enrollmentView.displayError(String.format("Error al inscribir al estudiante: %s", error.getMessage()));
        }
    }

    /**
     *
     */
    public void getStudentsInCourse() {
        try {
            String courseId = enrollmentView.getInput("Ingresa el código del curso: ");
            Course course = courseService.searchCourseById(courseId);
            List<Student> students = enrollmentService.getStudentsInCourse(course);
            if (students.isEmpty()) {
                enrollmentView.displayMessage("No hay estudiantes inscritos en este curso.");
            } else {
                enrollmentView.displayMessage(String.format("Estudiantes inscriptos en el curso %s-%s:", course.getName(), course.getId()));
                for (Student student : students) {
                    enrollmentView.displayMessage(String.format("- %s (ID: %s)", student.getName(), student.getId()));
                }
            }
        } catch (IllegalArgumentException error) {
            enrollmentView.displayError(String.format("Error: %s", error.getMessage()));
        }
    }

    /**
     *
     */
    public void getCoursesForStudent() {
        try {
            String studentId = enrollmentView.getInput("Ingresa el ID del estudiante: ");
            Student student = studentService.getStudentById(studentId);
            List<Course> courses = enrollmentService.getCoursesForStudent(student);
            if (courses.isEmpty()) {
                enrollmentView.displayMessage("El estudiante no está inscrito en algun curso.");
            } else {
                enrollmentView.displayMessage(String.format("Cursos inscriptos del estudiante %s.", student.getName()));
                for (Course course : courses) {
                    enrollmentView.displayMessage(String.format("- curso %s-%s", course.getName(), course.getId()));
                }
            }
        } catch (IllegalArgumentException error) {
            enrollmentView.displayError(String.format("Error: %s", error.getMessage()));
        } catch (StudentNotFoundException error) {
            enrollmentView.displayError(String.format("Error: %s", error.getMessage()));
        }
    }
}