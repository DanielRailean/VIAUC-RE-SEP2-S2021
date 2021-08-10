package mvvm.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import models.Account;
import models.Budget;
import models.Currency;
import mvvm.view.ViewController;
import mvvm.view.Views;
import mvvm.viewModel.AddIncome;
import services.ViewHandler;
import services.ViewModelFlyweight;

public class AddIncomeController extends ViewController {
    private AddIncome addIncome;
    private ViewHandler viewHandler;

    @FXML
    private Label errorLabel;
    @FXML
    private TextField amount;
    @FXML
    private TextField description;
    @FXML
    private ChoiceBox<Currency> currencies;
    @FXML
    private ChoiceBox<Account> accounts;

    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {
        this.addIncome = viewModelFlyweight.getAddIncome();
        this.viewHandler = viewHandler;
        amount.textProperty().bindBidirectional(addIncome.amountProperty());
        errorLabel.textProperty().bindBidirectional(addIncome.errorProperty());
        description.textProperty().bindBidirectional(addIncome.descriptionProperty());
        currencies.setItems(addIncome.getCurrencies());
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
        accounts.setItems(addIncome.getAccounts());
        accounts.setConverter(new StringConverter<Account>() {
            @Override
            public String toString(Account account) {
                return account.getName();
            }

            @Override
            public Account fromString(String s) {
                return null;
            }
        });
        currencies.getSelectionModel().selectFirst();
        accounts.getSelectionModel().selectFirst();
        errorLabel.textProperty().setValue("Select an account ,currency and budget and type in the amount and description");
    }

    public void add(MouseEvent mouseEvent){
        addIncome.addIncome(currencies.getSelectionModel().getSelectedItem().getId(),accounts.getSelectionModel().getSelectedItem().getId());
    }
    public void back(MouseEvent mouseEvent){
        viewHandler.setCenterView(Views.Incomes.name());
    }


}
