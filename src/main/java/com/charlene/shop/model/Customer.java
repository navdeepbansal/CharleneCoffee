package com.charlene.shop.model;

public class Customer {

  private int phoneNumber;
  private String customerName;
  private int stampCard = 0;

  public Customer(int phoneNumber, String customerName){
    this.phoneNumber = phoneNumber;
    this.customerName = customerName;
  }

  public int getStampCard() {
    return stampCard;
  }

  public void setStampCard(int stampCard) {
    this.stampCard = stampCard;
  }

  public int getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(int phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }
}
