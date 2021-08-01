package mvvm.view.controllers;

import javafx.fxml.FXML;
import mvvm.view.ViewController;
import mvvm.viewModel.Register;
import services.ViewHandler;
import services.ViewModelFactory;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import services.Views;

public class RegisterController extends ViewController {
    private Register register;
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
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.register = viewModelFactory.getRegister();
        this.viewHandler = viewHandler;
        emailField.textProperty().bindBidirectional(register.emailProperty());
        passwordField.textProperty().bindBidirectional(register.passwordProperty());
        errorLabel.textProperty().bindBidirectional(register.errorProperty());
        errorLabel.textProperty().setValue("Type email and password to sign up");
    }

    public void onRegister(MouseEvent mouseEvent){
        System.out.println(register.emailProperty().toString());
        if(register.register()){
            System.out.println("You are registered");
        }
    }
    public void back(MouseEvent mouseEvent){
        viewHandler.setCenterView(Views.Start.name());
    }


    }
