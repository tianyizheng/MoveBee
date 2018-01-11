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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * Utilizes GSON to generate instances of Movie.
 *
 * @author Tianyi, Elizabeth
 */
@ManagedBean
@SessionScoped
public class Search {
    private String searchInput;
    private GSONMovieList movie;
    private ArrayList<Movie> mList = new ArrayList<>();
    private UIComponent mainMessages;
    private String url;
    private final Gson gson = new Gson();


    /**
     * Getter for the search input.
     *
     * @return the current value in the search input
     */
    public String getSearchInput() {
        return searchInput;
    }

    /**
     * Setter for the search input.
     *
     * @param searchInput the new value for the search input
     */
    public void setSearchInput(String searchInput) {
        this.searchInput = searchInput;
    }

    /**
     * Getter for movie.
     *
     * @return the current value for movie.
     */
    public GSONMovieList getMovie() {
        return movie;
    }

    /**
     * Setter for movie.
     *
     * @param movie the new value for movie
     */
    public void setMovie(GSONMovieList movie) {
        this.movie = movie;
    }

    /**
     * Getter for mList.
     *
     * @return the current value for mList
     */
    public ArrayList<Movie> getMList() {
        return mList;
    }

    /**
     * Setter for mList.
     *
     * @param mList the new value for mList
     */
    public void setMList(ArrayList<Movie> mList) {
        this.mList = mList;
    }

    /**
     * Setter for Main Messages for UI components.
     *
     * @param mainMessages informational and error messages incurred during
     * registration
     */
    public void setMainMessages(UIComponent mainMessages) {
        this.mainMessages = mainMessages;
    }

    /**
     * Getter for Main Messages for UI components.
     *
     * @return informational and error messages incurred during
     * registration
     */
    public UIComponent getMainMessages() {
        return mainMessages;
    }

    /**
     * Creates url for JSON file.
     *
     * @return the search results page
     */
    public String makeName() {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < searchInput.length(); i++) {
            if (searchInput.charAt(i) == ' ') {
                buf.append("+");
            } else {
                buf.append(searchInput.charAt(i));
            }
        }
        String urlName = buf.toString();
        url = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?q="
                + urlName 
                + "&page_limit=10&page=1&apikey=2d35swbsdhxr77v6e2jgdtex";
        try {
            search();
        } catch (MalformedURLException e) {
            FacesMessage message = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "There was an error processing your search. "
                            + "Please try again",
                    "There was an error processing your search. "
                            + "Please try again"
            );
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(mainMessages.getClientId(context), message);
            return "main";
        } catch (IOException | com.google.gson.JsonSyntaxException e) {
            FacesMessage message = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "There was an error processing your search. "
                            + "Please try again",
                    "There was an error processing your search. "
                            + "Please try again"
            );
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(mainMessages.getClientId(context), message);
        }
        return "search";
    }

/**
 * Reads the Gson object and adds instances of Movie to the array list.
 *
 * @throws MalformedURLException if the url cannot be accessed
 * @throws IOException if the server encounters an error
 */
    public void search() throws MalformedURLException, IOException {
        InputStream stream = new URL(url).openStream();
        movie = null;
        try {
            Reader rd = new BufferedReader(new InputStreamReader(stream));
            movie = gson.fromJson(rd, GSONMovieList.class);
            mList = movie.createMovies();
        } finally {
            stream.close();
            FacesContext.getCurrentInstance().getExternalContext().
                    getSessionMap().remove("#{movieController.current}");
        }
    }

}