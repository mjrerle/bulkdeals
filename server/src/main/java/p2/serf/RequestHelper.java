package p2.serf;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import p2.util.Glogger;

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
    //switch on the method first, then look at the uri
    switch (meth) {
    case "GET":
      switch (uri) {
      case userAPI:
        break;
      case userAPI + "s":
        break;
      case favoriteAPI:
        break;
      case favoriteAPI + "s":
        break;
      case productAPI:
        break;
      case productAPI + "s":
        break;
      case interestAPI:
        break;
      case interestAPI + "s":
        break;
      case purchaseAPI:
        break;
      case purchaseAPI + "s":
        break;
      case taxonomyAPI:
        break;
      case taxonomiesAPI:
        break;
      }
      break;
    case "POST":
    case "PUT":
    case "DELETE":
      switch (uri) {
      case userAPI:
        break;
      case favoriteAPI:
        break;
      case productAPI:
        break;
      case interestAPI:
        break;
      case purchaseAPI:
        break;
      case taxonomyAPI:
        break;
      }
    }
  }
}