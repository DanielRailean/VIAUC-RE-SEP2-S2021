
import database.implementation.*;
import networking.implementation.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class StartServer {

    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(4000);
        UserService userService = new UserService();
        new UserServer(userService, registry);
        new AdminServer(new AdminService(),registry);
        new CurrencyServer(new CurrencyService(),registry);
        new CategoryServer(new CategoryService(), registry);
        new AccountServer(new AccountService(),userService,registry);
    }

}
