package p2.serf;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import p2.util.Glogger;
import p2.webservice.FavoriteWebService;
import p2.webservice.InterestWebService;
import p2.webservice.ProductWebService;
import p2.webservice.PurchaseWebService;
import p2.webservice.TaxonomyWebService;
import p2.webservice.UserWebService;

public class RequestHelper {
  public static Logger logger = Glogger.logger;

  public static void Process(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String uri = request.getRequestURI();
    logger.info("METHOD: " + request.getMethod() + " URI: " + uri);
    String meth = request.getMethod();
    // switch on the method first, then look at the uri
    switch (meth) {

    case "GET":
      switch (uri) {
      case API.user + "/getLoggedInUser":
        UserWebService.getLoggedInUser(request, response);
        break;
      case API.user:
        UserWebService.findById(request, response);
        break;
      case API.users:
        UserWebService.findAll(request, response);
        break;
      case API.favorite:
        FavoriteWebService.findById(request, response);
        break;
      // favorites/by_user?user_id=1
      case API.favorites + "/by_user":
        FavoriteWebService.findByUser(request, response);
        break;
      case API.favorites:
        FavoriteWebService.findAll(request, response);
        break;
      case API.product:
        ProductWebService.findById(request, response);
        break;
      case API.product + "/pennies":
        ProductWebService.findPennies(request, response);
        break;
      case API.products + "/pretty":
        ProductWebService.findPretties(request, response);
        break;
      case API.products + "/on_sale":
        ProductWebService.findAllOnSale(request, response);
        break;
      case API.products:
        ProductWebService.findAll(request, response);
        break;
      case API.products + "/by_type":
        ProductWebService.findAllProductsByType(request, response);
        break;
      case API.products + "/by_sub_type":
        ProductWebService.findAllProductsBySubType(request, response);
        break;
      case API.products + "/by_name":
        ProductWebService.findAllProductsByTaxonomyName(request, response);
        break;
      case API.interest:
        InterestWebService.findById(request, response);
        break;
      case API.interests:
        InterestWebService.findAll(request, response);
        break;
      case API.interests + "/by_user":
        InterestWebService.findByUserId(request, response);
        break;
      case API.interests + "/by_product":
        InterestWebService.findByProductId(request, response);
        break;
      case API.purchase:
        PurchaseWebService.findById(request, response);
        break;
      case API.purchases:
        PurchaseWebService.findAll(request, response);
        break;
      case API.taxonomy:
        TaxonomyWebService.findById(request, response);
        break;

      case API.taxonomies:
        TaxonomyWebService.findAll(request, response);
        break;
      }
      break;

    case "POST":
      switch (uri) {
      case API.user:
        UserWebService.insert(request, response);
        break;
      case API.user + "/login":
        UserWebService.login(request, response);
        break;
      case API.user + "/logout":
        UserWebService.logout(request, response);
        break;
      case API.favorite:
        FavoriteWebService.insert(request, response);
        break;
      case API.product:
        ProductWebService.insert(request, response);
        break;
      case API.interest:
        InterestWebService.insert(request, response);
        break;
      case API.purchase:
        PurchaseWebService.insert(request, response);
        break;
      case API.taxonomy:
        TaxonomyWebService.insert(request, response);
        break;
      }
      break;

    case "PUT":
      switch (uri) {
      case API.user:
        UserWebService.update(request, response);
        break;
      case API.favorite:
        FavoriteWebService.update(request, response);
        break;
      case API.product:
        ProductWebService.update(request, response);
        break;
      case API.interest:
        InterestWebService.update(request, response);
        break;
      case API.purchase:
        PurchaseWebService.update(request, response);
        break;
      case API.taxonomy:
        TaxonomyWebService.update(request, response);
        break;
      }
      break;
    case "DELETE":
      switch (uri) {
      case API.user:
        break;
      case API.favorite:
        FavoriteWebService.deleteById(request, response);
        break;
      case API.product:
        ProductWebService.deleteById(request, response);
        break;
      case API.interest:
        InterestWebService.deleteById(request, response);
        break;
      case API.purchase:
        PurchaseWebService.deleteById(request, response);
        break;
      case API.taxonomy:
        TaxonomyWebService.deleteById(request, response);
        break;
      }
    }
  }
}