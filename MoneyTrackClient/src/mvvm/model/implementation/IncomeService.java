package mvvm.model.implementation;

import models.Income;
import mvvm.model.Validator;
import mvvm.model.interfaces.IIncomeService;
import networking.interfaces.IIncomeServer;

import java.time.LocalDate;
import java.util.List;

public class IncomeService implements IIncomeService {
    private IIncomeServer incomeServer;

    public IncomeService(IIncomeServer incomeServer) {
        this.incomeServer = incomeServer;
    }

    @Override
    public String add(Income income) {
        if(!Validator.isValidIncome(income.getAmount())) return "Amount invalid!";
        if(!Validator.isValidIncomeDescription(income.getDescription())) return "Description can be 50 characters at max!";
        if (incomeServer.add(income)) {
            return "Income successfully added";
        }
        return "Unable to add income , " +
                "please try again later";
    }

    @Override
    public Income get(int incomeId) {
        return incomeServer.get(incomeId);
    }

    @Override
    public List<Income> getIncomesMonth(int userId, LocalDate date) {
        return incomeServer.getIncomesMonth(userId,date);
    }

    @Override
    public List<Income> getIncomesDay(int userId, LocalDate date) {
        return incomeServer.getIncomesDay(userId,date);
    }

    @Override
    public String delete(int incomeId) {
        if(incomeServer.delete(incomeId)){
            return "Income successfully deleted";
        }
        return "Unable to delete income, please try again later!";
    }
}
