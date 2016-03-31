package com.bluespurs.starterkit.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bluespurs.starterkit.controller.request.Product;
import com.bluespurs.starterkit.controller.request.ProductMapper;

public class DataServiceImpl implements DataService {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean addProduct(String productName, String email) throws Exception {
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

		String sql = "select * from PRODUCTS where " + productName + " like ? and "
				+ "(select min(PRICE) from PRODUCTS)";
		Product product = null;

		product = jdbcTemplateObject.queryForObject(sql, new Object[] { productName }, new ProductMapper());
		return product;

	}

	@Override
	public List<Product> getProductList() throws Exception {

		return null;
	}

}
