package mvvm.model.interfaces;

import models.Category;

import java.util.List;

public interface ICategoryService {
    String add(Category category);
    Category get(int categoryId) throws Exception;
    Category get(String categoryName) throws Exception;
    List<Category> getAll();
    String update(Category category);
}
