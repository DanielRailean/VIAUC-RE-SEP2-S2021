package networking.interfaces;

import models.Income;

import java.rmi.Remote;
import java.time.LocalDate;
import java.util.List;

public interface IIncomeServer extends Remote {
    boolean add(Income income);
    Income get(int incomeId);
    List<Income> getIncomesMonth(int userId, LocalDate date);
    List<Income> getIncomesDay(int userId, LocalDate date);
    //    boolean update(Income income);
    boolean delete(int incomeId);
}
