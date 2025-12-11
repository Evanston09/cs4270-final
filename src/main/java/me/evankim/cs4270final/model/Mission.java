package me.evankim.cs4270final.model;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;

/**
 * Abstract base class representing an FLL Robotics mission.
 * Each mission has specific completion criteria and scoring rules.
 * Extends ScoringComponent to provide score binding capabilities.
 */
public abstract class Mission extends ScoringComponent {
    protected ReadOnlyIntegerWrapper score;

    /**
     * Gets the mission number.
     *
     * @return the mission number (1-15)
     */
    public abstract int getMissionNumber();

    /**
     * Gets the mission name.
     *
     * @return the mission name/title
     */
    public abstract String getMissionName();

    /**
     * Gets the mission score as a read-only property for JavaFX bindings.
     * The score automatically updates when mission state changes.
     *
     * @return the score property
     */
    public ReadOnlyIntegerProperty scoreProperty() {
        return score.getReadOnlyProperty();
    }

    /**
     * Calculates the mission score.
     *
     * @return the mission's current score
     */
    @Override
    public int calculateScore() {
        return score.get();
    }
}
