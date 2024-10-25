package com.jpdr.apps.demo.webflux.retailer.exception;

public class SectorNotFoundException extends RuntimeException{
  
  public SectorNotFoundException(Integer sectorId){
    super("The sector " + sectorId + " wasn't found.");
  }
  
  
}
