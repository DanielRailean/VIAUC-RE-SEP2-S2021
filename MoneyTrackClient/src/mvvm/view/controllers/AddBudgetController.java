package mvvm.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import models.Category;
import models.Currency;
import mvvm.view.ViewController;
import mvvm.view.Views;
import mvvm.viewModel.AddBudget;
import services.ViewHandler;
import services.ViewModelFlyweight;

public class AddBudgetController extends ViewController {
    private AddBudget addBudget;
    private ViewHandler viewHandler;

    @FXML
    private Label errorLabel;
    @FXML
    private TextField amount;
    @FXML
    private ChoiceBox<Currency> currencies;
    @FXML
    private ChoiceBox<Category> categories;

    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {
        this.addBudget = viewModelFlyweight.getAddBudget();
        this.viewHandler = viewHandler;
        amount.textProperty().bindBidirectional(addBudget.amountProperty());
        errorLabel.textProperty().bindBidirectional(addBudget.errorProperty());
        currencies.setItems(addBudget.getCurrencies());
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
        categories.setItems(addBudget.getCategories());
        categories.setConverter(new StringConverter<Category>() {
            @Override
            public String toString(Category category) {
                return category.getName();
            }

            @Override
            public Category fromString(String s) {
                return null;
            }
        });
        currencies.getSelectionModel().selectFirst();
        categories.getSelectionModel().selectFirst();
        errorLabel.textProperty().setValue("Select a category and currency and type in the amount");
    }

    public void add(MouseEvent mouseEvent){
        addBudget.addBudget(categories.getSelectionModel().getSelectedItem().getId(),currencies.getSelectionModel().getSelectedItem().getId());
    }
    public void back(MouseEvent mouseEvent){
        viewHandler.setCenterView(Views.Budgets.name());
    }


}
