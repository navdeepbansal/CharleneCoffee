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

  /**
   * This method is used to initialize menu
   * products and their prices are initialized in this method
   */
  private void initializeMenu(){
    initializeCoffee();
    intializeJuice();
    intializeBacon();
    initializeExtras();
  }

  /**
   * @param customer
   * @param products
   * @return
   *
   * api to place an order
   */
  public Order placeOrder(Customer customer, List<Product> products){
    Order order = new Order();
    order.setOrderId(new Random().nextInt() & Integer.MAX_VALUE); // generates a random order id
    order.setCustomer(customer);
    order.setProducts(products);
    return order;
  }


  /**
   * @param order
   * @return
   *
   * generates receipt
   */
  public Receipt generateReceipt(Order order) {
    StringBuilder invoice = new StringBuilder();
    Receipt receipt = new Receipt();

    Double totalPrice = new Double(0); // to store the total price of order

    receipt.setId(new Random().nextInt() & Integer.MAX_VALUE);
    receipt.setOrderDetails(order);
    order.getCustomer().setStampCard(order.getCustomer().getStampCard() + 1);

    invoice.append("OrderId: " + order.getOrderId() + "\n");
    invoice.append("Customer Phone: " + order.getCustomer().getPhoneNumber() + "\n");

    totalPrice = calculatePriceForOrder(order, totalPrice, invoice);
    totalPrice = checkForDiscount(order, invoice, totalPrice);

    invoice.append("TOTAL PRICE --------- " + totalPrice + "\n");
    System.out.println(invoice);
    receipt.setPrice(totalPrice);
    return receipt;
  }

  /**
   * @param order
   * @param totalPrice
   * @param invoice
   * @return
   *
   * calculates price for an order placed
   */
  private Double calculatePriceForOrder(Order order, Double totalPrice, StringBuilder invoice){

    for (Product product : order.getProducts()) {
      Double productPrice = menu.getItems().get(product);
      invoice.append(product.getId().toUpperCase() + "------" + productPrice + "\n");

      if (Objects.nonNull(productPrice)) {
        totalPrice = totalPrice + calculatePriceOfProduct(product.getQuantity(), productPrice);
      }
      List<Extra> extras = product.getExtras();
      if (Objects.nonNull(extras) && extras.size() > 0) {
        for (Extra sideOrder : extras) {
          Double sideOrderPrice = menu.getItems().get(sideOrder);
          invoice.append(sideOrder.getId().toUpperCase() + "------" + sideOrderPrice + "\n");
          totalPrice = totalPrice + sideOrderPrice;
        }
      }
    }
    return  totalPrice;
  }

  /**
   * @param order
   * @param invoice
   * @param totalPrice
   * @return
   *
   * check for discount
   * Use Case - If a customer orders a beverage and a snack, one of the extra's is free
   * Use Case - offer a customer stamp card, where every 5th beverage is for free
   */
  private double checkForDiscount(Order order, StringBuilder invoice, Double totalPrice){
    // Use Case -> If a customer orders a beverage and a snack, one of the extra's is free.
    Optional<Product> beverageProduct = order.getProducts().stream().filter(product -> product.getType().equals(ProductType.BEVERAGE)).findAny();
    Optional<Product> snackProduct = order.getProducts().stream().filter(product -> product.getType().equals(ProductType.SNACK)).findAny();

    boolean applyDiscount = false;
    if (beverageProduct.isPresent() && snackProduct.isPresent()) {
      applyDiscount = true;
      for (Product product : order.getProducts()) {
        Optional<Extra> extraToDiscount = product.getExtras().stream().findAny();
        // to apply discount only for one of the extras
        if (extraToDiscount.isPresent() && applyDiscount) {
          Double priceToDiscount = menu.getItems().get(extraToDiscount.get());
          invoice.append("DISCOUNTED " + extraToDiscount.get().getId().toUpperCase() +"------"+ priceToDiscount + "\n");
          totalPrice = totalPrice - priceToDiscount;
          applyDiscount = false;
        }
      }
    }

    // Use Case - She decides to offer a customer stamp card, where every 5th beverage is for free
    if(order.getCustomer().getStampCard()%5 == 0){
      Optional<Product> beveragetoDiscount = order.getProducts().stream().filter(product -> product.getType().equals(ProductType.BEVERAGE)).findFirst();
      if(beveragetoDiscount.isPresent()){
        Double beveragePriceToDiscount = menu.getItems().get(beverageProduct.get());
        invoice.append("DISCOUNTED 5th BEVERAGE " + beverageProduct.get().getId().toUpperCase() +"------"+ beveragePriceToDiscount + "\n");
        totalPrice = totalPrice - beveragePriceToDiscount;
      }
    }
    return totalPrice;
  }


  /**
   * @param quantity
   * @param price
   * @return
   *
   * calculate price for a product, return price quantity wise
   */
  private Double calculatePriceOfProduct(int quantity, Double price){
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

  private void initializeExtras(){
    menu.getItems().put(new ExtraMilk(), 0.30);
    menu.getItems().put(new FoamedMilk(), 0.50);
    menu.getItems().put(new RoastCoffee(), 0.90);
  }

  private void intializeBacon() {
    Bacon bacon = new Bacon(4.50);
    menu.getItems().put(bacon, bacon.getPrice());
  }
}
