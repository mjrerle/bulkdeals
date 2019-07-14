package p2.service.impl.test;

import java.util.ArrayList;
import java.util.List;

// import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import p2.model.Taxonomy;
import p2.model.User;
import p2.service.ProductService;
import p2.service.TaxonomyService;
import p2.service.UserService;
import p2.util.Roles;

public class TaxonomyServiceTest {
  // private static Logger logger = Glogger.logger;
  private static int uid;
  private static int pid;
  private static int id;
  
  @BeforeSuite
  public void setup() {
    uid = UserService.insert(new User("vajira", "Hapu Arachchige", "vajirakarunathilake@gmail.com", "abc123", Roles.ADMIN.value, "abc", 123466, 322));
  }

  @Test
  public void aTaxonomyCanBeFound() {
    String taxName = "Samsung";
    id = TaxonomyService.insert(new Taxonomy(taxName, "Electronics", "Phones"));
    Taxonomy taxonomy = TaxonomyService.findById(id);
    Assert.assertNotNull(taxonomy);
    Assert.assertEquals(taxName, taxonomy.getName());
    TaxonomyService.deleteById(id);
  }

  @Test
  public void aTaxonomyCanBeInserted() {
    id = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));

    Taxonomy taxonomy = TaxonomyService.findById(id);
    Assert.assertTrue(taxonomy.getId() > -1);
    TaxonomyService.deleteById(taxonomy.getId());
  }

  @Test
  public void aTaxonomyCanBeUpdated() {
    id = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));

    Taxonomy taxonomy = TaxonomyService.findById(id);
    String newTaxName = "efg";
    taxonomy.setName(newTaxName);
    Assert.assertTrue(TaxonomyService.update(taxonomy));
    taxonomy = TaxonomyService.findById(taxonomy.getId());
    Assert.assertTrue(taxonomy.getName().equals(newTaxName));
    TaxonomyService.deleteById(taxonomy.getId());
  }

  @Test
  public void aTaxonomyCanBeDeleted() {
    id = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));

    Taxonomy taxonomy = TaxonomyService.findById(id);
    Assert.assertTrue(TaxonomyService.deleteById(taxonomy.getId()));
  }

  @Test(priority=3)
	public void allTaxonomiesCanBeFound(){
    List<Taxonomy> allTaxonomies = new ArrayList<>();
    id = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
    int id2 = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
    int id3 = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));

		allTaxonomies = TaxonomyService.findAll();
		Assert.assertEquals(allTaxonomies.size(), 3);
		TaxonomyService.deleteById(id);
		TaxonomyService.deleteById(id2);
		TaxonomyService.deleteById(id3);

  }

  @AfterSuite
  public void teardown() {
    ProductService.deleteById(pid);
    UserService.deleteById(uid);
  }
}
