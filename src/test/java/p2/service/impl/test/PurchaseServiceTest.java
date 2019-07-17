package p2.service.impl.test;

import java.time.LocalDate;

// import org.apache.log4j.Logger;
// import p2.util.Glogger;

import org.testng.Assert;
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

  @Test
  public void aPurchaseCanBeFound() {
    LocalDate now = LocalDate.now();
    int uid = UserService.insert(new User("vajira", "Hapu Arachchige", "vajirakarunathilake@gmail.com", "abc123",
        Roles.ADMIN.value, "abc", 123466, 322));
    int tid = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
    int pid = ProductService.insert(new Product("name", "description", 190, 190, 0, 0, "url", now, "Within Threshold",
        0, new Taxonomy(tid), new User(uid)));
    Purchase purchase = new Purchase(now, new User(uid), new Product(pid));
    int id = PurchaseService.insert(purchase);

    Purchase p = PurchaseService.findById(id);
    Assert.assertNotNull(p);
    Assert.assertEquals(uid, p.getUser().getUserId());
    Assert.assertEquals(pid, p.getProduct().getProductId());
    Assert.assertEquals(now, p.getDatePurchased());
    PurchaseService.deleteById(id);
    ProductService.deleteById(pid);
    TaxonomyService.deleteById(tid);
    UserService.deleteById(uid);
  }

  @Test
  public void aPurchaseCanBeInserted() {
    LocalDate now = LocalDate.now();
    int uid = UserService.insert(new User("vajira", "Hapu Arachchige", "vajirakarunathilake@gmail.com", "abc123",
        Roles.ADMIN.value, "abc", 123466, 322));
    int tid = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
    int pid = ProductService.insert(new Product("name", "description", 190, 190, 0, 0, "url", now, "Within Threshold",
        0, new Taxonomy(tid), new User(uid)));
    Purchase purchase = new Purchase(now, new User(uid), new Product(pid));
    int id = PurchaseService.insert(purchase);

    purchase = PurchaseService.findById(id);
    Assert.assertTrue(purchase.getPurchaseId() > -1);
    PurchaseService.deleteById(purchase.getPurchaseId());
    ProductService.deleteById(pid);
    TaxonomyService.deleteById(tid);

    UserService.deleteById(uid);
  }

  @Test
  public void aPurchaseCanBeUpdated() {
    LocalDate now = LocalDate.now();
    int uid = UserService.insert(new User("vajira", "Hapu Arachchige", "vajirakarunathilake@gmail.com", "abc123",
        Roles.ADMIN.value, "abc", 123466, 322));
    int tid = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
    int pid = ProductService.insert(new Product("name", "description", 190, 190, 0, 0, "url", now, "Within Threshold",
        0, new Taxonomy(tid), new User(uid)));
    Purchase purchase = new Purchase(now, new User(uid), new Product(pid));
    int id = PurchaseService.insert(purchase);

    Purchase updatedPurchase = PurchaseService.findById(id);
    LocalDate threeDaysFromNow = now.plusDays(3L);
    updatedPurchase.setDatePurchased(threeDaysFromNow);
    Assert.assertTrue(PurchaseService.update(updatedPurchase));
    updatedPurchase.setDatePurchased(now);
    updatedPurchase = PurchaseService.findById(updatedPurchase.getPurchaseId());
    Assert.assertTrue(updatedPurchase.getDatePurchased().equals(threeDaysFromNow));
    PurchaseService.deleteById(updatedPurchase.getPurchaseId());
    ProductService.deleteById(pid);
    TaxonomyService.deleteById(tid);

    UserService.deleteById(uid);
  }

  @Test
  public void aPurchaseCanBeDeleted() {
    LocalDate now = LocalDate.now();
    int uid = UserService.insert(new User("vajira", "Hapu Arachchige", "vajirakarunathilake@gmail.com", "abc123",
        Roles.ADMIN.value, "abc", 123466, 322));
    int tid = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
    int pid = ProductService.insert(new Product("name", "description", 190, 190, 0, 0, "url", now, "Within Threshold",
        0, new Taxonomy(tid), new User(uid)));
    Purchase purchase = new Purchase(now, new User(uid), new Product(pid));
    int id = PurchaseService.insert(purchase);
    purchase = PurchaseService.findById(id);
    Assert.assertTrue(PurchaseService.deleteById(purchase.getPurchaseId()));
    ProductService.deleteById(pid);
    TaxonomyService.deleteById(tid);

    UserService.deleteById(uid);
  }
}
