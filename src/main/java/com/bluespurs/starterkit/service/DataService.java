package com.bluespurs.starterkit.service;

import java.util.List;

import com.bluespurs.starterkit.controller.request.Product;

public interface DataService {
	public boolean addProduct(String productName, String email) throws Exception;

	public Product getLowestPriceProduct(String productName) throws Exception;

	public List<Product> getProductList() throws Exception;

}
