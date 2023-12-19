package com.sathya.servlet;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

public class ProductDao {

    // Method to insert a product with an image into the database
    public int saveProduct(Product product) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        int saveResult=0;
        
        try  {
        	connection = DatabaseUtils.createConnection();
        	preparedStatement = connection.prepareStatement("insert into products values(?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, product.getProId());
            preparedStatement.setString(2, product.getProName());
            preparedStatement.setDouble(3, product.getProPrice());
            preparedStatement.setString(4, product.getProBrand());
            preparedStatement.setString(5, product.getProMadeIn());
            preparedStatement.setDate(6, product.getProMfgDate());
            preparedStatement.setDate(7, product.getProExpDate());
            preparedStatement.setBinaryStream(8, product.getProImage());
            saveResult=preparedStatement.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
        }
        finally {
        	try {
        		if(connection!=null)
        			connection.close();
        		if(preparedStatement!=null)
        			preparedStatement.close();
        	}
        	catch(SQLException e)
        	{
        		e.printStackTrace();
        	}
        }
		return saveResult;
    }

    // Method to retrieve all products from the database
    public List<Product> displayListOfProducts() throws ServletException ,SQLException{
        List<Product> productList = new ArrayList<>();
        Connection connection = DatabaseUtils.createConnection();
        String sql = "SELECT * FROM products";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String proId = resultSet.getString("proId");
                String proName = resultSet.getString("proName");
                Double proPrice = resultSet.getDouble("proPrice");
                String proBrand = resultSet.getString("proBrand");
                String proMadeIn = resultSet.getString("proMadeIn");
                Date proMfgDate = resultSet.getDate("proMfgDate");
                Date proExpDate = resultSet.getDate("proExpDate");
                
                InputStream proImage = resultSet.getBinaryStream("proImage");

                Product product = new Product();
                product.setProId(resultSet.getString(1));
                product.setProName(resultSet.getString(2));
                product.setProPrice(resultSet.getDouble(3));
                product.setProBrand(resultSet.getString(4));
                product.setProMadeIn(resultSet.getString(5));
                product.setProMfgDate(resultSet.getDate(6));
                product.setProExpDate(resultSet.getDate(7));
                product.setProImage(resultSet.getBinaryStream(8));
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new ServletException("Database operation failed", e);
        }
        return productList;
    }
    //delete by the proId.
    public int deleteById(String proId) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
       int deleteresult=0;
        
        try  {
        	connection = DatabaseUtils.createConnection();
        	 preparedStatement = connection.prepareStatement("delete from products where proId=?");
            preparedStatement.setString(1, proId);
            
            deleteresult= preparedStatement.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
        }
       
        return deleteresult;
    }
    
    //update the data using the proid.
    
    public Product updateById(String proId) {
    	Product product=null;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
       ResultSet resultSet=null;
        
        try  {
        	connection = DatabaseUtils.createConnection();
        	 preparedStatement = connection.prepareStatement("select * from products where proId=?");
            preparedStatement.setString(1, proId);
            
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
            	product=new Product();
            	product.setProId(resultSet.getString(1));
            	product.setProName(resultSet.getString(2));
            	product.setProPrice(Double.parseDouble(resultSet.getString(3)));
            	product.setProBrand(resultSet.getString(4));
                product.setProMadeIn(resultSet.getString(5));
                product.setProMfgDate(resultSet.getDate(6));
                product.setProExpDate(resultSet.getDate(7));
                product.setProImage(resultSet.getBinaryStream(8));
            	
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
       
       return product;
    }
    //updating the product.
    public int updateProduct(Product updatedProduct) {
    	Connection connection=null;
    	PreparedStatement preparedStatement=null;
	    int result = 0;
	        try
	        {
	        	connection = DatabaseUtils.createConnection();
	             // SQL query to update product details
	            String sql = "UPDATE products SET proName=?, proPrice=?, proBrand=?, proMadeIn=?, " +
	                    "proMfgDate=?, proExpDate=?, proImage=? WHERE proId=?";
	            
	             preparedStatement = connection.prepareStatement(sql);
	                // Set the parameters for the update statement
	            preparedStatement.setString(1, updatedProduct.getProName());
	            preparedStatement.setDouble(2, updatedProduct.getProPrice());
	            preparedStatement.setString(3, updatedProduct.getProBrand());
	            preparedStatement.setString(4, updatedProduct.getProMadeIn());
	            preparedStatement.setDate(5, updatedProduct.getProMfgDate());
	            preparedStatement.setDate(6, updatedProduct.getProExpDate());
	            preparedStatement.setBinaryStream(7, updatedProduct.getProImage());
	            preparedStatement.setString(8, updatedProduct.getProId());

	                // Execute the update
	                result = preparedStatement.executeUpdate();
	                connection.commit();
	            }
	         catch (SQLException e) {
	            e.printStackTrace();
	        } 
	        finally {
	        	try {
	        		if(connection!=null)
	        			connection.close();
	        		if(preparedStatement!=null)
	        			preparedStatement.close();
	        	}
	        	catch(SQLException e)
	        	{
	        		e.printStackTrace();
	        	}
	        }

	        	
	        
	        return result;
	    }
}
    
    
    
    
    
    
    
    
    
    

