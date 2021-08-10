
import database.implementation.*;
import database.interfaces.IAccountService;
import database.interfaces.IBudgetService;
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
        IBudgetService budgetService = new BudgetService(currencyService);
        IAccountService accountService = new AccountService(currencyService);
        new UserServer(userService, registry);
        new AdminServer(new AdminService(),registry);
        new CurrencyServer(currencyService,registry);
        new CategoryServer(new CategoryService(), registry);
        new AccountServer(accountService,userService,registry);
        new BudgetServer(budgetService,registry);
        new ExpenseServer(new ExpenseService(budgetService,accountService),registry);
        new IncomeServer(new IncomeService(accountService),registry);
    }

}
