package co.edu.poli.service;

import co.edu.poli.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Service to manage students.
 * */
public class StudentService {
    private List<Student> students = new ArrayList<>();

    /**
     * Creates and adds a new student.
     * @param id Student identifier.
     * @param name Student name.
     * @param email Student email.
     * @return the created student, or null if one already exists with the same ID or email.
     * */
    public Student createStudent(String id, String name, String email) {
        for (Student stud : students) {
            if (stud.getId().equals(id) || stud.getEmail().equals(email)) {
                return null;
            }
        }
        Student student = new Student(id, name, email);
        students.add(student);
        return student;
    }
}