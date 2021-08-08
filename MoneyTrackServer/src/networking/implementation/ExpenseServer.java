package networking.implementation;


import database.interfaces.IExpenseService;
import models.Expense;
import networking.interfaces.IExpenseServer;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.List;

public class ExpenseServer implements IExpenseServer {

    private IExpenseService expenseService;

    public ExpenseServer(IExpenseService expenseService, Registry registry) {
        try {
            UnicastRemoteObject.exportObject(this,0);
            this.expenseService = expenseService;
            registry.bind("ExpenseServer", this);
            System.out.println("ExpenseServer server started!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(Expense expense) throws RemoteException {
        return expenseService.add(expense);
    }

    @Override
    public Expense get(int expenseId) throws RemoteException {
        return expenseService.get(expenseId);

    }

    @Override
    public List<Expense> getExpensesDay(int userId, LocalDate date) throws RemoteException {
        return expenseService.getExpensesDay(userId,date);
    }
    @Override
    public List<Expense> getExpensesMonth(int userId, LocalDate date) throws RemoteException {
        return expenseService.getExpensesMonth(userId,date);
    }


    @Override
    public boolean delete(int expenseId) throws RemoteException {
        return expenseService.delete(expenseId);
    }

}
