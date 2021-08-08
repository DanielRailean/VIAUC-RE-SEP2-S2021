package networking.implementation;

import database.interfaces.IUserService;
import models.User;
import networking.interfaces.IUserServer;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class UserServer implements IUserServer {
    private IUserService userService;

    public UserServer(IUserService userService, Registry registry) {
        try {
            UnicastRemoteObject.exportObject(this,0);
            this.userService = userService;
            registry.bind("UserServer", this);
            System.out.println("UserServer server started!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean register(User user) throws RemoteException {
        return userService.register(user);
    }

    @Override
    public User login(User user) throws RemoteException {
        return userService.login(user);
    }

    @Override
    public boolean changePassword(User user, String newPassword) throws RemoteException {
        return userService.changePassword(user,newPassword);
    }

    @Override
    public boolean changeEmail(User user, String newEmail) throws RemoteException {
        return userService.changeEmail(user,newEmail);
    }
}
