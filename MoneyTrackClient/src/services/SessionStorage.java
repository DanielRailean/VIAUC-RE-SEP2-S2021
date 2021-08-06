package services;

import models.User;

import java.util.HashMap;

public class SessionStorage {
    private static SessionStorage sessionStorage;
    private static User currentUser;
    private static HashMap<String, Object> items = new HashMap<>();


    public static SessionStorage getInstance() {
        if (sessionStorage == null) {
            sessionStorage = new ServerAccessFlyweight().getSessionStorage();
        }
        return sessionStorage;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        SessionStorage.currentUser = currentUser;
    }

    public static Object getItem(String name){
        return items.get(name);
    };
    public static void setItem(String key, Object value){
        items.put(key,value);
    }
    public static void deleteItem(String key){
        items.remove(key);
    }
}
