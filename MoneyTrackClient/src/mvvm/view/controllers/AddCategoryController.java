package mvvm.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import mvvm.view.ViewController;
import mvvm.view.Views;
import mvvm.viewModel.AddCategory;
import services.ViewHandler;
import services.ViewModelFlyweight;

public class AddCategoryController extends ViewController {
    private AddCategory addCategory;
    private ViewHandler viewHandler;

    @FXML
    private Label errorLabel;
    @FXML
    private TextField name;

    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {
        this.addCategory = viewModelFlyweight.getAddCategory();
        this.viewHandler = viewHandler;
        name.textProperty().bindBidirectional(addCategory.nameProperty());
        errorLabel.textProperty().bindBidirectional(addCategory.errorProperty());
        errorLabel.textProperty().setValue("Type category name");
    }

    public void add(MouseEvent mouseEvent){
        String result = addCategory.addCategory();
        System.out.println(result);
    }
    public void back(MouseEvent mouseEvent){
        viewHandler.setCenterView(Views.Categories.name());
    }


}
