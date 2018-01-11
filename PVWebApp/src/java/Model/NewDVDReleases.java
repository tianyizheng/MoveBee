
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
 *Prints out the InTheaters Movies
 * @author elizabethdudley, Tianyi Zheng
 */
@ManagedBean
@SessionScoped
public class NewDVDReleases {

    /**
     * ArrayList of new DVD releases to be used in other classes.
     */
    private ArrayList<Movie> releases = new ArrayList<>();

    /**
     * GSON class instance.
     */
    private final Gson gson = new Gson();

    /**
     * URL for RottenTomatoes API.
     */
    private static final String url = "http://api.rottentomatoes.com/api/public"
            + "/v1.0/l"
            + "ists/dvds/new_releases.json?apikey=2d35swbsdhxr77v6e2jgdtex";

    /**
     * GSONMovieList class instance to save Rotten Tomatoes data.
     */
    private GSONMovieList movie = null;
/**
 * goes through the search results and add movie informations.
 * @throws MalformedURLException
 * @throws IOException
 */
    public void makeInDVDs() throws MalformedURLException, IOException {
        try (InputStream stream = new URL(url).openStream()) {
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
    public void release() {
        if (movie == null) {
            try {
                makeInDVDs();
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
    public GSONMovieList getMovie() {
        return movie;
    }

    /**
     * getter for the new DVD releases arrayList.
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