package services;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.User;
import mvvm.view.ViewController;
import mvvm.view.Views;
import mvvm.view.controllers.MainController;

public class ViewHandler {
    private Stage stage = null;
    private Scene scene;
    private ViewController viewController = null;
    private ViewModelFlyweight viewModelFlyweight;
    private ViewControllersFlyweight viewControllersFlyweight;

    private MainController mainController;


    public ViewHandler(ViewModelFlyweight viewModelFlyweight) {
        this.viewModelFlyweight = viewModelFlyweight;
        this.viewControllersFlyweight = new ViewControllersFlyweight();
        mainController =  (MainController) viewControllersFlyweight.getViewController(Views.Main.name());
    }

    public void start() throws Exception{
        stage = new Stage();
        stage.setMinWidth(695);
        stage.setMinHeight(700);
        OpenView(Views.Main.name());
        setCenterView(Views.Start.name());
        setBottomView(Views.BottomBar.name());
        SessionStorage.setCurrentUser(new User("dd@dd.com","ddddd",1));
    }

    public void OpenView(String viewName) {
        viewController = viewControllersFlyweight.getViewController(viewName);
        viewController.init(viewModelFlyweight,this);
        stage.setTitle(viewName);
        scene= viewController.getScene();
        stage.setScene(scene);
        stage.show();
//        Stage newStage = new Stage();
//        newStage.show();
    }

    public void setCenterView(String viewName){
        viewController = viewControllersFlyweight.getViewController(viewName);
        viewController.init(viewModelFlyweight,this);
        Parent root = viewController.getRoot();
        mainController.borderPane.setCenter(root);
//        stage.sizeToScene();
    }

    public void setBottomView(String viewName){
        viewController = viewControllersFlyweight.getViewController(viewName);
        viewController.init(viewModelFlyweight,this);
        Parent root = viewController.getRoot();
        mainController.borderPane.setBottom(root);
//        stage.sizeToScene();
    }
}
