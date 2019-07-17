package p2.webservice;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;

import p2.model.Favorite;
import p2.model.Product;
import p2.model.User;
import p2.service.FavoriteService;
import p2.service.ProductService;
import p2.service.UserService;
import p2.util.Glogger;
import p2.util.ValidationUtilities;

public class FavoriteWebService {
	private static Logger logger = Glogger.logger;

	public static void insert(HttpServletRequest request, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		Favorite favorite = null;
		try {
			favorite = mapper.readValue(request.getInputStream(), Favorite.class);
		} catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		int favoriteId = -1;
		if (favorite != null) {
			// check if favorite has a product and a user
			if (favorite.getProduct() != null && favorite.getUser() != null) {
				Product product = ProductService.findById(favorite.getProduct().getProductId());
				User user = UserService.findById(favorite.getUser().getUserId());
				// if it does, then make sure that the user and the tax exist in the db
				if (product != null && user != null) {
					favoriteId = FavoriteService.insert(favorite);
				}
			}
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
		ObjectMapper mapper = new ObjectMapper();
		Favorite favorite = null;
		try {
			favorite = mapper.readValue(request.getInputStream(), Favorite.class);
		} catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		boolean success = false;
		if (favorite != null) {
			if (favorite.getFavoriteId() >= 0) {
				// check if favorite has a product and a user
				if (favorite.getProduct() != null && favorite.getUser() != null) {
					Product product = ProductService.findById(favorite.getProduct().getProductId());
					User user = UserService.findById(favorite.getUser().getUserId());
					// if it does, then make sure that the user and the tax exist in the db
					if (product != null && user != null) {
						success = FavoriteService.update(favorite);
					}
				}
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
		ObjectMapper mapper = new ObjectMapper();
		Favorite favorite = null;
		try {
			favorite = mapper.readValue(request.getInputStream(), Favorite.class);
		} catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		boolean success = false;
		if (favorite != null) {
			if (favorite.getFavoriteId() >= 0) {
				// check if favorite has a product and a user
				if (favorite.getProduct() != null && favorite.getUser() != null) {
					Product product = ProductService.findById(favorite.getProduct().getProductId());
					User user = UserService.findById(favorite.getUser().getUserId());
					// if it does, then make sure that the user and the tax exist in the db
					if (product != null && user != null) {
						success = FavoriteService.update(favorite);
					}
				}
			}
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
