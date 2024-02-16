package main.java.services;

import models.Course;
import persistence.CourseDAO;

public class CourseService {

    private final CourseDAO courseDAO;

    public CourseService() {
        this.courseDAO = new CourseDAO();
    }

    public void insertCourse(Course course) {
        if (course != null) {
            courseDAO.insertCourse(course);
        }
    }

    public Course getCourseById(int courseId) {
        if (courseId > 0) {
            return courseDAO.getCourseById(courseId);
        }
        return null;
    }

    public void updateCourse(Course course) {
        if (course != null) {
            courseDAO.updateCourse(course);
        }
    }

    public void deleteCourse(int courseId) {
        if (courseId > 0) {
            courseDAO.deleteCourse(courseId);
        }
    }
}