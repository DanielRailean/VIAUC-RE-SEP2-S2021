package networking.interfaces;

import models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUserServer extends Remote {
    boolean register(User user) throws RemoteException;
    User login(User user) throws RemoteException;
    boolean changePassword(User user,String newPassword) throws RemoteException;
    boolean changeEmail(User user,String newEmail) throws RemoteException;
}
