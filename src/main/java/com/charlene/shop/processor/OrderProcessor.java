package com.charlene.shop.processor;

import com.charlene.shop.model.Bacon;
import com.charlene.shop.model.Coffee;
import com.charlene.shop.model.Customer;
import com.charlene.shop.model.Extra;
import com.charlene.shop.model.ExtraMilk;
import com.charlene.shop.model.FoamedMilk;
import com.charlene.shop.model.FreshJuice;
import com.charlene.shop.model.Menu;
import com.charlene.shop.model.Order;
import com.charlene.shop.model.Product;
import com.charlene.shop.model.ProductType;
import com.charlene.shop.model.Receipt;
import com.charlene.shop.model.RoastCoffee;
import com.charlene.shop.model.Size;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class OrderProcessor {

  Menu menu = new Menu();

  public OrderProcessor(){
    initializeMenu();
  }

  private void initializeMenu(){
    initializeCoffee();
    intializeJuice();
    intializeBacon();

    menu.getItems().put(new ExtraMilk(), 0.30);
    menu.getItems().put(new FoamedMilk(), 0.50);
    menu.getItems().put(new RoastCoffee(), 0.90);
  }

  public Order placeOrder(Customer customer, List<Product> products){
    Order order = new Order();
    order.setOrderId(new Random().nextInt()); // generates a random order id
    order.setCustomer(customer);
    order.setProducts(products);
    return order;
  }


  public Receipt generateReceipt(Order order) {
    Receipt receipt = new Receipt();
    receipt.setId(new Random().nextInt());
    receipt.setOrderDetails(order);

    Double totalPrice = new Double(0);
    for (Product product : order.getProducts()) {
      Double productPrice = menu.getItems().get(product);
      if(Objects.nonNull(productPrice)){
         totalPrice = totalPrice + calculatePrice(product.getQuantity(), productPrice);
      }
      List<Extra> extras = product.getExtras();
      if(Objects.nonNull(extras) && extras.size() > 0){
        for(Extra sideOrder : extras){
          Double sideOrderPrice = menu.getItems().get(sideOrder);
          totalPrice = totalPrice + sideOrderPrice;
        }
      }
    }

    // Requirement -> If a customer orders a beverage and a snack, one of the extra's is free.
    Optional<Product> beverageProduct = order.getProducts().stream().filter(product -> product.getType().equals(ProductType.BEVERAGE)).findAny();
    Optional<Product> snackProduct = order.getProducts().stream().filter(product -> product.getType().equals(ProductType.SNACK)).findAny();

    /*if(beverageProduct.isPresent() && snackProduct.isPresent()){
      order.getProducts().stream().filter(product -> product.getExtras().stream().findAny().).findAny();
    } */

    receipt.setPrice(totalPrice);
    return receipt;
  }

  private Double calculatePrice(int quantity, Double price){
    return quantity * price;
  }

  private void initializeCoffee(){
    Coffee smallCoffee = new Coffee(Size.SMALL, 2.50);
    Coffee mediumCoffee = new Coffee(Size.MEDIUM, 3.00);
    Coffee largeCoffee = new Coffee(Size.LARGE, 3.50);

    menu.getItems().put(smallCoffee, smallCoffee.getPrice());
    menu.getItems().put(mediumCoffee, mediumCoffee.getPrice());
    menu.getItems().put(largeCoffee, largeCoffee.getPrice());
  }

  private void intializeJuice(){
    FreshJuice smallJuice = new FreshJuice(3.95);
    menu.getItems().put(smallJuice, smallJuice.getPrice());
  }

  private void intializeBacon() {
    Bacon bacon = new Bacon(4.50);
    menu.getItems().put(bacon, bacon.getPrice());
  }
}
