package mvvm.model.interfaces;

import models.Budget;

import java.time.LocalDate;
import java.util.List;

public interface IBudgetService {
    String add(Budget budget);
    Budget get(int budgetId);
    List<Budget> getBudgets(int userId, LocalDate date);
    String delete(int budgetId);
}
