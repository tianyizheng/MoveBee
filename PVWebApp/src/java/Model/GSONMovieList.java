/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 *MovieList class to get JSON file
 * @author Tianyi, Elizabeth
 */

@ManagedBean
@SessionScoped
public class GSONMovieList {
    private List<GSONMovie> movies = new ArrayList<>();
    /**
     * getter for movies
     * @return movies the list of movies
     */
    public List<GSONMovie> getMovies() {
        return movies;
    }

    /**
     * setter for movies
     * @param movies the list to set
     */
    public void setMovies(List<GSONMovie> movies) {
        this.movies = movies;
    }

    /**
     * removes the Movie at the given index
     * @param ind the int index to remove the movieInner
     * @return    a string representation of the removed movie
     */
    public String remove(int ind) {
        GSONMovie c = movies.remove(ind);
        return c.getTitle() + " (" + c.getYear() + ")";
    }

    /**
     * getter of a single movie
     * @param ind the int index to get the movieInner
     * @return  the movie at that index
     */
    public GSONMovie get(int ind) {
        return movies.get(ind);
    }

    /**
     * getter of the movies size
     * @return  the size of movies
     */
    public int length() {
        return movies.size();
    }

    public ArrayList<Movie> createMovies() {
        ArrayList<Movie> mList = new ArrayList<>();
        movies.stream().forEach((m) -> {
                mList.add(m.createMovie());
            });
        return mList;
    }
}
