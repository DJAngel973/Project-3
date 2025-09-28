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

/**
 * Service to manage enrollments of students in courses using Map<Course, List<Student>>.
 * */
public class EnrollmentService {
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
        List<Enrollment> courseEnrollments = enrollments.getOrDefault(course, new ArrayList<>());
        if (courseEnrollments.size() >= course.getCapacity()) {
            throw new IllegalArgumentException(String.format("El curso %s ya esta lleno.", course.getName()));
        }
        for (Enrollment enrollment : courseEnrollments) {
            if (enrollment.getStudent().equals(student)) {
                throw new IllegalArgumentException(String.format("El estudiante %s ya está inscrito en el curso %s", student.getName(), course.getName()));
            }
        }
        Enrollment enrollment = new Enrollment(student, course, LocalDate.now());
        courseEnrollments.add(enrollment);
        enrollments.put(course, courseEnrollments);
    }

    /**
     * Retrieves the list of students enrolled in a course.
     * @param course The course to get the list of students for.
     * @return The list of enrolled students.
     * */
    public List<Student> getStudentsInCourse(Course course) {
        List<Student> students = new ArrayList<>();
        List<Enrollment> courseEnrollments = enrollments.getOrDefault(course, new ArrayList<>());

        for (Enrollment enrollment : courseEnrollments){
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
        List<Course> enrolledCourses = new ArrayList<>();
        for (Map.Entry<Course, List<Enrollment>> entry : enrollments.entrySet()) {
            for (Enrollment enrollment : entry.getValue()) {
                if (enrollment.getStudent().equals(student)) {
                    enrolledCourses.add(enrollment.getCourse());
                }
            }
        }
        if (enrolledCourses.isEmpty()) {
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
        return new ArrayList<>(enrollments.getOrDefault(course, new ArrayList<>()));
    }
}