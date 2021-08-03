package networking.implementation;

import database.implementation.CurrencyService;
import database.interfaces.IAdminService;
import database.interfaces.ICurrencyService;
import models.Currency;
import networking.interfaces.ICurrencyServer;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CurrencyServer implements ICurrencyServer {

    private ICurrencyService currencyService;

    public CurrencyServer(ICurrencyService currencyService, Registry registry) {
        try {
            UnicastRemoteObject.exportObject(this,0);
            this.currencyService = currencyService;
            registry.bind("CurrencyServer", this);
            System.out.println("CurrencyServer server started!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(Currency currency) throws RemoteException {
        return currencyService.add(currency);
    }

    @Override
    public Currency get(int currencyId) throws RemoteException {
        return currencyService.get(currencyId);

    }

    @Override
    public Currency get(String currencyName) throws RemoteException {
        return currencyService.get(currencyName);
    }

    @Override
    public List<Currency> getAll() throws RemoteException {
        return currencyService.getAll();
    }

    @Override
    public boolean update(Currency currency) throws RemoteException {
        return currencyService.update(currency);
    }

    @Override
    public boolean delete(int currencyId) throws RemoteException {
        return currencyService.delete(currencyId);
    }
}
