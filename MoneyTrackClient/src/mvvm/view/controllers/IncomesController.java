package mvvm.view.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import models.Income;
import mvvm.view.ViewController;
import mvvm.view.Views;
import mvvm.viewModel.Incomes;
import services.ViewHandler;
import services.ViewModelFlyweight;

import java.time.LocalDate;
import java.util.Optional;


public class IncomesController extends ViewController {
    private ViewHandler viewHandler;
    private Incomes incomesVM;

    @FXML
    private TableView<Income> table;
    @FXML
    private TableColumn<String , Income> tableDescription;
    @FXML
    private TableColumn<String , Income> tableAccount;
    @FXML
    private TableColumn<Float, Income> tableAmount;
    @FXML
    private TableColumn<String, Income> tableCurrency;
    @FXML
    private Label error;
    @FXML
    DatePicker datePick;


    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.incomesVM = viewModelFlyweight.getIncomes();
        tableDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableAccount.setCellValueFactory(new PropertyValueFactory<>("accountName"));
        tableAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tableCurrency.setCellValueFactory(new PropertyValueFactory<>("currencyName"));
        datePick.valueProperty().bindBidirectional(incomesVM.localDateProperty());
        table.setItems(incomesVM.getIncomes());
        error.textProperty().bindBidirectional(incomesVM.errorProperty());
        table.getSelectionModel().selectFirst();
        System.out.println(table.getFocusModel().getFocusedItem());
        error.textProperty().setValue("Showing incomes for "+ theMonth(incomesVM.getLocalDate().getMonthValue()) +" of "+ incomesVM.getLocalDate().getYear());
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
        viewHandler.setCenterView(Views.AddIncome.name());
    }
    public void delete(MouseEvent mouseEvent){
        if (showAlert("Confirm Delete!", "Are you sure you want to delete your " + table.getFocusModel().getFocusedItem().getDescription() + " income?")) {
            incomesVM.delete(table.getFocusModel().getFocusedItem().getId());
            table.setItems(incomesVM.getIncomes());
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
        error.textProperty().setValue("Showing incomes for "+ theMonth(incomesVM.getLocalDate().getMonthValue()) +" of "+ incomesVM.getLocalDate().getYear());
        table.setItems(incomesVM.getIncomes());
    }

    public String theMonth(int month){
        month -=1;
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }
}
