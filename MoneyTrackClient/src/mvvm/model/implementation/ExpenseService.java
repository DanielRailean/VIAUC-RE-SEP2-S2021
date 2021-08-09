package mvvm.model.implementation;

import models.Expense;
import models.Category;
import mvvm.model.Validator;
import mvvm.model.interfaces.IExpenseService;
import networking.interfaces.IExpenseServer;

import java.time.LocalDate;
import java.util.List;

public class ExpenseService implements IExpenseService {
    private IExpenseServer expenseServer;

    public ExpenseService(IExpenseServer expenseServer) {
        this.expenseServer = expenseServer;
    }

    @Override
    public String add(Expense expense) {
        if(!Validator.isValidExpense(expense.getAmount())) return "Amount invalid!";
        if(!Validator.isValidExpenseDescription(expense.getDescription())) return "Description can be 50 characters at max!";
        if (expenseServer.add(expense)) {
            return "Expense successfully added";
        }
        return "Unable to add expense , " +
                "please try again later";
    }

    @Override
    public Expense get(int expenseId) {
        return expenseServer.get(expenseId);
    }

    @Override
    public List<Expense> getExpensesMonth(int userId, LocalDate date) {
        return expenseServer.getExpensesMonth(userId,date);
    }

    @Override
    public List<Expense> getExpensesDay(int userId, LocalDate date) {
        return expenseServer.getExpensesDay(userId,date);
    }

    @Override
    public String delete(int expenseId) {
        if(expenseServer.delete(expenseId)){
            return "Expense successfully deleted";
        }
        return "Unable to delete expense, please try again later!";
    }
}
