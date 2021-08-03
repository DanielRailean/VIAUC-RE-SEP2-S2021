package models;

import java.io.Serializable;

public class Currency implements Serializable {
    private int id;
    private String name;
    private float priceInEuro;

    public Currency(int id, String name, float priceInEuro) {
        this.id = id;
        this.name = name;
        this.priceInEuro = priceInEuro;
    }

    public Currency(String name, float priceInEuro) {
        this.name = name;
        this.priceInEuro = priceInEuro;
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

    public float getPriceInEuro() {
        return priceInEuro;
    }

    public void setPriceInEuro(float priceInEuro) {
        this.priceInEuro = priceInEuro;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priceInEuro=" + priceInEuro +
                '}';
    }
}
