package edu.ilstu.Foodimizer.app.db.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "PROFILES")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profileId", nullable = false, unique = true)
    private long profileId;
    @Column(name = "name", length = 100, nullable = false, unique = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "PROFILE_DISLIKES",
            joinColumns = @JoinColumn(name = "profileId"),
            inverseJoinColumns = @JoinColumn(name = "ingredientId")
    )
    Set<Ingredient> dislikes;

    @ManyToMany
    @JoinTable(
            name = "PROFILE_SHOPPINGLIST",
            joinColumns = @JoinColumn(name = "profileId"),
            inverseJoinColumns = @JoinColumn(name = "ingredientId")
    )
    Set<Ingredient> shoppingList;

    @ManyToMany
    @JoinTable(
            name = "PROFILE_PANTRY",
            joinColumns = @JoinColumn(name = "profileId"),
            inverseJoinColumns = @JoinColumn(name = "ingredientId")
    )
    Set<Ingredient> pantry;

    public Profile(long profileId, String name) {
        this.profileId = profileId;
        this.name = name;
    }

    public Profile() {

    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Ingredient> getDislikes() {
        return dislikes;
    }

    public void setDislikes(Set<Ingredient> dislikes) {
        this.dislikes = dislikes;
    }

    public Set<Ingredient> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(Set<Ingredient> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public Set<Ingredient> getPantry() {
        return pantry;
    }

    public void setPantry(Set<Ingredient> pantry) {
        this.pantry = pantry;
    }
}
