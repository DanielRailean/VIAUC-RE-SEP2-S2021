package networking.implementation;


import database.interfaces.IAccountService;
import models.Account;
import networking.interfaces.IAccountServer;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class AccountServer implements IAccountServer {

    private IAccountService accountService;

    public AccountServer(IAccountService accountService, Registry registry) {
        try {
            UnicastRemoteObject.exportObject(this,0);
            this.accountService = accountService;
            registry.bind("AccountServer", this);
            System.out.println("AccountServer server started!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(Account account) throws RemoteException {
        return accountService.add(account);
    }

    @Override
    public Account get(int accountId) throws RemoteException {
        return accountService.get(accountId);

    }

    @Override
    public List<Account> getAccounts(int userId) throws RemoteException {
        return accountService.getAccounts(userId);
    }

    @Override
    public boolean shareWith(int accountId, int shareWith) throws RemoteException {
        return accountService.shareWith(accountId,shareWith);
    }

    @Override
    public boolean update(Account account) throws RemoteException {
        return accountService.update(account);
    }

    @Override
    public boolean delete(int accountId) throws RemoteException {
        return accountService.delete(accountId);
    }

}
