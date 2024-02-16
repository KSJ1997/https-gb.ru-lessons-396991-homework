package main.java.persistence;

import models.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseDAO {

    private static final Logger logger = Logger.getLogger(CourseDAO.class.getName());

    private final SessionFactory sessionFactory;

    public CourseDAO() {
        this.sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();
    }

    public void insertCourse(Course course) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Error occurred while inserting course", e);
        } finally {
            sessionFactory.close();
        }
    }

    public Course getCourseById(int courseId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Course.class, courseId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving course", e);
            return null;
        } finally {
            sessionFactory.close();
        }
    }

    public void updateCourse(Course course) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Error occurred while updating course", e);
        } finally {
            sessionFactory.close();
        }
    }

    public void deleteCourse(int courseId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Course course = session.get(Course.class, courseId);
            if (course != null) {
                session.delete(course);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Error occurred while deleting course", e);
        } finally {
            sessionFactory.close();
        }
    }
}
