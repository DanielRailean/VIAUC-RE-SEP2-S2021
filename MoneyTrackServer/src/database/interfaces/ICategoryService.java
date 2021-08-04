package database.interfaces;

import models.Category;

import java.util.List;

public interface ICategoryService {
    boolean add(Category category);
    Category get(int categoryId);
    Category get(String categoryName);
    List<Category> getAll();
    boolean update(Category category);
}
