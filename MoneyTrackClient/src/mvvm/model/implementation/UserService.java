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
        if (result!=null) {
            SessionStorage.getInstance().setCurrentUser(result);
            System.out.println("current user "+SessionStorage.getInstance().getCurrentUser());
            System.out.println("logged in "+ user.getEmail());
            return "Logged in as user!";
        }
        return "Unable to login, password or email incorrect!";
    }

    @Override
    public String changePassword(User user, String newPassword) {
        boolean result;
        if(Validator.isValidAdminEmail(user.getEmail())) return "Not for admin uses!";
        if(!Validator.isValidEmail(user.getEmail())) return "Email invalid!";
        if(!Validator.isValidPassword(newPassword)) return "New password Password should contain 5-30 characters!";
        if(!Validator.isValidPassword(user.getPassword())) return "Old Password should contain 5-30 characters!";
        result = userServer.changePassword(user,newPassword);
        if (result) {
            return "Password successfully updated!";
        }
        return "Unable to change password, please double check your current password";
    }

    @Override
    public String changeEmail(User user, String newEmail) {
        boolean result;
        if(Validator.isValidAdminEmail(user.getEmail())) return "Not for admin uses!";
        if(!Validator.isValidEmail(user.getEmail())) return "Old email invalid!";
        if(!Validator.isValidEmail(newEmail)) return "New email invalid!";
        if(!Validator.isValidPassword(user.getPassword())) return "Password should contain 5-30 characters";
        result = userServer.changeEmail(user,newEmail);
        if (result) {
            return "Email successfully updated!";
        }
        return "Unable to change email, please double check your password";
    }
}
