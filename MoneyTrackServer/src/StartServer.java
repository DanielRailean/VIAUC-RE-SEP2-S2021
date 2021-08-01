
import database.implementation.RegisterService;
import networking.implementation.RegisterServer;
import networking.interfaces.IRegisterServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class StartServer {

    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(4000);
        IRegisterServer registerServer = new RegisterServer(new RegisterService(), registry);
    }

}
