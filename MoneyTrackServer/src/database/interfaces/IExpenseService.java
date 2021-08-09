package database.interfaces;

import models.Account;
import models.Expense;

import java.time.LocalDate;
import java.util.List;

public interface IExpenseService {
    boolean add(Expense expense);
    Expense get(int expenseId);
    List<Expense> getExpensesMonth(int userId, LocalDate date);
    List<Expense> getExpensesDay(int userId, LocalDate date);
    //    boolean update(Expense expense);
    boolean delete(int expenseId);
}
