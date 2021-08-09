package networking.implementation;

import models.Expense;
import networking.interfaces.IExpenseServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.util.List;

public class ExpenseServer implements IExpenseServer{
    private IExpenseServer expenseServer;

    public ExpenseServer() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4000);
            this.expenseServer = (IExpenseServer) registry.lookup("ExpenseServer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(Expense expense) {
        return expenseServer.add(expense);
    }

    @Override
    public Expense get(int expenseId) {
        return expenseServer.get(expenseId);
    }

    @Override
    public List<Expense> getExpensesDay(int userId, LocalDate date) {
        return expenseServer.getExpensesDay(userId,date);
    }
    @Override
    public List<Expense> getExpensesMonth(int userId, LocalDate date) {
        return expenseServer.getExpensesMonth(userId,date);
    }

    @Override
    public boolean delete(int expenseId) {
        return expenseServer.delete(expenseId);
    }
}
