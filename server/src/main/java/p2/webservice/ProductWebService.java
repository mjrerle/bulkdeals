package p2.webservice;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import p2.model.Favorite;
import p2.model.Product;
import p2.model.User;
import p2.service.impl.ProductService;
import p2.service.impl.ProductService;
import p2.service.impl.UserService;

public class ProductWebService {
	
	public static void insert(HttpServletRequest request, HttpServletResponse response) {
		int UID = Integer.parseInt(request.getParameter("sellerid"));
		String pName = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		ProductService ps = new ProductService();
		UserService us = new UserService();
		User s = us.findById(UID);

		
		Product p = new Product();
		p.setSeller(s);
		p.setProductName(pName);
		p.setPrice(price);
		p.setOnSale(0);
		p.setStatus("Standard Price");

		
		
		ProductService w = new ProductService();
		w.insert(p);
		//will need to change some voids to booleans for a return value
//		ObjectMapper om = new ObjectMapper();
//		try {
//			String json = om.writeValueAsString(f);
//			response.getWriter().append(json).close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}


	public static void update(HttpServletRequest request, HttpServletResponse response) {
		String pName = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		double saleprice = Double.parseDouble(request.getParameter("saleprice"));
		String status = request.getParameter("status");
		int PID = Integer.parseInt(request.getParameter("productid"));
		ProductService ps = new ProductService();

		Product p = ps.findById(PID);
		ProductService w = new ProductService();

		if (status.equals("Pending Interest") && p.getDateListed()!=null) {
			LocalDate day = LocalDate.now();
			p.setDateListed(day);
		}

		p.setProductName(pName);
		p.setPrice(price);
		p.setSalePrice(saleprice);
		p.setStatus(status);

		w.update(p);
		//will need to change some voids to booleans for a return value
//		ObjectMapper om = new ObjectMapper();
//		try {
//			String json = om.writeValueAsString(f);
//			response.getWriter().append(json).close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}


	public static void findAll(HttpServletRequest request, HttpServletResponse response) {
		ProductService w = new ProductService();
		List<Product> p = w.findAll();
		
		ObjectMapper om = new ObjectMapper();
		try {
			String json = om.writeValueAsString(p);
			response.getWriter().append(json).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void findById(HttpServletRequest request, HttpServletResponse response) {
		int PID = Integer.parseInt(request.getParameter("productid"));
		ProductService w = new ProductService();
		Product p = w.findById(PID);
		
		ObjectMapper om = new ObjectMapper();
		
		try {
			String json = om.writeValueAsString(p);
			response.getWriter().append(json).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void deleteById(HttpServletRequest request, HttpServletResponse response) {
		int PID = Integer.parseInt(request.getParameter("productid"));
		ProductService w = new ProductService();
		w.deleteById(PID);
	}

}
