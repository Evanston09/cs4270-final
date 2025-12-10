package me.evankim.cs4270final.model;

import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import me.evankim.cs4270final.model.missions.*;

/**
 * Represents a complete FRC Robotics scoring sheet with all mission scores,
 * equipment inspection, precision tokens, and gracious professionalism ratings.
 *
 * The scoresheet automatically calculates the total score by binding all
 * component scores together. Uses the property pattern for JavaFX bindings.
 */
public class Scoresheet {
    // Scoring components using polymorphism
    private final EquipmentInspection equipmentInspection;
    private final Mission[] missions; // Array of all 15 missions
    private final PrecisionTokens precisionTokens;
    private final GraciousProfessionalism graciousProfessionalism;
    private final ReadOnlyIntegerWrapper totalScore;

    /**
     * Creates a new Scoresheet with all default values.
     * Initializes all 15 missions and sets up automatic score calculation bindings.
     */
    public Scoresheet() {
        // Initialize scoring components
        this.equipmentInspection = new EquipmentInspection();

        // Initialize missions array with all 15 missions
        this.missions = new Mission[15];
        missions[0] = new Mission01SurfaceBrushing();
        missions[1] = new Mission02MapReveal();
        missions[2] = new Mission03MineshaftExplorer();
        missions[3] = new Mission04CarefulRecovery();
        missions[4] = new Mission05WhoLivedHere();
        missions[5] = new Mission06Forge();
        missions[6] = new Mission07HeavyLifting();
        missions[7] = new Mission08Silo();
        missions[8] = new Mission09WhatsOnSale();
        missions[9] = new Mission10TipTheScales();
        missions[10] = new Mission11AnglerArtifacts();
        missions[11] = new Mission12SalvageOperation();
        missions[12] = new Mission13StatueRebuild();
        missions[13] = new Mission14Forum();
        missions[14] = new Mission15SiteMarking();

        this.precisionTokens = new PrecisionTokens();
        this.graciousProfessionalism = new GraciousProfessionalism();

        // Bind total score
        this.totalScore = new ReadOnlyIntegerWrapper(0);
        IntegerBinding missionsBinding = new IntegerBinding() {
            {
                for (Mission mission : missions) {
                    super.bind(mission.scoreProperty());
                }
            }
            @Override
            protected int computeValue() {
                int sum = 0;
                for (Mission mission : missions) {
                    sum += mission.calculateScore();
                }
                return sum;
            }
        };
        totalScore.bind(equipmentInspection.scoreProperty()
                .add(missionsBinding)
                .add(precisionTokens.scoreProperty())
                .add(graciousProfessionalism.scoreProperty()));
    }

    /**
     * Gets the equipment inspection component.
     *
     * @return the equipment inspection
     */
    public EquipmentInspection getEquipmentInspection() {
        return equipmentInspection;
    }

    /**
     * Gets all missions in the scoresheet.
     *
     * @return array of all 15 missions
     */
    public Mission[] getMissions() {
        return missions;
    }

    /**
     * Gets a specific mission by number.
     *
     * @param missionNumber the mission number (1-15)
     * @return the mission at the specified number
     */
    public Mission getMission(int missionNumber) {
        return missions[missionNumber - 1];
    }

    /**
     * Gets Mission 01: Surface Brushing.
     *
     * @return Mission 01 cast to its specific type
     */
    public Mission01SurfaceBrushing getMission01() {
        return (Mission01SurfaceBrushing) getMission(1);
    }

    /**
     * Gets Mission 02: Map Reveal.
     *
     * @return Mission 02 cast to its specific type
     */
    public Mission02MapReveal getMission02() {
        return (Mission02MapReveal) getMission(2);
    }

    /**
     * Gets Mission 03: Mineshaft Explorer.
     *
     * @return Mission 03 cast to its specific type
     */
    public Mission03MineshaftExplorer getMission03() {
        return (Mission03MineshaftExplorer) getMission(3);
    }

    /**
     * Gets Mission 04: Careful Recovery.
     *
     * @return Mission 04 cast to its specific type
     */
    public Mission04CarefulRecovery getMission04() {
        return (Mission04CarefulRecovery) getMission(4);
    }

    /**
     * Gets Mission 05: Who Lived Here?.
     *
     * @return Mission 05 cast to its specific type
     */
    public Mission05WhoLivedHere getMission05() {
        return (Mission05WhoLivedHere) getMission(5);
    }

    /**
     * Gets Mission 06: Forge.
     *
     * @return Mission 06 cast to its specific type
     */
    public Mission06Forge getMission06() {
        return (Mission06Forge) getMission(6);
    }

    /**
     * Gets Mission 07: Heavy Lifting.
     *
     * @return Mission 07 cast to its specific type
     */
    public Mission07HeavyLifting getMission07() {
        return (Mission07HeavyLifting) getMission(7);
    }

    /**
     * Gets Mission 08: Silo.
     *
     * @return Mission 08 cast to its specific type
     */
    public Mission08Silo getMission08() {
        return (Mission08Silo) getMission(8);
    }

    /**
     * Gets Mission 09: What's On Sale?.
     *
     * @return Mission 09 cast to its specific type
     */
    public Mission09WhatsOnSale getMission09() {
        return (Mission09WhatsOnSale) getMission(9);
    }

    /**
     * Gets Mission 10: Tip the Scales.
     *
     * @return Mission 10 cast to its specific type
     */
    public Mission10TipTheScales getMission10() {
        return (Mission10TipTheScales) getMission(10);
    }

    /**
     * Gets Mission 11: Angler Artifacts.
     *
     * @return Mission 11 cast to its specific type
     */
    public Mission11AnglerArtifacts getMission11() {
        return (Mission11AnglerArtifacts) getMission(11);
    }

    /**
     * Gets Mission 12: Salvage Operation.
     *
     * @return Mission 12 cast to its specific type
     */
    public Mission12SalvageOperation getMission12() {
        return (Mission12SalvageOperation) getMission(12);
    }

    /**
     * Gets Mission 13: Statue Rebuild.
     *
     * @return Mission 13 cast to its specific type
     */
    public Mission13StatueRebuild getMission13() {
        return (Mission13StatueRebuild) getMission(13);
    }

    /**
     * Gets Mission 14: Forum.
     *
     * @return Mission 14 cast to its specific type
     */
    public Mission14Forum getMission14() {
        return (Mission14Forum) getMission(14);
    }

    /**
     * Gets Mission 15: Site Marking.
     *
     * @return Mission 15 cast to its specific type
     */
    public Mission15SiteMarking getMission15() {
        return (Mission15SiteMarking) getMission(15);
    }

    /**
     * Gets the precision tokens component.
     *
     * @return the precision tokens
     */
    public PrecisionTokens getPrecisionTokens() {
        return precisionTokens;
    }

    /**
     * Gets the gracious professionalism component.
     *
     * @return the gracious professionalism
     */
    public GraciousProfessionalism getGraciousProfessionalism() {
        return graciousProfessionalism;
    }

    /**
     * Gets the total score as a read-only property.
     * The score automatically updates when any component changes.
     *
     * @return the total score property
     */
    public ReadOnlyIntegerProperty totalScoreProperty() {
        return totalScore.getReadOnlyProperty();
    }

    /**
     * Gets the current total score value.
     *
     * @return the sum of all component scores
     */
    public int calculateTotalScore() {
        return totalScore.get();
    }
}