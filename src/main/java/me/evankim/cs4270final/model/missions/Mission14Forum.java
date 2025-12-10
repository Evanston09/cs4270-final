package me.evankim.cs4270final.model.missions;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import me.evankim.cs4270final.model.Mission;

/**
 * Mission 14: Forum
 * Score: 5 points per artifact in the forum (max 7 artifacts).
 * Artifacts: Brush, Topsoil, Precious Artifact, Opposing Team's Minecart,
 * Ore with Fossilized Artifact, Millstone, and Scale Pan.
 */
public class Mission14Forum extends Mission {
    // Artifacts: Brush, Topsoil, Precious Artifact, Opposing Team's Minecart,
    // Ore with Fossilized Artifact, Millstone, & Scale Pan
    private final IntegerProperty artifactsInForum; // 5 points each

    /**
     * Creates a new Mission 14 with default values (0 artifacts in forum).
     */
    public Mission14Forum() {
        this.artifactsInForum = new SimpleIntegerProperty(0);
        this.score = new ReadOnlyIntegerWrapper(0);
        this.score.bind(artifactsInForum.multiply(5));
    }

    /**
     * Gets the number of artifacts in the forum.
     *
     * @return the number of artifacts (0-7)
     */
    public int getArtifactsInForum() {
        return artifactsInForum.get();
    }

    /**
     * Gets the artifacts as a property for JavaFX bindings.
     *
     * @return the artifacts property
     */
    public IntegerProperty artifactsInForumProperty() {
        return artifactsInForum;
    }

    /**
     * Sets the number of artifacts in the forum.
     *
     * @param artifactsInForum the number of artifacts (clamped to 0-7)
     */
    public void setArtifactsInForum(int artifactsInForum) {
        this.artifactsInForum.set(Math.max(0, Math.min(artifactsInForum, 7))); // Max 7 artifacts
    }

    /**
     * Gets the mission number.
     *
     * @return 14
     */
    @Override
    public int getMissionNumber() {
        return 14;
    }

    /**
     * Gets the mission name.
     *
     * @return "Forum"
     */
    @Override
    public String getMissionName() {
        return "Forum";
    }
}
