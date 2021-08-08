package mvvm.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import mvvm.view.ViewController;
import mvvm.view.Views;
import mvvm.viewModel.ChangeEmail;
import mvvm.viewModel.Register;
import services.ViewHandler;
import services.ViewModelFlyweight;

public class ChangeEmailController extends ViewController {
    private ChangeEmail changeEmail;
    private ViewHandler viewHandler;

    @FXML
    private Label errorLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailField;

    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {
        this.changeEmail = viewModelFlyweight.getChangeEmail();
        this.viewHandler = viewHandler;
        emailField.textProperty().bindBidirectional(changeEmail.emailProperty());
        passwordField.textProperty().bindBidirectional(changeEmail.passwordProperty());
        errorLabel.textProperty().bindBidirectional(changeEmail.errorProperty());
        errorLabel.textProperty().setValue("Type new email and current password!");
    }

    public void save(MouseEvent mouseEvent){
        System.out.println(changeEmail.emailProperty().toString());
        changeEmail.changeEmail();
    }
    public void back(MouseEvent mouseEvent){
        viewHandler.setCenterView(Views.Details.name());
    }

    }
