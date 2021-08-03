package networking.implementation;

import database.interfaces.IAdminService;
import models.User;
import networking.interfaces.IAdminServer;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class AdminServer implements IAdminServer {
    private IAdminService adminService;

    public AdminServer(IAdminService adminService, Registry registry) {
        try {
            UnicastRemoteObject.exportObject(this,0);
            this.adminService = adminService;
            registry.bind("AdminServer", this);
            System.out.println("AdminServer server started!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean register(User user) throws RemoteException {
        return adminService.register(user);
    }

    @Override
    public User login(User user) throws RemoteException {
        return adminService.login(user);
    }
}
