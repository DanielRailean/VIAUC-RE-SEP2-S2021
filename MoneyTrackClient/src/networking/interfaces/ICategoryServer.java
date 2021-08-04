package networking.interfaces;

import models.Category;

import java.rmi.Remote;
import java.util.List;

public interface ICategoryServer extends Remote {
    boolean add(Category category);
    Category get(int categoryId);
    Category get(String categoryName);
    List<Category> getAll();
    boolean update(Category category);
}
