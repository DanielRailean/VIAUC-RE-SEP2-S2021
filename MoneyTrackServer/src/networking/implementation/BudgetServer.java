package networking.implementation;


import database.interfaces.IBudgetService;
import database.interfaces.IUserService;
import models.Budget;
import networking.interfaces.IBudgetServer;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class BudgetServer implements IBudgetServer {

    private IBudgetService budgetService;

    public BudgetServer(IBudgetService budgetService, Registry registry) {
        try {
            UnicastRemoteObject.exportObject(this,0);
            this.budgetService = budgetService;
            registry.bind("BudgetServer", this);
            System.out.println("BudgetServer server started!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(Budget budget) throws RemoteException {
        return budgetService.add(budget);
    }

    @Override
    public Budget get(int budgetId) throws RemoteException {
        return budgetService.get(budgetId);

    }

    @Override
    public List<Budget> getBudgets(int userId) throws RemoteException {
        return budgetService.getBudgets(userId);
    }


    @Override
    public boolean delete(int budgetId) throws RemoteException {
        return budgetService.delete(budgetId);
    }

}
