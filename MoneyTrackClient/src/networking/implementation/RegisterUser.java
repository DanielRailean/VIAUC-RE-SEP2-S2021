package networking.implementation;

import models.User;
import networking.interfaces.IRegisterServer;
import networking.interfaces.IRegisterUser;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RegisterUser implements IRegisterUser {
    private IRegisterServer registerServer;

    public RegisterUser() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4000);
            this.registerServer = (IRegisterServer) registry.lookup("RegisterServer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String register(User user) {
        try {
            return registerServer.register(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Unable to register!");
    }
}
