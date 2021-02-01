package com.charlene.shop.model;

import java.util.List;
import java.util.Objects;

public class ExtraMilk implements Extra {

  private String id = "extramilk";
  private ProductType type = ProductType.EXTRA;

  private double price;

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

  public String getId() {
    return id;
  }

  public void setId(String id) {
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

  public ProductType getType() {
    return type;
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
