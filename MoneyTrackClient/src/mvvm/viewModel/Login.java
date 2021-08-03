package mvvm.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.User;
import mvvm.model.interfaces.IUserService;

public class Login {
    private IUserService userService;

    private StringProperty error;
    private StringProperty password;
    private StringProperty email;

    public Login(IUserService userService) {
        this.userService = userService;
        error = new SimpleStringProperty();
        password = new SimpleStringProperty();
        email = new SimpleStringProperty();
    }

    public boolean login(){
        String result = userService.login(new User(email.getValue(),password.getValue()));
        error.setValue(result);
        return result.equals("Logged in!");
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
