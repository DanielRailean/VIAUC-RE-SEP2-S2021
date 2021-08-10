package mvvm.model.interfaces;

import models.Income;

import java.time.LocalDate;
import java.util.List;

public interface IIncomeService {
    String add(Income income);
    Income get(int incomeId);
    List<Income> getIncomesMonth(int userId, LocalDate date);
    List<Income> getIncomesDay(int userId, LocalDate date);
    //    boolean update(Income income);
    String delete(int incomeId);
}
