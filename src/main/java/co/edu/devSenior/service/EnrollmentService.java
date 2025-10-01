package co.edu.devSenior.service;

import co.edu.devSenior.exception.StudentNotFoundException;
import co.edu.devSenior.model.Course;
import co.edu.devSenior.model.Enrollment;
import co.edu.devSenior.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Service to manage enrollments of students in courses using Map<Course, List<Student>>.
 * */
public class EnrollmentService {
    private static final Logger logger = LogManager.getLogger(EnrollmentService.class);
    private final Map<Course, List<Enrollment>> enrollments;

    public EnrollmentService() {
        this.enrollments = new HashMap<>();
    }

    /**
     * Enrolls a student in a course.
     * @param course The course to enroll the student in.
     * @param student The student to enroll.
     * @throws IllegalArgumentException if the course is at full capacity or the student is already enrolled.
     * */
    public void enrollStudentInCourse(Course course, Student student) {
        logger.info("Inscribiendo un estudiante al curso {}.", course.getName());
        List<Enrollment> courseEnrollments = enrollments.getOrDefault(course, new ArrayList<>());
        if (courseEnrollments.size() >= course.getCapacity()) {
            logger.error("Error: el curso {}, ya esta lleno.", course.getName());
            throw new IllegalArgumentException(String.format("El curso %s ya esta lleno.", course.getName()));
        }
        for (Enrollment enrollment : courseEnrollments) {
            if (enrollment.getStudent().equals(student)) {
                logger.error("Error: el estudiante {} ya esta inscrito en el curso {}.", student.getName(), course.getName());
                throw new IllegalArgumentException(String.format("El estudiante %s ya está inscrito en el curso %s", student.getName(), course.getName()));
            }
        }
        Enrollment enrollment = new Enrollment(student, course, LocalDate.now());
        courseEnrollments.add(enrollment);
        logger.info("Estudiante {} inscrito al curso {}.", student.getName(), course.getName());
        enrollments.put(course, courseEnrollments);
    }

    /**
     * Retrieves the list of students enrolled in a course.
     * @param course The course to get the list of students for.
     * @return The list of enrolled students.
     * */
    public List<Student> getStudentsInCourse(Course course) {
        logger.info("Obteniendo la lista de estudiantes inscritos en el curso {}.", course.getName());
        List<Student> students = new ArrayList<>();
        List<Enrollment> courseEnrollments = enrollments.getOrDefault(course, new ArrayList<>());

        for (Enrollment enrollment : courseEnrollments){
            logger.info("El cursi {} tiene {} incripciones.", course.getName(), courseEnrollments.size());
            students.add(enrollment.getStudent());
        }
        return students;
    }

    /**
     * Retrieves the list of courses a student is enrolled in.
     * @param student The student to get the list of courses for.
     * @return The list of courses the student is enrolled in.
     * @throws StudentNotFoundException if the student has no enrollments or is not found.
     * */
    public List<Course> getCoursesForStudent(Student student) {
        logger.info("Generando listando cursos del estudiante {}.", student.getName());
        List<Course> enrolledCourses = new ArrayList<>();
        for (Map.Entry<Course, List<Enrollment>> entry : enrollments.entrySet()) {
            for (Enrollment enrollment : entry.getValue()) {
                if (enrollment.getStudent().equals(student)) {
                    logger.info("Cursos estudiante {}.", student.getName());
                    enrolledCourses.add(enrollment.getCourse());
                }
            }
        }
        if (enrolledCourses.isEmpty()) {
            logger.error("Error: el estudiante {} no tiene inscripciones o no existe.", student.getName());
            throw new StudentNotFoundException(String.format("El estudiante %s no tiene inscripciones o no existe.", student.getName()));
        }
        return enrolledCourses;
    }

    /**
     * Retrieves the list of enrollments for a course.
     * @param course The course to get the enrollments for.
     * @return The list of enrollments.
     * */
    public List<Enrollment> getEnrollmentsForCourse(Course course) {
        logger.info("Generando listado de cursos.");
        return new ArrayList<>(enrollments.getOrDefault(course, new ArrayList<>()));
    }
}