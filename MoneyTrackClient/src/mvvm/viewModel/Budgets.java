package mvvm.viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Budget;
import mvvm.model.interfaces.IBudgetService;
import services.SessionStorage;

import java.time.LocalDate;

public class Budgets {
    private IBudgetService budgetService;

    private StringProperty error;
    private ObjectProperty<LocalDate> localDate;
    private ObservableList<Budget> budgets;

    public Budgets(IBudgetService budgetService) {
        this.budgetService = budgetService;
        error = new SimpleStringProperty();
        localDate = new SimpleObjectProperty<>();
        localDate.setValue(LocalDate.now());
        budgets = FXCollections.observableArrayList();
        budgets.addAll(budgetService.getBudgets(SessionStorage.getCurrentUser().getId(),localDate.getValue()));
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

    public ObservableList<Budget> getBudgets() {
        budgets = FXCollections.observableArrayList();
        budgets.removeAll();
        System.out.println(localDate.getValue().getYear()+" "+localDate.getValue().getMonthValue());
        budgets.addAll(budgetService.getBudgets(SessionStorage.getCurrentUser().getId(),localDate.getValue()));
        return budgets;
    }

    public void delete(int budgetId) {
        error.setValue(budgetService.delete(budgetId));
    }

    public void setBudgets(ObservableList<Budget> budgets) {
        this.budgets = budgets;
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
