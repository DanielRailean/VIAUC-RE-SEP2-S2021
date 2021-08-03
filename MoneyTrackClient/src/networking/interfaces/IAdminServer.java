package networking.interfaces;

import models.User;

import java.rmi.Remote;

public interface IAdminServer extends Remote {
    boolean register(User user);
    User login(User user);
}
