package services;

import networking.implementation.RegisterAccess;
import networking.interfaces.IRegisterServer;

public class ServerAccessFlyweight {
    private SessionStorage sessionStorage;
    private IRegisterServer registerAccess;


    public ServerAccessFlyweight() {
    }

    public SessionStorage getSessionStorage() {
        if(sessionStorage == null){
            sessionStorage = new SessionStorage();
        }
        return sessionStorage;
    }

    public IRegisterServer getRegisterAccess() {
        if(registerAccess == null){
            registerAccess = new RegisterAccess();
        }
        return registerAccess;
    }


}
