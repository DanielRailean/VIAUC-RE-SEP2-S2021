package mvvm.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Account;
import models.Currency;
import mvvm.model.interfaces.IAccountService;
import mvvm.model.interfaces.ICurrencyService;
import services.SessionStorage;

public class ShareAccount {
    private IAccountService accountService;

    private StringProperty name;
    private StringProperty error;
    private StringProperty shareWith;

    private Account account;

    public ShareAccount(IAccountService accountService) {
        this.accountService = accountService;
        account = (Account) SessionStorage.getItem("sharedAccount");
        error = new SimpleStringProperty();
        name = new SimpleStringProperty();
        shareWith = new SimpleStringProperty();
        name.setValue(account.getName());
        shareWith.setValue(account.getSharedEmail());
    }

    public void shareAccount(){
        error.setValue(accountService.shareWith(account.getId(),shareWith.getValue()));
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getShareWith() {
        return shareWith.get();
    }

    public StringProperty shareWithProperty() {
        return shareWith;
    }

    public void setShareWith(String shareWith) {
        this.shareWith.set(shareWith);
    }
}
