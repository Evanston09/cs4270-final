package me.evankim.cs4270final.model;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;

/**
 * Abstract base class for all scoring components in an FRC Robotics scoresheet.
 * Provides common functionality for score calculation and property binding.
 */
public abstract class ScoringComponent {
    protected ReadOnlyIntegerWrapper score;

    /**
     * Calculates the score for this component.
     *
     * @return the component's score
     */
    public int calculateScore() {
        return score.get();
    }

    /**
     * Gets the score as a read-only property for JavaFX bindings.
     * The score automatically updates based on component state changes.
     *
     * @return the score property
     */
    public ReadOnlyIntegerProperty scoreProperty() {
        return score.getReadOnlyProperty();
    }
}
