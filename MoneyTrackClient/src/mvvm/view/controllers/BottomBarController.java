package mvvm.view.controllers;

import javafx.scene.input.MouseEvent;
import mvvm.view.ViewController;
import services.ViewHandler;
import services.ViewModelFlyweight;

public class BottomBarController extends ViewController {
    private ViewHandler viewHandler;

    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }
    public void expenses(MouseEvent mouseEvent){
        System.out.println("expenses");
    }
    public void incomes(MouseEvent mouseEvent){
        System.out.println("incomes");

    }
    public void accounts(MouseEvent mouseEvent){
        System.out.println("accounts");

    }
    public void budgets(MouseEvent mouseEvent){
        System.out.println("budgets");

    }
    public void settings(MouseEvent mouseEvent){
        System.out.println("settings");

    }
}
