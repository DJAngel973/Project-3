package co.edu.devSenior.model;

import java.time.LocalDate;

/**
 * Represents an enrollment of a student in a course.
 * */
public class Enrollment {
    private Student student;
    private Course course;
    private LocalDate enrollmentDate;

    public Enrollment() {}

    public Enrollment(Student student, Course course, LocalDate enrollmentDate) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
    }

    public Student getStudent() {return student;}
    public Course getCourse() {return course;}
    public LocalDate getEnrollmentDate() {return enrollmentDate;}

    public void setStudent(Student student) {this.student = student;}
    public void setCourse(Course course) {this.course = course;}
    public void setEnrollmentDate(LocalDate enrollmentDate) {this.enrollmentDate = enrollmentDate;}

    @Override
    public String toString() {
        return String.format("Estudiante %s inscripto al curso: %s-%s, fecha registro: %s", student.getName(), course.getName(), course.getId(), enrollmentDate.toString());
    }
}