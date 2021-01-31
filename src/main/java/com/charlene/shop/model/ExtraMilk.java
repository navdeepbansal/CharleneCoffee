package com.charlene.shop.model;

import java.util.List;
import java.util.Objects;

public class ExtraMilk implements Extra {

  private int id = 4;
  private ProductType type = ProductType.EXTRA;
  private double price;

  public ProductType getType() {
    return type;
  }

  public ExtraMilk(){}

  public ExtraMilk(double price){
    this.price = price;
  }
  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExtraMilk extraMilk = (ExtraMilk) o;
    return id == extraMilk.id &&
        type == extraMilk.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }
}
