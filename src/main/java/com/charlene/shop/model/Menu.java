package com.charlene.shop.model;

import java.util.HashMap;
import java.util.Map;

public class Menu {

  Map<Product, Double> items = new HashMap<Product, Double>();

  public Map<Product, Double> getItems() {
    return items;
  }

  public void setItems(Map<Product, Double> items) {
    this.items = items;
  }
}
