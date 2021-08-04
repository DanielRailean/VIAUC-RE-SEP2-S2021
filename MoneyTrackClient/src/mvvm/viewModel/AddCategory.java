package mvvm.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.Category;
import mvvm.model.interfaces.ICategoryService;

public class AddCategory {
    private ICategoryService categoryService;

    private StringProperty error;
    private StringProperty name;

    public AddCategory(ICategoryService categoryService) {
        this.categoryService = categoryService;
        error = new SimpleStringProperty();
        name = new SimpleStringProperty();
    }

    public String addCategory(){
        String result = categoryService.add(new Category(name.getValue()));
        error.setValue(result);
        return result;
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

}
