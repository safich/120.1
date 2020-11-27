package com.company;

public class Product {
    String name;
    String color;
    float price;
    int number;

    public Product(String name, float price, int number) {
        setName(name);
        setPrice(price);
        setNumber(number);
    }

    public Product(String name, float price, int number, String color) {
        this (name, price, number);
        setColor(color);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
