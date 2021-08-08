package mvvm.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import mvvm.view.ViewController;
import mvvm.view.Views;
import mvvm.viewModel.ChangePassword;
import services.ViewHandler;
import services.ViewModelFlyweight;

public class ChangePasswordController extends ViewController {
    private ChangePassword changePassword;
    private ViewHandler viewHandler;

    @FXML
    private Label errorLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmNew;


    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {
        this.changePassword = viewModelFlyweight.getChangePassword();
        this.viewHandler = viewHandler;
        newPassword.textProperty().bindBidirectional(changePassword.newPasswordProperty());
        passwordField.textProperty().bindBidirectional(changePassword.passwordProperty());
        confirmNew.textProperty().bindBidirectional(changePassword.confirmNewProperty());
        errorLabel.textProperty().bindBidirectional(changePassword.errorProperty());
        errorLabel.textProperty().setValue("Type new passwords and current password!");
    }

    public void save(MouseEvent mouseEvent){
        System.out.println(changePassword.newPasswordProperty().toString());
        changePassword.changePassword();
    }
    public void back(MouseEvent mouseEvent){
        viewHandler.setCenterView(Views.Details.name());
    }


    }
