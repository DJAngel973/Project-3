package co.edu.poli.service;

import co.edu.poli.exception.StudentAlreadyExistsException;
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
     * @return the created student
     * @throws StudentAlreadyExistsException if a student with the same ID or email already exists.
     * */
    public Student createStudent(String id, String name, String email) {
        for (Student stud : students) {
            if (stud.getId().equals(id)) {
                throw new StudentAlreadyExistsException("Ya existe un estudiante con este id: " + id);
            }
            if (stud.getEmail().equals(email)) {
                throw new StudentAlreadyExistsException("Ya existe un estudiante con este email: " + email);
            }
        }
        Student student = new Student(id, name, email);
        students.add(student);
        return student;
    }
}