package services;


import mvvm.viewModel.*;

public class ViewModelFlyweight {
    private ServicesFlyweight servicesFlyweight;
    private Register register;
    private Login login;
    private AddCurrency addCurrency;
    private Currencies currencies;

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
}
