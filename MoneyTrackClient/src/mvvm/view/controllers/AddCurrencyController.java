package mvvm.view.controllers;

import javafx.fxml.FXML;
import mvvm.view.ViewController;
import mvvm.viewModel.AddCurrency;
import mvvm.viewModel.Login;
import services.ViewHandler;
import services.ViewModelFlyweight;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import mvvm.view.Views;

public class AddCurrencyController extends ViewController {
    private AddCurrency addCurrency;
    private ViewHandler viewHandler;

    @FXML
    private Label errorLabel;
    @FXML
    private TextField name;
    @FXML
    private TextField price;

    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {
        this.addCurrency = viewModelFlyweight.getAddCurrency();
        this.viewHandler = viewHandler;
        price.textProperty().bindBidirectional(addCurrency.priceInEurProperty());
        name.textProperty().bindBidirectional(addCurrency.nameProperty());
        errorLabel.textProperty().bindBidirectional(addCurrency.errorProperty());
        errorLabel.textProperty().setValue("Type currency name and price in EURO");
    }

    public void add(MouseEvent mouseEvent){
        String result = addCurrency.addCurrency();
        System.out.println(result);
    }
    public void back(MouseEvent mouseEvent){
        viewHandler.setCenterView(Views.Currencies.name());
    }


}
