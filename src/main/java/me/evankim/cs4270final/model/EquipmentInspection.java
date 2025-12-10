package me.evankim.cs4270final.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Represents the equipment inspection scoring component.
 * If equipment inspection passes, 20 points are awarded. Otherwise, 0 points.
 */
public class EquipmentInspection extends ScoringComponent {
    private final BooleanProperty passed;

    /**
     * Creates an EquipmentInspection component with default passed state of false.
     */
    public EquipmentInspection() {
        this.passed = new SimpleBooleanProperty(false);
        this.score = new ReadOnlyIntegerWrapper(0);
        this.score.bind(passed.map(b -> b ? 20 : 0));
    }

    /**
     * Checks if the equipment inspection passed.
     *
     * @return true if passed, false otherwise
     */
    public boolean isPassed() {
        return passed.get();
    }

    /**
     * Gets the passed status as a property for JavaFX bindings.
     *
     * @return the passed property
     */
    public BooleanProperty passedProperty() {
        return passed;
    }

    /**
     * Sets the passed status.
     *
     * @param passed true if inspection passed, false otherwise
     */
    public void setPassed(boolean passed) {
        this.passed.set(passed);
    }
}
