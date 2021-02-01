package com.charlene.shop;

import com.charlene.shop.model.Coffee;
import com.charlene.shop.model.Customer;
import com.charlene.shop.model.Order;
import com.charlene.shop.model.Product;
import com.charlene.shop.model.Receipt;
import com.charlene.shop.model.Size;
import com.charlene.shop.processor.OrderProcessor;
import java.util.ArrayList;
import java.util.List;

public class Application {

  public static void main(String[] args) {
    OrderProcessor orderProcessor = new OrderProcessor();

    Customer customer = new Customer(123, "Navdeep Bansal");
    List<Product> orderedProducts = new ArrayList<Product>();
    Coffee coffee = new Coffee(Size.SMALL, 1);
    orderedProducts.add(coffee);

    Order order = orderProcessor.placeOrder(customer, orderedProducts);
    Receipt receipt = orderProcessor.generateReceipt(order);
    System.out.println("Total Bill: " + receipt.getPrice());
  }
}
