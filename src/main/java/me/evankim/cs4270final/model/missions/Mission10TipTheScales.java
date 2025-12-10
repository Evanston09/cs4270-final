package me.evankim.cs4270final.model.missions;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import me.evankim.cs4270final.model.Mission;

/**
 * Mission 10: Tip the Scales
 * Score: 20 points if scale is tipped and touching mat + 10 points if scale pan is completely removed.
 */
public class Mission10TipTheScales extends Mission {
    private final BooleanProperty scaleTippedAndTouchingMat; // 20 points
    private final BooleanProperty scalePanCompletelyRemoved; // 10 points

    /**
     * Creates a new Mission 10 with default values (scale not tipped, pan not removed).
     */
    public Mission10TipTheScales() {
        this.scaleTippedAndTouchingMat = new SimpleBooleanProperty(false);
        this.scalePanCompletelyRemoved = new SimpleBooleanProperty(false);
        this.score = new ReadOnlyIntegerWrapper(0);
        this.score.bind(
                Bindings.createIntegerBinding(() -> {
                    int score = 0;
                    if (isScaleTippedAndTouchingMat()) {
                        score += 20;
                    }
                    if (isScalePanCompletelyRemoved()) {
                        score += 10;
                    }
                    return score;
                }, scaleTippedAndTouchingMat, scalePanCompletelyRemoved)
        );
    }

    /**
     * Checks if the scale is tipped and touching the mat.
     *
     * @return true if scale is tipped and touching mat, false otherwise
     */
    public boolean isScaleTippedAndTouchingMat() {
        return scaleTippedAndTouchingMat.get();
    }

    /**
     * Gets the scale status as a property for JavaFX bindings.
     *
     * @return the scale property
     */
    public BooleanProperty scaleTippedAndTouchingMatProperty() {
        return scaleTippedAndTouchingMat;
    }

    /**
     * Sets whether the scale is tipped and touching the mat.
     *
     * @param scaleTippedAndTouchingMat true if scale is tipped and touching mat, false otherwise
     */
    public void setScaleTippedAndTouchingMat(boolean scaleTippedAndTouchingMat) {
        this.scaleTippedAndTouchingMat.set(scaleTippedAndTouchingMat);
    }

    /**
     * Checks if the scale pan is completely removed.
     *
     * @return true if scale pan is removed, false otherwise
     */
    public boolean isScalePanCompletelyRemoved() {
        return scalePanCompletelyRemoved.get();
    }

    /**
     * Gets the scale pan status as a property for JavaFX bindings.
     *
     * @return the scale pan property
     */
    public BooleanProperty scalePanCompletelyRemovedProperty() {
        return scalePanCompletelyRemoved;
    }

    /**
     * Sets whether the scale pan is completely removed.
     *
     * @param scalePanCompletelyRemoved true if pan is removed, false otherwise
     */
    public void setScalePanCompletelyRemoved(boolean scalePanCompletelyRemoved) {
        this.scalePanCompletelyRemoved.set(scalePanCompletelyRemoved);
    }

    /**
     * Gets the mission number.
     *
     * @return 10
     */
    @Override
    public int getMissionNumber() {
        return 10;
    }

    /**
     * Gets the mission name.
     *
     * @return "Tip the Scales"
     */
    @Override
    public String getMissionName() {
        return "Tip the Scales";
    }
}
