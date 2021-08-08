package mvvm.view.controllers;

import javafx.scene.input.MouseEvent;
import mvvm.view.ViewController;
import mvvm.view.Views;
import services.ViewHandler;
import services.ViewModelFlyweight;

public class BottomBarUserController extends ViewController {
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
        viewHandler.setCenterView(Views.Accounts.name());
    }
    public void budgets(MouseEvent mouseEvent){
        viewHandler.setCenterView(Views.Budgets.name());

    }
    public void settings(MouseEvent mouseEvent){
        viewHandler.setCenterView(Views.Details.name());

    }
}
