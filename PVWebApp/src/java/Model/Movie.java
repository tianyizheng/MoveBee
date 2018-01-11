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
public class Movie {
    /**
     * Title of the movie.
     */
    private String title;
    /**
     * Id of the movie.
     */
    private  String movieId;
    /**
     * Runtime of the movie.
     */
    private String runtime;
    /**
     * Release year of the movie.
     */
    private int releaseReleaseYear;
    /**
     * Synopsis of the movie.
     */
    private String synopsis;
    /**
     * mpaa rating of the movie.
     */
    private String mpaaRating;
    /**
     * No arg constructor.
     */
    public Movie() {
    }

   /**
    * Constructor.
    * @param t Title
    * @param i Id
    * @param r runtime
    * @param y year
    * @param s Synopsis
    * @param m mpaarating
    */
    public Movie(final String t, final String i, final String r, final int y,
            final String s, final String m) {
        title = t;
        movieId = i;
        runtime = r;
        releaseReleaseYear = y;
        synopsis = s;
        mpaaRating = m;
    }

    @Override
    public final String toString() {
        return title;
    }

    /**
     * getter for id.
     *
     * @return id
     */
    public final String getMovieId() {
        return movieId;
    }

    /**
     * setter for id.
     *
     * @param movieid the id for this movie
     */
    public final void setMovieId(final String movieid) {
        this.movieId = movieid;
    }

    /**
     * getter for title.
     *
     * @return title
     */
    public final String getTitle() {
        return title;
    }

    /**
     * setter for title.
     *
     * @param ti the title to set this title to
     */
    public final void setTitle(final String ti) {
        this.title = ti;
    }

    /**
     * getter for releaseReleaseYear.
     *
     * @return movies the list of movies
     */
    public final int getReleaseYear() {
        return releaseReleaseYear;
    }

    /**
     * setter for releaseReleaseYear.
     *
     * @param releaseYear the releaseReleaseYear to set this
     * releaseReleaseYear to
     */
    public final void setReleaseYear(final int releaseYear) {
        this.releaseReleaseYear = releaseYear;
    }

    /**
     * getter for mpaaRating.
     *
     * @return mpaaRating
     */
    public final String getMpaaRating() {
        return mpaaRating;
    }

    /**
     * setter for mpaaRating.
     *
     * @param mpRating the mpaaRating to set this mpaaRating to
     */
    public final void setMpaaRating(final String mpRating) {
        this.mpaaRating = mpRating;
    }

    /**
     * getter for runtime.
     *
     * @return runtime
     */
    public final String getRuntime() {
        return runtime;
    }

    /**
     * setter for runtime.
     *
     * @param rtime the runtime to set this runtime to
     */
    public final void setRuntime(final String rtime) {
        this.runtime = rtime;
    }

    /**
     * getter for synopsis.
     *
     * @return synopsis
     */
    public final String getSynopsis() {
        return synopsis;
    }

    /**
     * setter for synopsis.
     *
     * @param synopsi the synopsis to set this synopsis to
     */
    public final void setSynopsis(final String synopsi) {
        this.synopsis = synopsi;
    }
    /**
     * @return an ArrayList of ratings.
     */
    public final ArrayList<Rating> getRatings() {
        RatingHelper helper = new RatingHelper();
        return helper.findById(movieId);
    }

    /**
     * Get the average rating of movies.
     * @return the average rating
     */
    public final double getAvgRating() {
        ArrayList<Rating> ratings;
        ratings = this.getRatings();
        if (ratings == null) {
            return 0;
        } else {
            double avg = 0;

            for (Rating r : ratings) {
                avg += r.getRateValue();
            }
            return (avg / (ratings.size()));
        }
    }
}
