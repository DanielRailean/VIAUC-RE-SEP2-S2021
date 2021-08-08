package models;

import java.io.Serializable;

public class Budget implements Serializable {
    private int id;
    private int amount;
    private int month;
    private int year;
    private int categoryId;
    private int currencyId;
    private int ownerId;
    private String categoryName;
    private String currencyName;


    public Budget( int amount, int month, int year, int categoryId, int currencyId, int ownerId) {
        this.amount = amount;
        this.month = month;
        this.year = year;
        this.categoryId = categoryId;
        this.currencyId = currencyId;
        this.ownerId = ownerId;
    }

    public Budget(int id, int amount, int month, int year, int categoryId, int currencyId, int ownerId, String categoryName, String currencyName) {
        this.id = id;
        this.amount = amount;
        this.month = month;
        this.year = year;
        this.categoryId = categoryId;
        this.currencyId = currencyId;
        this.ownerId = ownerId;
        this.categoryName = categoryName;
        this.currencyName = currencyName;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}
