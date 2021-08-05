package networking.implementation;

import models.Account;
import networking.interfaces.IAccountServer;
import networking.interfaces.IAdminServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class AccountServer implements IAccountServer{
    private IAccountServer accountServer;

    public AccountServer() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4000);
            this.accountServer = (IAccountServer) registry.lookup("AccountServer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(Account account) {
        return accountServer.add(account);
    }

    @Override
    public Account get(int accountId) {
        return accountServer.get(accountId);
    }

    @Override
    public List<Account> getAccounts(int userId) {
        return accountServer.getAccounts(userId);
    }

    @Override
    public boolean shareWith(int accountId, String shareWith) {
        return accountServer.shareWith(accountId,shareWith);
    }

    @Override
    public boolean update(Account account) {
        return accountServer.update(account);
    }

    @Override
    public boolean delete(int accountId) {
        return accountServer.delete(accountId);
    }
}
