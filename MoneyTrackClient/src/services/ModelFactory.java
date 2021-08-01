package services;

import mvvm.model.implementation.Register;
import mvvm.model.interfaces.IRegister;
import networking.implementation.RegisterUser;

public class ModelFactory {
    private ClientFactory clientFactory;
    private IRegister register;

    public ModelFactory(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    public IRegister getRegister() {
        if(register == null){
            register = new Register(clientFactory.getRegisterUser());
        }
        return register;
    }
}
