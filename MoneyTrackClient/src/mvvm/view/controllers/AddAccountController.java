package mvvm.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import models.Currency;
import mvvm.view.ViewController;
import mvvm.view.Views;
import mvvm.viewModel.AddAccount;
import services.ViewHandler;
import services.ViewModelFlyweight;

public class AddAccountController extends ViewController {
    private AddAccount addAccount;
    private ViewHandler viewHandler;

    @FXML
    private Label errorLabel;
    @FXML
    private TextField name;
    @FXML
    private TextField balance;
    @FXML
    private ChoiceBox<Currency> currencies;

    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {
        this.addAccount = viewModelFlyweight.getAddAccount();
        this.viewHandler = viewHandler;
        balance.textProperty().bindBidirectional(addAccount.balanceProperty());
        name.textProperty().bindBidirectional(addAccount.nameProperty());
        errorLabel.textProperty().bindBidirectional(addAccount.errorProperty());
        currencies.setItems(addAccount.getCurrencies());
        currencies.setConverter(new StringConverter<Currency>() {
            @Override
            public String toString(Currency currency) {
                return currency.getName();
            }

            @Override
            public Currency fromString(String s) {
                return null;
            }
        });
        currencies.getSelectionModel().selectFirst();
        errorLabel.textProperty().setValue("Type account name, balance and select a currency");
    }

    public void add(MouseEvent mouseEvent){
        String result = addAccount.addAccount(currencies.getSelectionModel().getSelectedItem().getId());
        System.out.println(result);
    }
    public void back(MouseEvent mouseEvent){
        viewHandler.setCenterView(Views.Accounts.name());
    }


}
