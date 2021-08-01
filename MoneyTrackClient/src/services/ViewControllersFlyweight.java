package services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import mvvm.view.ViewController;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class ViewControllersFlyweight {
    private static HashMap<String, ViewController> viewControllers = new HashMap<>();

    public ViewController getViewController(String controllerName){
        ViewController viewController;
        synchronized (ViewControllersFlyweight.class){
            viewController = viewControllers.get(controllerName);
        }
        if(viewController == null){
            URL location = null;
            Path currentRelative = Paths.get("");
            String s = (currentRelative.toAbsolutePath().toString().replace("\\","/"));
            // uncomment one of the urls depending on the build type
            //production url
//            String path = "file:/" + s + "/views/";
            //development url
            String path = "file:/" + s + "/src/mvvm/view/fxml/";
            FXMLLoader loader = new FXMLLoader();
            try{
                String fxmlPath = path + controllerName + ".fxml";
                location = new URL(fxmlPath);
            } catch (Exception e){
                e.printStackTrace();
            }
            loader.setLocation(location);
            Parent root = null;
            try {
                root = loader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }
            viewController = loader.getController();
            viewController.setRoot(root);
            synchronized (ViewControllersFlyweight.class){
                viewControllers.put(controllerName,viewController);
            }
        }
        return viewController;
    }

    public static ViewController getNewViewController (String controllerName){
        URL location = null;
        Path currentRelative = Paths.get("");
        String s = (currentRelative.toAbsolutePath().toString().replace("\\","/"));
        String path = "file:/"+s+"/src/mvvm/view/fxml";
        FXMLLoader loader = new FXMLLoader();
        try{
            String fxmlPath = path + controllerName + ".fxml";
            location = new URL(fxmlPath);
        } catch (Exception e){
            e.printStackTrace();
        }
        loader.setLocation(location);
        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ViewController viewController = loader.getController();
        viewController.setRoot(root);
        synchronized (ViewControllersFlyweight.class){
            viewControllers.put(controllerName,viewController);
        }
        return viewController;
    }
}
