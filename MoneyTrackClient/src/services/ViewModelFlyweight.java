package services;


import mvvm.viewModel.*;

public class ViewModelFlyweight {
    private ServicesFlyweight servicesFlyweight;
    private Register register;
    private Login login;

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
}
