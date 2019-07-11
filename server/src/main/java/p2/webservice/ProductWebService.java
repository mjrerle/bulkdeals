package p2.webservice;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import p2.model.Interest;
import p2.model.Product;
import p2.model.User;
import p2.service.impl.ProductService;


public class ProductWebService {
	
	public static void insert(HttpServletRequest request, HttpServletResponse response) {
		String UIDm = request.getParameter("seller_id");
		String pName = request.getParameter("name");
		String priceM = request.getParameter("price");
		boolean success = false;
		User seller = null;
		Product product = null;
		int  UID = 0;
		double price = 0;
		if (UIDm != null && pName != null && priceM != null) {
			UID = Integer.parseInt(UIDm);
			price = Double.parseDouble(priceM);
			success = true;
		}
		
		if (success == true) {
			seller = new User(UID);
			product = new Product();
			product.setSeller(seller);
			product.setProductName(pName);
			product.setPrice(price);
			product.setOnSale(0);
			product.setStatus("Standard Price");
			ProductService.insert(product);
			
			try {
				response.getWriter().append("Product Added");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				response.getWriter().append("Product Add Failed");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		
	}


	public static void update(HttpServletRequest request, HttpServletResponse response) {
		String pName = request.getParameter("name");
		String priceM = request.getParameter("price");
		String salepriceM = request.getParameter("sale_price");
		String status = request.getParameter("status");
		String PIDm = request.getParameter("product_id");
		Product product = null;
		double price = 0;
		double saleprice = 0;
		int PID = 0;
		boolean success = false;
		if (PIDm != null) {
			PID = Integer.parseInt(PIDm);
			product = ProductService.findById(PID);
			if (status != null) {
				product.setStatus(status);
				if (status.equals("Pending Interest") && product.getDateListed() != null) {
					LocalDate day = LocalDate.now();
					product.setDateListed(day);
				}
			}
			if (pName != null) {
				product.setProductName(pName);
			}
			if (priceM != null) {
				price = Double.parseDouble(priceM);
				product.setPrice(price);
			}
			if (salepriceM != null) {
				saleprice = Double.parseDouble(salepriceM);
				product.setSalePrice(saleprice);
			}
			success = true;
		}
		if (success == true) {
			ProductService.update(product);
			try {
				response.getWriter().append("Product Updated");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				response.getWriter().append("Product Update Failed");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}


	public static void findAll(HttpServletRequest request, HttpServletResponse response) {

		List<Product> product = ProductService.findAll();
		
		ObjectMapper om = new ObjectMapper();
		try {
			String json = om.writeValueAsString(product);
			response.getWriter().append(json).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void findById(HttpServletRequest request, HttpServletResponse response) {
		String PIDm = request.getParameter("product_id");
		int PID = 0;
		boolean success = false;
		Product product = null;
		if (PIDm != null) {
			PID = Integer.parseInt(PIDm);
			success = true;
		}
		
		if (success == true) {
			product = ProductService.findById(PID);
			
			ObjectMapper om = new ObjectMapper();
			
			try {
				String json = om.writeValueAsString(product);
				response.getWriter().append(json).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				response.getWriter().append("Failed to Find Product");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}


	public static void deleteById(HttpServletRequest request, HttpServletResponse response) {
		String PIDm = request.getParameter("product_id");
		int PID = 0;
		boolean success = false;
		if (PIDm != null) {
			PID = Integer.parseInt(PIDm);
			success = true;
		}
		
		if (success == true) {
			ProductService.deleteById(PID);
			try {
				response.getWriter().append("Product Deleted");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				response.getWriter().append("Failed to Delete Product");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public static void findAllStandard(HttpServletRequest request, HttpServletResponse response) {

		List<Product> productOriginal = ProductService.findAll();
		List<Product> productNew = new ArrayList<Product>();
		//populating list of standard priced items being sold
		for (int i = 0; i < productOriginal.size(); i++) {
			if (productOriginal.get(i).getStatus().equals("Standard Price")){
				productNew.add(productOriginal.get(i));
			}
		}
		
		ObjectMapper om = new ObjectMapper();
		try {
			String json = om.writeValueAsString(productNew);
			response.getWriter().append(json).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void findAllSale(HttpServletRequest request, HttpServletResponse response) {

		List<Product> productOriginal = ProductService.findAll();
		List<Product> productNew = new ArrayList<Product>();
		//populating return list of those on sale
		for (int i = 0; i < productOriginal.size(); i++) {
			if (productOriginal.get(i).getStatus().equals("On Sale")){
				productNew.add(productOriginal.get(i));
			}
		}
		
		ObjectMapper om = new ObjectMapper();
		try {
			String json = om.writeValueAsString(productNew);
			response.getWriter().append(json).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void findAllInterest(HttpServletRequest request, HttpServletResponse response) {

		List<Product> productOriginal = ProductService.findAll();
		List<Product> productNew = new ArrayList<Product>();
		LocalDate today = LocalDate.now();
		//Populating list of those gaining interest
		for (int i = 0; i < productOriginal.size(); i++) {
			if (productOriginal.get(i).getStatus().equals("Gaining Interest")){
				productNew.add(productOriginal.get(i));
			}
		}
		//removing from list if interest not gained in one week and updating back to 
		for (int i = 0; i < productNew.size(); i++) {
			LocalDate dayMade = productNew.get(i).getDateListed();
			long difference = java.time.temporal.ChronoUnit.DAYS.between(dayMade,today);
			if (difference > 7) {
				productNew.get(i).setStatus("Standard Price");
				productNew.get(i).setDateListed(null);
				ProductService.update(productNew.get(i));
				productNew.remove(i);
			}
		}
		
		//removing from list if interest gained and setting on sale
		for (int j = 0; j < productNew.size(); j++) {
			Interest i = InterestService.findByProductId(productNew.get(j).getId());
			if (i.getQuantity >= 10) {
				productNew.get(j).setStatus("On Sale");
				ProductService.update(productNew.get(j));
				productNew.remove(j);
			}
		}
		
		ObjectMapper om = new ObjectMapper();
		try {
			String json = om.writeValueAsString(p);
			response.getWriter().append(json).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeFromSale(HttpServletRequest request, HttpServletResponse response) {
		String PIDm = request.getParameter("product_id");
		int PID = 0;
		boolean success = false;
		Product product = null;
		if (PIDm != null) {
			PID = Integer.parseInt(PIDm);
			success = true;
		}
		
		if (success == true) {
			product = ProductService.findById(PID);
			product.setStatus("Standard Price");
			product.setDateListed(null);
			ProductService.update(product);
			try {
				response.getWriter().append("Removed Product From Sale");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				response.getWriter().append("Failed to Remove Product From Sale");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
