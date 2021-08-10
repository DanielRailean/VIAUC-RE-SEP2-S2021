package networking.interfaces;

import models.Income;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

public interface IIncomeServer extends Remote {
    boolean add(Income income) throws RemoteException;
    Income get(int incomeId) throws RemoteException;
    List<Income> getIncomesMonth(int userId, LocalDate date) throws RemoteException;
    List<Income> getIncomesDay(int userId, LocalDate date) throws RemoteException;
    //    boolean update(Income income) throws RemoteException;
    boolean delete(int incomeId) throws RemoteException;
}
