package mvvm.view.controllers;

import javafx.fxml.FXML;
import mvvm.view.ViewController;
import mvvm.viewModel.RegisterAdmin;
import services.ViewHandler;
import services.ViewModelFlyweight;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import mvvm.view.Views;

public class RegisterAdminController extends ViewController {
    private RegisterAdmin registerAdmin;
    private ViewHandler viewHandler;

    @FXML
    private Label errorLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailField;

    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {
        this.registerAdmin = viewModelFlyweight.getRegisterAdmin();
        this.viewHandler = viewHandler;
        emailField.textProperty().bindBidirectional(registerAdmin.emailProperty());
        passwordField.textProperty().bindBidirectional(registerAdmin.passwordProperty());
        errorLabel.textProperty().bindBidirectional(registerAdmin.errorProperty());
        errorLabel.textProperty().setValue("Type email and password to sign up!");
    }

    public void onRegister(MouseEvent mouseEvent){
        System.out.println(registerAdmin.emailProperty().toString());
        if(registerAdmin.register()){
            System.out.println("You are registered");
        }
    }
    public void back(MouseEvent mouseEvent){
        viewHandler.setCenterView(Views.Start.name());
    }


}
