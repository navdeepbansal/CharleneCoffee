package com.charlene.shop.model;

import java.util.List;
import java.util.Objects;

public class FoamedMilk implements Extra{

  private int id = 5;
  private ProductType type = ProductType.EXTRA;
  private double price;

  public ProductType getType() {
    return type;
  }

  public FoamedMilk(){}

  public int getId() {
    return id;
  }

  public void setId(int id) {
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
    FoamedMilk that = (FoamedMilk) o;
    return id == that.id &&
        type == that.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  public FoamedMilk(double price){
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


}
