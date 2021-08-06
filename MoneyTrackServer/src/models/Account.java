package models;

import java.io.Serializable;

public class Account implements Serializable {
    private int id;
    private String name;
    private float balance;
    private int ownerId;
    private int currencyId;
    private int sharedWith;
    private String currencyName;
    private String sharedEmail;

    public Account(String name, float balance, int ownerId, int currencyId, int sharedWith, String currencyName, String sharedEmail) {
        this.name = name;
        this.balance = balance;
        this.ownerId = ownerId;
        this.currencyId = currencyId;
        this.sharedWith = sharedWith;
        this.currencyName = currencyName;
        this.sharedEmail = sharedEmail;
    }

    public Account(String name, float balance, int ownerId, int currencyId, int sharedWith) {
        this.name = name;
        this.balance = balance;
        this.ownerId = ownerId;
        this.currencyId = currencyId;
        this.sharedWith = sharedWith;
    }

    public Account(int id, String name, float balance, int ownerId, int currencyId, int sharedWith, String currencyName, String sharedEmail) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.ownerId = ownerId;
        this.currencyId = currencyId;
        this.sharedWith = sharedWith;
        this.currencyName = currencyName;
        this.sharedEmail = sharedEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBalance() {
        return balance;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getSharedEmail() {
        return sharedEmail;
    }

    public void setSharedEmail(String sharedEmail) {
        this.sharedEmail = sharedEmail;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public int getSharedWith() {
        return sharedWith;
    }

    public void setSharedWith(int sharedWith) {
        this.sharedWith = sharedWith;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", ownerId=" + ownerId +
                ", currencyId=" + currencyId +
                ", sharedWith=" + sharedWith +
                '}';
    }
}
