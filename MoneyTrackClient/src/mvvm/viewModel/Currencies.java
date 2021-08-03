package mvvm.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Currency;
import mvvm.model.interfaces.ICurrencyService;

public class Currencies {
    private ICurrencyService currencyService;

    private StringProperty error;
    private ObservableList<Currency> currencies;

    public Currencies(ICurrencyService currencyService) {
        this.currencyService = currencyService;
        error = new SimpleStringProperty();
        currencies = FXCollections.observableArrayList();
        currencies.addAll(currencyService.getAll());
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

    public ObservableList<Currency> getCurrencies() {
        currencies = FXCollections.observableArrayList();
        currencies.addAll(currencyService.getAll());
        return  currencies;
    }

    public void setCurrencies(ObservableList<Currency> currencies) {
        this.currencies = currencies;
    }
}
