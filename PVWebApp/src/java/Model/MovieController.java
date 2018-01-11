/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author elizabethdudley
 */
@ManagedBean
@SessionScoped
public class MovieController {
    /**
     * current movie instance.
    */
    private Movie current;

    /**
     * get the current movie.
     * @param current1 the current instance
     * @return the movie page
     */
    public final String goTo(final Movie current1) {
        this.current = current1;
        return "movie";
    }

    /**
     * getter for title.
     * @return title
     */
    public final String getTitle() {
        return current.getTitle();
    }

    /**
     * getter for release year.
     * @return release year
     */
    public final int getReleaseYear() {
        return current.getReleaseYear();
    }

    /**
     * getter for runtime.
     * @return run time
     */
    public final String getRuntime() {
        return current.getRuntime();
    }

    /**
     * getter for mpaa rating.
     * @return mpaa rating
     */
    public final String getMpaaRating() {
        return current.getMpaaRating();
    }

    /**
     * getting for synopsis.
     * @return synopsis
     */
    public final String getSynopsis() {
        return current.getSynopsis();
    }

    /**
     * saves user's rating.
     * @param r rating
     * @param s student
     */
    public final void saveRating(final Rating r, final Student s) {
        r.setMovie(current);
        r.setProfile(s.getProfile());
        RatingHelper helper = new RatingHelper();
        helper.saveRating(r);
    }

    /**
     * getter for ratings.
     * @return An ArrayList of ratings
     */
    public final ArrayList<Rating> getRatings() {
        return current.getRatings();
    }

    /**
     * getting for Average Rating.
     * @return Average rating
     */
    public final double getAvgRating() {
        return current.getAvgRating();
    }
    /**
     * getting for the current instance.
     * @return current instance
     */
    public final Movie getCurrent() {
        return current;
    }

}
