package networking.implementation;


import database.interfaces.IIncomeService;
import models.Income;
import networking.interfaces.IIncomeServer;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.List;

public class IncomeServer implements IIncomeServer {

    private IIncomeService incomeService;

    public IncomeServer(IIncomeService incomeService, Registry registry) {
        try {
            UnicastRemoteObject.exportObject(this,0);
            this.incomeService = incomeService;
            registry.bind("IncomeServer", this);
            System.out.println("IncomeServer server started!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(Income income) throws RemoteException {
        return incomeService.add(income);
    }

    @Override
    public Income get(int incomeId) throws RemoteException {
        return incomeService.get(incomeId);

    }

    @Override
    public List<Income> getIncomesDay(int userId, LocalDate date) throws RemoteException {
        return incomeService.getIncomesDay(userId,date);
    }
    @Override
    public List<Income> getIncomesMonth(int userId, LocalDate date) throws RemoteException {
        return incomeService.getIncomesMonth(userId,date);
    }


    @Override
    public boolean delete(int incomeId) throws RemoteException {
        return incomeService.delete(incomeId);
    }

}
