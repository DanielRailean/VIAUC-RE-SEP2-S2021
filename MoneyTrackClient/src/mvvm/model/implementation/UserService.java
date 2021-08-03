package mvvm.model.implementation;

import models.User;
import mvvm.model.interfaces.IUserService;
import networking.interfaces.IUserServer;
import services.SessionStorage;
import mvvm.model.Validator;

public class UserService implements IUserService {
    private IUserServer userServer;

    public UserService(IUserServer userServer) {
        this.userServer = userServer;
    }

    @Override
    public String register(User user) {
        boolean result;
        if(Validator.isValidAdminEmail(user.getEmail())) return "Not for admin registration!";
        if(!Validator.isValidEmail(user.getEmail())) return "Email invalid!";
        if(!Validator.isValidPassword(user.getPassword())) return "Password should contain 5-30 characters";
            result = userServer.register(user);
        if (result) {
            return "You are now registered, go back to sign in!";
        }
        return "Unable to register,  try using another email address!";
    }

    @Override
    public String login(User user) {
        User result;
        if(!Validator.isValidEmail(user.getEmail())) return "Email invalid!";
        if(!Validator.isValidPassword(user.getPassword())) return "Password should contain 5-30 characters";
        result = userServer.login(user);
        if (result.getPassword()!=null) {
            SessionStorage.getInstance().setCurrentUser(result);
            System.out.println("current user "+SessionStorage.getInstance().getCurrentUser());
            System.out.println("logged in "+ user.getEmail());
            return "Logged in as user!";
        }
        return "Unable to login, password or email incorrect!";
    }
}
