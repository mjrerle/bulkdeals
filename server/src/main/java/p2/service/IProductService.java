package p2.service;

import java.util.List;

import p2.model.Product;

public interface IProductService {
	
	public int insert(Product t);

	public void update(Product t);

	public List<Product> findAll();

	public Product findById(int id);
	
	public void deleteById(int id);
	

}
