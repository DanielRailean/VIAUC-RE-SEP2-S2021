package networking.interfaces;

import models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRegisterServer extends Remote {
    String register(User user) throws RemoteException;
}
