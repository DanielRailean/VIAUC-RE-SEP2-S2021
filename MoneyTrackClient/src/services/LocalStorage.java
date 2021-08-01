package services;

import models.User;

public class LocalStorage {
    private static LocalStorage localStorage;
    private User currentUser;

    public static LocalStorage getInstance() {
        if (localStorage == null) {
            localStorage = new ClientFactory().getLocalStorage();
        }
        return localStorage;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
