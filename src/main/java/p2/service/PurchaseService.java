package p2.service;

import java.util.List;

import p2.dao.impl.PurchaseDAO;
import p2.model.Purchase;

public class PurchaseService {

  private static PurchaseDAO purchaseDAO = new PurchaseDAO();

  public static int insert(Purchase purchase) {
    return purchaseDAO.insert(purchase);
  }

  public static boolean update(Purchase purchase) {
    return purchaseDAO.update(purchase);
  }

  public static List<Purchase> findAll() {
    return purchaseDAO.findAll();
  }

  public static Purchase findById(int id) {
    return purchaseDAO.findById(id);
  }

  public static boolean deleteById(int id) {
    return purchaseDAO.deleteById(id);
  }

}
