package services;

import mvvm.model.interfaces.ICategoryService;
import networking.implementation.*;
import networking.interfaces.*;

public class ServerAccessFlyweight {
    private SessionStorage sessionStorage;
    private IUserServer userServer;
    private IAdminServer adminServer;
    private ICurrencyServer currencyServer;
    private ICategoryServer categoryServer;
    private IAccountServer accountServer;
    private IBudgetServer budgetServer;
    private IExpenseServer expenseServer;
    private IIncomeServer incomeServer;


    public ServerAccessFlyweight() {
    }

    public SessionStorage getSessionStorage() {
        if(sessionStorage == null){
            sessionStorage = new SessionStorage();
        }
        return sessionStorage;
    }

    public IUserServer getUserServer() {
        if(userServer == null){
            userServer = new UserServer();
        }
        return userServer;
    }
    public IAdminServer getAdminServer() {
        if(adminServer == null){
            adminServer = new AdminServer();
        }
        return adminServer;
    }
    public ICurrencyServer getCurrencyServer() {
        if(currencyServer == null){
            currencyServer = new CurrencyServer();
        }
        return currencyServer;
    }
    public ICategoryServer getCategoryServer() {
        if(categoryServer == null){
            categoryServer = new CategoryServer();
        }
        return categoryServer;
    }
    public IAccountServer getAccountServer() {
        if(accountServer == null){
            accountServer = new AccountServer();
        }
        return accountServer;
    }
    public IBudgetServer getBudgetServer(){
        if (budgetServer == null) {
            budgetServer = new BudgetServer() ;
        }
        return budgetServer;
    }
    public IExpenseServer getExpenseServer(){
        if (expenseServer == null) {
            expenseServer = new ExpenseServer();
        }
        return expenseServer;
    }
    public IIncomeServer getIncomeServer(){
        if (incomeServer == null) {
            incomeServer = new IncomeServer();
        }
        return incomeServer;
    }

}
