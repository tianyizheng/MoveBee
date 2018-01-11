/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;

/**
 *
 * @author Hannah
 */
@ManagedBean
@SessionScoped
public class AdminHelper {
    public ArrayList<Student> getStudents(String email) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        List<Student> students;
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        students = session.createQuery("from Student as student where st"
                + "udent.email != :email").setParameter("email", email).list();
        if (students.size() > 0) {
            return (ArrayList) students;
        } else {
            return null;
        }
    }
    public void saveStudent(Student student) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.saveOrUpdate(student);
        session.getTransaction().commit();
    }
}