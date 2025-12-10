package me.evankim.cs4270final.model.missions;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import me.evankim.cs4270final.model.Mission;

/**
 * Mission 04: Careful Recovery
 * Score: 30 points if precious artifact is not touching mine + 10 points if both support structures are standing.
 */
public class Mission04CarefulRecovery extends Mission {
    private final BooleanProperty preciousArtifactNotTouchingMine; // 30 points
    private final BooleanProperty bothSupportStructuresStanding; // 10 points

    /**
     * Creates a new Mission 04 with default values (artifact touching mine, support structures not standing).
     */
    public Mission04CarefulRecovery() {
        this.preciousArtifactNotTouchingMine = new SimpleBooleanProperty(false);
        this.bothSupportStructuresStanding = new SimpleBooleanProperty(false);
        this.score = new ReadOnlyIntegerWrapper(0);
        this.score.bind(
                Bindings.createIntegerBinding(() -> {
                    int score = 0;
                    if (isPreciousArtifactNotTouchingMine()) {
                        score += 30;
                    }
                    if (isBothSupportStructuresStanding()) {
                        score += 10;
                    }
                    return score;
                }, preciousArtifactNotTouchingMine, bothSupportStructuresStanding)
        );
    }

    /**
     * Checks if the precious artifact is not touching the mine.
     *
     * @return true if artifact is not touching mine, false otherwise
     */
    public boolean isPreciousArtifactNotTouchingMine() {
        return preciousArtifactNotTouchingMine.get();
    }

    /**
     * Gets the precious artifact status as a property for JavaFX bindings.
     *
     * @return the precious artifact property
     */
    public BooleanProperty preciousArtifactNotTouchingMineProperty() {
        return preciousArtifactNotTouchingMine;
    }

    /**
     * Sets whether the precious artifact is not touching the mine.
     *
     * @param preciousArtifactNotTouchingMine true if artifact not touching mine, false otherwise
     */
    public void setPreciousArtifactNotTouchingMine(boolean preciousArtifactNotTouchingMine) {
        this.preciousArtifactNotTouchingMine.set(preciousArtifactNotTouchingMine);
    }

    /**
     * Checks if both support structures are standing.
     *
     * @return true if both structures are standing, false otherwise
     */
    public boolean isBothSupportStructuresStanding() {
        return bothSupportStructuresStanding.get();
    }

    /**
     * Gets the support structures status as a property for JavaFX bindings.
     *
     * @return the support structures property
     */
    public BooleanProperty bothSupportStructuresStandingProperty() {
        return bothSupportStructuresStanding;
    }

    /**
     * Sets whether both support structures are standing.
     *
     * @param bothSupportStructuresStanding true if both structures standing, false otherwise
     */
    public void setBothSupportStructuresStanding(boolean bothSupportStructuresStanding) {
        this.bothSupportStructuresStanding.set(bothSupportStructuresStanding);
    }

    /**
     * Gets the mission number.
     *
     * @return 4
     */
    @Override
    public int getMissionNumber() {
        return 4;
    }

    /**
     * Gets the mission name.
     *
     * @return "Careful Recovery"
     */
    @Override
    public String getMissionName() {
        return "Careful Recovery";
    }
}
