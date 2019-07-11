package p2.webservice;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;

import p2.model.Product;
import p2.model.Purchase;
import p2.model.User;
import p2.service.impl.PurchaseService;
import p2.util.Glogger;

public class PurchaseWebService {
  public static Logger logger = Glogger.logger;

  public static boolean checkNullOrEmpty(String parameter) {
    return parameter != null && !parameter.equals("");
  }

  public static void insert(HttpServletRequest request, HttpServletResponse response) {

    Purchase purchase = null;
    int purchaseId = -1;

    String maybeDate = request.getParameter("date_purchased");
    String maybeUserId = request.getParameter("user_id");
    String maybeProductId = request.getParameter("product_id");

    if (checkNullOrEmpty(maybeDate) && checkNullOrEmpty(maybeUserId) && checkNullOrEmpty(maybeProductId)) {
      Date datePurchased = new Date(Long.parseLong(maybeDate));
      int userId = Integer.parseInt(maybeUserId);
      int productId = Integer.parseInt(maybeProductId);
      User user = new User(userId);
      Product product = new Product(productId);
      purchase = new Purchase(datePurchased, user, product);
      purchaseId = PurchaseService.insert(purchase);
    }
    try {
      ObjectMapper om = new ObjectMapper();
      String json = om.writeValueAsString(purchaseId);
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().append(json).close();
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
    String maybePurchaseId = request.getParameter("purchase_id");
    Purchase purchase = null;

    if (checkNullOrEmpty(maybePurchaseId)) {
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