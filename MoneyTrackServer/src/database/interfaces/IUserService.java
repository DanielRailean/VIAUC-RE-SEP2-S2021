package database.interfaces;

import models.User;

public interface IUserService {
    boolean register(User user);
    User login(User user);
}
