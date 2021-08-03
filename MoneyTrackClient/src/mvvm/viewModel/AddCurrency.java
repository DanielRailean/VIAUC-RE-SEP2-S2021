package mvvm.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.Currency;
import mvvm.model.interfaces.ICurrencyService;

public class AddCurrency {
    private ICurrencyService currencyService;

    private StringProperty error;
    private StringProperty name;
    private StringProperty priceInEur;

    public AddCurrency(ICurrencyService currencyService) {
        this.currencyService = currencyService;
        error = new SimpleStringProperty();
        name = new SimpleStringProperty();
        priceInEur = new SimpleStringProperty();
    }

    public String addCurrency(){
        float price;
        try{
             price = Float.parseFloat(priceInEur.getValue());
        }catch (Exception e){
            error.setValue("Please input a real number!");
            return "Please input a real number!";
        }
        String result = currencyService.add(new Currency(name.getValue(),price));
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

    public String getPriceInEur() {
        return priceInEur.get();
    }

    public StringProperty priceInEurProperty() {
        return priceInEur;
    }

    public void setPriceInEur(String priceInEur) {
        this.priceInEur.set(priceInEur);
    }
}
