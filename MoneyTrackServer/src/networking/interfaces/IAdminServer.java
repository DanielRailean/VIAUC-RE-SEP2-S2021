package networking.interfaces;

import models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAdminServer extends Remote {
    boolean register(User user) throws RemoteException;
    User login(User user) throws RemoteException;
}
