package co.edu.poli.model;

/**
 * Represents an enrollment of a student in a course.
 * */
public class Enrollment {
    private Student student;
    private Course course;

    public Enrollment() {}

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Student getStudent() {return student;}
    public Course getCourse() {return course;}

    public void setStudent(Student student) {this.student = student;}
    public void setCourse(Course course) {this.course = course;}
}