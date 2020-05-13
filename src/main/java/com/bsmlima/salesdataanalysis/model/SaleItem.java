package com.bsmlima.salesdataanalysis.model;

public class SaleItem {

    private int id;
    private int quantity;
    private Double price;

    public SaleItem() {
    }

    public SaleItem(int id, int quantity, Double price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
