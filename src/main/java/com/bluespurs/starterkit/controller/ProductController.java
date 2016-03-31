package com.bluespurs.starterkit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bluespurs.starterkit.controller.request.Product;
import com.bluespurs.starterkit.service.DataService;

/**
 * This class will be the controller class which handles the HTTP operations
 * 
 * @author manisha
 *
 */
@RestController
@RequestMapping("product")
public class ProductController {
	public static final Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	DataService dataServices;

	Product product = new Product();

	/**
	 * Sample request = GET /product/search?name=ipad
	 * 
	 * @param productName
	 * @return product in json format
	 */
	@RequestMapping(value = "/search?name={productName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Product getLowestPrice(@PathVariable String productName) {
		Product product = null;
		try {
			product = dataServices.getLowestPriceProduct(productName);
		} catch (Exception e) {
			String error = e.getMessage();
			log.error("An error has occurred while obtaining the product: " + error);
		}
		return product;

	}

	/**
	 * Sample request = POST /product/alert { "productName": "ipad", "email":
	 * "someone@somewhere.com" }
	 * 
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/alert", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Product addProduct(@RequestBody String productName, String email) {

		try {
			dataServices.addProduct(productName, email);
		} catch (Exception e) {
			String error = e.getMessage();
			log.error("An error has occurred while adding the product: " + error);
		}
		return product;

	}
}
