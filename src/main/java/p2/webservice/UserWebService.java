package p2.webservice;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;

import p2.model.User;
import p2.service.UserService;
import p2.util.Glogger;
import p2.util.SessionVariableManager;
import p2.util.ValidationUtilities;

public class UserWebService {

	private static Logger logger = Glogger.logger;

	public static void login(HttpServletRequest request, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
    User user = null;
    try {
      user = mapper.readValue(request.getInputStream(), User.class);
    } catch (JsonParseException e1) {
      e1.printStackTrace();
    } catch (JsonMappingException e1) {
      e1.printStackTrace();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    if (user != null) {
      user = UserService.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }

		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			if (user != null) {
				user.setPassword(null);
				SessionVariableManager.addLoggedInUser(request, user);
				logger.info("User " + user.getEmail() + " logged into the system");
				ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(user);
        response.setContentType("application/json");
        response.getWriter().append(json).close();
			} else {
				response.getWriter().append("Failed to log in due to incorrect email/password").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void findById(HttpServletRequest request, HttpServletResponse response) {

    int userId = -1;
    String maybeUserId = request.getParameter("userId");
    User user = null;

    if (ValidationUtilities.checkNullOrEmpty(maybeUserId)) {
      userId = Integer.parseInt(maybeUserId);
      user = UserService.findById(userId);
    }

    try {
      ObjectMapper om = new ObjectMapper();
      String json = om.writeValueAsString(user);
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().append(json).close();
    } catch (IOException e) {
      logger.warn(e.getMessage());
      e.printStackTrace();
    }
	}
	
	public static void findAll(HttpServletRequest request, HttpServletResponse response) {

    List<User> users = UserService.findAll();

    try {
      ObjectMapper om = new ObjectMapper();
      String json = om.writeValueAsString(users);
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().append(json).close();
    } catch (IOException e) {
      logger.warn(e.getMessage());
      e.printStackTrace();
    }
  }

	public static void getLoggedInUser(HttpServletRequest request, HttpServletResponse response) {

		ObjectMapper om = new ObjectMapper();

		try {
			String json = om.writeValueAsString(SessionVariableManager.getLoggedInUser());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).close();
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}



	public static void logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			SessionVariableManager.removeLoggedInUser();
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append("User Logged Out").close();
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void insert(HttpServletRequest request, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
    User user = null;
    try {
      user = mapper.readValue(request.getInputStream(), User.class);
    } catch (JsonParseException e1) {
      e1.printStackTrace();
    } catch (JsonMappingException e1) {
      e1.printStackTrace();
    } catch (IOException e1) {
      e1.printStackTrace();
		}
		
		int userId = -1;
    if (user != null) {
      userId = UserService.insert(user);
    }
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			if (userId >= 0) {
				logger.info("User " + user.getEmail() + " Inserted");				
				response.getWriter().append("User Inserted").close();
			} else {
				response.getWriter().append("User Insert Failed").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void update(HttpServletRequest request, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
    User user = null;
    try {
      user = mapper.readValue(request.getInputStream(), User.class);
    } catch (JsonParseException e1) {
      e1.printStackTrace();
    } catch (JsonMappingException e1) {
      e1.printStackTrace();
    } catch (IOException e1) {
      e1.printStackTrace();
		}
		
		boolean success = false;
    if (user != null) {
      success = UserService.update(user);
    }

		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			if (success) {
				logger.info("User " + user.getEmail() + " Updated");
				response.getWriter().append("User Updated").close();
			} else {
				response.getWriter().append("User Update Failed").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}
}
