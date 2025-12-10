package me.evankim.cs4270final;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import me.evankim.cs4270final.model.GraciousProfessionalism;
import me.evankim.cs4270final.model.Scoresheet;
import me.evankim.cs4270final.model.missions.Mission01SurfaceBrushing;
import me.evankim.cs4270final.omr.ScoresheetProcessor;

import java.io.IOException;

public class ScoresheetController {

    // Equipment Inspection
    @FXML private CheckBox equipmentInspectionCheck;

    // Mission 01
    @FXML private TextField mission01SoilDeposits;
    @FXML private CheckBox mission01BrushCheck;
    @FXML private Label mission01SoilScore;
    @FXML private Label mission01Total;

    // Mission 02
    @FXML private TextField mission02TopsoilSections;
    @FXML private Label mission02Score;
    @FXML private Label mission02Total;

    // Mission 03
    @FXML private CheckBox mission03YourMinecart;
    @FXML private CheckBox mission03OpposingMinecart;
    @FXML private Label mission03Total;

    // Mission 04
    @FXML private CheckBox mission04PreciousArtifact;
    @FXML private CheckBox mission04SupportStructures;
    @FXML private Label mission04Total;

    // Mission 05
    @FXML private CheckBox mission05StructureFloor;
    @FXML private Label mission05Total;

    // Mission 06
    @FXML private TextField mission06OreBlocks;
    @FXML private Label mission06Score;
    @FXML private Label mission06Total;

    // Mission 07
    @FXML private CheckBox mission07Millstone;
    @FXML private Label mission07Total;

    // Mission 08
    @FXML private TextField mission08PreservedPieces;
    @FXML private Label mission08Score;
    @FXML private Label mission08Total;

    // Mission 09
    @FXML private CheckBox mission09Roof;
    @FXML private CheckBox mission09MarketWares;
    @FXML private Label mission09Total;

    // Mission 10
    @FXML private CheckBox mission10ScaleTipped;
    @FXML private CheckBox mission10PanRemoved;
    @FXML private Label mission10Total;

    // Mission 11
    @FXML private CheckBox mission11ArtifactsRaised;
    @FXML private CheckBox mission11CraneFlag;
    @FXML private Label mission11Total;

    // Mission 12
    @FXML private CheckBox mission12SandCleared;
    @FXML private CheckBox mission12ShipRaised;
    @FXML private Label mission12Total;

    // Mission 13
    @FXML private CheckBox mission13StatueRaised;
    @FXML private Label mission13Total;

    // Mission 14
    @FXML private TextField mission14Artifacts;
    @FXML private Label mission14Score;
    @FXML private Label mission14Total;

    // Mission 15
    @FXML private TextField mission15Sites;
    @FXML private Label mission15Score;
    @FXML private Label mission15Total;

    // Precision Tokens
    @FXML private ComboBox<Integer> precisionTokensCombo;
    @FXML private Label precisionTokensScore;

    // Gracious Professionalism
    @FXML private RadioButton gpDeveloping;
    @FXML private RadioButton gpAccomplished;
    @FXML private RadioButton gpExceeds;

    // Final Score
    @FXML private Label finalScoreLabel;

    // Model
    private Scoresheet scoresheet;
    private ToggleGroup gpToggleGroup;

    /**
     * Initializes the controller after the FXML file has been loaded.
     * Sets up the model, toggle groups, precision tokens combo, and binds all UI controls to the model.
     */
    @FXML
    public void initialize() {
        scoresheet = new Scoresheet();

        // Set up Gracious Professionalism toggle group
        gpToggleGroup = new ToggleGroup();
        gpDeveloping.setToggleGroup(gpToggleGroup);
        gpAccomplished.setToggleGroup(gpToggleGroup);
        gpExceeds.setToggleGroup(gpToggleGroup);

        // Initialize precision tokens combo
        precisionTokensCombo.getItems().addAll(0, 1, 2, 3, 4, 5, 6);

        bindControlsToModel();
    }

    private void bindControlsToModel() {
        // Equipment Inspection
        equipmentInspectionCheck.selectedProperty().bindBidirectional(scoresheet.getEquipmentInspection().passedProperty());

        // Missions
        bindMission01();
        bindMission02();
        bindMission03();
        bindMission04();
        bindMission05();
        bindMission06();
        bindMission07();
        bindMission08();
        bindMission09();
        bindMission10();
        bindMission11();
        bindMission12();
        bindMission13();
        bindMission14();
        bindMission15();

        // Precision Tokens
        precisionTokensCombo.valueProperty().bindBidirectional(scoresheet.getPrecisionTokens().tokensRemainingProperty().asObject());
        precisionTokensScore.textProperty().bind(scoresheet.getPrecisionTokens().scoreProperty().asString());

        // Gracious Professionalism
        bindGraciousProfessionalism();


        // Final Score
        finalScoreLabel.textProperty().bind(scoresheet.totalScoreProperty().asString());
    }

