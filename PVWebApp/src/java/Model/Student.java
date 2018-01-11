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
 * @author elizabethdudley
 */
@ManagedBean
@SessionScoped
public class Student extends Account implements java.io.Serializable {

    private String status;
    private Profile profile;

    /**
     * No arg constructor.
     */
    public Student() { }

    /**
     * Constructor.
     *
     * @param username the Student's username
     * @param pass the Student's password
     * @param email the Student's email
     */
    public Student(String username, String pass, String email) {
        super(username, pass, email);
        this.status = "Active";
        this.profile = new Profile(email);
    }

    /**
     * Getter for status.
     *
     * @return the current value for status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter for status.
     *
     * @param status the new value for status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getter for profile.
     *
     * @return the current value for profile.
     */
    public Profile getProfile() {
        return this.profile;
    }

    /**
     * Setter for profile.
     *
     * @param profile the new value for Profile.
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Updates the value for status within a relational database.
     *
     * @return the admin page
     */
    public String saveStudent() {
        AdminHelper helper = new AdminHelper();
        helper.saveStudent(this);
        return "admin";
    }

}
