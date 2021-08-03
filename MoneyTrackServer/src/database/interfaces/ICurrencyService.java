package database.interfaces;

import models.Currency;

import java.util.List;

public interface ICurrencyService {
    boolean add(Currency currency);
    Currency get(int currencyId);
    Currency get(String currencyName);
    List<Currency> getAll();
    boolean update(Currency currency);
    boolean delete(int currencyId);
}
