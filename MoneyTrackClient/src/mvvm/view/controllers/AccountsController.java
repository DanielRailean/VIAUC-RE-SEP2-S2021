package mvvm.view.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Account;
import mvvm.view.ViewController;
import mvvm.view.Views;
import mvvm.viewModel.Accounts;
import services.SessionStorage;
import services.ViewHandler;
import services.ViewModelFlyweight;

import java.util.List;
import java.util.Optional;


public class AccountsController extends ViewController {
    private ViewHandler viewHandler;
    private Accounts accountsVM;

    @FXML
    private TableView<Account> table;
    @FXML
    private TableColumn<String , Account> tableName;
    @FXML
    private TableColumn<Float, Account> tableBalance;
    @FXML
    private TableColumn<String, Account> tableCurrency;
    @FXML
    private Label error;


    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.accountsVM = viewModelFlyweight.getAccounts();
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        tableCurrency.setCellValueFactory(new PropertyValueFactory<>("currencyName"));
        table.setItems(accountsVM.getAccounts());
        error.textProperty().bindBidirectional(accountsVM.errorProperty());
        table.getSelectionModel().selectFirst();
    }
    public void back(MouseEvent mouseEvent){
        System.out.println("back");
        viewHandler.setCenterView(Views.Start.name());

    }
    public void add(MouseEvent mouseEvent){
        System.out.println("add");
        viewHandler.setCenterView(Views.AddAccount.name());
    }
    public void update(MouseEvent mouseEvent){
        if(SessionStorage.getCurrentUser().getId()!=table.getFocusModel().getFocusedItem().getOwnerId()){
            showAlert("Cannot edit!","You cannot edit this account because it is shared with you , not owned by you!");
            return;
        }
        System.out.println("update " + table.getFocusModel().getFocusedItem().getId());
        SessionStorage.setItem("updatedAccount", table.getFocusModel().getFocusedItem());
        viewHandler.setCenterView(Views.UpdateAccount.name());

    }
    public boolean showAlert(String name, String heading){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(name);
        alert.setHeaderText(heading);
        Optional<ButtonType> result = alert.showAndWait();
        return (result.isPresent() && result.get() == ButtonType.OK);
    }
}
