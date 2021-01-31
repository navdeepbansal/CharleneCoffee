package com.charlene.shop.model;

public enum Size {

  SMALL(2.50),
  MEDIUM(3.00),
  LARGE(3.50);

  private double price;

  Size(final double price) {
    this.price = price;
  }

  public double getPrice() {
    return price;
  }

}
