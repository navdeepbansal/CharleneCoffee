package com.charlene.shop.model;

import java.util.List;
import java.util.Objects;

public class RoastCoffee implements Extra {

  private String id = "roastCoffee";
  private ProductType type = ProductType.EXTRA;

  private double price;

  public RoastCoffee(){}

  public RoastCoffee(double price){
    this.price = price;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public int getQuantity() {
    return 0;
  }

  @Override
  public List<Extra> getExtras() {
    return null;
  }

  @Override
  public ProductType getType() {
    return null;
  }

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoastCoffee that = (RoastCoffee) o;
    return id == that.id &&
        type == that.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }
}
