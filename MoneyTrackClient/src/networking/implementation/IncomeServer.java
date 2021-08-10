package networking.implementation;

import models.Income;
import networking.interfaces.IIncomeServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.util.List;

public class IncomeServer implements IIncomeServer{
    private IIncomeServer incomeServer;

    public IncomeServer() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4000);
            this.incomeServer = (IIncomeServer) registry.lookup("IncomeServer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(Income income) {
        return incomeServer.add(income);
    }

    @Override
    public Income get(int incomeId) {
        return incomeServer.get(incomeId);
    }

    @Override
    public List<Income> getIncomesDay(int userId, LocalDate date) {
        return incomeServer.getIncomesDay(userId,date);
    }
    @Override
    public List<Income> getIncomesMonth(int userId, LocalDate date) {
        return incomeServer.getIncomesMonth(userId,date);
    }

    @Override
    public boolean delete(int incomeId) {
        return incomeServer.delete(incomeId);
    }
}
