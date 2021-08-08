package mvvm.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.User;
import mvvm.model.interfaces.IUserService;
import services.SessionStorage;

public class ChangeEmail {
    private IUserService userService;

    private StringProperty error;
    private StringProperty password;
    private StringProperty email;

    public ChangeEmail(IUserService userService) {
        this.userService = userService;
        error = new SimpleStringProperty();
        password = new SimpleStringProperty();
        email = new SimpleStringProperty();
    }

    public void changeEmail(){
        User updated = SessionStorage.getCurrentUser();
        updated.setPassword(password.getValue());
        String result = userService.changeEmail(updated,email.getValue());
        updated.setEmail(email.getValue());
        userService.login(updated);
        error.setValue(result);
    }

    public String getError() {
        return error.get();
    }

    public StringProperty errorProperty() {
        return error;
    }

    public void setError(String error) {
        this.error.set(error);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
