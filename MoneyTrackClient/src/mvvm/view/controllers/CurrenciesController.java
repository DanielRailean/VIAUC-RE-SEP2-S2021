package mvvm.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Currency;
import mvvm.view.ViewController;
import mvvm.view.Views;
import mvvm.viewModel.Currencies;
import services.SessionStorage;
import services.ViewHandler;
import services.ViewModelFlyweight;


public class CurrenciesController extends ViewController {
    private ViewHandler viewHandler;
    private Currencies currenciesVM;

    @FXML
    private TableView<Currency> currencies;
    @FXML
    private TableColumn<String , Currency> tableName;
    @FXML
    private TableColumn<Float, Currency> tablePrice;
    @FXML
    private Label error;


    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {

        this.viewHandler = viewHandler;
        this.currenciesVM = viewModelFlyweight.getCurrencies();
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tablePrice.setCellValueFactory(new PropertyValueFactory<>("priceInEuro"));
        currencies.setItems(currenciesVM.getCurrencies());
        error.textProperty().bindBidirectional(currenciesVM.errorProperty());

    }
    public void back(MouseEvent mouseEvent){
        System.out.println("back");
        viewHandler.setCenterView(Views.Start.name());

    }
    public void add(MouseEvent mouseEvent){
        System.out.println("add");
        viewHandler.setCenterView(Views.AddCurrency.name());
    }
    public void update(MouseEvent mouseEvent){
        System.out.println("update " +currencies.getFocusModel().getFocusedItem().getId());
        SessionStorage.setItem("updatedCurrency", currencies.getFocusModel().getFocusedItem());
        viewHandler.setCenterView(Views.UpdateCurrency.name());

    }
}
