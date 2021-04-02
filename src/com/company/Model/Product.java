package com.company.Model;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    private final Long vendorCode;
    private final String name;
    private String color;
    private final float price;
    private int number;

    public Product(Long vendorCode, String name, String color, float price, int number) {
        this.vendorCode = vendorCode;
        this.name = name;
        setColor(color);
        this.price = price;
        setNumber(number);
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color == null) {
            this.color = "";
        }
        else this.color = color;
    }

    public float getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        if(number < 0) return;
        else this.number = number;
    }

    public Long getVendorCode() {
        return vendorCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Product other = (Product) o;
        return this.vendorCode.equals(other.vendorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendorCode);
    }

    @Override
    public String toString() {
        return name + " " + color;
    }
}
