package mvvm.model.implementation;

import models.Currency;
import mvvm.model.Validator;
import mvvm.model.interfaces.ICurrencyService;
import networking.interfaces.ICurrencyServer;

import java.util.List;

public class CurrencyService implements ICurrencyService {
    private ICurrencyServer currencyServer;

    public CurrencyService(ICurrencyServer currencyServer) {
        this.currencyServer = currencyServer;
    }

    @Override
    public String add(Currency currency) {
        if(!Validator.isValidCurrencyName(currency.getName())) return "Name invalid!";
        if(!Validator.isValidPriceInEur(currency.getPriceInEuro())) return "price should be between 0 and 10^7";
        if (currencyServer.add(currency)) {
            return "Currency successfully added";
        }
        return "Unable to add currency , most likely , name is already taken !";
    }

    @Override
    public Currency get(int currencyId) throws Exception {
        Currency currency = currencyServer.get(currencyId);
        if(currency!=null) return  currency;
        throw new Exception("Currency does not exist");
    }

    @Override
    public Currency get(String currencyName) throws Exception {
        Currency currency = currencyServer.get(currencyName);
        if(currency!=null) return  currency;
        throw new Exception("Currency does not exist");
    }

    @Override
    public List<Currency> getAll() {
        return currencyServer.getAll();
    }

    @Override
    public String update(Currency currency) {
        if(!Validator.isValidCurrencyName(currency.getName())) return "Name invalid!";
        if(!Validator.isValidPriceInEur(currency.getPriceInEuro())) return "price should be between 0 and 10^7";
        if (currencyServer.update(currency)) {
            return "Currency successfully updated";
        }
        return "Unable to update currency , most likely , name is already taken !";
    }

    @Override
    public String delete(int currencyId) {
        if(currencyServer.delete(currencyId)) return "Successfully deleted!";
        return "Such currency does not exist!";
    }
}
