package com.company;

import java.util.Date;

public class Order {
    private Date orderDate;
    private String customerName;
    private long customerPhoneNumber;
    private String deliveryAddress;
    private float discount;
    private OrderStatus orderStatus;
    private Date shippingDate;

    public Order(Date orderDate, String customerName, long customerPhoneNumber, String deliveryAddress, float discount, OrderStatus orderStatus, Date shippingDate) {
        setOrderDate(orderDate);
        setCustomerName(customerName);
        setCustomerPhoneNumber(customerPhoneNumber);
        setDeliveryAddress(deliveryAddress);
        setDiscount(discount);
        setOrderStatus(orderStatus);
        setShippingDate(shippingDate);
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(long customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
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
        this.shippingDate = shippingDate;
    }
}
