package p2.serf;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import p2.util.Glogger;
import p2.webservice.FavoriteWebService;
import p2.webservice.ProductWebService;
import p2.webservice.PurchaseWebService;
import p2.webservice.TaxonomyWebService;

public class RequestHelper {
  public static Logger logger = Glogger.logger;
  private static final String baseURI = "/project2";
  private static final String baseAPI = baseURI + "/api";
  private static final String userAPI = baseAPI + "/user";
  private static final String favoriteAPI = baseAPI + "/favorite";
  private static final String interestAPI = baseAPI + "/interest";
  private static final String productAPI = baseAPI + "/product";
  private static final String purchaseAPI = baseAPI + "/purchase";
  private static final String taxonomyAPI = baseAPI + "/taxonomy";
  private static final String taxonomiesAPI = baseAPI + "/taxonomies";

  public static void Process(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String uri = request.getRequestURI();
    logger.info(request.getMethod() + " " + uri + " " + request.getQueryString());
    String meth = request.getMethod();
    // switch on the method first, then look at the uri
    switch (meth) {
    case "GET":
      switch (uri) {
      case userAPI:
        break;
      case userAPI + "s":
        break;
      case favoriteAPI:
        FavoriteWebService.findById(request, response);
        break;
      // favorites/by?customer_id=1
      case favoriteAPI + "s/by_user":
        FavoriteWebService.findByUser(request, response);
        break;
      case favoriteAPI + "s":
        FavoriteWebService.findAll(request, response);
        break;
      case productAPI:
        ProductWebService.findById(request, response);
        break;
      case productAPI + "/interests":
        ProductWebService.findAllInterest(request, response);
        break;
      case productAPI + "s/standard":
        ProductWebService.findAllStandard(request, response);
        break;
      case productAPI + "s/on_sale":
        ProductWebService.findAllSale(request, response);
        break;
      case productAPI + "s":
        ProductWebService.findAll(request, response);
        break;
      case interestAPI:
        break;
      case interestAPI + "s":
        break;
      case purchaseAPI:
        PurchaseWebService.findById(request, response);
        break;
      case purchaseAPI + "s":
        PurchaseWebService.findAll(request, response);
        break;
      case taxonomyAPI:
        TaxonomyWebService.findById(request, response);
        break;
      case taxonomiesAPI:
        TaxonomyWebService.findById(request, response);
        break;
      }
      break;
    case "POST":
      switch (uri) {
      case userAPI:
        break;
      case favoriteAPI:
        FavoriteWebService.insert(request, response);
        break;
      case productAPI:
        ProductWebService.insert(request, response);
        break;
      case interestAPI:
        break;
      case purchaseAPI:
        PurchaseWebService.insert(request, response);
        break;
      case taxonomyAPI:
        TaxonomyWebService.insert(request, response);
        break;
      }
      break;
    case "PUT":
      switch (uri) {
      case userAPI:
        break;
      case favoriteAPI:
        FavoriteWebService.update(request, response);
        break;
      case productAPI:
        ProductWebService.update(request, response);
        break;
      case interestAPI:
        break;
      case purchaseAPI:
        PurchaseWebService.update(request, response);
        break;
      case taxonomyAPI:
        TaxonomyWebService.update(request, response);
        break;
      }
      break;
    case "DELETE":
      switch (uri) {
      case userAPI:
        break;
      case favoriteAPI:
        FavoriteWebService.deleteById(request, response);
        break;
      case productAPI:
        ProductWebService.deleteById(request, response);
        break;
      case interestAPI:
        break;
      case purchaseAPI:
        PurchaseWebService.deleteById(request, response);
        break;
      case taxonomyAPI:
        TaxonomyWebService.deleteById(request, response);
        break;
      }
    }
  }
}