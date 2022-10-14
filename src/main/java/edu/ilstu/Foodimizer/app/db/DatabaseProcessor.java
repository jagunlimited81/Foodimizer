package edu.ilstu.Foodimizer.app.db;


import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

public class DatabaseProcessor {
    public DatabaseProcessor() {

        // Entity Manager Creator
        emf = Persistence.createEntityManagerFactory("FoodimizerDB");
        // The Entity Manager object created by the factory
        em = emf.createEntityManager();
        // transaction object;
        tx = em.getTransaction();
        // em.close();
        // emf.close();
    }

    public void createProfiles() {
        tx.begin();
        // Create Profiles
        for (int i = 0; i < 5; i++) {
            Profile profile = new Profile();
            profile.setName("profile " + i);
            em.persist(profile);
        }
        tx.commit();

    }
    public void createIngredients() {
        tx.begin();
        String[] ingredientsList = new String[] {"egg", "bacon", "cheese", "sausage", "bread", "english muffin"};
        for (String s : ingredientsList) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(s);
            em.persist(ingredient);
        }
        tx.commit();
    }
    public void createRecipes() {
        tx.begin();
        Recipe recipe = new Recipe();
        recipe.setName("English Muffin");
        em.persist(recipe);
        tx.commit();
    }

    EntityManager em;
    EntityManagerFactory emf;
    EntityTransaction tx;

    public List<Profile> getAllProfilesFromDB() {
        tx.begin();
        List<Profile> profiles = em.createQuery("from Profile", Profile.class).getResultList();
        tx.commit();
        return profiles;
    }
}
