package mvvm.model.interfaces;

import models.Account;

import java.util.List;

public interface IAccountService {
    String add(Account account);
    Account get(int accountId);
    List<Account> getAccounts(int userId);
    String shareWith(int accountId,String shareWith);
    String update(Account account);
    String delete(int accountId);
}
