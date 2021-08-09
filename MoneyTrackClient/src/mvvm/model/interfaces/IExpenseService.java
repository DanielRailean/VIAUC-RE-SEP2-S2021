package mvvm.model.interfaces;

import models.Expense;

import java.time.LocalDate;
import java.util.List;

public interface IExpenseService {
    String add(Expense expense);
    Expense get(int expenseId);
    List<Expense> getExpensesMonth(int userId, LocalDate date);
    List<Expense> getExpensesDay(int userId, LocalDate date);
    //    boolean update(Expense expense);
    String delete(int expenseId);
}
