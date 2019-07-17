package p2.webservice;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;

import p2.model.Product;
import p2.model.Purchase;
import p2.model.User;
import p2.service.ProductService;
import p2.service.PurchaseService;
import p2.service.UserService;
import p2.util.Glogger;
import p2.util.ValidationUtilities;

public class PurchaseWebService {
  public static Logger logger = Glogger.logger;

  public static void insert(HttpServletRequest request, HttpServletResponse response) {

    ObjectMapper mapper = new ObjectMapper();
    Purchase purchase = null;
    try {
      purchase = mapper.readValue(request.getInputStream(), Purchase.class);
    } catch (JsonParseException e1) {
      e1.printStackTrace();
    } catch (JsonMappingException e1) {
      e1.printStackTrace();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    int purchaseId = -1;
    if (purchase != null) {
      // check if favorite has a product and a user
      if (purchase.getProduct() != null && purchase.getUser() != null) {
        Product product = ProductService.findById(purchase.getProduct().getProductId());
        User user = UserService.findById(purchase.getUser().getUserId());
        // if it does, then make sure that the user and the tax exist in the db
        if (product != null && user != null) {
          purchaseId = PurchaseService.insert(purchase);
        }
      }
    }
    try {
      response.setContentType("text/html");
      response.setCharacterEncoding("UTF-8");
      if (purchaseId >= 0) {
        response.getWriter().append("Purchase Added").close();
      } else {
        response.getWriter().append("Purchase Add Failed").close();
      }

    } catch (IOException e) {
      logger.warn(e.getMessage());
      e.printStackTrace();
    }
  }

  public static void update(HttpServletRequest request, HttpServletResponse response) {

    ObjectMapper mapper = new ObjectMapper();
    Purchase purchase = null;
    try {
      purchase = mapper.readValue(request.getInputStream(), Purchase.class);
    } catch (JsonParseException e1) {
      e1.printStackTrace();
    } catch (JsonMappingException e1) {
      e1.printStackTrace();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    boolean success = false;
    if (purchase != null) {
      if (purchase.getPurchaseId() >= 0) {
        // check if favorite has a product and a user
        if (purchase.getProduct() != null && purchase.getUser() != null) {
          Product product = ProductService.findById(purchase.getProduct().getProductId());
          User user = UserService.findById(purchase.getUser().getUserId());
          // if it does, then make sure that the user and the tax exist in the db
          if (product != null && user != null) {
            success = PurchaseService.update(purchase);
          }
        }
      }
    }

    try {
      response.setContentType("text/html");
      response.setCharacterEncoding("UTF-8");
      if (success) {
        response.getWriter().append("Purchase Updated").close();
      } else {
        response.getWriter().append("Purchase Update Failed").close();
      }
    } catch (IOException e) {
      logger.warn(e.getMessage());
      e.printStackTrace();
    }
  }

  public static void deleteById(HttpServletRequest request, HttpServletResponse response) {
    ObjectMapper mapper = new ObjectMapper();
    Purchase purchase = null;
    try {
      purchase = mapper.readValue(request.getInputStream(), Purchase.class);
    } catch (JsonParseException e1) {
      e1.printStackTrace();
    } catch (JsonMappingException e1) {
      e1.printStackTrace();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    boolean success = false;
    if (purchase != null) {
      if (purchase.getPurchaseId() >= 0) {
        // check if favorite has a product and a user
        if (purchase.getProduct() != null && purchase.getUser() != null) {
          Product product = ProductService.findById(purchase.getProduct().getProductId());
          User user = UserService.findById(purchase.getUser().getUserId());
          // if it does, then make sure that the user and the tax exist in the db
          if (product != null && user != null) {
            success = PurchaseService.deleteById(purchase.getPurchaseId());
          }
        }
      }
    }

    try {
      response.setContentType("text/html");
      response.setCharacterEncoding("UTF-8");
      if (success) {
        response.getWriter().append("Purchase Deleted").close();
      } else {
        response.getWriter().append("Purchase Delete Failed").close();
      }
    } catch (IOException e) {
      logger.warn(e.getMessage());
      e.printStackTrace();
    }
  }

  public static void findAll(HttpServletRequest request, HttpServletResponse response) {

    List<Purchase> purchases = PurchaseService.findAll();

    try {
      ObjectMapper om = new ObjectMapper();
      String json = om.writeValueAsString(purchases);
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().append(json).close();
    } catch (IOException e) {
      logger.warn(e.getMessage());
      e.printStackTrace();
    }
  }

  public static void findById(HttpServletRequest request, HttpServletResponse response) {

    int purchaseId = -1;
    String maybePurchaseId = request.getParameter("purchaseId");
    Purchase purchase = null;

    if (ValidationUtilities.checkNullOrEmpty(maybePurchaseId)) {
      purchaseId = Integer.parseInt(maybePurchaseId);
      purchase = PurchaseService.findById(purchaseId);
    }

    try {
      ObjectMapper om = new ObjectMapper();
      String json = om.writeValueAsString(purchase);
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().append(json).close();
    } catch (IOException e) {
      logger.warn(e.getMessage());
      e.printStackTrace();
    }
  }

}