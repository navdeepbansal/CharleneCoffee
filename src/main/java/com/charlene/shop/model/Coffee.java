package com.charlene.shop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Coffee implements Product{

  private int id = 1;
  private ProductType type = ProductType.BEVERAGE;
  private Size size;
  private int quantity;
  private double price;
  List<Extra> extras = new ArrayList<>(); // Assumption -> Coffee can be ordered with any extra

  public Coffee(){}

  public Coffee(Size size, int quantity){
    this.size = size;
    this.quantity = quantity;
  }

  public Coffee(Size size, double price){
    this.size = size;
    this.price = price;
  }

  public ProductType getType() {
    return type;
  }

  public List<Extra> getExtras() {
    return extras;
  }

  public void setExtras(List<Extra> extras) {
    this.extras = extras;
  }

  public Size getSize() {
    return size;
  }

  public void setSize(Size size) {
    this.size = size;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Coffee coffee = (Coffee) o;
    return id == coffee.id &&
        type == coffee.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
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

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


}
