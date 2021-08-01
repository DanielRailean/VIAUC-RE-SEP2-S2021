package services;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mvvm.view.ViewController;
import mvvm.view.controllers.MainController;

public class ViewHandler {
    private Stage stage = null;
    private Scene scene;
    private ViewController viewController = null;
    private ViewModelFactory viewModelFactory;
    private ViewControllerFactory viewControllerFactory;

    private MainController mainController;


    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        this.viewControllerFactory = new ViewControllerFactory();
        mainController =  (MainController)viewControllerFactory.getViewController("Main");
    }

    public void start() throws Exception{
        stage = new Stage();
        viewControllerFactory.getViewController(Views.Main.name());
        viewControllerFactory.getViewController(Views.Start.name());
        Object var = viewControllerFactory.getViewController(Views.Register.name());
        OpenView(Views.Main.name());
        setCenterView(Views.Start.name());
    }

    public void OpenView(String viewName) {
        viewController = viewControllerFactory.getViewController(viewName);
        viewController.init(viewModelFactory,this);
        stage.setTitle(viewName);
        scene= viewController.getScene();
        stage.setScene(scene);
        stage.show();
    }

    public void setCenterView(String viewName){
        viewController = viewControllerFactory.getViewController(viewName);
        viewController.init(viewModelFactory,this);
        Parent root = viewController.getRoot();
        mainController.borderPane.setCenter(root);
        stage.sizeToScene();
    }
}
