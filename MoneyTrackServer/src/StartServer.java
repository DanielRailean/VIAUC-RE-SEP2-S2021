
import database.implementation.*;
import database.interfaces.ICurrencyService;
import database.interfaces.IUserService;
import networking.implementation.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class StartServer {

    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(4000);
        IUserService userService = new UserService();
        ICurrencyService currencyService = new CurrencyService();
        new UserServer(userService, registry);
        new AdminServer(new AdminService(),registry);
        new CurrencyServer(currencyService,registry);
        new CategoryServer(new CategoryService(), registry);
        new AccountServer(new AccountService(currencyService),userService,registry);
        new BudgetServer(new BudgetService(currencyService),registry);
        new ExpenseServer(new ExpenseService(),registry);
    }

}
