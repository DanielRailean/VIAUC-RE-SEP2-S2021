package database.interfaces;

import models.User;

public interface IAdminService {
    boolean emailFree(String email);
    boolean register(User admin);
    User login(User admin);
}
