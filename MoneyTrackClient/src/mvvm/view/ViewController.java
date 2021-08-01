package mvvm.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import services.ViewHandler;
import services.ViewModelFlyweight;

public abstract class ViewController {

    private Parent root;
    private Scene scene;

    abstract public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler);

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
        scene = new Scene(root);
    }

    public Scene getScene() {
        return scene;
    }
}
