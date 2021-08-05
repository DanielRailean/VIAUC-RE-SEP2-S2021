package mvvm.model.implementation;

import models.Account;
import models.Category;
import mvvm.model.Validator;
import mvvm.model.interfaces.IAccountService;
import networking.interfaces.IAccountServer;

import java.util.List;

public class AccountService implements IAccountService {
    private IAccountServer accountServer;

    public AccountService(IAccountServer accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    public String add(Account account) {
        if(!Validator.isValidAccountName(account.getName())) return "Name invalid!";
        if(!Validator.isValidBalance(account.getBalance())) return "Balance invalid!";
        if (accountServer.add(account)) {
            return "Account successfully added";
        }
        return "Unable to add account , most likely , name is already taken !";
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
    public String shareWith(int accountId, String shareWith) {
        if(accountServer.shareWith(accountId,shareWith)){
            return "Account successfully shared";
        }
        return "Unable to share category, please double-check user email!";
    }

    @Override
    public String update(Account account) {
        if(!Validator.isValidAccountName(account.getName())) return "Name invalid!";
        if(!Validator.isValidBalance(account.getBalance())) return "Balance invalid!";
        if (accountServer.add(account)) {
            return "Account successfully updated";
        }
        return "Unable to update account , most likely , name is already taken !";
    }

    @Override
    public String delete(int accountId) {
        if(accountServer.delete(accountId)){
            return "Account successfully deleted";
        }
        return "Unable to delete account, please try again later!";
    }
}
