package networking.interfaces;

import models.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IAccountServer extends Remote {
    boolean add(Account account) throws RemoteException;
    Account get(int accountId) throws RemoteException;
    List<Account> getAccounts(int userId) throws RemoteException;
    boolean shareWith(int accountId,String shareWith) throws RemoteException;
    boolean update(Account account) throws RemoteException;
    boolean delete(int accountId) throws RemoteException;
}
