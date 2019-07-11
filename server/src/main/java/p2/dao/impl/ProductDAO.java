package p2.dao.impl;

import p2.model.Product;

import java.util.List;

import p2.dao.IProductDAO;

public class ProductDAO extends GenericDAO<Product> implements IProductDAO {


	public int insert(Product t) {
		// TODO Auto-generated method stub
		return super.insert(t);
	}


	public boolean update(Product t) {
		// TODO Auto-generated method stub
		super.update(t);
	}


	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}


	public Product findById(int id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}


	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		super.deleteById(id);
	}
	
}
