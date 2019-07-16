package p2.service.impl.test;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import p2.model.Interest;
import p2.model.Product;
import p2.model.Taxonomy;
import p2.model.User;
import p2.service.InterestService;
import p2.service.ProductService;
import p2.service.TaxonomyService;
import p2.service.UserService;
import p2.util.Roles;

public class InterestServiceTest {

  // @Test
  // public void anInterestCanBeInserted() {
  //   LocalDate now = LocalDate.now();
  //   int tid = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
  //   int uid = UserService.insert(
  //       new User("vajira", "Hapu Arachchige", "sfasda@gmail.com", "abc123", Roles.ADMIN.value, "abc", 123466, 322));
  //   int pid = ProductService.insert(new Product("name", "description", 190, 190, 0, 0, "url", now, "Within Threshold",
  //       0, new Taxonomy(tid), new User(uid)));

  //   int id = InterestService.insert(new Interest(new User(uid), new Product(pid), 300));

  //   Interest interest = InterestService.findById(id);
  //   Assert.assertTrue(interest.getId() >= 0);
  //   InterestService.deleteById(id);

  //   ProductService.deleteById(pid);
  //   UserService.deleteById(uid);
  //   TaxonomyService.deleteById(tid);
  // }

  // @Test
  // public void anInterestCanBeUpdate() {
  //   LocalDate now = LocalDate.now();
  //   int tid = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
  //   int uid = UserService.insert(
  //       new User("vajira", "Hapu Arachchige", "sfasda@gmail.com", "abc123", Roles.ADMIN.value, "abc", 123466, 322));
  //   int pid = ProductService.insert(new Product("name", "description", 190, 190, 0, 0, "url", now, "Within Threshold",
  //       0, new Taxonomy(tid), new User(uid)));

  //   int id = InterestService.insert(new Interest(new User(uid), new Product(pid), 300));

  //   Interest interest = InterestService.findById(id);

  //   interest.setQuantity(400);
  //   Assert.assertTrue(InterestService.update(interest));
  //   Assert.assertEquals(InterestService.findById(interest.getId()).getQuantity(), 400);
  //   InterestService.deleteById(interest.getId());

  //   ProductService.deleteById(pid);
  //   UserService.deleteById(uid);
  //   TaxonomyService.deleteById(tid);
  // }

  // @Test
  // public void anInterestCanBeDeleted() {
  //   LocalDate now = LocalDate.now();
  //   int tid = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
  //   int uid = UserService.insert(
  //       new User("vajira", "Hapu Arachchige", "sfasda@gmail.com", "abc123", Roles.ADMIN.value, "abc", 123466, 322));
  //   int pid = ProductService.insert(new Product("name", "description", 190, 190, 0, 0, "url", now, "Within Threshold",
  //       0, new Taxonomy(tid), new User(uid)));

  //   int id = InterestService.insert(new Interest(new User(uid), new Product(pid), 300));

  //   Interest interest = InterestService.findById(id);
  //   Assert.assertTrue(InterestService.deleteById(interest.getId()));

  //   ProductService.deleteById(pid);
  //   UserService.deleteById(uid);
  //   TaxonomyService.deleteById(tid);
  // }

  // @Test
  // public void allInterestsCanBeFound() {
  //   LocalDate now = LocalDate.now();
  //   int tid = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
  //   int uid = UserService.insert(
  //       new User("vajira", "Hapu Arachchige", "sfasda@gmail.com", "abc123", Roles.ADMIN.value, "abc", 123466, 322));
  //   int pid = ProductService.insert(new Product("name", "description", 190, 190, 0, 0, "url", now, "Within Threshold",
  //       0, new Taxonomy(tid), new User(uid)));

  //   int id1 = InterestService.insert(new Interest(new User(uid), new Product(pid), 300));
  //   int id2 = InterestService.insert(new Interest(new User(uid), new Product(pid), 300));
  //   int id3 = InterestService.insert(new Interest(new User(uid), new Product(pid), 300));

  //   Assert.assertEquals(InterestService.findAll().size(), 3);
  //   InterestService.deleteById(id1);
  //   InterestService.deleteById(id2);
  //   InterestService.deleteById(id3);

  //   ProductService.deleteById(pid);
  //   UserService.deleteById(uid);
  //   TaxonomyService.deleteById(tid);
  // }
}
