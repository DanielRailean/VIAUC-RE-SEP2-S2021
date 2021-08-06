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

public class UpdateAccount {
    private ICurrencyService currencyService;
    private IAccountService accountService;

    private ObservableList<Currency> currencies;

    private StringProperty name;
    private StringProperty error;
    private StringProperty balance;

    private Account account;

    public UpdateAccount(ICurrencyService currencyService, IAccountService accountService) {
        this.currencyService = currencyService;
        this.accountService = accountService;
        account = (Account) SessionStorage.getItem("updatedAccount");
        error = new SimpleStringProperty();
        name = new SimpleStringProperty();
        balance = new SimpleStringProperty();
        name.setValue(account.getName());
        balance.setValue(account.getBalance()+"");
        currencies = FXCollections.observableArrayList();
        currencies.addAll(currencyService.getAll());
    }

    public String updateAccount(int currencyId){
        float startBalance;
        try{
            startBalance = Float.parseFloat(balance.getValue());
        }catch (Exception e){
            error.setValue("Please input a real number!");
            return "Please input a real number!";
        }
        account.setBalance(startBalance);
        account.setName(name.getValue());
        account.setCurrencyId(currencyId);
        String result = accountService.update(account);
        error.setValue(result);
        return result;
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

    public String getBalance() {
        return balance.get();
    }

    public StringProperty balanceProperty() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance.set(balance);
    }

    public ObservableList<Currency> getCurrencies() {
        currencies = FXCollections.observableArrayList();
        currencies.addAll(currencyService.getAll());
        return  currencies;
    }

    public void setCurrencies(ObservableList<Currency> currencies) {
        this.currencies = currencies;
    }
}
