package networking.implementation;

import models.Currency;
import networking.interfaces.ICurrencyServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class CurrencyServer implements ICurrencyServer {
    private ICurrencyServer currencyServer;

    public CurrencyServer() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4000);
            this.currencyServer = (ICurrencyServer) registry.lookup("CurrenciesServer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(Currency currency) {
        return currencyServer.add(currency);
    }

    @Override
    public Currency get(int currencyId) {
        return currencyServer.get(currencyId);
    }

    @Override
    public Currency get(String currencyName) {
        return currencyServer.get(currencyName);
    }

    @Override
    public List<Currency> getAll() {
        return currencyServer.getAll();
    }

    @Override
    public boolean update(Currency currency) {
        return currencyServer.update(currency);
    }

    @Override
    public boolean delete(int currencyId) {
        return currencyServer.delete(currencyId);
    }
}
