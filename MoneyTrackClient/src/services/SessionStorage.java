package services;

import models.User;

public class SessionStorage {
    private static SessionStorage sessionStorage;
    private User currentUser;

    public static SessionStorage getInstance() {
        if (sessionStorage == null) {
            sessionStorage = new ServerAccessFlyweight().getSessionStorage();
        }
        return sessionStorage;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