    private void bindMission01() {
        Mission01SurfaceBrushing m = scoresheet.getMission01();
        mission01SoilDeposits.textProperty().bindBidirectional(m.soilDepositsClearedProperty(), new NumberStringConverter());
        mission01BrushCheck.selectedProperty().bindBidirectional(m.brushNotTouchingDigSiteProperty());
        mission01SoilScore.textProperty().bind(m.soilDepositsClearedProperty().multiply(10).asString());
        mission01Total.textProperty().bind(m.scoreProperty().asString());
    }

    private void bindMission02() {
        var m = scoresheet.getMission02();
        mission02TopsoilSections.textProperty().bindBidirectional(m.topsoilSectionsClearedProperty(), new NumberStringConverter());
        mission02Score.textProperty().bind(m.topsoilSectionsClearedProperty().multiply(10).asString());
        mission02Total.textProperty().bind(m.scoreProperty().asString());
    }

    private void bindMission03() {
        var m = scoresheet.getMission03();
        mission03YourMinecart.selectedProperty().bindBidirectional(m.yourMinecartOnOpposingFieldProperty());
        mission03OpposingMinecart.selectedProperty().bindBidirectional(m.opposingMinecartOnYourFieldProperty());
        mission03Total.textProperty().bind(m.scoreProperty().asString());
    }

    private void bindMission04() {
        var m = scoresheet.getMission04();
        mission04PreciousArtifact.selectedProperty().bindBidirectional(m.preciousArtifactNotTouchingMineProperty());
        mission04SupportStructures.selectedProperty().bindBidirectional(m.bothSupportStructuresStandingProperty());
        mission04Total.textProperty().bind(m.scoreProperty().asString());
    }

    private void bindMission05() {
        var m = scoresheet.getMission05();
        mission05StructureFloor.selectedProperty().bindBidirectional(m.structureFloorCompletelyUprightProperty());
        mission05Total.textProperty().bind(m.scoreProperty().asString());
    }

    private void bindMission06() {
        var m = scoresheet.getMission06();
        mission06OreBlocks.textProperty().bindBidirectional(m.oreBlocksNotTouchingForgeProperty(), new NumberStringConverter());
        mission06Score.textProperty().bind(m.oreBlocksNotTouchingForgeProperty().multiply(10).asString());
        mission06Total.textProperty().bind(m.scoreProperty().asString());
    }

    private void bindMission07() {
        var m = scoresheet.getMission07();
        mission07Millstone.selectedProperty().bindBidirectional(m.millstoneNotTouchingBaseProperty());
        mission07Total.textProperty().bind(m.scoreProperty().asString());
    }

    private void bindMission08() {
        var m = scoresheet.getMission08();
        mission08PreservedPieces.textProperty().bindBidirectional(m.preservedPiecesOutsideSiloProperty(), new NumberStringConverter());
        mission08Score.textProperty().bind(m.preservedPiecesOutsideSiloProperty().multiply(10).asString());
        mission08Total.textProperty().bind(m.scoreProperty().asString());
    }

    private void bindMission09() {
        var m = scoresheet.getMission09();
        mission09Roof.selectedProperty().bindBidirectional(m.roofCompletelyRaisedProperty());
        mission09MarketWares.selectedProperty().bindBidirectional(m.marketWaresRaisedProperty());
        mission09Total.textProperty().bind(m.scoreProperty().asString());
    }

    private void bindMission10() {
        var m = scoresheet.getMission10();
        mission10ScaleTipped.selectedProperty().bindBidirectional(m.scaleTippedAndTouchingMatProperty());
        mission10PanRemoved.selectedProperty().bindBidirectional(m.scalePanCompletelyRemovedProperty());
        mission10Total.textProperty().bind(m.scoreProperty().asString());
    }

    private void bindMission11() {
        var m = scoresheet.getMission11();
        mission11ArtifactsRaised.selectedProperty().bindBidirectional(m.artifactsRaisedAboveGroundLayerProperty());
        mission11CraneFlag.selectedProperty().bindBidirectional(m.craneFlagAtLeastPartlyLoweredProperty());
        mission11Total.textProperty().bind(m.scoreProperty().asString());
    }

