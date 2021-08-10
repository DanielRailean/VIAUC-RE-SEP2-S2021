package mvvm.viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Income;
import mvvm.model.interfaces.IIncomeService;
import services.SessionStorage;

import java.time.LocalDate;

public class Incomes {
    private IIncomeService incomeService;

    private StringProperty error;
    private ObjectProperty<LocalDate> localDate;
    private ObservableList<Income> incomes;

    public Incomes(IIncomeService incomeService) {
        this.incomeService = incomeService;
        error = new SimpleStringProperty();
        localDate = new SimpleObjectProperty<>();
        localDate.setValue(LocalDate.now());
        incomes = FXCollections.observableArrayList();
        incomes.addAll(incomeService.getIncomesMonth(SessionStorage.getCurrentUser().getId(),localDate.getValue()));
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

    public ObservableList<Income> getIncomes() {
        incomes = FXCollections.observableArrayList();
        incomes.removeAll();
        System.out.println(localDate.getValue().getYear()+" "+localDate.getValue().getMonthValue());
        incomes.addAll(incomeService.getIncomesMonth(SessionStorage.getCurrentUser().getId(),localDate.getValue()));
        return incomes;
    }

    public void delete(int incomeId) {
        error.setValue(incomeService.delete(incomeId));
    }

    public void setIncomes(ObservableList<Income> incomes) {
        this.incomes = incomes;
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
