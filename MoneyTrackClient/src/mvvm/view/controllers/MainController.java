package mvvm.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import mvvm.view.ViewController;
import services.ViewHandler;
import services.ViewModelFlyweight;

public class MainController extends ViewController {

    private ViewHandler viewHandler;

    @FXML
    public BorderPane borderPane;

    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {

    }
}
