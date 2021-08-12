package services;


import mvvm.viewModel.*;

public class ViewModelFlyweight {
    private ServicesFlyweight servicesFlyweight;
    private Register register;
    private Login login;
    private AddCurrency addCurrency;
    private Currencies currencies;
    private AddCategory addCategory;
    private Categories categories;
    private RegisterAdmin registerAdmin;
    private Accounts accounts;
    private AddAccount addAccount;
    private Details details;
    private ChangeEmail changeEmail;
    private ChangePassword changePassword;
    private Budgets budgets;
    private AddBudget addBudget;
    private Expenses expenses;
    private AddExpense addExpense;
    private Incomes incomes;
    private AddIncome addIncome;

    public ViewModelFlyweight(ServicesFlyweight servicesFlyweight) {
        this.servicesFlyweight = servicesFlyweight;
    }

    public Register getRegister() {
        return new Register(servicesFlyweight.getUserService());

    }

    public Login getLogin() {
        return new Login(servicesFlyweight.getUserService(),servicesFlyweight.getAdminService());
    }

    public AddCurrency getAddCurrency() {
        return new AddCurrency(servicesFlyweight.getCurrencyService());

    }
    public Currencies getCurrencies() {
        if(currencies == null){
            currencies = new Currencies(servicesFlyweight.getCurrencyService());
        }
        return currencies;
    }

    public UpdateCurrency getUpdateCurrency() {
        
        return new UpdateCurrency(servicesFlyweight.getCurrencyService());
    }

    public AddCategory getAddCategory() {
        return new AddCategory(servicesFlyweight.getCategoryService());

    }
    public Categories getCategories() {
        if(categories == null){
            categories = new Categories(servicesFlyweight.getCategoryService());
        }
        return categories;
    }

    public UpdateCategory getUpdateCategory() {
        
        return new UpdateCategory(servicesFlyweight.getCategoryService());
    }
    public RegisterAdmin getRegisterAdmin(){
        if(registerAdmin == null){
            registerAdmin = new RegisterAdmin(servicesFlyweight.getAdminService());
        }
        return registerAdmin;
    }

    public Accounts getAccounts() {
        if(accounts == null) accounts = new Accounts(servicesFlyweight.getAccountService());
        return accounts;
    }

    public AddAccount getAddAccount(){
        return new AddAccount(servicesFlyweight.getCurrencyService(),servicesFlyweight.getAccountService());

    }

    public UpdateAccount getUpdateAccount(){
        return new UpdateAccount(servicesFlyweight.getCurrencyService(),servicesFlyweight.getAccountService());
    }

    public Details getDetails(){
        if(details == null) {
            details = new Details();
        }
        return details;
    }
    public ChangeEmail getChangeEmail(){
        if(changeEmail==null){
            changeEmail = new ChangeEmail(servicesFlyweight.getUserService());
        }
        return changeEmail;
    }
    public ChangePassword getChangePassword(){
        if(changePassword==null){
            changePassword = new ChangePassword(servicesFlyweight.getUserService());
        }
        return changePassword;
    }

    public ShareAccount getShareAccount(){
        return new ShareAccount(servicesFlyweight.getAccountService());
    }
    public Budgets getBudgets(){
        if(budgets == null){
            budgets = new Budgets(servicesFlyweight.getBudgetService());
        }
        return budgets;
    }
    public AddBudget getAddBudget(){
        return new AddBudget(servicesFlyweight.getCurrencyService(),servicesFlyweight.getCategoryService(),servicesFlyweight.getBudgetService());

    }

    public Expenses getExpenses(){
        if (expenses == null) {
            expenses = new Expenses(servicesFlyweight.getExpenseService());
        }
        return expenses;
    }

    public AddExpense getAddExpense(){
        return new AddExpense(servicesFlyweight.getCurrencyService(),servicesFlyweight.getBudgetService(),servicesFlyweight.getAccountService(),servicesFlyweight.getExpenseService());
    }
    public Incomes getIncomes(){
        if (incomes == null) {
            incomes = new Incomes(servicesFlyweight.getIncomeService());
        }
        return incomes;
    }

    public AddIncome getAddIncome(){
        return new AddIncome(servicesFlyweight.getCurrencyService(),servicesFlyweight.getAccountService(),servicesFlyweight.getIncomeService());

    }
}
