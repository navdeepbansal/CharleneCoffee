package com.charlene.shop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FreshJuice implements Product{

  private int id = 3;
  private ProductType type = ProductType.BEVERAGE;
  private int quantity;
  private double price;
  List<Extra> extras = new ArrayList<>(); // Assumption -> Juice can be ordered with any extra

  public FreshJuice(){

  }

  public FreshJuice(int quantity){
    this.quantity = quantity;
  }

  public FreshJuice(double price){
    this.price = price;
  }

  public List<Extra> getExtras() {
    return extras;
  }

  public void setExtra(List<Extra> extras) {
    this.extras = extras;
  }


  public ProductType getType() {
    return type;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FreshJuice that = (FreshJuice) o;
    return id == that.id &&
        type == that.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

}