    private void bindMission12() {
        var m = scoresheet.getMission12();
        mission12SandCleared.selectedProperty().bindBidirectional(m.sandCompletelyClearedProperty());
        mission12ShipRaised.selectedProperty().bindBidirectional(m.shipCompletelyRaisedProperty());
        mission12Total.textProperty().bind(m.scoreProperty().asString());
    }

    private void bindMission13() {
        var m = scoresheet.getMission13();
        mission13StatueRaised.selectedProperty().bindBidirectional(m.statueCompletelyRaisedProperty());
        mission13Total.textProperty().bind(m.scoreProperty().asString());
    }

    private void bindMission14() {
        var m = scoresheet.getMission14();
        mission14Artifacts.textProperty().bindBidirectional(m.artifactsInForumProperty(), new NumberStringConverter());
        mission14Score.textProperty().bind(m.artifactsInForumProperty().multiply(5).asString());
        mission14Total.textProperty().bind(m.scoreProperty().asString());
    }

    private void bindMission15() {
        var m = scoresheet.getMission15();
        mission15Sites.textProperty().bindBidirectional(m.sitesWithFlagsProperty(), new NumberStringConverter());
        mission15Score.textProperty().bind(m.sitesWithFlagsProperty().multiply(10).asString());
        mission15Total.textProperty().bind(m.scoreProperty().asString());
    }

    private void bindGraciousProfessionalism() {
        java.util.Map<Toggle, GraciousProfessionalism.Level> toggleToLevel = java.util.Map.of(
            gpDeveloping, GraciousProfessionalism.Level.DEVELOPING,
            gpAccomplished, GraciousProfessionalism.Level.ACCOMPLISHED,
            gpExceeds, GraciousProfessionalism.Level.EXCEEDS
        );

        java.util.Map<GraciousProfessionalism.Level, RadioButton> levelToToggle = java.util.Map.of(
            GraciousProfessionalism.Level.DEVELOPING, gpDeveloping,
            GraciousProfessionalism.Level.ACCOMPLISHED, gpAccomplished,
            GraciousProfessionalism.Level.EXCEEDS, gpExceeds
        );

        // UI -> Model
        gpToggleGroup.selectedToggleProperty().addListener((obs, old, newVal) ->
            java.util.Optional.ofNullable(toggleToLevel.get(newVal))
                .ifPresent(level -> scoresheet.getGraciousProfessionalism().levelProperty().set(level))
        );

        // Model -> UI
        scoresheet.getGraciousProfessionalism().levelProperty().addListener((obs, old, newVal) ->
            java.util.Optional.ofNullable(levelToToggle.get(newVal))
                .ifPresent(rb -> rb.setSelected(true))
        );
    }

