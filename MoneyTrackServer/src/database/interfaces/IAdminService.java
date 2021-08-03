package database.interfaces;

import models.User;

public interface IAdminService {
    boolean register(User admin);
    User login(User admin);
}
