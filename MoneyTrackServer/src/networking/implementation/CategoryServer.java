package networking.implementation;


import database.interfaces.ICategoryService;
import models.Category;
import networking.interfaces.ICategoryServer;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CategoryServer implements ICategoryServer {

    private ICategoryService categoryService;

    public CategoryServer(ICategoryService categoryService, Registry registry) {
        try {
            UnicastRemoteObject.exportObject(this,0);
            this.categoryService = categoryService;
            registry.bind("CurrenciesServer", this);
            System.out.println("CategoryServer server started!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(Category category) throws RemoteException {
        return categoryService.add(category);
    }

    @Override
    public Category get(int categoryId) throws RemoteException {
        return categoryService.get(categoryId);

    }

    @Override
    public Category get(String categoryName) throws RemoteException {
        return categoryService.get(categoryName);
    }

    @Override
    public List<Category> getAll() throws RemoteException {
        return categoryService.getAll();
    }

    @Override
    public boolean update(Category category) throws RemoteException {
        return categoryService.update(category);
    }

}
