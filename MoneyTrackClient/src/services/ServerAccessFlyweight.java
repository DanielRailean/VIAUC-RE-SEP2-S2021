package services;

import networking.implementation.UserServer;
import networking.interfaces.IUserServer;

public class ServerAccessFlyweight {
    private SessionStorage sessionStorage;
    private IUserServer registerAccess;


    public ServerAccessFlyweight() {
    }

    public SessionStorage getSessionStorage() {
        if(sessionStorage == null){
            sessionStorage = new SessionStorage();
        }
        return sessionStorage;
    }

    public IUserServer getRegisterServer() {
        if(registerAccess == null){
            registerAccess = new UserServer();
        }
        return registerAccess;
    }


}
