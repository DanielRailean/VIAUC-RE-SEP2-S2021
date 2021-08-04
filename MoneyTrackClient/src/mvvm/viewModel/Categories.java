package mvvm.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Category;
import mvvm.model.interfaces.ICategoryService;

public class Categories {
    private ICategoryService categoryService;

    private StringProperty error;
    private ObservableList<Category> categories;

    public Categories(ICategoryService categoryService) {
        this.categoryService = categoryService;
        error = new SimpleStringProperty();
        categories = FXCollections.observableArrayList();
        categories.addAll(categoryService.getAll());
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

    public ObservableList<Category> getCategories() {
        categories = FXCollections.observableArrayList();
        categories.addAll(categoryService.getAll());
        return  categories;
    }

    public void setCategories(ObservableList<Category> categories) {
        this.categories = categories;
    }
}
