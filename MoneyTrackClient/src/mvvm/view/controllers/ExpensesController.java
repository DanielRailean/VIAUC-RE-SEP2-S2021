package mvvm.view.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Expense;
import mvvm.view.ViewController;
import mvvm.view.Views;
import mvvm.viewModel.Expenses;
import services.ViewHandler;
import services.ViewModelFlyweight;

import java.util.Optional;


public class ExpensesController extends ViewController {
    private ViewHandler viewHandler;
    private Expenses expensesVM;

    @FXML
    private TableView<Expense> table;
    @FXML
    private TableColumn<String , Expense> tableDescription;
    @FXML
    private TableColumn<String , Expense> tableAccount;
    @FXML
    private TableColumn<String , Expense> tableName;
    @FXML
    private TableColumn<Float, Expense> tableAmount;
    @FXML
    private TableColumn<String, Expense> tableCurrency;
    @FXML
    private Label error;
    @FXML
    DatePicker datePick;


    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.expensesVM = viewModelFlyweight.getExpenses();
        tableDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableAccount.setCellValueFactory(new PropertyValueFactory<>("accountName"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        tableAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tableCurrency.setCellValueFactory(new PropertyValueFactory<>("currencyName"));
        datePick.valueProperty().bindBidirectional(expensesVM.localDateProperty());
        table.setItems(expensesVM.getExpenses());
        error.textProperty().bindBidirectional(expensesVM.errorProperty());
        table.getSelectionModel().selectFirst();
        System.out.println(table.getFocusModel().getFocusedItem());
        error.textProperty().setValue("Showing expenses for "+ theMonth(expensesVM.getLocalDate().getMonthValue()) +" of "+ expensesVM.getLocalDate().getYear());
        error.textProperty().setValue("");

    }
    public void back(MouseEvent mouseEvent){
        System.out.println("back");
        viewHandler.setCenterView(Views.Start.name());

    }
    public void add(MouseEvent mouseEvent){
        System.out.println("add");
        viewHandler.setCenterView(Views.AddExpense.name());
    }
    public void delete(MouseEvent mouseEvent){
        if (showAlert("Confirm Delete!", "Are you sure you want to delete your " + table.getFocusModel().getFocusedItem().getCategoryName() + " expense?")) {
            expensesVM.delete(table.getFocusModel().getFocusedItem().getId());
            table.setItems(expensesVM.getExpenses());
        }
    }
    public boolean showAlert(String name, String heading){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(name);
        alert.setHeaderText(heading);
        Optional<ButtonType> result = alert.showAndWait();
        return (result.isPresent() && result.get() == ButtonType.OK);
    }
    public void updateTable(Event event){
        System.out.println("event");
        error.textProperty().setValue("Showing expenses for "+ theMonth(expensesVM.getLocalDate().getMonthValue()) +" of "+ expensesVM.getLocalDate().getYear());
        table.setItems(expensesVM.getExpenses());
    }

    public String theMonth(int month){
        month -=1;
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }
}
