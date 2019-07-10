package p2.webservice;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import p2.dao.impl.FavoriteDAO;
import p2.model.Favorite;
import p2.model.Product;
import p2.model.User;
import p2.service.impl.FavoriteService;
import p2.service.impl.ProductService;
import p2.service.impl.UserService;

public class FavoriteWebService {
	

	public static void insert(HttpServletRequest request, HttpServletResponse response) {
		int UID = Integer.parseInt(request.getParameter("customerid"));
		int PID = Integer.parseInt(request.getParameter("productid"));
		ProductService ps = new ProductService();
		UserService us = new UserService();
		User u = us.findById(UID);
		Product p = ps.findById(PID);
		
		Favorite f = new Favorite();
		f.setUser(u);
		f.setProduct(p);
		
		
		FavoriteService w = new FavoriteService();
		w.insert(f);
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
		int FID = Integer.parseInt(request.getParameter("favoriteid"));
		int UID = Integer.parseInt(request.getParameter("customerid"));
		int PID = Integer.parseInt(request.getParameter("productid"));
		ProductService ps = new ProductService();
		UserService us = new UserService();
		User u = us.findById(UID);
		Product p = ps.findById(PID);
		FavoriteService w = new FavoriteService();
		Favorite f = w.findById(FID);
		f.setUser(u);
		f.setProduct(p);
		
		

		w.update(f);
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
		FavoriteService w = new FavoriteService();
		List<Favorite> f = w.findAll();
		
		ObjectMapper om = new ObjectMapper();
		try {
			String json = om.writeValueAsString(f);
			response.getWriter().append(json).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void findById(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		FavoriteService w = new FavoriteService();
		Favorite f = w.findById(id);
		
		ObjectMapper om = new ObjectMapper();
		
		try {
			String json = om.writeValueAsString(f);
			response.getWriter().append(json).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void deleteById(HttpServletRequest request, HttpServletResponse response) {
		int FID = Integer.parseInt(request.getParameter("favoriteid"));
		FavoriteService w = new FavoriteService();
		w.deleteById(FID);
	}
	

	public static void findByUser(HttpServletRequest request, HttpServletResponse response){
		int UID = Integer.parseInt(request.getParameter("customerid"));
		UserService us = new UserService();
		User u = us.findById(UID);
		FavoriteService w = new FavoriteService();
		w.findByUser(u);
	}

}
