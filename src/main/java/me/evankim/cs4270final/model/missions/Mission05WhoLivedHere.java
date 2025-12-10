package me.evankim.cs4270final.model.missions;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import me.evankim.cs4270final.model.Mission;

/**
 * Mission 05: Who Lived Here?
 * Score: 30 points if structure floor is completely upright.
 */
public class Mission05WhoLivedHere extends Mission {
    private final BooleanProperty structureFloorCompletelyUpright; // 30 points

    /**
     * Creates a new Mission 05 with default values (structure floor not upright).
     */
    public Mission05WhoLivedHere() {
        this.structureFloorCompletelyUpright = new SimpleBooleanProperty(false);
        this.score = new ReadOnlyIntegerWrapper(0);
        this.score.bind(
                structureFloorCompletelyUpright.map(b -> b ? 30 : 0)
        );
    }

    /**
     * Checks if the structure floor is completely upright.
     *
     * @return true if structure floor is upright, false otherwise
     */
    public boolean isStructureFloorCompletelyUpright() {
        return structureFloorCompletelyUpright.get();
    }

    /**
     * Gets the structure floor status as a property for JavaFX bindings.
     *
     * @return the structure floor property
     */
    public BooleanProperty structureFloorCompletelyUprightProperty() {
        return structureFloorCompletelyUpright;
    }

    /**
     * Sets whether the structure floor is completely upright.
     *
     * @param structureFloorCompletelyUpright true if floor is upright, false otherwise
     */
    public void setStructureFloorCompletelyUpright(boolean structureFloorCompletelyUpright) {
        this.structureFloorCompletelyUpright.set(structureFloorCompletelyUpright);
    }

    /**
     * Gets the mission number.
     *
     * @return 5
     */
    @Override
    public int getMissionNumber() {
        return 5;
    }

    /**
     * Gets the mission name.
     *
     * @return "Who Lived Here?"
     */
    @Override
    public String getMissionName() {
        return "Who Lived Here?";
    }
}
