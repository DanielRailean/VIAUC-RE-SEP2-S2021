package mvvm.model.implementation;

import models.User;
import mvvm.model.interfaces.IRegister;
import networking.interfaces.IRegisterUser;
import services.LocalStorage;

public class Register implements IRegister {
    private IRegisterUser registerUser;

    public Register(IRegisterUser registerUser) {
        this.registerUser = registerUser;
    }

    @Override
    public String register(User user) {
        String result = registerUser.register(user);
        if (result.equals("Welcome aboard!")) {
            LocalStorage.getInstance().setCurrentUser(new User(user.getEmail(),user.getPassword()));
        }
        return result;
    }
}
