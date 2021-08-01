import javafx.application.Application;
import javafx.stage.Stage;
import services.ServerAccessFlyweight;
import services.ServicesFlyweight;
import services.ViewHandler;
import services.ViewModelFlyweight;

public class MoneyTrack extends Application {
    private ServerAccessFlyweight serverAccessFlyweight;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try{
            serverAccessFlyweight = new ServerAccessFlyweight();
            ServicesFlyweight servicesFlyweight = new ServicesFlyweight(serverAccessFlyweight);
            ViewModelFlyweight viewModelFlyweight = new ViewModelFlyweight(servicesFlyweight);
            ViewHandler viewHandler = new ViewHandler(viewModelFlyweight);
            viewHandler.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
