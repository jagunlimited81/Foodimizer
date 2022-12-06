package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.ServicesEntityManager;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;
import edu.ilstu.Foodimizer.lib.ByteTools;
import org.junit.*;

import javax.imageio.ImageIO;
import javax.swing.plaf.nimbus.State;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class EditProfileTest {

    ProfileService ps;
    EditProfile ep;

    @BeforeClass
    public static void init() {
        ServicesEntityManager sem = ServicesEntityManager.getInstance();
        sem.init("FoodimizerDB-TEST");
    }

    @Before
    public void setUp()
    {
        ps = new ProfileService();
        ep = new EditProfile();
    }

    @After
    public void tearDown() {
        List<Profile> createdDuringTesting = ps.getAll();
        for (Profile r : createdDuringTesting) {
            ps.delete(r);
        }
    }

    @Test
    public void testEditActiveProfileName() {
        StateManager sm = StateManager.getInstance();
        Profile testProf = new Profile();
        testProf.setName("testProf");
        ps.save(testProf);

        sm.setActiveProfile(testProf);

        ep.editActiveProfileName("newName");

        Assert.assertTrue(sm.getActiveProfile().getName() == "newName");

        List<Profile> allProfs = ps.getAll();
        Assert.assertTrue(allProfs.size() == 1);
        Assert.assertTrue(allProfs.get(0).getName() == "newName");
    }

    @Test
    public void testEditActiveProfileIcon() {
        StateManager sm = StateManager.getInstance();
        Profile testProf = new Profile();
        testProf.setName("testProf");
        sm.setActiveProfile(testProf);

        BufferedImage image;
        try {
            image = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/nopfp0.png")));
            byte[] bytes = ByteTools.toByteArray(image, "png");
            testProf.setProfilePic(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ps.save(testProf);

        BufferedImage newImage;
        byte[] newBytes;
        try {
            image = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/nopfp1.png")));
            newBytes = ByteTools.toByteArray(image, "png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ep.editActiveProfileIcon("/images/nopfp1.png");

        Assert.assertFalse(sm.getActiveProfile().getProfilePic() == newBytes);

        List<Profile> allProfs = ps.getAll();
        Assert.assertTrue(allProfs.size() == 1);
        Assert.assertArrayEquals(allProfs.get(0).getProfilePic(), newBytes);
    }
}