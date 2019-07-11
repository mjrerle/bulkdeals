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
			Favorite f = new Favorite();
			f.setUser(u);
			f.setProduct(p);
			FavoriteService.insert(f);		

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
		User u = null;
		Product p = null;
		Favorite f = null;
		if(UIDm != null && PIDm != null && FIDm != null) {
			int UID = Integer.parseInt(UIDm);
			int PID = Integer.parseInt(PIDm);
			int FID = Integer.parseInt(FIDm);
			u = new User(UID);
			p = new Product(PID);
			f = FavoriteService.findById(FID);
			f.setUser(u);
			f.setProduct(p);
			success = true;
		}

		if (success == true) {
			FavoriteService.update(f);		

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

		List<Favorite> f = FavoriteService.findAll();
		
		ObjectMapper om = new ObjectMapper();
		try {
			String json = om.writeValueAsString(f);
			response.getWriter().append(json).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void findById(HttpServletRequest request, HttpServletResponse response) {
		String IDm = request.getParameter("id");
		boolean success = false;
		Favorite f = null;
		if (IDm != null) {
			int id = Integer.parseInt(IDm);
			f = FavoriteService.findById(id);
			success = true;
		}
		
		if (success == true) {
		
			ObjectMapper om = new ObjectMapper();
			
			try {
				String json = om.writeValueAsString(f);
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
		User u = null;
		boolean success = false;
		
		if (UIDm != null) {
			int UID = Integer.parseInt(UIDm);
			u = new User(UID);
			success = true;
		}
		
		if (success == true) {
			List<Favorite> f = FavoriteService.findByUser(u);
			
			ObjectMapper om = new ObjectMapper();
			
			try {
				String json = om.writeValueAsString(f);
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
