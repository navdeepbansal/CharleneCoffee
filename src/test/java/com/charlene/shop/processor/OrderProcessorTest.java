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

  /**
   * Use Case - Customer orders 5 beverages in a single order
   * 1st order - 5 Beverages
   */
  @Test
  public void fifthBeverageFreeForSameCustomerTest(){
    OrderProcessor orderProcessor = new OrderProcessor();

    Customer customer = new Customer(123, "Navdeep Bansal");

    List<Product> orderedProducts = new ArrayList<>();
    Coffee coffee = new Coffee(Size.SMALL, 5);
    orderedProducts.add(coffee);
    Order order = orderProcessor.placeOrder(customer, orderedProducts);
    orderProcessor.generateReceipt(order);
  }

  /**
   * Use Case - Customer orders 5 beverages in 2 orders
   * 1st order - 3 Beverages
   * 2nd order - 2 Beverage
   */
  @Test
  public void fifthBeverageFreeForSameCustomerTest1(){
    OrderProcessor orderProcessor = new OrderProcessor();

    Customer customer = new Customer(123, "Navdeep Bansal");

    List<Product> orderedProducts = new ArrayList<>();
    Coffee coffee = new Coffee(Size.SMALL, 3);
    orderedProducts.add(coffee);
    Order order = orderProcessor.placeOrder(customer, orderedProducts);

    List<Product> orderedProducts1 = new ArrayList<>();
    FreshJuice juice = new FreshJuice(2);
    orderedProducts1.add(juice);
    Order order1 = orderProcessor.placeOrder(customer, orderedProducts1);

    orderProcessor.generateReceipt(order);
    orderProcessor.generateReceipt(order1);
  }

  /**
   * Use Case - Customer orders 5 beverages in 3 orders
   * 1st order - 3 Beverages
   * 2nd order - 1 Beverage
   * 3rd order - 1 Beverage
   */
  @Test
  public void fifthBeverageFreeForSameCustomerTest2(){
    OrderProcessor orderProcessor = new OrderProcessor();

    Customer customer = new Customer(123, "Navdeep Bansal");

    List<Product> orderedProducts = new ArrayList<>();
    Coffee coffee = new Coffee(Size.SMALL, 3);
    orderedProducts.add(coffee);
    Order order = orderProcessor.placeOrder(customer, orderedProducts);

    List<Product> orderedProducts1 = new ArrayList<>();
    FreshJuice juice = new FreshJuice(1);
    orderedProducts1.add(juice);
    Order order1 = orderProcessor.placeOrder(customer, orderedProducts1);

    List<Product> orderedProducts2 = new ArrayList<>();
    Coffee coffee1 = new Coffee(Size.LARGE, 1);
    orderedProducts2.add(coffee1);
    Order order2 = orderProcessor.placeOrder(customer, orderedProducts2);

    orderProcessor.generateReceipt(order);
    orderProcessor.generateReceipt(order1);
    orderProcessor.generateReceipt(order2);
  }
}
