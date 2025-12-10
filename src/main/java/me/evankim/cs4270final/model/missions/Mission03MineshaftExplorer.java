package me.evankim.cs4270final.model.missions;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import me.evankim.cs4270final.model.Mission;

/**
 * Mission 03: Mineshaft Explorer
 * Score: 30 points if your minecart is on the opposing field + 10 bonus if opposing minecart is on your field.
 */
public class Mission03MineshaftExplorer extends Mission {
    private final BooleanProperty yourMinecartOnOpposingField; // 30 points
    private final BooleanProperty opposingMinecartOnYourField; // 10 points bonus

    /**
     * Creates a new Mission 03 with default values (minecarts not on opposing fields).
     */
    public Mission03MineshaftExplorer() {
        this.yourMinecartOnOpposingField = new SimpleBooleanProperty(false);
        this.opposingMinecartOnYourField = new SimpleBooleanProperty(false);
        this.score = new ReadOnlyIntegerWrapper(0);
        this.score.bind(
                Bindings.when(yourMinecartOnOpposingField)
                        .then(Bindings.when(opposingMinecartOnYourField).then(40).otherwise(30))
                        .otherwise(0)
        );
    }

    /**
     * Checks if your minecart is on the opposing field.
     *
     * @return true if your minecart is on opposing field, false otherwise
     */
    public boolean isYourMinecartOnOpposingField() {
        return yourMinecartOnOpposingField.get();
    }

    /**
     * Gets the your minecart status as a property for JavaFX bindings.
     *
     * @return the your minecart property
     */
    public BooleanProperty yourMinecartOnOpposingFieldProperty() {
        return yourMinecartOnOpposingField;
    }

    /**
     * Sets whether your minecart is on the opposing field.
     *
     * @param yourMinecartOnOpposingField true if your minecart is on opposing field, false otherwise
     */
    public void setYourMinecartOnOpposingField(boolean yourMinecartOnOpposingField) {
        this.yourMinecartOnOpposingField.set(yourMinecartOnOpposingField);
    }

    /**
     * Checks if the opposing minecart is on your field.
     *
     * @return true if opposing minecart is on your field, false otherwise
     */
    public boolean isOpposingMinecartOnYourField() {
        return opposingMinecartOnYourField.get();
    }

    /**
     * Gets the opposing minecart status as a property for JavaFX bindings.
     *
     * @return the opposing minecart property
     */
    public BooleanProperty opposingMinecartOnYourFieldProperty() {
        return opposingMinecartOnYourField;
    }

    /**
     * Sets whether the opposing minecart is on your field.
     *
     * @param opposingMinecartOnYourField true if opposing minecart is on your field, false otherwise
     */
    public void setOpposingMinecartOnYourField(boolean opposingMinecartOnYourField) {
        this.opposingMinecartOnYourField.set(opposingMinecartOnYourField);
    }

    /**
     * Gets the mission number.
     *
     * @return 3
     */
    @Override
    public int getMissionNumber() {
        return 3;
    }

    /**
     * Gets the mission name.
     *
     * @return "Mineshaft Explorer"
     */
    @Override
    public String getMissionName() {
        return "Mineshaft Explorer";
    }
}
