package mvvm.model.implementation;

import models.User;
import mvvm.model.interfaces.IRegisterService;
import networking.interfaces.IRegisterServer;
import services.SessionStorage;
import mvvm.model.Validator;

public class RegisterService implements IRegisterService {
    private IRegisterServer registerServer;

    public RegisterService(IRegisterServer registerServer) {
        this.registerServer = registerServer;
    }

    @Override
    public String register(User user) {
        boolean result;
        if(!Validator.isValidEmail(user.getEmail())) return "Email invalid!";
        if(!Validator.isValidPassword(user.getPassword())) return "Password should contain 5-30 characters";
            result = registerServer.register(user);
        if (result) {
            SessionStorage.getInstance().setCurrentUser(new User(user.getEmail(),user.getPassword()));
            return "You are now registered, go back to sign in!";
        }
        return "Unable to register,  try using another email address!";
    }
}
