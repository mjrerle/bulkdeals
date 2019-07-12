package p2.dao;

import java.util.List;

import p2.model.Purchase;

public interface IPurchaseDAO {
  public int insert(Purchase purchase);

	public boolean update(Purchase purchase);

	public List<Purchase> findAll();

	public Purchase findById(int id);

	public boolean deleteById(int id);

}