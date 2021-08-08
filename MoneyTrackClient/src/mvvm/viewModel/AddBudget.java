package mvvm.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Budget;
import models.Category;
import models.Currency;
import mvvm.model.interfaces.IBudgetService;
import mvvm.model.interfaces.ICategoryService;
import mvvm.model.interfaces.ICurrencyService;
import services.SessionStorage;

import java.time.LocalDate;

public class AddBudget {
    private ICurrencyService currencyService;
    private IBudgetService budgetService;
    private ICategoryService categoryService;

    private ObservableList<Currency> currencies;
    private ObservableList<Category> categories;

    private StringProperty name;
    private StringProperty error;
    private StringProperty amount;

    public AddBudget(ICurrencyService currencyService, ICategoryService categoryService, IBudgetService budgetService) {
        this.currencyService = currencyService;
        this.budgetService = budgetService;
        this.categoryService = categoryService;
        error = new SimpleStringProperty();
        name = new SimpleStringProperty();
        amount = new SimpleStringProperty();
        currencies = FXCollections.observableArrayList();
        currencies.addAll(currencyService.getAll());
        categories = FXCollections.observableArrayList();
        categories.addAll(categoryService.getAll());
    }

    public void addBudget(int categoryId,int currencyId){
        int amount = 0;
        try{
            amount = Integer.parseInt(this.amount.getValue());
        }catch (Exception e){
            error.setValue("Please input a whole number!");
        }
        LocalDate now = LocalDate.now();
        String result = budgetService.add(new Budget(amount,now.getMonthValue(),now.getYear(),categoryId,currencyId,SessionStorage.getCurrentUser().getId()));
        error.setValue(result);
    }

    public ObservableList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ObservableList<Category> categories) {
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
