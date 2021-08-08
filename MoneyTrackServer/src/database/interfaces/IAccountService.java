package database.interfaces;

import models.Account;
import models.Expense;

import java.util.List;

public interface IAccountService {
    boolean add(Account account);
    Account get(int accountId);
    List<Account> getAccounts(int userId);
    boolean shareWith(int accountId,int shareWith);
    boolean addExpense(Expense expense);
    boolean deleteExpense(Expense expense);
    boolean update(Account account);
    boolean delete(int accountId);
}
