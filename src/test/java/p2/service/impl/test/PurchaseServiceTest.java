package p2.service.impl.test;

import java.sql.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

import p2.model.Product;
import p2.model.Purchase;
import p2.model.User;
// import p2.service.ProductService;
import p2.service.PurchaseService;
import p2.service.UserService;
import p2.util.Roles;

public class PurchaseServiceTest {
  @Test
  public void aPurchaseCanBeFound() {
    int uid = UserService.insert(new User("vajira", "Hapu Arachchige", "vajirakarunathilake@gmail.com", "abc123", Roles.ADMIN.value, "abc", 123466, 322));
    // int pid = ProductService.insert(new Product());
    int id = PurchaseService.insert(new Purchase(new Date(1L), new User(uid), new Product(0)));
    Purchase p = PurchaseService.findById(id);
    Assert.assertNotNull(p);
    Assert.assertEquals(0, p.getUser().getId());
    Assert.assertEquals(0, p.getProduct().getId());
    Assert.assertEquals(1L, p.getDatePurchased().getTime());
    PurchaseService.deleteById(id);
  }

  @Test
  public void aPurchaseCanBeInserted() {
    Purchase a = new Purchase(new Date(1L), new User(0), new Product(0));
    int id = PurchaseService.insert(a);
    Purchase b = PurchaseService.findById(id);
    Assert.assertEquals(a, b);
    PurchaseService.deleteById(b.getId());
  }
}
