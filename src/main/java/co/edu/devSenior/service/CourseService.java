package co.edu.devSenior.service;

import co.edu.devSenior.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private final List<Course> courses = new ArrayList<>();

    public Course createCourse(String id, String name, Integer capacity) {
        for (Course cur : courses) {
            if (cur.getId().equals(id)) {
                throw new IllegalArgumentException(String.format("El curso con el id %s ya existe", id));
            }
        }
        Course course = new Course(id, name, capacity);
        courses.add(course);
        return course;
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public Course searchCourseById(String id) {
        for (Course course : courses) {
            if (course.getId().equals(id)){
                return course;
            }
        }
        throw new IllegalArgumentException(String.format("El curso con el id %s no existe.", id));
    }
}