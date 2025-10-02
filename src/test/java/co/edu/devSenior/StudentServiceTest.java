package co.edu.devSenior;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.edu.devSenior.exception.StudentAlreadyExistsException;
import co.edu.devSenior.model.Student;
import co.edu.devSenior.service.StudentService;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link co.edu.devSenior.service.StudentService}.
 * <p>
 * This class verifies the functionality of the StudentService, including:
 * <ul>
 *     <li>Creating students with valid data.</li>
 *     <li>Handling duplicate student IDs and emails.</li>
 *     <li>Searching for students by ID.</li>
 *     <li>Throwing exceptions for invalid operations.</li>
 * </ul>
 * </p>
 */
public class StudentServiceTest {
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService();
    }

    /**
     * Tests the creation of a student with valid data.
     * Verifies that the student is created successfully and that the attributes are correct.
     */
    @Test
    void testCreateStudent(){
        Student student = studentService.createStudent("cc50045", "Pedro Salazar", "ped34@tes.co");
        assertNotNull(student);
        assertEquals("cc50045", student.getId());
        assertEquals("Pedro Salazar", student.getName());
        assertEquals("ped34@tes.co", student.getEmail());
    }

    /**
     * Tests that an exception is thrown when attempting to create a student with a duplicate ID.
     * Verifies that the exception message is correct.
     */
    @Test
    void testCreateStudentWithDuplicateIdThrowException() {
        studentService.createStudent("cc50045", "Pedro Salazar", "ped34@tes.co");
        StudentAlreadyExistsException exception = assertThrows(
            StudentAlreadyExistsException.class,
            () -> studentService.createStudent("cc50045", "Pedro Alvarez", "ped34@test.co"));
            assertEquals("Ya existe un estudiante con este id: cc50045", exception.getMessage());
    }

    /**
     * Tests that an exception is thrown when attempting to create a student with a duplicate email.
     * Verifies that the exception message is correct.
     */
    @Test
    void testCreateStudentWithDuplicateEmailThrowsException() {
        studentService.createStudent("cc50045", "Pedro Salazar", "ped34@tes.co");
        StudentAlreadyExistsException exception = assertThrows(
            StudentAlreadyExistsException.class,
            () -> studentService.createStudent("cc50046", "Pedro Alvarez", "ped34@tes.co")
        );
        assertEquals("Ya existe un estudiante con este email: ped34@tes.co", exception.getMessage());
    }

    /**
     * Tests the retrieval of a student by their ID.
     * Verifies that the correct student is returned and that their attributes are correct.
     */
    @Test
    void testGetStudentById() {
        studentService.createStudent("cc50045", "Pedro Salazar", "ped34@tes.co");
        Student student = studentService.getStudentById("cc50045");
        assertNotNull(student);
        assertEquals("cc50045", student.getId());
        assertEquals("Pedro Salazar", student.getName());
    }

    /**
     * Tests that an exception is thrown when attempting to retrieve a student by an ID that does not exist.
     * Verifies that the exception message is correct.
     */
    @Test
    void testGetStudentByIdThrowsException() {
        // Intentar buscar un estudiante que no existe
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> studentService.getStudentById("cc99999")
        );

        // Verificar el mensaje de la excepción
        assertEquals("No se encontró un estudiante con el ID: cc99999", exception.getMessage());
    }
}
