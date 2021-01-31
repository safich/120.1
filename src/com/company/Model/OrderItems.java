package com.company.Model;

public class OrderItems {
    private Product product;
    private float buyPrice;
    private int amount;

    public OrderItems(Product product, float buyPrice, int amount) {
        this.product = product;
        this.buyPrice = buyPrice;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return product.getName();
    }
}
