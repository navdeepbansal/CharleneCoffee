package com.charlene.shop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Assumption -> size is not considered for bacon
public class Bacon implements Product{

  private int id = 2;
  private ProductType type = ProductType.SNACK;

  public ProductType getType() {
    return type;
  }

  int quantity;
  double price;
  List<Extra> extras = new ArrayList<>(); // Assumption -> bacon can be ordered with any extra

  public Bacon(){}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Bacon(int quantity){
    this.quantity = quantity;
  }

  public Bacon(double price){
    this.price = price;
  }

  public List<Extra> getExtras() {
    return extras;
  }

  public void setExtra(List<Extra> extras) {
    this.extras = extras;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Bacon bacon = (Bacon) o;
    return id == bacon.id &&
        type == bacon.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  public void setPrice(double price) {
    this.price = price;
  }


}
