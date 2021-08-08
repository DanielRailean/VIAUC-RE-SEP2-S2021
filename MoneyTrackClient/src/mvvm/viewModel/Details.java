package mvvm.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import services.SessionStorage;

public class Details {

    private StringProperty error;

    public Details() {
        error = new SimpleStringProperty();
    }

    public String getError() {
        return error.get();
    }

    public StringProperty errorProperty() {
        return error;
    }

    public void setError(String error) {
        this.error.set(error);
    }

    public void logout(){
        SessionStorage.setCurrentUser(null);
    }
}
