package database.interfaces;

import models.Budget;
import models.Expense;

import java.time.LocalDate;
import java.util.List;

public interface IBudgetService {
    boolean add(Budget budget);
    Budget get(int budgetId);
    Budget get(int userId,int categoryId);
    List<Budget> getBudgets(int userId, LocalDate date);
    boolean addExpense(Expense expense);
    boolean deleteExpense(Expense expense);
    boolean delete(int budgetId);
}
