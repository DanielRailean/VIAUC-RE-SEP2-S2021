package mvvm.model.implementation;

import models.User;
import mvvm.model.interfaces.IAdminService;
import networking.interfaces.IAdminServer;
import services.SessionStorage;
import mvvm.model.Validator;

public class AdminService implements IAdminService {
    private IAdminServer adminServer;

    public AdminService(IAdminServer adminServer) {
        this.adminServer = adminServer;
    }

    @Override
    public String register(User admin) {
        boolean result;
        if(!Validator.isValidAdminEmail(admin.getEmail())) return "Admin email invalid!";
        if(!Validator.isValidPassword(admin.getPassword())) return "Password should contain 5-30 characters";
        result = adminServer.register(admin);
        if (result) {
            return "Admin successfully added! He can sign in now";
        }
        return "Unable to register, try using another email address!";
    }

    @Override
    public String login(User admin) {
        User result;
        if(!Validator.isValidEmail(admin.getEmail())) return "Email invalid!";
        if(!Validator.isValidPassword(admin.getPassword())) return "Password should contain 5-30 characters";
        result = adminServer.login(admin);
        if (result.getPassword()!=null) {
            SessionStorage.getInstance().setCurrentUser(result);
            System.out.println("current user "+SessionStorage.getInstance().getCurrentUser());
            System.out.println("logged in "+ admin.getEmail());
            return "Logged in as admin!";
        }
        return "Unable to login, password or email incorrect!";
    }
}
