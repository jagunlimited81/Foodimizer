package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.db.ServicesEntityManager;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class CreateProfileTest {

    ProfileService ps;
    CreateProfile crp;

    @BeforeClass
    public static void setUp() {
        ServicesEntityManager sem = ServicesEntityManager.getInstance();
        sem.init("FoodimizerDB-TEST");
    }

    @Before
    public void setUpTest()
    {
        ps = new ProfileService();
        crp = new CreateProfile();
    }

    @After
    public void tearDown() {
        List<Profile> createdDuringTesting = ps.getAll();
        for (Profile r : createdDuringTesting) {
            ps.delete(r);
        }
    }

    @Test
    public void testCreateProfile() {

        crp.createProfile("testProf", "/images/nopfp0.png");

        List<Profile> allProfs = ps.getAll();
        Assert.assertTrue(allProfs.size() == 1);
        Assert.assertTrue(allProfs.get(0).getName() == "testProf");
    }
}