package p2.service.impl.test;

import java.time.LocalDate;

// import org.apache.log4j.Logger;
// import p2.util.Glogger;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import p2.model.Product;
import p2.model.Purchase;
import p2.model.Taxonomy;
import p2.model.User;
import p2.service.ProductService;
import p2.service.PurchaseService;
import p2.service.TaxonomyService;
import p2.service.UserService;
import p2.util.Roles;

public class PurchaseServiceTest {
  // private static Logger logger = Glogger.logger;
  private static int uid;
  private static int pid;
  private static int tid;
  private static int id;
  private static LocalDate now;
  private static Purchase purchase;

  @BeforeSuite
  public void setup() {
    now = LocalDate.now();
    uid = UserService.insert(new User("vajira", "Hapu Arachchige", "vajirakarunathilake@gmail.com", "abc123",
        Roles.ADMIN.value, "abc", 123466, 322));
    tid = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
    pid = ProductService.insert(new Product("name", "description", 190, 190, 0, 0, "url", now, "Within Threshold", 0,
        new Taxonomy(tid), new User(uid)));
    purchase = new Purchase(now, new User(uid), new Product(pid));
  }

  @Test
  public void aPurchaseCanBeFound() {
    id = PurchaseService.insert(purchase);

    Purchase p = PurchaseService.findById(id);
    Assert.assertNotNull(p);
    Assert.assertEquals(uid, p.getUser().getId());
    Assert.assertEquals(pid, p.getProduct().getId());
    Assert.assertEquals(now, p.getDatePurchased());
    PurchaseService.deleteById(id);
  }

  @Test
  public void aPurchaseCanBeInserted() {
    id = PurchaseService.insert(purchase);

    purchase = PurchaseService.findById(id);
    Assert.assertTrue(purchase.getId() > -1);
    PurchaseService.deleteById(purchase.getId());
  }

  @Test
  public void aPurchaseCanBeUpdated() {
    id = PurchaseService.insert(purchase);

    Purchase updatedPurchase = PurchaseService.findById(id);
    LocalDate threeDaysFromNow = now.plusDays(3L);
    updatedPurchase.setDatePurchased(threeDaysFromNow);
    Assert.assertTrue(PurchaseService.update(updatedPurchase));
    updatedPurchase.setDatePurchased(now);
    updatedPurchase = PurchaseService.findById(updatedPurchase.getId());
    Assert.assertTrue(updatedPurchase.getDatePurchased().equals(threeDaysFromNow));
    PurchaseService.deleteById(updatedPurchase.getId());
  }

  @Test
  public void aPurchaseCanBeDeleted() {
    id = PurchaseService.insert(purchase);
    purchase = PurchaseService.findById(id);
    Assert.assertTrue(PurchaseService.deleteById(purchase.getId()));
  }

  @AfterSuite
  public void teardown() {
    ProductService.deleteById(pid);
    UserService.deleteById(uid);
  }
}
