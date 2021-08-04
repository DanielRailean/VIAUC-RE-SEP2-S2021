package mvvm.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import mvvm.view.ViewController;
import mvvm.view.Views;
import mvvm.viewModel.UpdateCategory;
import services.ViewHandler;
import services.ViewModelFlyweight;

public class UpdateCategoryController extends ViewController {
    private UpdateCategory updateCategory;
    private ViewHandler viewHandler;

    @FXML
    private Label errorLabel;
    @FXML
    private TextField name;
    @FXML
    private TextField price;

    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {
        this.updateCategory = viewModelFlyweight.getUpdateCategory();
        this.viewHandler = viewHandler;
        errorLabel.textProperty().setValue("Type category name and price in EURO");
        name.textProperty().bindBidirectional(updateCategory.nameProperty());
        errorLabel.textProperty().bindBidirectional(updateCategory.errorProperty());
        name.textProperty().setValue(updateCategory.getUpdated().getName());
    }

    public void update(MouseEvent mouseEvent){
        String result = updateCategory.updateCategory();
        System.out.println(result);
    }
    public void back(MouseEvent mouseEvent){
        viewHandler.setCenterView(Views.Categories.name());
    }


}
