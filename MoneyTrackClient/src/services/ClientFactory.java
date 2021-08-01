package services;

import networking.implementation.RegisterUser;
import networking.interfaces.IRegisterUser;

public class ClientFactory {
    private LocalStorage localStorage;
    private IRegisterUser registerUser;


    public ClientFactory() {
    }

    public LocalStorage getLocalStorage() {
        if(localStorage == null){
            localStorage = new LocalStorage();
        }
        return localStorage;
    }

    public IRegisterUser getRegisterUser() {
        if(registerUser == null){
            registerUser = new RegisterUser();
        }
        return registerUser;
    }

}
