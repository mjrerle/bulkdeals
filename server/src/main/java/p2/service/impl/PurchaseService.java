package p2.service.impl;

import java.util.List;

import p2.dao.impl.PurchaseDAO;
import p2.model.Purchase;
import p2.service.IPurchaseService;

public class PurchaseService implements IPurchaseService {

  PurchaseDAO purchaseDAO = new PurchaseDAO();

  @Override
  public int insert(Purchase purchase) {
    return purchaseDAO.insert(purchase);
  }

  @Override
  public void update(Purchase purchase) {
    purchaseDAO.update(purchase);
  }

  @Override
  public List<Purchase> findAll() {
    return purchaseDAO.findAll();
  }

  @Override
  public Purchase findById(int id) {
    return purchaseDAO.findById(id);
  }

  @Override
  public void deleteById(int id) {
    purchaseDAO.deleteById(id);
  }

	

}
