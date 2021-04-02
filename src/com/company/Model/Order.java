package com.company.Model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {
    private final Date orderDate;
    private String customerName;
    private String customerPhoneNumber;
    private String deliveryAddress;
    private float discount;
    private OrderStatus orderStatus;
    private Date shippingDate;
    private ArrayList<OrderItems> orderItems;

    public Order(String customerName, String customerPhoneNumber, String deliveryAddress, float discount, ArrayList<OrderItems> orderItems) {
        this.orderStatus = OrderStatus.PREPARING;
        this.orderDate = new Date();
        setCustomerName(customerName);
        setCustomerPhoneNumber(customerPhoneNumber);
        setDeliveryAddress(deliveryAddress);
        setDiscount(discount);
        this.orderItems = orderItems;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        if (this.orderStatus.equals(OrderStatus.PREPARING)) {
            this.customerName = customerName;
        }
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        if (this.orderStatus.equals(OrderStatus.PREPARING)) {
            this.customerPhoneNumber = customerPhoneNumber;
        }
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        if (this.orderStatus.equals(OrderStatus.PREPARING)) {
            this.deliveryAddress = deliveryAddress;
        }
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        if (this.orderStatus.equals(OrderStatus.PREPARING)) {
            this.discount = discount;
        }
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
        }

    public String getStringOrderStatus() {
        if (orderStatus.equals(OrderStatus.CANCELED)) return "Отменен";
        else if (orderStatus.equals(OrderStatus.PREPARING)) return "Готовится";
        else if (orderStatus.equals(OrderStatus.SHIPPED)) return "Отправлен";
        return null;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getShippingDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
        String date = dateFormat.format(shippingDate);
        return date;
    }

    public void setShippingDate(Date shippingDate) {
        if (this.orderStatus.equals(OrderStatus.SHIPPED)) {
            this.shippingDate = shippingDate;
        }
    }

    public ArrayList<OrderItems> getOrderItems() {
        return orderItems;
    }

    public String getOrderItemsNames() {
        String s = "";
        for (int i = 0; i < orderItems.size(); i++) {
            s = s + orderItems.get(i).getProduct().getName() + ", " + orderItems.get(i).getProduct().getColor();
        }
        return s;
    }

    public double getOrderItemsPrice() {
        float f = 0;
        for (int i = 0; i < orderItems.size(); i++) {
            f = f + orderItems.get(i).getBuyPrice();
        }
        return f - (f * (discount * 0.01));
    }

    @Override
    public String toString() {
        return customerName + " от " + orderDate;
    }
}
