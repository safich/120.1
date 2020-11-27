package com.company;

public class OrderPosition {
    private Product product;
    private float buyPrice;
    private int buyNumber;

    public OrderPosition(Product product, float buyPrice, int buyNumber) {
        setProduct(product);
        setBuyPrice(buyPrice);
        setBuyNumber(buyNumber);
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

    public int getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(int buyNumber) {
        this.buyNumber = buyNumber;
    }
}
