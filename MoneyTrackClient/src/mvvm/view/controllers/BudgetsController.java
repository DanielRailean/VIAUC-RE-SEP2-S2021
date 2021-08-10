package mvvm.view.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import models.Budget;
import mvvm.view.ViewController;
import mvvm.view.Views;
import mvvm.viewModel.Budgets;
import services.SessionStorage;
import services.ViewHandler;
import services.ViewModelFlyweight;

import java.time.LocalDate;
import java.util.Optional;


public class BudgetsController extends ViewController {
    private ViewHandler viewHandler;
    private Budgets budgetsVM;

    @FXML
    private TableView<Budget> table;
    @FXML
    private TableColumn<String , Budget> tableName;
    @FXML
    private TableColumn<Float, Budget> tableAmount;
    @FXML
    private TableColumn<Float, Budget> tableSpent;
    @FXML
    private TableColumn<String, Budget> tableCurrency;
    @FXML
    private Label error;
    @FXML
    DatePicker datePick;


    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.budgetsVM = viewModelFlyweight.getBudgets();
        tableName.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        tableAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tableSpent.setCellValueFactory(new PropertyValueFactory<>("alreadySpent"));
        tableCurrency.setCellValueFactory(new PropertyValueFactory<>("currencyName"));
        datePick.valueProperty().bindBidirectional(budgetsVM.localDateProperty());
        table.setItems(budgetsVM.getBudgets());
        error.textProperty().bindBidirectional(budgetsVM.errorProperty());
        table.getSelectionModel().selectFirst();
        error.textProperty().setValue("Showing budgets for "+ theMonth(budgetsVM.getLocalDate().getMonthValue()) +" of "+ budgetsVM.getLocalDate().getYear());
        datePick.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                return theMonth(date.getMonthValue()) + " of " + date.getYear();
            }

            @Override
            public LocalDate fromString(String s) {
                return null;
            }
        });
    }
    public void back(MouseEvent mouseEvent){
        System.out.println("back");
        viewHandler.setCenterView(Views.Start.name());

    }
    public void add(MouseEvent mouseEvent){
        System.out.println("add");
        viewHandler.setCenterView(Views.AddBudget.name());
    }
    public void delete(MouseEvent mouseEvent){
        if (showAlert("Confirm Delete!", "Are you sure you want to delete your " + table.getFocusModel().getFocusedItem().getCategoryName() + " budget?")) {
            budgetsVM.delete(table.getFocusModel().getFocusedItem().getId());
            table.setItems(budgetsVM.getBudgets());
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
        error.textProperty().setValue("Showing budgets for "+ theMonth(budgetsVM.getLocalDate().getMonthValue()) +" of "+ budgetsVM.getLocalDate().getYear());
        table.setItems(budgetsVM.getBudgets());
    }
    public String theMonth(int month){
        month -=1;
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }
}
