package com.bluespurs.starterkit.service;

import java.util.List;
import java.util.logging.Level;

//import org.apache.http.HttpException;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.CookieStore;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.protocol.ClientContext;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
//import org.apache.http.impl.client.BasicCookieStore;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.protocol.BasicHttpContext;
//import org.apache.http.protocol.HttpContext;
//import org.apache.http.ssl.SSLContextBuilder;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
import sun.misc.BASE64Encoder;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bluespurs.starterkit.controller.request.Product;
import com.bluespurs.starterkit.controller.request.ProductMapper;

public class DataServiceImpl implements DataService {

	@Autowired
	EmailService emailService;
	
	public static final Logger logger = LoggerFactory.getLogger(DataServiceImpl.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean addProduct(String productName, String email) throws Exception {
		double currentPrice = 0.0f; 
		double newPrice = 0.0f; 
		Product product = new Product();
		/**
		//if getting the data from an external API
		 * 
		String apiEndpointUrl = "https://api.bestbuy.com/v1/";
		String apiKey = "pfe9fpy68yg28hvvma49sc89";
        logger.info("API_ENDPOINT_URL : " + apiEndpointUrl);
        try {
            HttpClient client = getHttpClient();
            HttpGet apiMethod = new HttpGet(apiEndpointUrl);
            apiMethod.addHeader("Authorization", "Bearer " + apiKey);

            HttpResponse response = client.execute(apiMethod);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                product = response.getEntity().getContent().;
            } else {
                logger.log(Level.SEVERE, "Error occurred invoking the api endpoint. Http Status : " + response.getStatusLine().getStatusCode());
                throw new Exception("Failed to get products from backend API:" + apiEndpointUrl);
            }

        } catch (HttpException e) {
            logger.log(Level.SEVERE, "Error occurred while invoking API endpoint.", e);
            throw new Exception("Failed to get products from backend API:" + apiEndpointUrl);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred while invoking API endpoint.", e);
            throw new Exception("Failed to get products from backend API:" + apiEndpointUrl);
        }**/
		newPrice = product.getPrice();
		currentPrice = getLowestPriceProduct(productName).getPrice();
		if(product.getPrice() < getLowestPriceProduct(productName).getPrice()){
			String subject = "The price of the" + productName +  " has dropped!";
			String message = "The price of the "  + productName + "has dropped from "+ currentPrice + " to " + 
					newPrice + "Get it quick!";
			emailService.sendEmail(email, subject, message);
		}
		return false;
	}

	/**
	 * This method returns the lowest price product
	 * 
	 * @param productName
	 *            name of the product
	 * @return product the product object
	 */
	@Override
	public Product getLowestPriceProduct(String productName) throws Exception {

		// Here I have implemented the system with the assumption of getting the products from a database
		String sql = "select * from PRODUCTS where " + productName + " like ? and "
				+ "(select min(PRICE) from PRODUCTS)";
		Product product = null;

		product = jdbcTemplateObject.queryForObject(sql, new Object[] { productName }, new ProductMapper());
		return product;
		
		/**
		//if getting the data from an external API and store in db
		 * 
		String apiEndpointUrl = "https://api.bestbuy.com/v1/";
		String apiKey = "pfe9fpy68yg28hvvma49sc89";
        logger.info("API_ENDPOINT_URL : " + apiEndpointUrl);
        try {
            HttpClient client = getHttpClient();
            HttpGet apiMethod = new HttpGet(apiEndpointUrl);
            apiMethod.addHeader("Authorization", "Bearer " + apiKey);

            HttpResponse response = client.execute(apiMethod);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                return formatDataToProducts(getStringFromInputStream(response.getEntity().getContent()));
            } else {
                logger.log(Level.SEVERE, "Error occurred invoking the api endpoint. Http Status : " + response.getStatusLine().getStatusCode());
                throw new Exception("Failed to get products from backend API:" + apiEndpointUrl);
            }

        } catch (HttpException e) {
            logger.log(Level.SEVERE, "Error occurred while invoking API endpoint.", e);
            throw new Exception("Failed to get products from backend API:" + apiEndpointUrl);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred while invoking API endpoint.", e);
            throw new Exception("Failed to get products from backend API:" + apiEndpointUrl);
        }**/

    }

		

	

	@Override
	public List<Product> getProductList() throws Exception {

		return null;
	}

}
