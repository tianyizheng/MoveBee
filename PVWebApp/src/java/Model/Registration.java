/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Registers a Student by saving it to a relational database. Logs in and logs
 * out existing Student.
 *
 * @author elizabethdudley, Tianyi Zheng
 */

@ManagedBean
@SessionScoped
public class Registration {

    private String username;
    private String pass;
    private String email;
    private UIComponent mainMessages;
    private UIComponent loginMessages;
    private Student current;

    /**
     * No arg constructor.
     */
    public Registration() {

    }

    /**
     * Getter for username.
     *
     * @return the current value for username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Setter for username.
     *
     * @param username the new value for username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for password.
     *
     * @return the current value for pass
     */
    public String getPass() {
        return this.pass;
    }

    /**
     * Setter for password.
     *
     * @param pass the new value for pass
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Getter for email.
     *
     * @return the current value for email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter for email.
     *
     * @param email the new value for email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for current Student.
     *
     * @return the Student currently logged in
     */
    public Student getCurrent() {
        return current;
    }

    /**
     * Setter for current Student.
     *
     * @param current the Student currently logged in
     */
    public void setCurrent(Student current) {
        this.current = current;
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
     * Setter for Login Specific UI Messages.
     *
     * @param loginMessages informational and error messages incurred during
     * login
     */
    public void setLoginMessages(UIComponent loginMessages) {
        this.loginMessages = loginMessages;
    }

    /**
     * Getter for Login Specific UI Messages.
     *
     * @return informational and error messages incurred during
     * login
     */
    public UIComponent getLoginMessages() {
        return loginMessages;
    }

    /**
     * Saves a new Student to a relational database.
     *
     * @return the main page is registration is successful; the index page if
     * the email provided is already in use
     */
    public String register() {
        RegistrationHelper helper = new RegistrationHelper();
        if (helper.findByEmail(email) != null) {
            FacesMessage message = new FacesMessage(
                    FacesMessage.SEVERITY_INFO,
                    "A user is already registered under this email. "
                            + "Enter your password to login.",
                    "A user is already registered under this email. "
                            + "Enter your password to login."
            );
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(loginMessages.getClientId(context), message);
            return "index";
        } else {
            current = new Student(username, pass, email);

            helper.registerStudent(current);

            FacesMessage message = new FacesMessage(
                    FacesMessage.SEVERITY_INFO,
                    "You are now a registered user.",
                    "You are now a registered user."
            );
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(mainMessages.getClientId(context), message);
            return "main";
        }
    }

    /**
     * Logs a Student in by first searching a relational database for a given
     * email and password then setting the Student found to be the current
     * Student.
     *
     * @return the main page if login is successful; the index page otherwise
     */
    public String login() {
        RegistrationHelper helper = new RegistrationHelper();
        if (helper.findByEmail(email) == null) {
            FacesMessage message = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Sorry, this is not a registered email.",
                    "Sorry, this is not a registered email."
            );
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(loginMessages.getClientId(context), message);
            return "index";
        } else {
            Student studentByEmail = helper.findByEmail(email);
            if (studentByEmail.getStatus().equals("Banned")) {
                FacesMessage message = new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        "This User has been Banned.",
                        "This User has been Banned."
                );
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(loginMessages.getClientId(context), message);
                return "index";
            }
            if (pass.equals(studentByEmail.getPass())) {
                current = studentByEmail;
                return "main";
            } else {
                FacesMessage message = new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        "Incorrect email and password combination.",
                        "Incorrect email and password combination."
                );
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(loginMessages.getClientId(context), message);
                return "index";
            }
        }
    }

    /**
     * Logs a Student out by setting the current Student to null.
     *
     * @return the index page
     */
    public String logout() {
        current = null;

        FacesMessage message = new FacesMessage(
                FacesMessage.SEVERITY_INFO,
                "Logout successful.",
                "Logout successful."
        );
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(loginMessages.getClientId(context), message);

        HttpSession sesh = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(true);
        sesh.invalidate();

        return "index";
    }
}
