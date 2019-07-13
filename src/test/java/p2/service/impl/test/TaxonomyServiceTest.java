package p2.service.impl.test;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import p2.model.Product;
import p2.model.Purchase;
import p2.model.User;
import p2.service.ProductService;
import p2.service.PurchaseService;
import p2.service.UserService;
import p2.util.Roles;

public class TaxonomyServiceTest {
  // private static Logger logger = Glogger.logger;
  private static int uid;
  private static int pid;
  private static int id;
  private static LocalDate now;
  
  @BeforeSuite
  public void setup() {
    now = LocalDate.now();
    uid = UserService.insert(new User("vajira", "Hapu Arachchige", "vajirakarunathilake@gmail.com", "abc123", Roles.ADMIN.value, "abc", 123466, 322));
    pid = ProductService.insert(new Product("abc", 190, 190, 0, 0, now, "Within Threshold", 0, new User(uid)));
  }

  @Test
  public void aPurchaseCanBeFound() {
    id = PurchaseService.insert(new Purchase(now, new User(uid), new Product(pid)));
    
    Purchase p = PurchaseService.findById(id);
    Assert.assertNotNull(p);
    Assert.assertEquals(pid, p.getUser().getId());
    Assert.assertEquals(uid, p.getProduct().getId());
    Assert.assertEquals(now, p.getDatePurchased());
    PurchaseService.deleteById(id);
  }

  @Test
  public void aPurchaseCanBeInserted() {
    id = PurchaseService.insert(new Purchase(now, new User(uid), new Product(pid)));

    Purchase purchase = PurchaseService.findById(id);
    Assert.assertTrue(purchase.getId() > -1);
    PurchaseService.deleteById(purchase.getId());
  }

  @Test
  public void aPurchaseCanBeUpdated() {
    id = PurchaseService.insert(new Purchase(now, new User(uid), new Product(pid)));

    Purchase purchase = PurchaseService.findById(id);
    LocalDate threeDaysFromNow = LocalDate.now().plusDays(3L);
    purchase.setDatePurchased(threeDaysFromNow);
    Assert.assertTrue(PurchaseService.update(purchase));
    purchase = PurchaseService.findById(purchase.getId());
    Assert.assertTrue(purchase.getDatePurchased().equals(threeDaysFromNow));
    PurchaseService.deleteById(purchase.getId());
  }

  @Test
  public void aPurchaseCanBeDeleted() {
    id = PurchaseService.insert(new Purchase(now, new User(uid), new Product(pid)));

    Purchase purchase = PurchaseService.findById(id);
    Assert.assertTrue(PurchaseService.deleteById(purchase.getId()));
  }

  @AfterSuite
  public void teardown() {
    ProductService.deleteById(pid);
    UserService.deleteById(uid);
  }
}
