package co.edu.devSenior.service;

import co.edu.devSenior.model.Course;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CourseService {
    private static final Logger logger = LogManager.getLogger(CourseService.class);
    private final List<Course> courses = new ArrayList<>();

    public Course createCourse(String id, String name, Integer capacity) {
        logger.info("Creando el curso: {}-código:{}, para {} estudiantes.", name, id, capacity);
        for (Course cur : courses) {
            if (cur.getId().equals(id)) {
                logger.error("Error al crear curso {}-código {}", name, id);
                throw new IllegalArgumentException(String.format("El curso curso %s-código %s ya existe", name, id));
            }
        }
        Course course = new Course(id, name, capacity);
        courses.add(course);
        logger.info("Curso {}-código {} creado exitosamente.", name,id);
        return course;
    }

    public List<Course> getAllCourses() {
        logger.info("Total cursos {}.", courses.size());
        return new ArrayList<>(courses);
    }

    public Course searchCourseById(String id) {
        logger.info("Buscando el curso con código {}", id);
        for (Course course : courses) {
            if (course.getId().equals(id)){
                logger.info("Curso de código {} encontrado.", id);
                return course;
            }
        }
        logger.error("Curso de código {} no existe.", id);
        throw new IllegalArgumentException(String.format("El curso con el código %s no existe.", id));
    }
}