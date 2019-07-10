package p2.service.impl;
import java.util.List;
import p2.dao.impl.ProductDAO;
import p2.model.Product;
import p2.service.IProductService;

public class ProductService implements IProductService{

	@Override
	public int insert(Product t) {
		ProductDAO p = new ProductDAO();
		return p.insert(t);
	}

	@Override
	public void update(Product t) {
		ProductDAO p = new ProductDAO();
		p.update(t);
	}

	@Override
	public List<Product> findAll() {
		ProductDAO p = new ProductDAO();
		return p.findAll();
	}

	@Override
	public Product findById(int id) {
		ProductDAO p = new ProductDAO();
		return p.findById(id);
	}

	@Override
	public void deleteById(int id) {
		ProductDAO p = new ProductDAO();
		p.deleteById(id);
	}
	

}
