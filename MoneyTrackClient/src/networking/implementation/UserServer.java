package networking.implementation;

import models.User;
import networking.interfaces.IUserServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class UserServer implements IUserServer {
    private IUserServer registerServer;

    public UserServer() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4000);
            this.registerServer = (IUserServer) registry.lookup("UserServer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean register(User user) {
            return registerServer.register(user);
    }

    @Override
    public User login(User user) {
            return registerServer.login(user);
    }
}
