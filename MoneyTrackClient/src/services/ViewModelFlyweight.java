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

    public ViewModelFlyweight(ServicesFlyweight servicesFlyweight) {
        this.servicesFlyweight = servicesFlyweight;
    }

    public Register getRegister() {
        if(register == null){
            register = new Register(servicesFlyweight.getUserService());
        }
        return register;
    }

    public Login getLogin() {
        if(login == null){
            login = new Login(servicesFlyweight.getUserService(),servicesFlyweight.getAdminService());
        }
        return login;
    }

    public AddCurrency getAddCurrency() {
        if(addCurrency == null){
            addCurrency = new AddCurrency(servicesFlyweight.getCurrencyService());
        }
        return addCurrency;
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
        if(addCategory == null){
            addCategory = new AddCategory(servicesFlyweight.getCategoryService());
        }
        return addCategory;
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
        if(addAccount == null) addAccount = new AddAccount(servicesFlyweight.getCurrencyService(),servicesFlyweight.getAccountService());
        return addAccount;
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
}
