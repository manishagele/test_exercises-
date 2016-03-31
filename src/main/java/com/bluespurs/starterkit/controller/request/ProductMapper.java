package com.bluespurs.starterkit.controller.request;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ProductMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Product product = new Product();
		product.setProductName(resultSet.getString("PRODUCT_NAME"));
		product.setPrice(resultSet.getDouble("PRICE"));
		product.setCurrencyType(resultSet.getString("CURRENCY"));
		product.setLocation(resultSet.getString("LOCATION"));
		return product;
	}

}
