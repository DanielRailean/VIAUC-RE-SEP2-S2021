package networking.interfaces;

import models.Budget;

import java.rmi.Remote;
import java.time.LocalDate;
import java.util.List;

public interface IBudgetServer extends Remote {
    boolean add(Budget budget);
    Budget get(int budgetId);
    List<Budget> getBudgets(int userId, LocalDate date);
    //    boolean update(Budget budget);
    boolean delete(int budgetId);
}
