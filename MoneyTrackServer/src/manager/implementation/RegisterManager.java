package manager.implementation;

import database.implementation.RegisterService;
import database.interfaces.IRegisterService;
import manager.interfaces.IRegisterManager;
import models.User;

public class RegisterManager implements IRegisterManager {
    private IRegisterService registerService;

    public RegisterManager() {
        this.registerService = new RegisterService();
    }

    @Override
    public String register(User user) {
        boolean emailFree = registerService.emailTaken(user.getEmail());

        if (!emailFree) {
            return "Email already taken!";
        } else {
            if (registerService.register(user)) {
                return "You are now registered!";
            } else {
                return "Error happened on the server side!";
            }
        }
    }
}
