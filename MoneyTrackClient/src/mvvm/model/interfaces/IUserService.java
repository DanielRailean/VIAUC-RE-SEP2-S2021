package mvvm.model.interfaces;

import models.User;

public interface IUserService {
    String register(User user);
    String login(User user);
    String changePassword(User user,String newPassword);
    String changeEmail(User user,String newEmail);
}
