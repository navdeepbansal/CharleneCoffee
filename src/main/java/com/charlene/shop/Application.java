package com.charlene.shop;

import com.charlene.shop.model.Bacon;
import com.charlene.shop.model.Coffee;
import com.charlene.shop.model.Customer;
import com.charlene.shop.model.Extra;
import com.charlene.shop.model.FoamedMilk;
import com.charlene.shop.model.FreshJuice;
import com.charlene.shop.model.Order;
import com.charlene.shop.model.Product;
import com.charlene.shop.model.Size;
import com.charlene.shop.processor.OrderProcessor;
import java.util.ArrayList;
import java.util.List;

public class Application {

  public static void main(String[] args) {
    OrderProcessor orderProcessor = new OrderProcessor();

    /* Use Case - When customer orders beverage with extra and snack, extra is free
     Small Coffee -> 2.5, Foamed Milk -> 0.50, Bacon -> 4.5
     Total Bill -> (2.5 + 0.5 + 4.5) - 0.5 = 7.0
     */
    Customer customer = new Customer(123, "Navdeep Bansal");
    List<Product> orderedProducts = new ArrayList<Product>();
    Coffee coffee = new Coffee(Size.SMALL, 1); // customer has to mention size and quantity

    List<Extra> extras = new ArrayList<Extra>();
    extras.add(new FoamedMilk());
    coffee.setExtras(extras);

    Bacon bacon = new Bacon(1); // customer has to mention quantity
    orderedProducts.add(coffee);
    orderedProducts.add(bacon);

    Order order = orderProcessor.placeOrder(customer, orderedProducts);
    orderProcessor.generateReceipt(order);

    // Use Case - Offer a customer stamp card, where every 5th beverage is for free

    System.out.println("---------------------------------------------------------------");
    Customer customer1 = new Customer(456, "Johny Bansal");

    List<Product> orderedProducts1 = new ArrayList<Product>();
    Coffee coffee1 = new Coffee(Size.SMALL,3);
    orderedProducts1.add(coffee1);
    Order order1 = orderProcessor.placeOrder(customer1, orderedProducts1);

    List<Product> orderedProducts2 = new ArrayList<Product>();
    FreshJuice juice = new FreshJuice(2);
    orderedProducts2.add(juice);
    Order order2 = orderProcessor.placeOrder(customer1, orderedProducts2);

    orderProcessor.generateReceipt(order1);
    orderProcessor.generateReceipt(order2);

  }
}
