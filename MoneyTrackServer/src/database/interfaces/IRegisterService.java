package database.interfaces;

import models.User;

public interface IRegisterService {
    boolean emailFree(String email);
    boolean register(User user);
}
