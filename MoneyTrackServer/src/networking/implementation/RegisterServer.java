package networking.implementation;

import manager.implementation.RegisterManager;
import models.User;
import networking.interfaces.IRegisterServer;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RegisterServer implements IRegisterServer {
    private RegisterManager registerManager;

    public RegisterServer(RegisterManager registerManager, Registry registry) {
        try {
            UnicastRemoteObject.exportObject(this,0);
            this.registerManager = registerManager;
            registry.bind("RegisterServer", this);
            System.out.println("Register server started!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String register(User user) throws RemoteException {
        return registerManager.register(user);
    }
}
