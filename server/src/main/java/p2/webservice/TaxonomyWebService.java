package p2.webservice;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;

import p2.model.Product;
import p2.model.Taxonomy;
import p2.service.impl.TaxonomyService;
import p2.util.Glogger;

public class TaxonomyWebService {
  public static Logger logger = Glogger.logger;

  private static boolean checkNullOrEmpty(String parameter) {
    return parameter != null && !parameter.equals("");
  }

  public static void insert(HttpServletRequest request, HttpServletResponse response) {

    Taxonomy taxonomy = null;
    int taxonomyId = -1;

    String maybeName = request.getParameter("name");
    String maybeType = request.getParameter("type");
    String maybeSubType = request.getParameter("sub_type");
    String maybeProductId = request.getParameter("product_id");

    if (checkNullOrEmpty(maybeName) && checkNullOrEmpty(maybeType)
        && checkNullOrEmpty(maybeSubType) && checkNullOrEmpty(maybeProductId)) {
      
      int productId = Integer.parseInt(maybeProductId);
      taxonomy = new Taxonomy(maybeName, maybeType, maybeSubType, new Product(productId));
      taxonomyId = TaxonomyService.insert(taxonomy);

    }
    try {
      response.setContentType("text/html");
      response.setCharacterEncoding("UTF-8");
      if (taxonomyId >= 0) {
        response.getWriter().append("Taxonomy Added").close();
      } else {
        response.getWriter().append("Taxonomy Add Failed").close();
      }

    } catch (IOException e) {
      logger.warn(e.getMessage());
      e.printStackTrace();
    }
  }

  public static void update(HttpServletRequest request, HttpServletResponse response) {

    String maybeName = request.getParameter("name");
    String maybeType = request.getParameter("type");
    String maybeSubType = request.getParameter("sub_type");
    String maybeProductId = request.getParameter("product_id");
    String maybeTaxonomyId = request.getParameter("taxonomy_id");

    boolean success = false;
    if (checkNullOrEmpty(maybeTaxonomyId)) {
      Taxonomy taxonomy = new Taxonomy(Integer.parseInt(maybeTaxonomyId));

      if (checkNullOrEmpty(maybeName)) {
        taxonomy.setName(maybeName);
      }
      if (checkNullOrEmpty(maybeProductId)) {
        int productId = Integer.parseInt(maybeProductId);
        Product product = new Product(productId);
        taxonomy.setProduct(product);
      }
      if (checkNullOrEmpty(maybeType)) {
        taxonomy.setType(maybeType);
      }

      if(checkNullOrEmpty(maybeSubType)) {
        taxonomy.setSubType(maybeSubType);
      }
      TaxonomyService.update(taxonomy);
      success = true;
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
    int taxonomyId = -1;
    String maybeTaxonomyId = request.getParameter("taxonomy_id");
    boolean success = false;

    if (checkNullOrEmpty(maybeTaxonomyId)) {
      taxonomyId = Integer.parseInt(maybeTaxonomyId);
      success = TaxonomyService.deleteById(taxonomyId);
    }

    try {
      response.setContentType("text/html");
      response.setCharacterEncoding("UTF-8");
      if (success) {
        response.getWriter().append("Taxonomy Deleted").close();
      } else {
        response.getWriter().append("Taxonomy Delete Failed").close();
      }
    } catch (IOException e) {
      logger.warn(e.getMessage());
      e.printStackTrace();
    }
  }

  public static void findAll(HttpServletRequest request, HttpServletResponse response) {

    List<Taxonomy> taxonomies = TaxonomyService.findAll();

    try {
      ObjectMapper om = new ObjectMapper();
      String json = om.writeValueAsString(taxonomies);
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().append(json).close();
    } catch (IOException e) {
      logger.warn(e.getMessage());
      e.printStackTrace();
    }
  }

  public static void findById(HttpServletRequest request, HttpServletResponse response) {

    int taxonomyId = -1;
    String maybeTaxonomyId = request.getParameter("taxonomy_id");
    Taxonomy taxonomy = null;

    if (checkNullOrEmpty(maybeTaxonomyId)) {
      taxonomyId = Integer.parseInt(maybeTaxonomyId);
      taxonomy = TaxonomyService.findById(taxonomyId);
    }

    try {
      ObjectMapper om = new ObjectMapper();
      String json = om.writeValueAsString(taxonomy);
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().append(json).close();
    } catch (IOException e) {
      logger.warn(e.getMessage());
      e.printStackTrace();
    }
  }
}