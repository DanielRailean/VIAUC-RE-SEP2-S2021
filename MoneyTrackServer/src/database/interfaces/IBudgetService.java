package database.interfaces;

import models.Account;
import models.Budget;

import java.util.List;

public interface IBudgetService {
    boolean add(Budget budget);
    Budget get(int budgetId);
    List<Budget> getBudgets(int userId);
//    boolean update(Budget budget);
    boolean delete(int budgetId);
}
