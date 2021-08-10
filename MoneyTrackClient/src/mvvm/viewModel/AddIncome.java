package mvvm.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Account;
import models.Budget;
import models.Currency;
import models.Income;
import mvvm.model.interfaces.IAccountService;
import mvvm.model.interfaces.IBudgetService;
import mvvm.model.interfaces.ICurrencyService;
import mvvm.model.interfaces.IIncomeService;
import services.SessionStorage;

import java.time.LocalDate;

public class AddIncome {
    private ICurrencyService currencyService;
    private IIncomeService incomeService;
    private IAccountService accountService;

    private ObservableList<Currency> currencies;
    private ObservableList<Account> accounts;

    private StringProperty name;
    private StringProperty error;
    private StringProperty amount;
    private StringProperty description;

    public AddIncome(ICurrencyService currencyService, IAccountService accountService, IIncomeService incomeService) {
        this.currencyService = currencyService;
        this.incomeService = incomeService;
        this.accountService = accountService;
        error = new SimpleStringProperty();
        name = new SimpleStringProperty();
        amount = new SimpleStringProperty();
        description = new SimpleStringProperty();
        currencies = FXCollections.observableArrayList();
        currencies.addAll(currencyService.getAll());
        accounts = FXCollections.observableArrayList();
        accounts.addAll(accountService.getAccounts(SessionStorage.getCurrentUser().getId()));
    }

    public void addIncome(int currencyId,int accountId){
        float amount = 0;
        try{
            amount = Float.parseFloat(this.amount.getValue());
        }catch (Exception e){
            error.setValue("Please input a whole number!");
        }
        LocalDate now = LocalDate.now();
        String result = incomeService.add(new Income(amount,description.getValue(),now.getDayOfMonth(),now.getMonthValue(),now.getYear(),accountId,currencyId,SessionStorage.getCurrentUser().getId()));
        error.setValue(result);
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
        return accounts;
    }

    public void setAccounts(ObservableList<Account> accounts) {
        this.accounts = accounts;
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
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

    public String getAmount() {
        return amount.get();
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
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
