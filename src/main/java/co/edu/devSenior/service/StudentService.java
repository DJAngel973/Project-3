package co.edu.devSenior.service;

import co.edu.devSenior.exception.StudentAlreadyExistsException;
import co.edu.devSenior.model.Student;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Service to manage students.
 * */
public class StudentService {
    private static final Logger logger = LogManager.getLogger(StudentService.class);
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
        logger.info("Creando estudiante con nombre {}.", name);
        for (Student stud : students) {
            if (stud.getId().equals(id)) {
                logger.error("Error: el id {} ya está en uso, verifica de nuevo.", id);
                throw new StudentAlreadyExistsException("Ya existe un estudiante con este id: " + id);
            }
            if (stud.getEmail().equals(email)) {
                logger.error("Error: problema con el correo {}, verifica de nuevo.", email);
                throw new StudentAlreadyExistsException("Ya existe un estudiante con este email: " + email);
            }
        }
        Student student = new Student(id, name, email);
        students.add(student);
        logger.info("Estudiante {} creado con id {}.", name, id);
        return student;
    }
}