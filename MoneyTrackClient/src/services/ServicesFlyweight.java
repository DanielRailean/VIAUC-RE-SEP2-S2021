package services;

import mvvm.model.implementation.RegisterService;
import mvvm.model.interfaces.IRegisterService;

public class ServicesFlyweight {
    private ServerAccessFlyweight serverAccessFlyweight;
    private IRegisterService register;

    public ServicesFlyweight(ServerAccessFlyweight serverAccessFlyweight) {
        this.serverAccessFlyweight = serverAccessFlyweight;
    }

    public IRegisterService getRegister() {
        if(register == null){
            register = new RegisterService(serverAccessFlyweight.getRegisterAccess());
        }
        return register;
    }
}
