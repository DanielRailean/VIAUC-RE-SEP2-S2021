package services;

import mvvm.model.implementation.UserService;
import mvvm.model.interfaces.IUserService;

public class ServicesFlyweight {
    private ServerAccessFlyweight serverAccessFlyweight;
    private IUserService register;

    public ServicesFlyweight(ServerAccessFlyweight serverAccessFlyweight) {
        this.serverAccessFlyweight = serverAccessFlyweight;
    }

    public IUserService getUserService() {
        if(register == null){
            register = new UserService(serverAccessFlyweight.getRegisterServer());
        }
        return register;
    }
}
