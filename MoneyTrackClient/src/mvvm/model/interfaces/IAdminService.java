package mvvm.model.interfaces;

import models.User;

public interface IAdminService {
    String register(User user);
    String login(User user);
}
