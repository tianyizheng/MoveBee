/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author kenneththompson
 */
public class MovieHelper {
    /**
     *
     * @param movieId id of the movie
     * @return the matching movie
     */
    public final Movie findById(final String movieId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        List<Movie> movies;
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        movies = session.createQuery("from Movie as movie where movie.movie"
                + "Id = :movieId").setParameter("movieId", movieId).list();

        if (movies.size() > 0) {
            return movies.get(0);
        } else {
            return null;
        }

    }
    /**
     * saves the movie.
     * @param movie to be saved
     */
    public final void saveMovie(final Movie movie) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.saveOrUpdate(movie);
        session.getTransaction().commit();

    }
}
