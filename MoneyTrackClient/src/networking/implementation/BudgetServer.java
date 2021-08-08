package networking.implementation;

import models.Budget;
import networking.interfaces.IBudgetServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.util.List;

public class BudgetServer implements IBudgetServer{
    private IBudgetServer budgetServer;

    public BudgetServer() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4000);
            this.budgetServer = (IBudgetServer) registry.lookup("BudgetServer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(Budget budget) {
        return budgetServer.add(budget);
    }

    @Override
    public Budget get(int budgetId) {
        return budgetServer.get(budgetId);
    }

    @Override
    public List<Budget> getBudgets(int userId, LocalDate date) {
        return budgetServer.getBudgets(userId,date);
    }

    @Override
    public boolean delete(int budgetId) {
        return budgetServer.delete(budgetId);
    }
}
