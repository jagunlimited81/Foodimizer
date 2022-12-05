package edu.ilstu.Foodimizer.app.db.models;

import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class JoinProfileRateRecipeId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long profileId;
    private Long recipeId;

    public JoinProfileRateRecipeId() {

    }

    public JoinProfileRateRecipeId(long profileId, long recipeId) {
        super();
        this.profileId = profileId;
        this.recipeId = recipeId;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((recipeId == null) ? 0 : recipeId.hashCode());
        result = prime * result
                + ((profileId == null) ? 0 : profileId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JoinProfileRateRecipeId other = (JoinProfileRateRecipeId) obj;
        return Objects.equals(getProfileId(), other.getProfileId()) && Objects.equals(getRecipeId(), other.getRecipeId());
    }
}
