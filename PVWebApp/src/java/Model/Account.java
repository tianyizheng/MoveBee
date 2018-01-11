/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *Sets up the Account class for User to use
 * @author elizabethdudley, Tianyi Zheng
 */
@ManagedBean
@SessionScoped
public class Account implements java.io.Serializable {
    private String username;
    private String pass;
    private String email;
     /**
     *No-arg Constructor for Account
     */
    public Account() {
    }
    /**
     *Constructor for Account
     *@param username a string representing the username
     *@param pass a string representing the password
     *@param email a string representing the email
     */
    public Account(String username, String pass, String email) {
        this.username = username;
        this.pass = pass;
        this.email = email;
    }
    /**
     *getter method for username
     *@return the account's username
     */
    public String getUsername() {
        return this.username;
    }
    /**
     *getter method for password
     *@return account's password
     */
    public String getPass() {
        return this.pass;
    }
    /**
     *getter method for email
     *@return account's email
     */
    public String getEmail() {
        return this.email;
    }

     /**
     *setter method for username
     *@param username account's new username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     *setter method for password
     *@param pass account's new password
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     *setter method for email
     *@param email account's new email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
