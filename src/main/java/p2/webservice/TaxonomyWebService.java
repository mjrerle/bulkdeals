package p2.webservice;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;

import p2.model.Taxonomy;
import p2.service.TaxonomyService;
import p2.util.Glogger;
import p2.util.ValidationUtilities;

public class TaxonomyWebService {
  public static Logger logger = Glogger.logger;

  public static void insert(HttpServletRequest request, HttpServletResponse response) {

    Taxonomy taxonomy = null;
    int taxonomyId = -1;

    String maybeName = request.getParameter("name");
    String maybeType = request.getParameter("type");
    String maybeSubType = request.getParameter("subType");

    if (ValidationUtilities.checkNullOrEmpty(maybeName) && ValidationUtilities.checkNullOrEmpty(maybeType)
        && ValidationUtilities.checkNullOrEmpty(maybeSubType)) {

      taxonomy = new Taxonomy(maybeName, maybeType, maybeSubType);
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
    String maybeSubType = request.getParameter("subType");
    String maybeTaxonomyId = request.getParameter("taxonomyId");

    boolean success = false;
    if (ValidationUtilities.checkNullOrEmpty(maybeTaxonomyId)) {
      Taxonomy taxonomy = TaxonomyService.findById(Integer.parseInt(maybeTaxonomyId));
      if (taxonomy != null) {
        if (ValidationUtilities.checkNullOrEmpty(maybeName)) {
          taxonomy.setName(maybeName);
        }
        if (ValidationUtilities.checkNullOrEmpty(maybeType)) {
          taxonomy.setType(maybeType);
        }

        if (ValidationUtilities.checkNullOrEmpty(maybeSubType)) {
          taxonomy.setSubType(maybeSubType);
        }
        TaxonomyService.update(taxonomy);
        success = true;
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
    int taxonomyId = -1;
    String maybeTaxonomyId = request.getParameter("taxonomyId");
    boolean success = false;

    if (ValidationUtilities.checkNullOrEmpty(maybeTaxonomyId)) {
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
    String maybeTaxonomyId = request.getParameter("taxonomyId");
    Taxonomy taxonomy = null;

    if (ValidationUtilities.checkNullOrEmpty(maybeTaxonomyId)) {
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