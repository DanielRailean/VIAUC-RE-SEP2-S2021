import javafx.application.Application;
import javafx.stage.Stage;
import services.ClientFactory;
import services.ModelFactory;
import services.ViewHandler;
import services.ViewModelFactory;

public class MoneyTrack extends Application {
    private ClientFactory clientFactory;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try{
            clientFactory = new ClientFactory();
            ModelFactory modelFactory = new ModelFactory(clientFactory);
            ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
            ViewHandler viewHandler = new ViewHandler(viewModelFactory);
            viewHandler.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
