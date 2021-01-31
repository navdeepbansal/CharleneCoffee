package com.charlene.shop.model;

public class Receipt {

  private int id;
  private Order orderDetails;
  private double price;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Order getOrderDetails() {
    return orderDetails;
  }

  public void setOrderDetails(Order orderDetails) {
    this.orderDetails = orderDetails;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
