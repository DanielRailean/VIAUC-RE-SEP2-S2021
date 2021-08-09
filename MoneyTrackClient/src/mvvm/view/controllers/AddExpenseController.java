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
import mvvm.viewModel.AddExpense;
import services.ViewHandler;
import services.ViewModelFlyweight;

public class AddExpenseController extends ViewController {
    private AddExpense addExpense;
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
    private ChoiceBox<Budget> budgets;
    @FXML
    private ChoiceBox<Account> accounts;

    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {
        this.addExpense = viewModelFlyweight.getAddExpense();
        this.viewHandler = viewHandler;
        amount.textProperty().bindBidirectional(addExpense.amountProperty());
        errorLabel.textProperty().bindBidirectional(addExpense.errorProperty());
        description.textProperty().bindBidirectional(addExpense.descriptionProperty());
        currencies.setItems(addExpense.getCurrencies());
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
        budgets.setItems(addExpense.getCategories());
        budgets.setConverter(new StringConverter<Budget>() {
            @Override
            public String toString(Budget budget) {
                return budget.getCategoryName();
            }

            @Override
            public Budget fromString(String s) {
                return null;
            }
        });
        accounts.setItems(addExpense.getAccounts());
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
        budgets.getSelectionModel().selectFirst();
        accounts.getSelectionModel().selectFirst();
        errorLabel.textProperty().setValue("Select an account ,currency and budget and type in the amount and description");
    }

    public void add(MouseEvent mouseEvent){
        addExpense.addExpense(budgets.getSelectionModel().getSelectedItem().getCategoryId(),currencies.getSelectionModel().getSelectedItem().getId(),accounts.getSelectionModel().getSelectedItem().getId());
    }
    public void back(MouseEvent mouseEvent){
        viewHandler.setCenterView(Views.Expenses.name());
    }


}
