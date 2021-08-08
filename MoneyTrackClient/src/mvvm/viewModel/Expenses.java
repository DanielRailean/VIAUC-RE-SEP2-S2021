package mvvm.viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Expense;
import mvvm.model.interfaces.IExpenseService;
import services.SessionStorage;

import java.time.LocalDate;

public class Expenses {
    private IExpenseService expenseService;

    private StringProperty error;
    private ObjectProperty<LocalDate> localDate;
    private ObservableList<Expense> expenses;

    public Expenses(IExpenseService expenseService) {
        this.expenseService = expenseService;
        error = new SimpleStringProperty();
        localDate = new SimpleObjectProperty<>();
        localDate.setValue(LocalDate.now());
        expenses = FXCollections.observableArrayList();
        expenses.addAll(expenseService.getExpensesMonth(SessionStorage.getCurrentUser().getId(),localDate.getValue()));
    }

    public String getError() {
        return error.get();
    }

    public StringProperty errorProperty() {
        return error;
    }

    public void setError(String error) {
        this.error.set(error);
    }

    public ObservableList<Expense> getExpenses() {
        expenses = FXCollections.observableArrayList();
        expenses.removeAll();
        System.out.println(localDate.getValue().getYear()+" "+localDate.getValue().getMonthValue());
        expenses.addAll(expenseService.getExpensesMonth(SessionStorage.getCurrentUser().getId(),localDate.getValue()));
        return expenses;
    }

    public void delete(int expenseId) {
        error.setValue(expenseService.delete(expenseId));
    }

    public void setExpenses(ObservableList<Expense> expenses) {
        this.expenses = expenses;
    }

    public LocalDate getLocalDate() {
        return localDate.get();
    }

    public ObjectProperty<LocalDate> localDateProperty() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate.set(localDate);
    }
}
