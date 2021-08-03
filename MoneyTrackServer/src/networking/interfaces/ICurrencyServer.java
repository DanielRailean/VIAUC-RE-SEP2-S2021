package networking.interfaces;

import models.Currency;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ICurrencyServer extends Remote {
    boolean add(Currency currency) throws RemoteException;
    Currency get(int currencyId) throws RemoteException;
    Currency get(String currencyName) throws RemoteException;
    List<Currency> getAll() throws RemoteException;
    boolean update(Currency currency) throws RemoteException;
    boolean delete(int currencyId) throws RemoteException;
}
