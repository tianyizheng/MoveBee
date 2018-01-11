/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Hannah
 */
@ManagedBean
@SessionScoped
public class Rating implements java.io.Serializable {

    private Movie movie;
    private Profile profile;
    private int rateValue;
    private String review;

    /**
     * Getter for Movie Title.
     *
     * @return String
     */
    public final Movie getMovie() {
        return movie;
    }

    /**
     * Getter for Profile.
     *
     * @return Profile
     */
    public final Profile getProfile() {
        return profile;
    }

    /**
     * Setter for Movie Title.
     *
     * @param profileSet profile
     */
    public final void setProfile(Profile profileSet) {
        this.profile = profileSet;
    }

    /**
     * Getter for Rate Value.
     *
     * @return int
     */
    public final int getRateValue() {
        return rateValue;
    }

    /**
     * Getter for Review.
     *
     * @return String
     */
    public final String getReview() {
        return review;
    }

    /**
     * Setter for Movie Title.
     *
     * @param movieSet movie
     */
    public final void setMovie(Movie movieSet) {
        this.movie = movieSet;
    }

    /**
     * Setter for Rate Value.
     *
     * @param rateVal int
     */
    public final void setRateValue(int rateVal) {
        this.rateValue = rateVal;
    }

    /**
     * Setter for Review.
     *
     * @param reviewSet String
     */
    public final void setReview(String reviewSet) {
        this.review = reviewSet;
    }
}
