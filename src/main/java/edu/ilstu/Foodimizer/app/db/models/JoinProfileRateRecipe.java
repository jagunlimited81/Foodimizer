package edu.ilstu.Foodimizer.app.db.models;

import jakarta.persistence.*;

@Entity
@Table(name = "JOINPROFILERATERECIPE")
public class JoinProfileRateRecipe {
    @EmbeddedId
    private final JoinProfileRateRecipeId id = new JoinProfileRateRecipeId();

    @ManyToOne
    @MapsId("profileId")
    private Profile profile;

    @ManyToOne
    @MapsId("recipeId")
    private Recipe recipe;

    @Column(name = "rating")
    private byte rating;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }
}
