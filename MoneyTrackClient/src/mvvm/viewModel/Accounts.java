package mvvm.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Account;
import mvvm.model.interfaces.IAccountService;
import services.SessionStorage;

import java.util.List;

public class Accounts {
    private IAccountService accountService;

    private StringProperty error;
    private ObservableList<Account> accounts;

    public Accounts(IAccountService accountService) {
        this.accountService = accountService;
        error = new SimpleStringProperty();
    }

    public String getError() {
        return error.get();
    }

    public StringProperty errorProperty() {
        return error;
    }

    public void setError(String error) {
        this.error.set(error);
    }

    public ObservableList<Account> getAccounts() {
        List<Account> accountIList = accountService.getAccounts(SessionStorage.getCurrentUser().getId());
        int userId = SessionStorage.getCurrentUser().getId();
        for (int i=0;i<accountIList.size();i++) {
            Account account = accountIList.get(i);
            if (account.getOwnerId() != userId ) {
                account.setName(account.getName()+" (shared with you)");
            }
            if (account.getSharedWith() != userId ) {
                account.setName(account.getName()+" (shared by you)");
            }
            accountIList.set(i,account);
            System.out.println(account);
        }
        accounts = FXCollections.observableArrayList();
        accounts.addAll(accountIList);
        return accounts;
    }

    public void setAccounts(ObservableList<Account> accounts) {
        this.accounts = accounts;
    }
}
