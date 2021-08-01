package networking.implementation;

import models.User;
import networking.interfaces.IRegisterServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RegisterAccess implements IRegisterServer {
    private IRegisterServer registerServer;

    public RegisterAccess() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4000);
            this.registerServer = (IRegisterServer) registry.lookup("RegisterServer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean register(User user) {
        try {
            return registerServer.register(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
