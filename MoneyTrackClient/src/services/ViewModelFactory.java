package services;


import mvvm.viewModel.Register;

public class ViewModelFactory {
    private ModelFactory modelFactory;
    private Register register;

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    public Register getRegister() {
        if(register == null){
            register = new Register(modelFactory.getRegister());
        }
        return register;
    }
}
