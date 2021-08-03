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
        errorLabel.textProperty().setValue("Type email and password to log in!");
    }

    public void login(MouseEvent mouseEvent){
        String result = login.login();
        if(result.equals("Logged in as user!")) viewHandler.setBottomView(Views.BottomBar.name());
        else if (result.equals("Logged in as admin!")) viewHandler.setBottomView(Views.BottomBarAdmin.name());
        System.out.println(result);
    }
    public void back(MouseEvent mouseEvent){
        viewHandler.setCenterView(Views.Start.name());
    }


}
