package com.sathya.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/Productsaveservlet")
@MultipartConfig(maxFileSize = 16177215) // 16 MB
public class Productsaveservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	// Retrieve form parameters
		    String proId = request.getParameter("proId");
	        String proName = request.getParameter("proName");
	        Double proPrice = Double.parseDouble(request.getParameter("proPrice"));
	        String proBrand = request.getParameter("proBrand");
	        String proMadeIn =request.getParameter("proMadeIn");
	        Date proMfgDate = Date.valueOf(request.getParameter("proMfgDate"));
	        Date proExpDate = Date.valueOf(request.getParameter("proExpDate"));

	        // Process image file
	        Part filePart = request.getPart("proImage");
	        InputStream proImage = filePart.getInputStream();
	        

	        // Encode image bytes to base64 for storage
	       // String base64Image = Base64.getEncoder().encodeToString(pimage);

	        // Create a Product object
	        Product product = new Product();
	        product.setProId(proId);
	        product.setProName(proName);
	        product.setProPrice(proPrice);
	        product.setProBrand(proBrand);
	        product.setProMadeIn(proMadeIn);
	        product.setProMfgDate(proMfgDate);
	        product.setProExpDate(proExpDate);
	        product.setProImage(proImage);
	        
	        //calling the dao layer  saveProduct method by passing the product data.
	        ProductDao dao=new ProductDao();
	        int saveResult=dao.saveProduct(product);
	        
	        //based on the result farword the result to the jsp file.
	        if(saveResult==1)
	        {
	        	RequestDispatcher dispatcher=request.getRequestDispatcher("productresult.jsp");
	        	dispatcher.forward(request, response);
	        }
	        else
	        {
	        	response.setContentType("text/html");
	        	PrintWriter writer=response.getWriter();
	        	writer.println("<b>Product details not saved check once <b>");
	        	RequestDispatcher dispatcher=request.getRequestDispatcher("saveproduct.html");
	        	dispatcher.include(request, response);
	        }
	        
	 }
}


