package mvvm.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Category;
import mvvm.view.ViewController;
import mvvm.view.Views;
import mvvm.viewModel.Categories;
import services.SessionStorage;
import services.ViewHandler;
import services.ViewModelFlyweight;


public class CategoriesController extends ViewController {
    private ViewHandler viewHandler;
    private Categories categoriesVM;

    @FXML
    private TableView<Category> table;
    @FXML
    private TableColumn<String , Category> tableName;
    @FXML
    private Label error;


    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {

        this.viewHandler = viewHandler;
        this.categoriesVM = viewModelFlyweight.getCategories();
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.setItems(categoriesVM.getCategories());
        error.textProperty().bindBidirectional(categoriesVM.errorProperty());

    }
    public void back(MouseEvent mouseEvent){
        System.out.println("back");
        viewHandler.setCenterView(Views.Start.name());

    }
    public void add(MouseEvent mouseEvent){
        System.out.println("add");
        viewHandler.setCenterView(Views.AddCategory.name());
    }
    public void update(MouseEvent mouseEvent){
        System.out.println("update " + table.getFocusModel().getFocusedItem().getId());
        SessionStorage.setItem("updatedCategory", table.getFocusModel().getFocusedItem());
        viewHandler.setCenterView(Views.UpdateCategory.name());

    }
}
