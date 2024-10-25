package com.jpdr.apps.demo.webflux.retailer.exception;

public class RetailerNotFoundException extends RuntimeException{
  
  public RetailerNotFoundException(Integer retailerId){
    super("The retailer " + retailerId + " wasn't found.");
  }
  
}
