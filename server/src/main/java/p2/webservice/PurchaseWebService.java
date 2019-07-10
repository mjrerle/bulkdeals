package p2.webservice;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;

import p2.model.Purchase;
import p2.util.Glogger;

public class PurchaseWebService {
  public static Logger logger = Glogger.logger;
  public static void insert(HttpServletRequest request, HttpServletResponse response) {
    
    // List<Role> roles = new ArrayList<>();
    // String maybeUid = request.getParameter("u_id");
    // String maybeUsername = request.getParameter("username");
    // if(maybeUid != null) {
    //   int u_id = Integer.parseInt(maybeUid);
    //   roles = RoleService.getRolesForUser(u_id);
    // } else if(maybeUsername != null) {
    //   roles = RoleService.getRolesForUser(maybeUsername);
    // }

    Purchase purchase = null;

    String maybeDate = request.getParameter("date_purchased");
    String maybeUserId = request.getParameter("user_id");
    String maybeProductId = request.getParameter("product_id");


    try {
      if(purchase != null) {
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(purchase);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(json).close();
      } else {
        response.sendError(404);
      }
    } catch(IOException e) {
      logger.warn(e.getMessage());
      e.printStackTrace();
    }
  }

  public static boolean checkNullOrEmpty(String parameter) {
    return parameter != null && !parameter.equals("");
  }
}