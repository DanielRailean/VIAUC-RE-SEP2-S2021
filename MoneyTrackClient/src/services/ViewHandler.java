package services;

import javafx.scene.Scene;
import javafx.stage.Stage;
import mvvm.view.ViewController;

public class ViewHandler {
    private Stage stage = null;
    private Scene scene;
    private ViewController viewController = null;
    private ViewModelFactory viewModelFactory;
    private ViewControllerFactory viewControllerFactory;


    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        this.viewControllerFactory = new ViewControllerFactory();
    }

    public void start() throws Exception{
        stage = new Stage();
        OpenView(Views.Register.name());
    }

    public void OpenView(String viewName) {
        viewController = viewControllerFactory.getViewController(viewName);
        viewController.init(viewModelFactory,this);
        stage.setTitle(viewName);
        scene= viewController.getScene();
        stage.setScene(scene);
        stage.show();
    }
}
