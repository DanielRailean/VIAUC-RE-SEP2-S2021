package services;


import mvvm.viewModel.Register;

public class ViewModelFlyweight {
    private ServicesFlyweight servicesFlyweight;
    private Register register;

    public ViewModelFlyweight(ServicesFlyweight servicesFlyweight) {
        this.servicesFlyweight = servicesFlyweight;
    }

    public Register getRegister() {
        if(register == null){
            register = new Register(servicesFlyweight.getRegister());
        }
        return register;
    }
}
