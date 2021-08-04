package mvvm.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.Category;
import mvvm.model.interfaces.ICategoryService;
import services.SessionStorage;

public class UpdateCategory {
    private ICategoryService categoryService;

    private StringProperty error;
    private StringProperty name;
    private Category updated;

    public Category getUpdated() {
        return updated;
    }

    public UpdateCategory(ICategoryService categoryService) {
        this.categoryService = categoryService;
        error = new SimpleStringProperty();
        name = new SimpleStringProperty();
        updated = (Category) SessionStorage.getItem("updatedCategory");
        name.setValue(updated.getName());
        System.out.println(updated);
    }

    public String updateCategory(){
        String result = categoryService.update(new Category(updated.getId(),name.getValue()));
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
