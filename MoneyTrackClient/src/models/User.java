package models;

import java.io.Serializable;

/**
 * User model for the user Data.
 */

public class User implements Serializable {
    private String email;
    private String password;
    private int id;

    /**
     * Constructor with email and password
     * @param email userEmail
     * @param password userPassword
     */

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Empty constructor
     */

    public User() {
    }

    /**
     * Email getter
     * @return  email
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}