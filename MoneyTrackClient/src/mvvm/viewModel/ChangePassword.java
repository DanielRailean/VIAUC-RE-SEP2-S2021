package mvvm.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.User;
import mvvm.model.interfaces.IUserService;
import services.SessionStorage;

public class ChangePassword {
    private IUserService userService;

    private StringProperty error;
    private StringProperty password;
    private StringProperty newPassword;
    private StringProperty confirmNew;

    public ChangePassword(IUserService userService) {
        this.userService = userService;
        error = new SimpleStringProperty();
        password = new SimpleStringProperty();
        newPassword = new SimpleStringProperty();
        confirmNew = new SimpleStringProperty();
    }

    public void changePassword(){
        if(!newPassword.getValue().equals(confirmNew.getValue())) {
            error.setValue("New passwords do not match");
            return;
        }
        User updated = SessionStorage.getCurrentUser();
        updated.setPassword(password.getValue());
        String result = userService.changePassword(updated,newPassword.getValue());
        updated.setPassword(newPassword.getValue());
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

    public String getNewPassword() {
        return newPassword.get();
    }

    public StringProperty newPasswordProperty() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword.set(newPassword);
    }

    public String getConfirmNew() {
        return confirmNew.get();
    }

    public StringProperty confirmNewProperty() {
        return confirmNew;
    }

    public void setConfirmNew(String confirmNew) {
        this.confirmNew.set(confirmNew);
    }
}
