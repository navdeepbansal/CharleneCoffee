package com.charlene.shop.model;

import java.util.List;

public interface Product {

  public int getQuantity();
  public String getId();
  public List<Extra> getExtras();
  public ProductType getType();
}
