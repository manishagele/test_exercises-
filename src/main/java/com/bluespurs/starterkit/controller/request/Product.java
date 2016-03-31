/**
 * 
 */
package com.bluespurs.starterkit.controller.request;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Product DTO which holds the attributes of a
 * product requested by a user
 * @author manisha
 *
 */
@XmlRootElement(name = "Product")
public class Product {

	String productName;
	double price;
	String currencyType;
	String location;
	
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getCurrencyType() {
		return currencyType;
	}
	
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}


}
