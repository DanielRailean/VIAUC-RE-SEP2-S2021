package database.interfaces;

import models.Account;
import models.Budget;

import java.time.LocalDate;
import java.util.List;

public interface IBudgetService {
    boolean add(Budget budget);
    Budget get(int budgetId);
    List<Budget> getBudgets(int userId, LocalDate date);
//    boolean update(Budget budget);
    boolean delete(int budgetId);
}
