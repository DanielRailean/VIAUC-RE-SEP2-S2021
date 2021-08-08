package networking.interfaces;

import models.Budget;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IBudgetServer extends Remote {
    boolean add(Budget budget) throws RemoteException;
    Budget get(int budgetId) throws RemoteException;
    List<Budget> getBudgets(int userId) throws RemoteException;
    //    boolean update(Budget budget) throws RemoteException;
    boolean delete(int budgetId) throws RemoteException;
}
