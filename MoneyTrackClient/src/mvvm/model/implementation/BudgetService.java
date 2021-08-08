package mvvm.model.implementation;

import models.Budget;
import models.Category;
import mvvm.model.Validator;
import mvvm.model.interfaces.IBudgetService;
import networking.interfaces.IBudgetServer;

import java.time.LocalDate;
import java.util.List;

public class BudgetService implements IBudgetService {
    private IBudgetServer budgetServer;

    public BudgetService(IBudgetServer budgetServer) {
        this.budgetServer = budgetServer;
    }

    @Override
    public String add(Budget budget) {
        if(!Validator.isValidBudgetAmount(budget.getAmount())) return "Amount invalid!";
        if (budgetServer.add(budget)) {
            return "Budget successfully added";
        }
        return "Unable to add budget , most likely you already have a budget for this category for this month!";
    }

    @Override
    public Budget get(int budgetId) {
        return budgetServer.get(budgetId);
    }

    @Override
    public List<Budget> getBudgets(int userId, LocalDate date) {
        return budgetServer.getBudgets(userId,date);
    }

    @Override
    public String delete(int budgetId) {
        if(budgetServer.delete(budgetId)){
            return "Budget successfully deleted";
        }
        return "Unable to delete budget, please try again later!";
    }
}
