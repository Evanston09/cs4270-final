package me.evankim.cs4270final.omr;

import me.evankim.cs4270final.model.GraciousProfessionalism;
import me.evankim.cs4270final.model.Scoresheet;

public class FieldMapper {

    public void applyDetection(DetectionResult result, Scoresheet scoresheet) {
        if (!result.hasValue()) {
            return;
        }

        String fieldId = result.getFieldId();
        String value = result.getDetectedValue();

        try {
            switch (fieldId) {
                case "EI":
                    scoresheet.getEquipmentInspection().setPassed(parseBoolean(value));
                    break;
                case "M01_SOIL":
                    scoresheet.getMission01().setSoilDepositsCleared(parseInt(value));
                    break;
                case "M01_BRUSH":
                    scoresheet.getMission01().setBrushNotTouchingDigSite(parseBoolean(value));
                    break;
                case "M02":
                    scoresheet.getMission02().setTopsoilSectionsCleared(parseInt(value));
                    break;
                case "M03_YOUR":
                    scoresheet.getMission03().setYourMinecartOnOpposingField(parseBoolean(value));
                    break;
                case "M03_OPP":
                    scoresheet.getMission03().setOpposingMinecartOnYourField(parseBoolean(value));
                    break;
                case "M04_ART":
                    scoresheet.getMission04().setPreciousArtifactNotTouchingMine(parseBoolean(value));
                    break;
                case "M04_SUP":
                    scoresheet.getMission04().setBothSupportStructuresStanding(parseBoolean(value));
                    break;
                case "M05":
                    scoresheet.getMission05().setStructureFloorCompletelyUpright(parseBoolean(value));
                    break;
                case "M06":
                    scoresheet.getMission06().setOreBlocksNotTouchingForge(parseInt(value));
                    break;
                case "M07":
                    scoresheet.getMission07().setMillstoneNotTouchingBase(parseBoolean(value));
                    break;
                case "M08":
                    scoresheet.getMission08().setPreservedPiecesOutsideSilo(parseInt(value));
                    break;
                case "M09_ROOF":
                    scoresheet.getMission09().setRoofCompletelyRaised(parseBoolean(value));
                    break;
                case "M09_WARES":
                    scoresheet.getMission09().setMarketWaresRaised(parseBoolean(value));
                    break;
                case "M10_SCALE":
                    scoresheet.getMission10().setScaleTippedAndTouchingMat(parseBoolean(value));
                    break;
                case "M10_PAN":
                    scoresheet.getMission10().setScalePanCompletelyRemoved(parseBoolean(value));
                    break;
                case "M11_ART":
                    scoresheet.getMission11().setArtifactsRaisedAboveGroundLayer(parseBoolean(value));
                    break;
                case "M11_FLAG":
                    scoresheet.getMission11().setCraneFlagAtLeastPartlyLowered(parseBoolean(value));
                    break;
                case "M12_SAND":
                    scoresheet.getMission12().setSandCompletelyCleared(parseBoolean(value));
                    break;
                case "M12_SHIP":
                    scoresheet.getMission12().setShipCompletelyRaised(parseBoolean(value));
                    break;
                case "M13":
                    scoresheet.getMission13().setStatueCompletelyRaised(parseBoolean(value));
                    break;
                case "M14":
                    scoresheet.getMission14().setArtifactsInForum(parseInt(value));
                    break;
                case "M15":
                    scoresheet.getMission15().setSitesWithFlags(parseInt(value));
                    break;
                case "PRECISION":
                    scoresheet.getPrecisionTokens().setTokensRemaining(parseInt(value));
                    break;
                case "GP":
                    scoresheet.getGraciousProfessionalism().setLevel(parseGPLevel(value));
                    break;
                default:
                    System.err.println("Warning: Unknown field ID: " + fieldId);
            }

            System.out.println("Applied: " + fieldId + " = " + value);

        } catch (Exception e) {
            System.err.println("Error applying field " + fieldId + " with value " + value + ": " + e.getMessage());
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
