package edu.ilstu.Foodimizer;

import edu.ilstu.Foodimizer.app.db.service.TestIngredientService;
import edu.ilstu.Foodimizer.app.db.service.TestProfileService;
import edu.ilstu.Foodimizer.app.db.service.TestRecipeService;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        TestIngredientService.class,
        TestProfileService.class,
        TestRecipeService.class
})

public class JunitTestSuite {
}
