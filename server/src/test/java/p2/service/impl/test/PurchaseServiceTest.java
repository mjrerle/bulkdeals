package p2.service.impl.test;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Test;

import p2.model.Product;
import p2.model.Purchase;
import p2.model.User;
import p2.service.impl.PurchaseService;

public class PurchaseServiceTest {
  @Test
  public void aPurchaseCanBeFound() {
    int id = PurchaseService.insert(new Purchase(new Date(1L), new User(0), new Product(0)));
    Purchase p = PurchaseService.findById(id);
    assertEquals(0, p.getUser().getId());
    assertEquals(0, p.getProduct().getId());
    assertEquals(1L, p.getDatePurchased().getTime());
    PurchaseService.deleteById(id);
  }

  @Test
  public void aPurchaseCanBeInserted() {
    Purchase a = new Purchase(new Date(1L), new User(0), new Product(0));
    int id = PurchaseService.insert(a);
    Purchase b = PurchaseService.findById(id);
    assertEquals(a, b);
    PurchaseService.deleteById(b.getId());
  }
}
