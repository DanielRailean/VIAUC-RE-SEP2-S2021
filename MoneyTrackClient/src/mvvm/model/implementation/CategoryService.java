package mvvm.model.implementation;

import models.Category;
import mvvm.model.Validator;
import mvvm.model.interfaces.ICategoryService;
import networking.interfaces.ICategoryServer;

import java.util.List;

public class CategoryService implements ICategoryService {
    private ICategoryServer categoryServer;

    public CategoryService(ICategoryServer categoryServer) {
        this.categoryServer = categoryServer;
    }

    @Override
    public String add(Category category) {
        if(!Validator.isValidCategoryName(category.getName())) return "Name invalid!";
        if (categoryServer.add(category)) {
            return "Category successfully added";
        }
        return "Unable to add category , most likely , name is already taken !";
    }

    @Override
    public Category get(int categoryId) throws Exception {
        Category category = categoryServer.get(categoryId);
        if(category!=null) return  category;
        throw new Exception("Category does not exist");
    }

    @Override
    public Category get(String categoryName) throws Exception {
        Category category = categoryServer.get(categoryName);
        if(category!=null) return  category;
        throw new Exception("Category does not exist");
    }

    @Override
    public List<Category> getAll() {
        return categoryServer.getAll();
    }

    @Override
    public String update(Category category) {
        if(!Validator.isValidCategoryName(category.getName())) return "Name invalid!";
        if (categoryServer.update(category)) {
            return "Category successfully updated";
        }
        return "Unable to update category , most likely , name is already taken !";
    }

}
