package mvvm.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import models.Currency;
import mvvm.view.ViewController;
import mvvm.view.Views;
import mvvm.viewModel.UpdateAccount;
import services.SessionStorage;
import services.ViewHandler;
import services.ViewModelFlyweight;

import javax.swing.text.View;
import java.util.Optional;

public class UpdateAccountController extends ViewController {
    private UpdateAccount updateAccount;
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
        this.updateAccount = viewModelFlyweight.getUpdateAccount();
        this.viewHandler = viewHandler;
        balance.textProperty().bindBidirectional(updateAccount.balanceProperty());
        name.textProperty().bindBidirectional(updateAccount.nameProperty());
        errorLabel.textProperty().bindBidirectional(updateAccount.errorProperty());
        currencies.setItems(updateAccount.getCurrencies());
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

    public void update(MouseEvent mouseEvent){
        String result = updateAccount.updateAccount(currencies.getSelectionModel().getSelectedItem().getId());
        System.out.println(result);
    }
    public void back(MouseEvent mouseEvent){
        viewHandler.setCenterView(Views.Accounts.name());
    }

    public void share(MouseEvent mouseEvent){
        SessionStorage.setItem("sharedAccount", updateAccount.getAccount());
        viewHandler.setCenterView(Views.ShareAccount.name());
    }
    public void delete(MouseEvent mouseEvent){
        if (showAlert("Confirm Account Delete!", "Are you sure you want to delete your " + updateAccount.getAccount().getName() + " account ? \nWarning! It will also delete any expenses/incomes related to this account!")) {
            updateAccount.delete();
            viewHandler.setCenterView(Views.Accounts.name());
        }
    }

    public boolean showAlert(String name, String heading){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(name);
        alert.setHeaderText(heading);
        Optional<ButtonType> result = alert.showAndWait();
        return (result.isPresent() && result.get() == ButtonType.OK);
    }
}
