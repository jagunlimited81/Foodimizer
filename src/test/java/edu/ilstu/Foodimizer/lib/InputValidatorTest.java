package edu.ilstu.Foodimizer.lib;

import edu.ilstu.Foodimizer.app.db.ServicesEntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InputValidatorTest {

    private static InputValidator iv = null;
    @Before
    public void setUp() throws Exception {
        ServicesEntityManager sem = ServicesEntityManager.getInstance();
        sem.init("FoodimizerDB-TEST");
    }

    @Test
    public void validProfileName() {
        String validName = "Sna";
        boolean result = iv.validateProfileName(validName);

        assertTrue(result);
    }

    @Test
    public void invalidProfileName() {
        String invalidName = "$$$$";
        boolean result = iv.validateProfileName(invalidName);

        assertFalse(result);
    }

    @After
    public void tearDown() throws Exception {
        iv = null;
    }
}