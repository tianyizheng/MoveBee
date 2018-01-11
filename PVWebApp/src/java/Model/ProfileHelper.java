/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author kenneththompson
 */
public class ProfileHelper {

    /**
    * Search the database for user profile by email.
    *
    * @param email String
    * @return Profile connected to Email
    */
    public Profile findByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        List<Profile> profiles;
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        profiles = session.createQuery("from Profile as profile where profile"
                + ".email = :email").setParameter("email", email).list();

        if (profiles.isEmpty()) {
            return null;
        } else {
            return profiles.get(0);
        }

    }

    /**
    * Search the database for all users other than the one associated with
    * entered email.
    *
    * @param email String
    * @return List<Profile> that are not associated with the email
    */
    ArrayList<Profile> getProfiles(String email) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        List<Profile> profiles;
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        profiles = session.createQuery("from Profile as profile where profile."
                + "email != :email").setParameter("email", email).list();

        if (profiles.isEmpty()) {
            return null;
        } else {
            return (ArrayList) profiles;
        }
    }

    /**
    * Save inputted profile to database.
    *
    * @param profile Profile
    */
    public void saveProfile(Profile profile) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.saveOrUpdate(profile);
        session.getTransaction().commit();

    }

}
