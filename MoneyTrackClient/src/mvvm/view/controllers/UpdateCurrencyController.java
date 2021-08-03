package mvvm.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import mvvm.view.ViewController;
import mvvm.view.Views;
import mvvm.viewModel.AddCurrency;
import mvvm.viewModel.Currencies;
import mvvm.viewModel.UpdateCurrency;
import services.ViewHandler;
import services.ViewModelFlyweight;

public class UpdateCurrencyController extends ViewController {
    private UpdateCurrency updateCurrency;
    private ViewHandler viewHandler;

    @FXML
    private Label errorLabel;
    @FXML
    private TextField name;
    @FXML
    private TextField price;

    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {
        this.updateCurrency = viewModelFlyweight.getUpdateCurrency();
        this.viewHandler = viewHandler;
        errorLabel.textProperty().setValue("Type currency name and price in EURO");
        name.textProperty().bindBidirectional(updateCurrency.nameProperty());
        price.textProperty().bindBidirectional(updateCurrency.priceInEurProperty());
        errorLabel.textProperty().bindBidirectional(updateCurrency.errorProperty());
        name.textProperty().setValue(updateCurrency.getUpdated().getName());
        price.textProperty().setValue(updateCurrency.getUpdated().getPriceInEuro()+"");
        name.setDisable(true);
    }

    public void update(MouseEvent mouseEvent){
        String result = updateCurrency.updateCurrency();
        System.out.println(result);
    }
    public void back(MouseEvent mouseEvent){
        viewHandler.setCenterView(Views.Currencies.name());
    }


}
