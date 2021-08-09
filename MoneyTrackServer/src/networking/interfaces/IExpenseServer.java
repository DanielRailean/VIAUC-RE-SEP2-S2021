package networking.interfaces;

import models.Expense;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

public interface IExpenseServer extends Remote {
    boolean add(Expense expense) throws RemoteException;
    Expense get(int expenseId) throws RemoteException;
    List<Expense> getExpensesMonth(int userId, LocalDate date) throws RemoteException;
    List<Expense> getExpensesDay(int userId, LocalDate date) throws RemoteException;
    //    boolean update(Expense expense) throws RemoteException;
    boolean delete(int expenseId) throws RemoteException;
}
