package me.evankim.cs4270final.model.missions;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import me.evankim.cs4270final.model.Mission;

/**
 * Mission 11: Angler Artifacts
 * Score: 20 points if artifacts are raised above ground layer + 10 bonus if crane flag is at least partly lowered.
 */
public class Mission11AnglerArtifacts extends Mission {
    private final BooleanProperty artifactsRaisedAboveGroundLayer; // 20 points
    private final BooleanProperty craneFlagAtLeastPartlyLowered; // 10 points bonus

    /**
     * Creates a new Mission 11 with default values (artifacts not raised, flag not lowered).
     */
    public Mission11AnglerArtifacts() {
        this.artifactsRaisedAboveGroundLayer = new SimpleBooleanProperty(false);
        this.craneFlagAtLeastPartlyLowered = new SimpleBooleanProperty(false);
        this.score = new ReadOnlyIntegerWrapper(0);
        this.score.bind(
                Bindings.when(artifactsRaisedAboveGroundLayer)
                        .then(Bindings.when(craneFlagAtLeastPartlyLowered).then(30).otherwise(20))
                        .otherwise(0)
        );
    }

    /**
     * Checks if the artifacts are raised above the ground layer.
     *
     * @return true if artifacts are raised above ground layer, false otherwise
     */
    public boolean isArtifactsRaisedAboveGroundLayer() {
        return artifactsRaisedAboveGroundLayer.get();
    }

    /**
     * Gets the artifacts status as a property for JavaFX bindings.
     *
     * @return the artifacts property
     */
    public BooleanProperty artifactsRaisedAboveGroundLayerProperty() {
        return artifactsRaisedAboveGroundLayer;
    }

    /**
     * Sets whether the artifacts are raised above the ground layer.
     *
     * @param artifactsRaisedAboveGroundLayer true if artifacts are raised, false otherwise
     */
    public void setArtifactsRaisedAboveGroundLayer(boolean artifactsRaisedAboveGroundLayer) {
        this.artifactsRaisedAboveGroundLayer.set(artifactsRaisedAboveGroundLayer);
    }

    /**
     * Checks if the crane flag is at least partly lowered.
     *
     * @return true if crane flag is at least partly lowered, false otherwise
     */
    public boolean isCraneFlagAtLeastPartlyLowered() {
        return craneFlagAtLeastPartlyLowered.get();
    }

    /**
     * Gets the crane flag status as a property for JavaFX bindings.
     *
     * @return the crane flag property
     */
    public BooleanProperty craneFlagAtLeastPartlyLoweredProperty() {
        return craneFlagAtLeastPartlyLowered;
    }

    /**
     * Sets whether the crane flag is at least partly lowered.
     *
     * @param craneFlagAtLeastPartlyLowered true if flag is partly lowered, false otherwise
     */
    public void setCraneFlagAtLeastPartlyLowered(boolean craneFlagAtLeastPartlyLowered) {
        this.craneFlagAtLeastPartlyLowered.set(craneFlagAtLeastPartlyLowered);
    }

    /**
     * Gets the mission number.
     *
     * @return 11
     */
    @Override
    public int getMissionNumber() {
        return 11;
    }

    /**
     * Gets the mission name.
     *
     * @return "Angler Artifacts"
     */
    @Override
    public String getMissionName() {
        return "Angler Artifacts";
    }
}
