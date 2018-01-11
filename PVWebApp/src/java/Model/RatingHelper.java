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
public class RatingHelper {

    /**
     * Constructor for RatingHelper.
     *
     */
    public RatingHelper() {
    }

    /**
     * Finds an arrayList of ratings by using email.
     *
     * @param email String
     * @return ArrayList<Rating>
     */
    public final ArrayList<Rating> findByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        List<Rating> ratings;
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        ratings = session.createQuery("select rating from Rating rating inner "
                + "join rating.profile profile where profile.email = :email")
                .setParameter("email", email).list();

        if (ratings.size() > 0) {
            return (ArrayList) ratings;
        } else {
            return new ArrayList<>();
        }

    }

    /**
     * Finds the array list of ratings by movieId.
     *
     * @param movieId String
     * @return ArrayList<Rating>
     */
    final ArrayList<Rating> findById(String movieId) {
       Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        List<Rating> ratings;
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        ratings = session.createQuery("select rating from Rating rating inner "
                + "join rating.movie movie where movie.movieId = :movieId")
                .setParameter("movieId", movieId).list();

        if (ratings.size() > 0) {
            return (ArrayList) ratings;
        } else {
            return null;
        }
    }

    /**
     * Saves the user rating to the movie.
     *
     * @param rating current rating
     */
    public final void saveRating(Rating rating) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.saveOrUpdate(rating);
        session.getTransaction().commit();

    }

}
