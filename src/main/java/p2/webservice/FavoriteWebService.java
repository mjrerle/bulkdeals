package p2.webservice;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import p2.model.Favorite;
import p2.model.Product;
import p2.model.User;
import p2.service.impl.FavoriteService;


public class FavoriteWebService {
	

	public static void insert(HttpServletRequest request, HttpServletResponse response) {
		String UIDm = request.getParameter("customer_id");
		String PIDm = request.getParameter("product_id");
		boolean success = false;
		User u = null;
		Product p = null;
		if(UIDm != null && PIDm != null) {
			int UID = Integer.parseInt(UIDm);
			int PID = Integer.parseInt(PIDm);
			u = new User(UID);
			p = new Product(PID);
			success = true;
		}

		if (success == true) {
			Favorite fav = new Favorite();
			fav.setUser(u);
			fav.setProduct(p);
			FavoriteService.insert(fav);		

			try {
				response.getWriter().append("Favorite Added");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				response.getWriter().append("Favorite Addition Failed");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}


	public static void update(HttpServletRequest request, HttpServletResponse response) {
		String FIDm = request.getParameter("favorite_id");
		String UIDm = request.getParameter("customer_id");
		String PIDm = request.getParameter("product_id");
		boolean success = false;
		User user = null;
		Product product = null;
		Favorite fav = null;
		if(UIDm != null && PIDm != null && FIDm != null) {
			int UID = Integer.parseInt(UIDm);
			int PID = Integer.parseInt(PIDm);
			int FID = Integer.parseInt(FIDm);
			user = new User(UID);
			product = new Product(PID);
			fav = FavoriteService.findById(FID);
			fav.setUser(user);
			fav.setProduct(product);
			success = true;
		}

		if (success == true) {
			FavoriteService.update(fav);		

			try {
				response.getWriter().append("Favorite Updated");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				response.getWriter().append("Favorite Update Failed");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}


	public static void findAll(HttpServletRequest request, HttpServletResponse response) {

		List<Favorite> fav = FavoriteService.findAll();
		
		ObjectMapper om = new ObjectMapper();
		try {
			String json = om.writeValueAsString(fav);
			response.getWriter().append(json).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void findById(HttpServletRequest request, HttpServletResponse response) {
		String IDm = request.getParameter("id");
		boolean success = false;
		Favorite fav = null;
		if (IDm != null) {
			int id = Integer.parseInt(IDm);
			fav = FavoriteService.findById(id);
			success = true;
		}
		
		if (success == true) {
		
			ObjectMapper om = new ObjectMapper();
			
			try {
				String json = om.writeValueAsString(fav);
				response.getWriter().append(json).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				response.getWriter().append("Find by ID failed");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public static void deleteById(HttpServletRequest request, HttpServletResponse response) {
		String FIDm = request.getParameter("favorite_id");
		boolean success = false;
		int FID = 0;
		if (FIDm != null) {
			FID = Integer.parseInt(FIDm);
			success = true;
		}
		
		if (success == true) {
			FavoriteService.deleteById(FID);
			try {
				response.getWriter().append("Favorite Deleted");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else {
			try {
				response.getWriter().append("Delete failed");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	public static void findByUser(HttpServletRequest request, HttpServletResponse response){
		String UIDm = request.getParameter("customer_id");
		User user = null;
		boolean success = false;
		
		if (UIDm != null) {
			int UID = Integer.parseInt(UIDm);
			user = new User(UID);
			success = true;
		}
		
		if (success == true) {
			List<Favorite> fav = FavoriteService.findByUser(user);
			
			ObjectMapper om = new ObjectMapper();
			
			try {
				String json = om.writeValueAsString(fav);
				response.getWriter().append(json).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				response.getWriter().append("Find Failed");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	

}
