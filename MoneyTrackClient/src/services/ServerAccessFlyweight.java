package services;

import networking.implementation.*;
import networking.interfaces.*;

public class ServerAccessFlyweight {
    private SessionStorage sessionStorage;
    private IUserServer userServer;
    private IAdminServer adminServer;
    private ICurrencyServer currencyServer;


    public ServerAccessFlyweight() {
    }

    public SessionStorage getSessionStorage() {
        if(sessionStorage == null){
            sessionStorage = new SessionStorage();
        }
        return sessionStorage;
    }

    public IUserServer getUserServer() {
        if(userServer == null){
            userServer = new UserServer();
        }
        return userServer;
    }
    public IAdminServer getAdminServer() {
        if(adminServer == null){
            adminServer = new AdminServer();
        }
        return adminServer;
    }
    public ICurrencyServer getCurrencyServer() {
        if(currencyServer == null){
            currencyServer = new CurrencyServer();
        }
        return currencyServer;
    }

}
