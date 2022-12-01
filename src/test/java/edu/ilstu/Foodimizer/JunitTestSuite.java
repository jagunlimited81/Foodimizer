package edu.ilstu.Foodimizer;

import edu.ilstu.Foodimizer.app.TestIngredientService;
import edu.ilstu.Foodimizer.app.TestProfileService;
import edu.ilstu.Foodimizer.app.TestRecipeService;
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
