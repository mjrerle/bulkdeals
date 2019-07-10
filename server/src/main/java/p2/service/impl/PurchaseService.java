package p2.service.impl;

import java.util.List;

import p2.dao.impl.PurchaseDAO;
import p2.model.Purchase;

public class PurchaseService {

  private static PurchaseDAO purchaseDAO = new PurchaseDAO();

  public static int insert(Purchase purchase) {
    return purchaseDAO.insert(purchase);
  }

  public static void update(Purchase purchase) {
    purchaseDAO.update(purchase);
  }

  public static List<Purchase> findAll() {
    return purchaseDAO.findAll();
  }

  public static Purchase findById(int id) {
    return purchaseDAO.findById(id);
  }

  public static void deleteById(int id) {
    purchaseDAO.deleteById(id);
  }

	

}
