package mvvm.model.interfaces;

import models.User;

public interface IUserService {
    String register(User user);
    String login(User user);
}
