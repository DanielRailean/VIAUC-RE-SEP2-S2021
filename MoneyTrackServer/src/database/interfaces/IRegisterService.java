package database.interfaces;

import models.User;

public interface IRegisterService {
    boolean emailTaken(String email);
    boolean register(User user);
}
