package p2.webservice;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;

import p2.model.Product;
import p2.model.Purchase;
import p2.model.User;
import p2.service.PurchaseService;
import p2.util.Glogger;
import p2.util.ValidationUtilities;

public class PurchaseWebService {
  public static Logger logger = Glogger.logger;

  public static void insert(HttpServletRequest request, HttpServletResponse response) {

    Purchase purchase = null;
    int purchaseId = -1;

    String maybeUserId = request.getParameter("userId");
    String maybeProductId = request.getParameter("productId");

    if (ValidationUtilities.checkNullOrEmpty(maybeUserId) && ValidationUtilities.checkNullOrEmpty(maybeProductId)) {
      LocalDate datePurchased = LocalDate.now();
      int userId = Integer.parseInt(maybeUserId);
      int productId = Integer.parseInt(maybeProductId);
      User user = new User(userId);
      Product product = new Product(productId);
      purchase = new Purchase(datePurchased, user, product);
      purchaseId = PurchaseService.insert(purchase);
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

    String maybeDate = request.getParameter("date_purchased");
    String maybeUserId = request.getParameter("userId");
    String maybeProductId = request.getParameter("productId");
    String maybePurchaseId = request.getParameter("purchaseId");
    boolean success = false;
    if (ValidationUtilities.checkNullOrEmpty(maybePurchaseId)) {
      Purchase purchase = PurchaseService.findById(Integer.parseInt(maybePurchaseId));
      if (purchase != null) {
        if (ValidationUtilities.checkNullOrEmpty(maybeDate)) {
          LocalDate datePurchased = LocalDate.parse(maybeDate);
          purchase.setDatePurchased(datePurchased);
        }
        if (ValidationUtilities.checkNullOrEmpty(maybeProductId)) {
          int productId = Integer.parseInt(maybeProductId);
          Product product = new Product(productId);
          purchase.setProduct(product);
        }
        if (ValidationUtilities.checkNullOrEmpty(maybeUserId)) {
          int userId = Integer.parseInt(maybeUserId);
          User user = new User(userId);
          purchase.setUser(user);
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
    int purchaseId = -1;
    String maybePurchaseId = request.getParameter("purchaseId");
    boolean success = false;

    if (ValidationUtilities.checkNullOrEmpty(maybePurchaseId)) {
      purchaseId = Integer.parseInt(maybePurchaseId);
      success = PurchaseService.deleteById(purchaseId);
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