    private void applyScannedResults(Scoresheet scannedScoresheet) {
        // Update the model. The UI will update automatically due to bindings.

        // Equipment Inspection
        copyProperty(scoresheet.getEquipmentInspection().passedProperty(),
                    scannedScoresheet.getEquipmentInspection().passedProperty());

        // Mission 01
        copyProperty(scoresheet.getMission01().soilDepositsClearedProperty(),
                    scannedScoresheet.getMission01().soilDepositsClearedProperty());
        copyProperty(scoresheet.getMission01().brushNotTouchingDigSiteProperty(),
                    scannedScoresheet.getMission01().brushNotTouchingDigSiteProperty());

        // Mission 02
        copyProperty(scoresheet.getMission02().topsoilSectionsClearedProperty(),
                    scannedScoresheet.getMission02().topsoilSectionsClearedProperty());

        // Mission 03
        copyProperty(scoresheet.getMission03().yourMinecartOnOpposingFieldProperty(),
                    scannedScoresheet.getMission03().yourMinecartOnOpposingFieldProperty());
        copyProperty(scoresheet.getMission03().opposingMinecartOnYourFieldProperty(),
                    scannedScoresheet.getMission03().opposingMinecartOnYourFieldProperty());

        // Mission 04
        copyProperty(scoresheet.getMission04().preciousArtifactNotTouchingMineProperty(),
                    scannedScoresheet.getMission04().preciousArtifactNotTouchingMineProperty());
        copyProperty(scoresheet.getMission04().bothSupportStructuresStandingProperty(),
                    scannedScoresheet.getMission04().bothSupportStructuresStandingProperty());

        // Mission 05
        copyProperty(scoresheet.getMission05().structureFloorCompletelyUprightProperty(),
                    scannedScoresheet.getMission05().structureFloorCompletelyUprightProperty());

        // Mission 06
        copyProperty(scoresheet.getMission06().oreBlocksNotTouchingForgeProperty(),
                    scannedScoresheet.getMission06().oreBlocksNotTouchingForgeProperty());

        // Mission 07
        copyProperty(scoresheet.getMission07().millstoneNotTouchingBaseProperty(),
                    scannedScoresheet.getMission07().millstoneNotTouchingBaseProperty());

        // Mission 08
        copyProperty(scoresheet.getMission08().preservedPiecesOutsideSiloProperty(),
                    scannedScoresheet.getMission08().preservedPiecesOutsideSiloProperty());

        // Mission 09
        copyProperty(scoresheet.getMission09().roofCompletelyRaisedProperty(),
                    scannedScoresheet.getMission09().roofCompletelyRaisedProperty());
        copyProperty(scoresheet.getMission09().marketWaresRaisedProperty(),
                    scannedScoresheet.getMission09().marketWaresRaisedProperty());

        // Mission 10
        copyProperty(scoresheet.getMission10().scaleTippedAndTouchingMatProperty(),
                    scannedScoresheet.getMission10().scaleTippedAndTouchingMatProperty());
        copyProperty(scoresheet.getMission10().scalePanCompletelyRemovedProperty(),
                    scannedScoresheet.getMission10().scalePanCompletelyRemovedProperty());

        // Mission 11
        copyProperty(scoresheet.getMission11().artifactsRaisedAboveGroundLayerProperty(),
                    scannedScoresheet.getMission11().artifactsRaisedAboveGroundLayerProperty());
        copyProperty(scoresheet.getMission11().craneFlagAtLeastPartlyLoweredProperty(),
                    scannedScoresheet.getMission11().craneFlagAtLeastPartlyLoweredProperty());

        // Mission 12
        copyProperty(scoresheet.getMission12().sandCompletelyClearedProperty(),
                    scannedScoresheet.getMission12().sandCompletelyClearedProperty());
        copyProperty(scoresheet.getMission12().shipCompletelyRaisedProperty(),
                    scannedScoresheet.getMission12().shipCompletelyRaisedProperty());

        // Mission 13
        copyProperty(scoresheet.getMission13().statueCompletelyRaisedProperty(),
                    scannedScoresheet.getMission13().statueCompletelyRaisedProperty());

        // Mission 14
        copyProperty(scoresheet.getMission14().artifactsInForumProperty(),
                    scannedScoresheet.getMission14().artifactsInForumProperty());

        // Mission 15
        copyProperty(scoresheet.getMission15().sitesWithFlagsProperty(),
                    scannedScoresheet.getMission15().sitesWithFlagsProperty());

        // Precision Tokens
        copyProperty(scoresheet.getPrecisionTokens().tokensRemainingProperty(),
                    scannedScoresheet.getPrecisionTokens().tokensRemainingProperty());

        // Gracious Professionalism
        copyProperty(scoresheet.getGraciousProfessionalism().levelProperty(),
                    scannedScoresheet.getGraciousProfessionalism().levelProperty());
    }

    private <T> void copyProperty(javafx.beans.property.Property<T> target, javafx.beans.property.Property<T> source) {
        target.setValue(source.getValue());
    }

    /**
     * Opens the webcam scanner window for capturing and processing FRC scoresheet images.
     * Creates a new stage with the scan view and sets up the processor with callback handlers.
     * Handles page detection, processing completion, and error states.
     * The window closes automatically when both pages have been scanned.
     */
    @FXML
    protected void openWebcamScanner() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    ScoresheetController.class.getResource("scan-view.fxml"));
            Parent root = fxmlLoader.load();
            ScanController scanController = fxmlLoader.getController();

            Stage scanStage = new Stage();
            ScoresheetProcessor processor = new ScoresheetProcessor(
                    new ScoresheetProcessor.ProcessorCallback() {
                        @Override
                        public void onProcessingComplete(Scoresheet scannedScoresheet) {
                            System.out.println("Scanning complete!");
                            applyScannedResults(scannedScoresheet);
                            scanStage.close();
                        }

                        @Override
                        public void onProcessingError(String errorMessage) {
                            System.err.println("Scanning error: " + errorMessage);
                        }

                        @Override
                        public void onPageScanned(int pageNumber) {
                            System.out.println("Page " + pageNumber + " scanned successfully!");
                            scanController.showPageScannedNotification(pageNumber);
                        }
                    }
            );

            scanStage.setTitle("Scan Sheet");
            Scene scene = new Scene(root, 1920, 1080);
            scanStage.setScene(scene);

            scanController.setProcessorAndStage(processor, scanStage);

            scanStage.setOnCloseRequest(event -> {
                scanController.shutdown();
                processor.release();
            });

            scanStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}