package database.interfaces;

import models.User;

public interface IUserService {
    boolean register(User user);
    User login(User user);
    boolean changePassword(User user,String newPassword);
    boolean changeEmail(User user,String newEmail);
    int getId(String email);
}
