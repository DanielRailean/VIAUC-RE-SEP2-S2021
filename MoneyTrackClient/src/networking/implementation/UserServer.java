package networking.implementation;

import models.User;
import networking.interfaces.IUserServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class UserServer implements IUserServer {
    private IUserServer userServer;

    public UserServer() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4000);
            this.userServer = (IUserServer) registry.lookup("UserServer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean register(User user) {
            return userServer.register(user);
    }

    @Override
    public User login(User user) {
            return userServer.login(user);
    }

    @Override
    public boolean changePassword(User user, String newPassword) {
        return userServer.changePassword(user,newPassword);
    }

    @Override
    public boolean changeEmail(User user, String newEmail) {
        return userServer.changeEmail(user,newEmail);
    }
}
