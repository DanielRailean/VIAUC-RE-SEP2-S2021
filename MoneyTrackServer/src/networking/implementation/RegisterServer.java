package networking.implementation;

import database.implementation.RegisterService;
import database.interfaces.IRegisterService;
import models.User;
import networking.interfaces.IRegisterServer;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RegisterServer implements IRegisterServer {
    private IRegisterService registerService;

    public RegisterServer(IRegisterService registerService, Registry registry) {
        try {
            UnicastRemoteObject.exportObject(this,0);
            this.registerService = registerService;
            registry.bind("RegisterServer", this);
            System.out.println("Register server started!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean register(User user) throws RemoteException {
        return registerService.register(user);
    }
}
