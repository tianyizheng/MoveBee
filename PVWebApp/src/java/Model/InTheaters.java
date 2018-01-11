/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Prints out the InTheaters Movies.
 * @author elizabethdudley, Tianyi Zheng
 */
@ManagedBean
@SessionScoped
public class InTheaters {
    /**
     * gson.
     */
    private ArrayList<Movie> releases = new ArrayList<>();
    
    private Gson gson = new Gson();
    /**
     * URL for gson.
     */
    private static final String URL = "http://api.rottentomatoes.com/api/publ"
            + "ic/v1.0/lists/movies/box_office.json?apikey=2d35swbsdhxr77v6e2"
            + "jgdtex";
    /**
     * Initiating GSONMovieList.
     */
     private GSONMovieList movie = null;

    /**
     * goes through the search results and add movie informations.
     * @throws MalformedURLException Url exception
     * @throws IOException Io exception
     */
    public final void makeInTheaters() throws MalformedURLException,
            IOException {
        try (InputStream stream = new URL(URL).openStream()) {
            Reader rd = new BufferedReader(new InputStreamReader(stream));
            movie = gson.fromJson(rd, GSONMovieList.class);
            movie.createMovies();
            makeReleases();
        }
    }

    /**
     * utilizes the makeInTheaters class.
     * gets the thumbnail images
     */
    @PostConstruct
    public final void release() {
        if (movie == null) {
            try {
                makeInTheaters();
            } catch (MalformedURLException e) {
                System.out.print("Malformed Url");
            } catch (IOException e) {
            System.out.print("IOException");
            }
        }
    }

    /**
     * getter for the movie.
     * @return movie
     */
    public final GSONMovieList getMovie() {
        return movie;
    }
       /**
     * getter for the in theaters releases arrayList.
     * @return ArrayList releases
     */
    public ArrayList<Movie> getReleases() {
        return releases;
    }

    /**
     * Gets the GSONMovieList and converts that into ArrayList releases.
     */
    private void makeReleases() {
        movie.getMovies().stream().map((curGson) ->
                new Movie(curGson.getTitle(),
                curGson.getId(), curGson.getRuntime(), curGson.getYear(),
                        curGson.getRuntime(),
                curGson.getMpaaRating())).forEach((curMovie) -> {
                        releases.add(curMovie);
                    });
    }
}