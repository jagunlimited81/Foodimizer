package edu.ilstu.Foodimizer.app.db.service;

import edu.ilstu.Foodimizer.app.db.ServicesEntityManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.service.IngredientService;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;
import org.junit.*;

import java.util.List;

public class TestProfileService {
    private ProfileService ps;

    @BeforeClass
    public static void beforeClass()
    {
        ServicesEntityManager sem = ServicesEntityManager.getInstance();
        sem.init("FoodimizerDB-TEST");
    }

    @Before
    public void before() {
        ps = new ProfileService();

    }

    @After
    public void after() {
        List<Profile> createdDuringTesting = ps.getAll();
        for (Profile r : createdDuringTesting) {
            ps.delete(r);
        }

    }


    //getAll
    @Test
    public void testGetAllSuccessful() {
        Profile prof = new Profile();
        prof.setName("test1");
        ps.save(prof);

        Profile prof2 = new Profile();
        prof2.setName("test3");
        ps.save(prof2);

        Profile prof3 = new Profile();
        prof3.setName("test2");
        ps.save(prof3);

        Assert.assertTrue(ps.getAll().size() == 3);
        Assert.assertTrue(ps.getAll().contains(prof));
        Assert.assertTrue(ps.getAll().contains(prof2));
        Assert.assertTrue(ps.getAll().contains(prof3));
    }

    //save
    @Test
    public void testSaveSuccessful() {
        Profile prof = new Profile();
        prof.setName("test");

        ps.save(prof);

        Assert.assertTrue(ps.getAll().size() == 1);
        Assert.assertTrue(ps.getAll().get(0).getName().equals("test"));
    }

    //update
    @Test
    public void testUpdateSuccessful() {
        Profile prof = new Profile();
        prof.setName("test");
        ps.save(prof);

        prof.setName("test2");
        ps.update(prof, "");

        Assert.assertTrue(ps.getAll().size() == 1);
        Assert.assertTrue(ps.getAll().get(0).getName().equals("test2"));
    }

    //delete
    @Test
    public void testDeleteSuccessful() {
        Profile prof = new Profile();
        prof.setName("test");
        ps.save(prof);

        ps.delete(prof);

        Assert.assertTrue(ps.getAll().isEmpty());
    }
}
