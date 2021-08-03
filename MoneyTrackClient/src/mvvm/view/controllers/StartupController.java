package mvvm.view.controllers;

import javafx.scene.input.MouseEvent;
import mvvm.view.ViewController;
import services.ViewHandler;
import services.ViewModelFlyweight;
import mvvm.view.Views;

public class StartupController extends ViewController {
    private ViewHandler viewHandler;

    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }

    public void redirectRegister(MouseEvent mouseEvent){
        System.out.println("redirect register");
        viewHandler.setCenterView(Views.Register.name());
    }
    public void redirectBrowse(MouseEvent mouseEvent){
        System.out.println("redirect browse");

    }
    public void redirectLogin(MouseEvent mouseEvent){
        System.out.println("redirect login");
        viewHandler.setCenterView(Views.Login.name());
    }
    }
