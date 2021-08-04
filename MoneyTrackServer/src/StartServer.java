
import database.implementation.*;
import networking.implementation.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class StartServer {

    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(4000);
        new UserServer(new UserService(), registry);
        new AdminServer(new AdminService(),registry);
        new CurrencyServer(new CurrencyService(),registry);
        new CategoryServer(new CategoryService(), registry);
    }

}
