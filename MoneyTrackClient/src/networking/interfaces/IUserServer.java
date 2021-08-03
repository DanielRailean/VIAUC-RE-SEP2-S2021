package networking.interfaces;

import models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUserServer extends Remote {
    boolean register(User user);
    User login(User user);
}
