package me.evankim.cs4270final.model.missions;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import me.evankim.cs4270final.model.Mission;

/**
 * Mission 07: Heavy Lifting
 * Score: 30 points if millstone is not touching the base.
 */
public class Mission07HeavyLifting extends Mission {
    private final BooleanProperty millstoneNotTouchingBase; // 30 points

    /**
     * Creates a new Mission 07 with default values (millstone touching base).
     */
    public Mission07HeavyLifting() {
        this.millstoneNotTouchingBase = new SimpleBooleanProperty(false);
        this.score = new ReadOnlyIntegerWrapper(0);
        this.score.bind(
                millstoneNotTouchingBase.map(b -> b ? 30 : 0)
        );
    }

    /**
     * Checks if the millstone is not touching the base.
     *
     * @return true if millstone is not touching base, false otherwise
     */
    public boolean isMillstoneNotTouchingBase() {
        return millstoneNotTouchingBase.get();
    }

    /**
     * Gets the millstone status as a property for JavaFX bindings.
     *
     * @return the millstone property
     */
    public BooleanProperty millstoneNotTouchingBaseProperty() {
        return millstoneNotTouchingBase;
    }

    /**
     * Sets whether the millstone is not touching the base.
     *
     * @param millstoneNotTouchingBase true if millstone not touching base, false otherwise
     */
    public void setMillstoneNotTouchingBase(boolean millstoneNotTouchingBase) {
        this.millstoneNotTouchingBase.set(millstoneNotTouchingBase);
    }

    /**
     * Gets the mission number.
     *
     * @return 7
     */
    @Override
    public int getMissionNumber() {
        return 7;
    }

    /**
     * Gets the mission name.
     *
     * @return "Heavy Lifting"
     */
    @Override
    public String getMissionName() {
        return "Heavy Lifting";
    }
}
