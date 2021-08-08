package mvvm.view.controllers;

import javafx.scene.input.MouseEvent;
import mvvm.view.ViewController;
import mvvm.view.Views;
import services.ViewHandler;
import services.ViewModelFlyweight;

public class DetailsController extends ViewController {
    private ViewHandler viewHandler;

    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }


    public void logout(MouseEvent mouseEvent){
        System.out.println("logout");
        viewHandler.setBottomView(null);
    }
    public void email(MouseEvent mouseEvent){
        viewHandler.setCenterView(Views.ChangeEmail.name());

    }
    public void password(MouseEvent mouseEvent){
        viewHandler.setCenterView(Views.ChangePassword.name());

    }
    public void back(MouseEvent mouseEvent){
        viewHandler.setCenterView(Views.Start.name());
    }
}
