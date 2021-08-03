package networking.implementation;

import models.User;
import networking.interfaces.IAdminServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AdminServer implements IAdminServer {
    private IAdminServer registerServer;

    public AdminServer() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4000);
            this.registerServer = (IAdminServer) registry.lookup("AdminServer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean register(User admin) {
        try {
            return registerServer.register(admin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User login(User admin) {
        try {
            return registerServer.login(admin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new User();
    }
}
