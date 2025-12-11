package me.evankim.cs4270final.omr;

import me.evankim.cs4270final.model.GraciousProfessionalism;
import me.evankim.cs4270final.model.Scoresheet;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Maps detected field values from a scoresheet to the corresponding Scoresheet model.
 */
public class FieldMapper {

    private final Map<String, BiConsumer<Scoresheet, String>> fieldSetters = new HashMap<>();

    /**
     * Creates a new FieldMapper and initializes the field setter map.
     */
    public FieldMapper() {
        initializeFieldSetters();
    }

    private void initializeFieldSetters() {
        // Equipment Inspection
        fieldSetters.put("EI", (s, v) -> s.getEquipmentInspection().passedProperty().set(parseBoolean(v)));

        // Mission 01
        fieldSetters.put("M01_SOIL", (s, v) -> s.getMission01().soilDepositsClearedProperty().set(parseInt(v)));
        fieldSetters.put("M01_BRUSH", (s, v) -> s.getMission01().brushNotTouchingDigSiteProperty().set(parseBoolean(v)));

        // Mission 02
        fieldSetters.put("M02", (s, v) -> s.getMission02().topsoilSectionsClearedProperty().set(parseInt(v)));

        // Mission 03
        fieldSetters.put("M03_YOUR", (s, v) -> s.getMission03().yourMinecartOnOpposingFieldProperty().set(parseBoolean(v)));
        fieldSetters.put("M03_OPP", (s, v) -> s.getMission03().opposingMinecartOnYourFieldProperty().set(parseBoolean(v)));

        // Mission 04
        fieldSetters.put("M04_ART", (s, v) -> s.getMission04().preciousArtifactNotTouchingMineProperty().set(parseBoolean(v)));
        fieldSetters.put("M04_SUP", (s, v) -> s.getMission04().bothSupportStructuresStandingProperty().set(parseBoolean(v)));

        // Mission 05
        fieldSetters.put("M05", (s, v) -> s.getMission05().structureFloorCompletelyUprightProperty().set(parseBoolean(v)));

        // Mission 06
        fieldSetters.put("M06", (s, v) -> s.getMission06().oreBlocksNotTouchingForgeProperty().set(parseInt(v)));

        // Mission 07
        fieldSetters.put("M07", (s, v) -> s.getMission07().millstoneNotTouchingBaseProperty().set(parseBoolean(v)));

        // Mission 08
        fieldSetters.put("M08", (s, v) -> s.getMission08().preservedPiecesOutsideSiloProperty().set(parseInt(v)));

        // Mission 09
        fieldSetters.put("M09_ROOF", (s, v) -> s.getMission09().roofCompletelyRaisedProperty().set(parseBoolean(v)));
        fieldSetters.put("M09_WARES", (s, v) -> s.getMission09().marketWaresRaisedProperty().set(parseBoolean(v)));

        // Mission 10
        fieldSetters.put("M10_SCALE", (s, v) -> s.getMission10().scaleTippedAndTouchingMatProperty().set(parseBoolean(v)));
        fieldSetters.put("M10_PAN", (s, v) -> s.getMission10().scalePanCompletelyRemovedProperty().set(parseBoolean(v)));

        // Mission 11
        fieldSetters.put("M11_ART", (s, v) -> s.getMission11().artifactsRaisedAboveGroundLayerProperty().set(parseBoolean(v)));
        fieldSetters.put("M11_FLAG", (s, v) -> s.getMission11().craneFlagAtLeastPartlyLoweredProperty().set(parseBoolean(v)));

        // Mission 12
        fieldSetters.put("M12_SAND", (s, v) -> s.getMission12().sandCompletelyClearedProperty().set(parseBoolean(v)));
        fieldSetters.put("M12_SHIP", (s, v) -> s.getMission12().shipCompletelyRaisedProperty().set(parseBoolean(v)));

        // Mission 13
        fieldSetters.put("M13", (s, v) -> s.getMission13().statueCompletelyRaisedProperty().set(parseBoolean(v)));

        // Mission 14
        fieldSetters.put("M14", (s, v) -> s.getMission14().artifactsInForumProperty().set(parseInt(v)));

        // Mission 15
        fieldSetters.put("M15", (s, v) -> s.getMission15().sitesWithFlagsProperty().set(parseInt(v)));

        // Precision Tokens
        fieldSetters.put("PRECISION", (s, v) -> s.getPrecisionTokens().tokensRemainingProperty().set(parseInt(v)));

        // Gracious Professionalism
        fieldSetters.put("GP", (s, v) -> s.getGraciousProfessionalism().levelProperty().set(parseGPLevel(v)));
    }

    /**
     * Applies a detected field value to the scoresheet model.
     *
     * @param result the detection result containing field ID and value
     * @param scoresheet the scoresheet to update
     */
    public void applyDetection(DetectionResult result, Scoresheet scoresheet) {
        if (!result.hasValue()) {
            return;
        }

        String fieldId = result.getFieldId();
        String value = result.getDetectedValue();

        BiConsumer<Scoresheet, String> setter = fieldSetters.get(fieldId);
        if (setter != null) {
            try {
                setter.accept(scoresheet, value);
            } catch (Exception e) {
                System.err.println("Error applying field " + fieldId + " with value " + value + ": " + e.getMessage());
            }
        }
    }

    private boolean parseBoolean(String value) {
        return "Y".equals(value);
    }

    private int parseInt(String value) {
        return Integer.parseInt(value);
    }

    private GraciousProfessionalism.Level parseGPLevel(String value) {
        return switch (value) {
            case "2" -> GraciousProfessionalism.Level.DEVELOPING;
            case "3" -> GraciousProfessionalism.Level.ACCOMPLISHED;
            case "4" -> GraciousProfessionalism.Level.EXCEEDS;
            default -> GraciousProfessionalism.Level.ACCOMPLISHED;
        };
    }
}