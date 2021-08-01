package mvvm.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.User;
import mvvm.model.interfaces.IRegister;

public class Register {
    private IRegister registerModel;

    private StringProperty error;
    private StringProperty password;
    private StringProperty email;

    public Register(IRegister registerModel) {
        this.registerModel = registerModel;
        error = new SimpleStringProperty();
        password = new SimpleStringProperty();
        email = new SimpleStringProperty();
    }

    public boolean register(){
        String result = registerModel.register(new User(email.getValue(),password.getValue()));
        error.setValue(result);
        return result.equals("You are now registered!");
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
