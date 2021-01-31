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
  public void orderMediumCoffeeWithExtraMilkTest(){ // medium coffee 3 and extra milk 0.3

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
    Assert.assertEquals(3.3, receipt.getPrice());

  }

  @Test
  public void orderJuiceAndbaconTest(){

    OrderProcessor orderProcessor = new OrderProcessor();

    Customer customer = new Customer(123, "Navdeep Bansal");
    List<Product> orderedProducts = new ArrayList<Product>();

    Bacon bacon = new Bacon(1);
    FreshJuice juice = new FreshJuice(1);
    orderedProducts.add(bacon);
    orderedProducts.add(juice);

    Order order = orderProcessor.placeOrder(customer, orderedProducts);

    Receipt receipt = orderProcessor.generateReceipt(order);
    Assert.assertEquals(8.45, receipt.getPrice());

  }




}
