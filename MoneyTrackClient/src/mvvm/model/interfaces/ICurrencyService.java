package mvvm.model.interfaces;

import models.Currency;

import java.util.List;

public interface ICurrencyService {
    String add(Currency currency);
    Currency get(int currencyId) throws Exception;
    Currency get(String currencyName) throws Exception;
    List<Currency> getAll();
    String update(Currency currency);
    String delete(int currencyId);
}
