package mvvm.view.controllers;

import javafx.fxml.FXML;
import mvvm.view.ViewController;
import mvvm.viewModel.Login;
import services.ViewHandler;
import services.ViewModelFlyweight;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import mvvm.view.Views;

public class LoginController extends ViewController {
    private Login login;
    private ViewHandler viewHandler;

    @FXML
    private Label errorLabel;
    @FXML
    private Label registerLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailField;

    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {
        this.login = viewModelFlyweight.getLogin();
        this.viewHandler = viewHandler;
        emailField.textProperty().bindBidirectional(login.emailProperty());
        passwordField.textProperty().bindBidirectional(login.passwordProperty());
        errorLabel.textProperty().bindBidirectional(login.errorProperty());
        errorLabel.textProperty().setValue("Type email and password to log in up");
    }

    public void login(MouseEvent mouseEvent){
        System.out.println(login.login());
    }
    public void back(MouseEvent mouseEvent){
        viewHandler.setCenterView(Views.Start.name());
    }


}
