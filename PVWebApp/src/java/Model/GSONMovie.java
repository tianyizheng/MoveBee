/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.google.gson.annotations.SerializedName;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *serialized in order for gson to create an instance
 * @author Tianyi Zheng
 */
@ManagedBean
@SessionScoped
public class GSONMovie {
    @SerializedName("posters")
    private Posters poster;

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("year")
    private int year;

    @SerializedName("mpaa_rating")
    private String mpaaRating;

    @SerializedName("runtime")
    private String runtime;

    @SerializedName("synopsis")
    private String synopsis;

    /**
     * getter for poster
     * @return poster
     */
    public Posters getPoster() {
        return poster;
    }

    /**
     * setter for poster
     * @param poster the poster to set this poster to
     */
    public void setPoster(Posters poster) {
        this.poster = poster;
    }

    /**
     * getter for id
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * setter for id
     * @param id the id to set this id to
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * getter for title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * setter for title
     * @param title the title to set this title to
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * getter for year
     * @return movies the list of movies
     */
    public int getYear() {
        return year;
    }

    /**
     * setter for year
     * @param year the year to set this year to
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * getter for mpaaRating
     * @return mpaaRating
     */
    public String getMpaaRating() {
        return mpaaRating;
    }

    /**
     * setter for mpaaRating
     * @param mpaaRating the mpaaRating to set this mpaaRating to
     */
    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    /**
     * getter for runtime
     * @return runtime
     */
    public String getRuntime() {
        return runtime;
    }

    /**
     * setter for runtime
     * @param runtime the runtime to set this runtime to
     */
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    /**
     * getter for synopsis
     * @return synopsis
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * setter for synopsis
     * @param synopsis the synopsis to set this synopsis to
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Movie createMovie() {
        MovieHelper helper = new MovieHelper();
        if (helper.findById(id) == null) {
            Movie newMovie = new Movie(title, id, runtime,
                    year, synopsis, mpaaRating);
            helper.saveMovie(newMovie);
            return newMovie;
        } else {
            return helper.findById(id);
        }
    }
}