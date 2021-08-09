package mvvm.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.*;
import mvvm.model.interfaces.*;
import services.SessionStorage;

import java.time.LocalDate;

public class AddExpense {
    private ICurrencyService currencyService;
    private IExpenseService expenseService;
    private IBudgetService budgetService;
    private IAccountService accountService;

    private ObservableList<Currency> currencies;
    private ObservableList<Budget> categories;
    private ObservableList<Account> accounts;

    private StringProperty name;
    private StringProperty error;
    private StringProperty amount;
    private StringProperty description;

    public AddExpense(ICurrencyService currencyService, IBudgetService budgetService,IAccountService accountService, IExpenseService expenseService) {
        this.currencyService = currencyService;
        this.expenseService = expenseService;
        this.budgetService = budgetService;
        this.accountService = accountService;
        error = new SimpleStringProperty();
        name = new SimpleStringProperty();
        amount = new SimpleStringProperty();
        description = new SimpleStringProperty();
        currencies = FXCollections.observableArrayList();
        currencies.addAll(currencyService.getAll());
        categories = FXCollections.observableArrayList();
        categories.addAll(budgetService.getBudgets(SessionStorage.getCurrentUser().getId(),LocalDate.now()));
        accounts = FXCollections.observableArrayList();
        accounts.addAll(accountService.getAccounts(SessionStorage.getCurrentUser().getId()));
    }

    public void addExpense(int categoryId,int currencyId,int accountId){
        float amount = 0;
        try{
            amount = Float.parseFloat(this.amount.getValue());
        }catch (Exception e){
            error.setValue("Please input a whole number!");
        }
        LocalDate now = LocalDate.now();
        String result = expenseService.add(new Expense(amount,description.getValue(),now.getDayOfMonth(),now.getMonthValue(),now.getYear(),accountId,currencyId,categoryId,SessionStorage.getCurrentUser().getId()));
        error.setValue(result);
    }

    public ObservableList<Budget> getCategories() {
        return categories;
    }

    public void setCategories(ObservableList<Budget> categories) {
        this.categories = categories;
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
