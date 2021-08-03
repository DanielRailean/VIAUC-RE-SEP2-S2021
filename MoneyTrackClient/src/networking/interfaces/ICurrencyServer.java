package networking.interfaces;

import models.Currency;

import java.rmi.Remote;
import java.util.List;

public interface ICurrencyServer extends Remote {
    boolean add(Currency currency);
    Currency get(int currencyId);
    Currency get(String currencyName);
    List<Currency> getAll();
    boolean update(Currency currency);
    boolean delete(int currencyId);
}
