package networking.interfaces;
import models.Account;

import java.rmi.Remote;
import java.util.List;

public interface IAccountServer extends Remote {
    boolean add(Account account);
    Account get(int accountId);
    List<Account> getAccounts(int userId);
    boolean shareWith(int accountId,String shareWith);
    boolean update(Account account);
    boolean delete(int accountId);
}
