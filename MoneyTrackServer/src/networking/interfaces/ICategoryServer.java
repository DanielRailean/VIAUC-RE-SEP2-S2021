package networking.interfaces;

import models.Category;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ICategoryServer extends Remote {
    boolean add(Category category) throws RemoteException;
    Category get(int categoryId) throws RemoteException;
    Category get(String categoryName) throws RemoteException;
    List<Category> getAll() throws RemoteException;
    boolean update(Category category) throws RemoteException;
}
