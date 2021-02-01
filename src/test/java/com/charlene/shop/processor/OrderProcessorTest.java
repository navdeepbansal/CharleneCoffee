package com.charlene.shop.processor;

import com.charlene.shop.model.Bacon;
import com.charlene.shop.model.Coffee;
import com.charlene.shop.model.Customer;
import com.charlene.shop.model.Extra;
import com.charlene.shop.model.ExtraMilk;
import com.charlene.shop.model.FreshJuice;
import com.charlene.shop.model.Order;
import com.charlene.shop.model.Product;
import com.charlene.shop.model.Receipt;
import com.charlene.shop.model.RoastCoffee;
import com.charlene.shop.model.Size;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class OrderProcessorTest {


  // Customer has ordered a coffee with extra milk
  @Test
  public void orderCoffeeWithExtraMilkTest(){

    OrderProcessor orderProcessor = new OrderProcessor();

    Customer customer = new Customer(123, "Navdeep Bansal");
    List<Product> orderedProducts = new ArrayList<Product>();

    Coffee coffee = new Coffee(Size.MEDIUM, 1);
    List<Extra> extras = new ArrayList<Extra>();
    extras.add(new ExtraMilk());
    coffee.setExtras(extras);

    orderedProducts.add(coffee);
    Order order = orderProcessor.placeOrder(customer,orderedProducts);

    for (Product product: order.getProducts()) {
      if(product instanceof Coffee){
        Assert.assertEquals(Size.MEDIUM, ((Coffee) product).getSize());
        Assert.assertEquals(1, ((Coffee) product).getQuantity());
      }
    }
  }

  @Test
  public void priceForSmallCoffeeTest(){ // small coffee 2.5

    OrderProcessor orderProcessor = new OrderProcessor();

    Customer customer = new Customer(123, "Navdeep Bansal");
    List<Product> orderedProducts = new ArrayList<Product>();

    Coffee coffee = new Coffee(Size.SMALL, 1);
    orderedProducts.add(coffee);

    Order order = orderProcessor.placeOrder(customer, orderedProducts);
    Receipt receipt = orderProcessor.generateReceipt(order);
    Assert.assertEquals(2.50, receipt.getPrice(), 0.00);

  }

  @Test
  public void priceForMediumCoffeeTest(){ // medium coffee 3.0

    OrderProcessor orderProcessor = new OrderProcessor();

    Customer customer = new Customer(123, "Navdeep Bansal");
    List<Product> orderedProducts = new ArrayList<Product>();

    Coffee coffee = new Coffee(Size.MEDIUM, 1);
    orderedProducts.add(coffee);

    Order order = orderProcessor.placeOrder(customer, orderedProducts);
    Receipt receipt = orderProcessor.generateReceipt(order);
    Assert.assertEquals(3.00, receipt.getPrice(), 0.00);

  }

  @Test
  public void priceForLargeCoffeeTest(){ // large coffee 3.5

    OrderProcessor orderProcessor = new OrderProcessor();

    Customer customer = new Customer(123, "Navdeep Bansal");
    List<Product> orderedProducts = new ArrayList<Product>();

    Coffee coffee = new Coffee(Size.LARGE, 1);
    orderedProducts.add(coffee);

    Order order = orderProcessor.placeOrder(customer, orderedProducts);
    Receipt receipt = orderProcessor.generateReceipt(order);
    Assert.assertEquals(3.50, receipt.getPrice(), 0.00);

  }

  @Test
  public void priceForMediumCoffeeWithExtraMilkTest(){ // medium coffee 3 and extra milk 0.3

    OrderProcessor orderProcessor = new OrderProcessor();

    Customer customer = new Customer(123, "Navdeep Bansal");
    List<Product> orderedProducts = new ArrayList<Product>();

    Coffee coffee = new Coffee(Size.MEDIUM, 1);
    List<Extra> extras = new ArrayList<Extra>();
    extras.add(new ExtraMilk());
    coffee.setExtras(extras);

    orderedProducts.add(coffee);
    Order order = orderProcessor.placeOrder(customer, orderedProducts);
    Receipt receipt = orderProcessor.generateReceipt(order);
    Assert.assertEquals(3.3, receipt.getPrice(), 0.00);

  }

  @Test
  public void priceForJuiceTest(){

    OrderProcessor orderProcessor = new OrderProcessor();

    Customer customer = new Customer(123, "Navdeep Bansal");
    List<Product> orderedProducts = new ArrayList<Product>();

    FreshJuice juice = new FreshJuice(1);
    orderedProducts.add(juice);

    Order order = orderProcessor.placeOrder(customer, orderedProducts);
    Receipt receipt = orderProcessor.generateReceipt(order);
    Assert.assertEquals(3.95, receipt.getPrice(), 0.00);

  }

  @Test
  public void priceForBaconTest(){

    OrderProcessor orderProcessor = new OrderProcessor();

    Customer customer = new Customer(123, "Navdeep Bansal");
    List<Product> orderedProducts = new ArrayList<Product>();

    Bacon bacon = new Bacon(1);
    orderedProducts.add(bacon);

    Order order = orderProcessor.placeOrder(customer, orderedProducts);
    Receipt receipt = orderProcessor.generateReceipt(order);
    Assert.assertEquals(4.50, receipt.getPrice(), 0.00);

  }

  @Test
  public void priceForJuiceAndBaconTest(){

    OrderProcessor orderProcessor = new OrderProcessor();

    Customer customer = new Customer(123, "Navdeep Bansal");
    List<Product> orderedProducts = new ArrayList<Product>();

    Bacon bacon = new Bacon(1);
    FreshJuice juice = new FreshJuice(1);
    orderedProducts.add(bacon);
    orderedProducts.add(juice);

    Order order = orderProcessor.placeOrder(customer, orderedProducts);
    Receipt receipt = orderProcessor.generateReceipt(order);
    Assert.assertEquals(8.45, receipt.getPrice(), 0.00);

  }

  @Test
  public void priceForCoffeeWithExtraAndSnackTest(){ // Small Coffee 2.5 ExtraMilk 0.30 Snack 4.5

    OrderProcessor orderProcessor = new OrderProcessor();

    Customer customer = new Customer(123, "Navdeep Bansal");
    List<Product> orderedProducts = new ArrayList<Product>();

    Bacon bacon = new Bacon(1);
    Coffee coffee = new Coffee(Size.SMALL, 1);
    List<Extra> extras = new ArrayList<Extra>();
    extras.add(new ExtraMilk());
    coffee.setExtras(extras);

    orderedProducts.add(coffee);
    orderedProducts.add(bacon);

    Order order = orderProcessor.placeOrder(customer, orderedProducts);
    Receipt receipt = orderProcessor.generateReceipt(order);
    Assert.assertEquals(7.00, receipt.getPrice(), 0.00);

  }


  @Test
  public void priceForCoffeeWithMultipleExtraAndSnackTest(){

    OrderProcessor orderProcessor = new OrderProcessor();

    Customer customer = new Customer(123, "Navdeep Bansal");
    List<Product> orderedProducts = new ArrayList<Product>();

    Bacon bacon = new Bacon(1);
    Coffee coffee = new Coffee(Size.SMALL, 1);
    List<Extra> extras = new ArrayList<Extra>();
    extras.add(new ExtraMilk());
    extras.add(new RoastCoffee());
    coffee.setExtras(extras);

    orderedProducts.add(coffee);
    orderedProducts.add(bacon);

    Order order = orderProcessor.placeOrder(customer, orderedProducts);
    Receipt receipt = orderProcessor.generateReceipt(order);
    Assert.assertEquals(7.90, receipt.getPrice(), 0.01);

  }

  @Test
  public void fifthBeverageFreeForSameCustomerTest(){

    OrderProcessor orderProcessor = new OrderProcessor();

    Customer customer = new Customer(123, "Navdeep Bansal");

    List<Product> orderedProducts1 = new ArrayList<Product>();
    Coffee coffee = new Coffee(Size.SMALL,1);
    orderedProducts1.add(coffee);
    Order order1 = orderProcessor.placeOrder(customer, orderedProducts1);

    List<Product> orderedProducts2 = new ArrayList<Product>();
    FreshJuice juice = new FreshJuice(1);
    orderedProducts2.add(juice);
    Order order2 = orderProcessor.placeOrder(customer, orderedProducts2);

    List<Product> orderedProducts3 = new ArrayList<Product>();
    FreshJuice juice1 = new FreshJuice(1);
    orderedProducts3.add(juice1);
    Order order3 = orderProcessor.placeOrder(customer, orderedProducts3);

    List<Product> orderedProducts4 = new ArrayList<Product>();
    Bacon bacon = new Bacon(1);
    orderedProducts4.add(bacon);
    Order order4 = orderProcessor.placeOrder(customer, orderedProducts4);

    List<Product> orderedProducts5 = new ArrayList<Product>();
    Coffee coffee1 = new Coffee(Size.SMALL,1);
    orderedProducts5.add(coffee1);
    Order order5 = orderProcessor.placeOrder(customer, orderedProducts5);

    Receipt receipt1 = orderProcessor.generateReceipt(order1);
    Receipt receipt2 = orderProcessor.generateReceipt(order2);
    Receipt receipt3 = orderProcessor.generateReceipt(order3);
    Receipt receipt4 = orderProcessor.generateReceipt(order4);
    Receipt receipt5 = orderProcessor.generateReceipt(order5);

    Assert.assertEquals(0.00, receipt5.getPrice(), 0.00);

  }

  @Test
  public void onlyOneFifthBeverageFreeForSameCustomerTest(){

    OrderProcessor orderProcessor = new OrderProcessor();

    Customer customer = new Customer(123, "Navdeep Bansal");

    List<Product> orderedProducts1 = new ArrayList<Product>();
    Coffee coffee = new Coffee(Size.SMALL,1);
    orderedProducts1.add(coffee);
    Order order1 = orderProcessor.placeOrder(customer, orderedProducts1);

    List<Product> orderedProducts2 = new ArrayList<Product>();
    FreshJuice juice = new FreshJuice(1);
    orderedProducts2.add(juice);
    Order order2 = orderProcessor.placeOrder(customer, orderedProducts2);

    List<Product> orderedProducts3 = new ArrayList<Product>();
    FreshJuice juice1 = new FreshJuice(1);
    orderedProducts3.add(juice1);
    Order order3 = orderProcessor.placeOrder(customer, orderedProducts3);

    List<Product> orderedProducts4 = new ArrayList<Product>();
    Bacon bacon = new Bacon(1);
    orderedProducts4.add(bacon);
    Order order4 = orderProcessor.placeOrder(customer, orderedProducts4);

    List<Product> orderedProducts5 = new ArrayList<Product>();
    Coffee coffee1 = new Coffee(Size.SMALL,1);
    orderedProducts5.add(coffee1);
    FreshJuice juice2 = new FreshJuice(1);
    orderedProducts5.add(juice2);
    Order order5 = orderProcessor.placeOrder(customer, orderedProducts5);

    Receipt receipt1 = orderProcessor.generateReceipt(order1);
    Receipt receipt2 = orderProcessor.generateReceipt(order2);
    Receipt receipt3 = orderProcessor.generateReceipt(order3);
    Receipt receipt4 = orderProcessor.generateReceipt(order4);
    Receipt receipt5 = orderProcessor.generateReceipt(order5);

    Assert.assertEquals(3.95, receipt5.getPrice(), 0.00);

  }



}
