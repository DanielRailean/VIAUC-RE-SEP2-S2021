package networking.implementation;


import database.interfaces.IAccountService;
import database.interfaces.IUserService;
import models.Account;
import networking.interfaces.IAccountServer;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class AccountServer implements IAccountServer {

    private IAccountService accountService;
    private IUserService userService;

    public AccountServer(IAccountService accountService, IUserService userService, Registry registry) {
        try {
            UnicastRemoteObject.exportObject(this,0);
            this.accountService = accountService;
            this.userService = userService;
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
    public boolean shareWith(int accountId, String shareWith) throws RemoteException {
        int id = userService.getId(shareWith);
        if(id == 0) return false;
        return accountService.shareWith(accountId,id);
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
