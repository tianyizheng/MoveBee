/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
/**
 *
 * @author Hannah
 */
public class Recommendation {
    private Profile current;
    private String movie;
    private TreeMap<Profile, Integer> similar = new TreeMap<Profile, Integer>();


    /**
     * No-arg Constructor for Recommendation.
     */
    public Recommendation() {

    }

    /**
     * Constructor for Recommendation.
     *
     * @param currentProf current profile
     * @param movieCur current movie
     */
    public Recommendation(final Profile currentProf, final String movieCur) {
        current = currentProf;
        movie = movieCur;
    }

    /**
     * Returns the current profile that the recommendation is for.
     *
     * @return Profile current profile
     */
    public final Profile getCurrent() {
        return current;
    }

    /**
     * Returns the current movie that the recommendation is for.
     *
     * @return String movie
     */
    public final String getMovie() {
        return movie;
    }

    /**
    * Finds the most similar user based on class year, major, and which movies
    * they both rated 4 or 5 stars. Then finds the first movie that the most
    * similar gave 4 or 5 stars that the current user hasn't rated yet.
    *
    * @param currentProf Profile
    * @return String movie
    */
    public String generate(Profile currentProf) {
        try {
        if (currentProf.getFullName() == null) {
            return "Please fill out your profile. Click 'Edit Profile' in "
            + "the top right corner.";
        }
        ArrayList<Movie> curFavMovies = favoriteMovieHelper(currentProf);
        //look through a list of profiles to find a similar one
        ProfileHelper helper = new ProfileHelper();
        ArrayList<Profile> profiles = helper.getProfiles(currentProf.getEmail());
        //have map of matched profiles where value is the # of times they match
        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i) != null && profiles.get(i).getClassYear() !=null && currentProf.getClassYear() !=null ) {
                if (profiles.get(i).getClassYear().equals(currentProf.getClassYear())) {
                    similarHelper(profiles.get(i));
                }
            }
            if(profiles.get(i) != null && profiles.get(i).getMajor() != null) {
                if (profiles.get(i).getMajor().equals(currentProf.getMajor())) {
                    similarHelper(profiles.get(i));
                }
            } 
            
            ArrayList<Movie> otherFavs = favoriteMovieHelper(profiles.get(i));
            for (int j = 0; j < otherFavs.size(); j++) {
                if (curFavMovies.contains(otherFavs.get(j))) {
                    similarHelper(profiles.get(i));
                }
            }
        }
        
        //find the profile that matched the most times
        Profile mostSimilar = null;
        int mostSimilarValue = 0;
        if (similar != null) {
            for (Profile p: similar.keySet()) {
                if (similar.get(p) != null) {
                    if (similar.get(p) > mostSimilarValue) {
                        mostSimilar = p;
                    }
                }
                
            }
        } else {
            return "No matches found! Try rating some more movies!";
        }
        //find movie mostSimilar has rated 4/5 stars but current hasn't rated
        ArrayList<Movie> mostSimilarFaves = favoriteMovieHelper(mostSimilar);
        ArrayList<Rating> currentRatings = currentProf.getRatings();
        ArrayList<Movie> currentRatedMovies = new ArrayList<Movie>();
        for (int i = 0; i < currentRatings.size(); i++) {
            currentRatedMovies.add(currentRatings.get(i).getMovie());
        }
        for (int i = 0; i < mostSimilarFaves.size(); i++) {
            if (!currentRatedMovies.contains(mostSimilarFaves.get(i))) {
                return mostSimilarFaves.get(i).toString();
            }
        }
        
        return "No matches found! Try rating some more movies!";
        }catch (Error e) {
                return "Toy Story";
                
        }
    
    }

    /**
     * Creates Favorite Movies List from Profile based on highest rated movies.
     *
     * @param curProfile Profile
     * @return ArrayList<String>
     */
    private ArrayList<Movie> favoriteMovieHelper(Profile curProfile) {
        int hiRating = 4;
        ArrayList<Movie> favorites = new ArrayList<Movie>();
        if (curProfile != null) {
            if (curProfile.getRatings() == null) {
                return favorites;
            }
            for (int i = 0; i < curProfile.getRatings().size(); i++) {
                if (curProfile.getRatings().get(i).getRateValue() >= hiRating) {
                    favorites.add(curProfile.getRatings().get(i).getMovie());
                }
            }
        }
        return favorites;
    }

    /**
     * Increments the times the 2 users (via Profiles) have matched.
     *
     * @param curProfile current profile
     */
    private void similarHelper(Profile curProfile) {
        if (curProfile != null && similar != null) {
            try {
                if (!similar.containsKey(curProfile)) {
                    similar.put(curProfile, 1);
                } else {
                    int old = similar.get(curProfile);
                    similar.put(curProfile, old + 1);
                }
            } catch (Error e) {
                
            }
        }
    }
}