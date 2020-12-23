package com.company.Model;

import java.util.Date;

public class Order {
    private Date orderDate;
    private String customerName;
    private String customerPhoneNumber;
    private String deliveryAddress;
    private float discount;
    private OrderStatus orderStatus;
    private Date shippingDate;
    private OrderItems orderItems;

    public Order(Date orderDate, String customerName, String customerPhoneNumber, String deliveryAddress, float discount,
                 OrderStatus orderStatus) {
        this.orderDate = new Date();
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.deliveryAddress = deliveryAddress;
        this.discount = discount;
        this.orderStatus = OrderStatus.PREPARING;
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

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        if (this.orderStatus.equals(OrderStatus.SHIPPED)) {
            this.shippingDate = shippingDate;
        }
    }

    public OrderItems getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(OrderItems oi) {
        this.orderItems = oi;
    }

    @Override
    public String toString() {
        return customerName + " от " + orderDate;
    }
}
