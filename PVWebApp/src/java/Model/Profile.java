/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *a Profile class for User.
 * @author Tianyi Zheng
 */
@ManagedBean
@SessionScoped
public class Profile implements java.io.Serializable, Comparable {

    /**
     * Student Associated with Profile.
     */
    private Student student;

    /**
     * Email associated with Profile.
     */
    private String email;

    /**
     * Name of User.
     */
    private String fullName;

    /**
     * Major of User.
     */
    private String major;

    /**
     * Year of User.
     */
    private String classYear;

    /**
     * Bio of User.
     */
    private String bio;

    /**
     * All Ratings made by User.
     */
    private ArrayList<Rating> ratings = new ArrayList<>();

    /**
     * Messages to be Delivered to UI.
     */
    private UIComponent mainMessages;

    /**
     * Current Rating being saved/edited.
     */
    private Rating current;

    /**
     * No-argument Constructor for Profile.
     */
    public Profile() {

    }

    /**
     * Profile Constructor that takes in the email of the user.
     * @param email
     */
    public Profile(String email) {
        this.email = email;
    }

    /**
     * Getter for current rating.
     * @return Rating current
     */
    public Rating getC() {
        return current;
    }

    /**
     * Setter for Current Rating.
     * @param r
     */
    public void setC(Rating r) {
        this.current = r;
    }

    /**
     * Getter for student.
     * @return String student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Setter for Student.
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;
    }

     /**
    * Getter for email.
    * @return String email
    */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email.
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for Full Name.
     * @return String Full Name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Setter for Full Name.
     * @param fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Getter for Major.
     * @return String Major
     */
    public String getMajor() {
        return major;
    }

    /**
     * Setter for Major.
     * @param major
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * Getter for Class Year.
     * @return String Class Year
     */
    public String getClassYear() {
        return classYear;
    }

    /**
     * Setter for CLass Year.
     * @param classYear
     */
    public void setClassYear(String classYear) {
        this.classYear = classYear;
    }

    /**
     * Getter for Bio.
     * @return String bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * Setter for Bio.
     * @param bio
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * Getter for Ratings Array List.
     * @return ArrayList ratings
     */
    public ArrayList<Rating> getRatings() {
        if (ratings.isEmpty()) {
            RatingHelper helper = new RatingHelper();
            ratings = helper.findByEmail(email);
        }
        return ratings;
    }

    /**
     * Setter for UI Message.
     * @param mainMessages
     */
    public void setMainMessages(UIComponent mainMessages) {
        this.mainMessages = mainMessages;
    }

    /**
     * Getter for UI Message.
     * @return UIComponent mainMessages
     */
    public UIComponent getMainMessages() {
        return mainMessages;
    }
    /**
     * generates error messages .
     * @return to the main page
     */
    public String saveProfile() {
        ProfileHelper helper = new ProfileHelper();
        helper.saveProfile(this);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sa"
                + "ve successful.", "Save successful.");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(mainMessages.getClientId(context), message);
        return "main";
    }

    /**
     * gets this user's rating for a movie.
     * @param movie to check
     * @return to the main page
     */
    public Rating getRatingOfMovie(Movie movie) {
        ratings = this.getRatings();
        if (ratings != null) {
            for (Rating r : ratings) {
                if (r.getMovie().getMovieId().equals(movie.getMovieId())) {
                    current = r;
                    return r;
                }
            }
        }
        current = new Rating();
        current.setMovie(movie);
        ratings.add(current);
        return current;
    }

    /**
     * Overriding compareTo method to compare two profiles for recommendations.
     * @param o Object
     * @return int comparison
     */
    @Override
    public int compareTo(Object o) {
        Profile p = (Profile) o;
        return (this.fullName.compareTo(p.fullName));
    }

}
