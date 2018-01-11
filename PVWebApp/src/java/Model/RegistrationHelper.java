/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import org.hibernate.Session;

/**
 * Saves and retrieves Student records from a relational database.
 *
 * @author kenneththompson
 */
public class RegistrationHelper {

    /**
     * Searches for a Student record by email.
     *
     * @param email the email being searched
     * @return the Student record, if found; otherwise, null
     */
    public Student findByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        List<Student> students;
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        students = session.createQuery(
                "from Student as student where student.email = :email"
        ).setParameter("email", email).list();

        if (students.size() > 0) {
            return students.get(0);
        } else {
            return null;
        }

    }

    /**
     * Saves a Student record to a relational database.
     *
     * @param student the student record being saved
     */
    public void registerStudent(Student student) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.save(student);
        session.getTransaction().commit();

    }
}
