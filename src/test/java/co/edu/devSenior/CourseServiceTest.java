package co.edu.devSenior;

import co.edu.devSenior.model.Course;
import co.edu.devSenior.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link co.edu.devSenior.service.CourseService}.
 * <p>
 *     Verifies course creation, duplicate handling, retrieving all courses,
 *     and searching for courses by ID, including error validations.
 * </p>
 * */
class CourseServiceTest {
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        courseService = new CourseService();
    }

    @Test
    void testCreateCourseSuccessfully() {
        Course course = courseService.createCourse("J101", "Java", 50);
        assertNotNull(course);
        assertEquals("J101", course.getId());
        assertEquals("Java", course.getName());
        assertEquals(50, course.getCapacity());
    }

    @Test
    void testCreateCourseWithDuplicateIdThrowException() {
        courseService.createCourse("J101", "Java", 50);
        try {
            courseService.createCourse("J101", "Python", 40);
            fail("Se esperaba una IllegalArgumentException para un ID duplicado, pero no se lanzó.");
        } catch (IllegalArgumentException error) {
            assertEquals("El curos de Python-código J101 ya existe", error.getMessage());
        }
    }

    @Test
    void testGetAllCourses() {
        courseService.createCourse("J101", "Java", 50);
        courseService.createCourse("P101", "Python", 40);
        List<Course> courses = courseService.getAllCourses();
        assertNotNull(courses);
        assertEquals(2, courses.size());
        assertEquals("J101", courses.get(0).getId());
        assertEquals("P101", courses.get(1).getId());
    }

    @Test
    void testSearchCourseByIdSuccessfully() {
        courseService.createCourse("J101", "Java", 50);
        Course course = courseService.searchCourseById("J101");
        assertNotNull(course);
        assertEquals("J101", course.getId());
        assertEquals("Java", course.getName());
    }

    @Test
    void testSearchCourseByIdThrowsExceptionWhenNotFound() {
        try {
            courseService.searchCourseById("500");
            fail("Se esperaba una IlegalArgumentException para un curso no existente, pero no se lanzó.");
        } catch (IllegalArgumentException error) {
            assertEquals("El curso con código 500 no existe.", error.getMessage());
        }
    }
}