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
     * Constructor with email password and id
     * @param email userEmail
     * @param password userPassword
     * @param id userId
     */

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, int id) {
        this.email = email;
        this.password = password;
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
