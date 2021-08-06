package mvvm.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Account;
import mvvm.model.interfaces.IAccountService;
import services.SessionStorage;

public class Accounts {
    private IAccountService accountService;

    private StringProperty error;
    private ObservableList<Account> accounts;

    public Accounts(IAccountService accountService) {
        this.accountService = accountService;
        error = new SimpleStringProperty();
        accounts = FXCollections.observableArrayList();
        accounts.addAll(accountService.getAccounts(SessionStorage.getCurrentUser().getId()));
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
        accounts = FXCollections.observableArrayList();
        accounts.addAll(accountService.getAccounts(SessionStorage.getCurrentUser().getId()));
        return accounts;
    }

    public void setAccounts(ObservableList<Account> accounts) {
        this.accounts = accounts;
    }
}
