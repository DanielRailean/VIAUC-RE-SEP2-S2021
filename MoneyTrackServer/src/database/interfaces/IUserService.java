package database.interfaces;

import models.User;

public interface IUserService {
    boolean emailFree(String email);
    boolean register(User user);
    User login(User user);
}
