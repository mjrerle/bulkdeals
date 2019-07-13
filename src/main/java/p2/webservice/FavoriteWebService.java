package p2.webservice;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;

import p2.model.Favorite;
import p2.model.Product;
import p2.model.User;
import p2.service.FavoriteService;
import p2.util.Glogger;
import p2.util.ValidationUtilities;

public class FavoriteWebService {
	private static Logger logger = Glogger.logger;

	public static void insert(HttpServletRequest request, HttpServletResponse response) {
		String maybeUserId = request.getParameter("userId");
		String maybeProductId = request.getParameter("productId");
		int favoriteId = -1;
		User user = null;
		Product product = null;
		if (ValidationUtilities.checkNullOrEmpty(maybeUserId) && ValidationUtilities.checkNullOrEmpty(maybeProductId)) {
			int customerId = Integer.parseInt(maybeUserId);
			int productId = Integer.parseInt(maybeProductId);
			user = new User(customerId);
			product = new Product(productId);
			favoriteId = FavoriteService.insert(new Favorite(user, product));
		}

		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			if (favoriteId >= 0) {
				response.getWriter().append("Favorite Inserted").close();
			} else {
				response.getWriter().append("Favorite Insert Failed").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void update(HttpServletRequest request, HttpServletResponse response) {
		String maybeFavoriteId = request.getParameter("favoriteId");
		String maybeUserId = request.getParameter("userId");
		String maybeProductId = request.getParameter("productId");
		boolean success = false;
		Favorite favorite = null;
		if (ValidationUtilities.checkNullOrEmpty(maybeFavoriteId)) {
			int favoriteId = Integer.parseInt(maybeFavoriteId);
			favorite = FavoriteService.findById(favoriteId);
			if (favorite != null) {
				if (ValidationUtilities.checkNullOrEmpty(maybeUserId)) {
					int customerId = Integer.parseInt(maybeUserId);
					favorite.setUser(new User(customerId));
				}
				if (ValidationUtilities.checkNullOrEmpty(maybeProductId)) {
					int productId = Integer.parseInt(maybeProductId);
					favorite.setProduct(new Product(productId));
				}
				success = FavoriteService.update(favorite);
			}
		}

		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			if (success) {
				response.getWriter().append("Favorite Updated").close();
			} else {
				response.getWriter().append("Favorite Update Failed").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void findAll(HttpServletRequest request, HttpServletResponse response) {

		List<Favorite> allFavorites = FavoriteService.findAll();

		ObjectMapper om = new ObjectMapper();
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			String json = om.writeValueAsString(allFavorites);
			response.getWriter().append(json).close();
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void findById(HttpServletRequest request, HttpServletResponse response) {
		String maybeFavoriteId = request.getParameter("favoriteId");
		Favorite favorite = null;
		if (ValidationUtilities.checkNullOrEmpty(maybeFavoriteId)) {
			int favoriteId = Integer.parseInt(maybeFavoriteId);
			favorite = FavoriteService.findById(favoriteId);
		}

		try {
			response.setCharacterEncoding("UTF-8");
			if (favorite != null) {
				ObjectMapper om = new ObjectMapper();
				String json = om.writeValueAsString(favorite);
				response.setContentType("application/json");
				response.getWriter().append(json).close();
			} else {
				response.setContentType("text/html");
				response.getWriter().append("Find By ID Failed").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void deleteById(HttpServletRequest request, HttpServletResponse response) {
		String maybeFavoriteId = request.getParameter("favoriteId");
		boolean success = false;
		int favoriteId = -1;
		if (ValidationUtilities.checkNullOrEmpty(maybeFavoriteId)) {
			favoriteId = Integer.parseInt(maybeFavoriteId);
			success = FavoriteService.deleteById(favoriteId);
		}

		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			if (success) {
				response.getWriter().append("Favorite Deleted").close();
			} else {
				response.getWriter().append("Favorite Delete Failed").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void findByUser(HttpServletRequest request, HttpServletResponse response) {
		String maybeUserId = request.getParameter("userId");
		List<Favorite> favoritesByUser = null;

		if (ValidationUtilities.checkNullOrEmpty(maybeUserId)) {
			int customerId = Integer.parseInt(maybeUserId);
			favoritesByUser = FavoriteService.findByUser(new User(customerId));
		}

		try {
			response.setCharacterEncoding("UTF-8");
			if (favoritesByUser != null) {
				ObjectMapper om = new ObjectMapper();
				String json = om.writeValueAsString(favoritesByUser);
				response.setContentType("application/json");
				response.getWriter().append(json).close();
			} else {
				response.setContentType("text/html");
				response.getWriter().append("Find All Favorites By User Failed").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}
}
