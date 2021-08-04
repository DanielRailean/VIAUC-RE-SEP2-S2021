package networking.implementation;

import models.Category;
import networking.interfaces.ICategoryServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class CategoryServer implements ICategoryServer {
    private ICategoryServer categoryServer;

    public CategoryServer() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4000);
            this.categoryServer = (ICategoryServer) registry.lookup("CategoriesServer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(Category category) {
        return categoryServer.add(category);
    }

    @Override
    public Category get(int categoryId) {
        return categoryServer.get(categoryId);
    }

    @Override
    public Category get(String categoryName) {
        return categoryServer.get(categoryName);
    }

    @Override
    public List<Category> getAll() {
        return categoryServer.getAll();
    }

    @Override
    public boolean update(Category category) {
        return categoryServer.update(category);
    }
}
