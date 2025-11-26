package me.evankim.cs4270final;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import me.evankim.cs4270final.model.*;
import me.evankim.cs4270final.model.missions.*;

import java.io.IOException;

public class ScoresheetController {

    // Header fields
    @FXML private TextField teamNumberField;
    @FXML private TextField matchField;
    @FXML private TextField refereeField;
    @FXML private TextField tableField;
    @FXML private TextField teamInitialsField;
    @FXML private Button scanButton;

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
    @FXML private ComboBox<String> precisionTokensCombo;
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

    @FXML
    public void initialize() {
        scoresheet = new Scoresheet();

        // Set up Gracious Professionalism toggle group
        gpToggleGroup = new ToggleGroup();
        gpDeveloping.setToggleGroup(gpToggleGroup);
        gpAccomplished.setToggleGroup(gpToggleGroup);
        gpExceeds.setToggleGroup(gpToggleGroup);

        // Initialize precision tokens combo
        precisionTokensCombo.getItems().addAll("0", "1", "2", "3", "4", "5", "6");
        precisionTokensCombo.setValue("6");

        // Set up all listeners
        setupHeaderListeners();
        setupEquipmentInspectionListeners();
        setupMission01Listeners();
        setupMission02Listeners();
        setupMission03Listeners();
        setupMission04Listeners();
        setupMission05Listeners();
        setupMission06Listeners();
        setupMission07Listeners();
        setupMission08Listeners();
        setupMission09Listeners();
        setupMission10Listeners();
        setupMission11Listeners();
        setupMission12Listeners();
        setupMission13Listeners();
        setupMission14Listeners();
        setupMission15Listeners();
        setupPrecisionTokensListeners();
        setupGraciousProfessionalismListeners();

        // Initial calculation
        updateAllScores();
    }

    private void setupHeaderListeners() {
        teamNumberField.textProperty().addListener((obs, old, newVal) -> scoresheet.setTeamNumber(newVal));
        matchField.textProperty().addListener((obs, old, newVal) -> scoresheet.setMatch(newVal));
        refereeField.textProperty().addListener((obs, old, newVal) -> scoresheet.setReferee(newVal));
        tableField.textProperty().addListener((obs, old, newVal) -> scoresheet.setTable(newVal));
        teamInitialsField.textProperty().addListener((obs, old, newVal) -> scoresheet.setTeamInitials(newVal));
    }

    private void setupEquipmentInspectionListeners() {
        equipmentInspectionCheck.selectedProperty().addListener((obs, old, newVal) -> {
            scoresheet.getEquipmentInspection().setPassed(newVal);
            updateFinalScore();
        });
    }

    private void setupMission01Listeners() {
        mission01SoilDeposits.textProperty().addListener((obs, old, newVal) -> {
            try {
                int count = newVal.isEmpty() ? 0 : Integer.parseInt(newVal);
                scoresheet.getMission01().setSoilDepositsCleared(count);
                mission01SoilScore.setText(String.valueOf(count * 10));
                updateMission01Score();
            } catch (NumberFormatException e) {
                mission01SoilScore.setText("0");
            }
        });

        mission01BrushCheck.selectedProperty().addListener((obs, old, newVal) -> {
            scoresheet.getMission01().setBrushNotTouchingDigSite(newVal);
            updateMission01Score();
        });
    }

    private void updateMission01Score() {
        mission01Total.setText(String.valueOf(scoresheet.getMission01().calculateScore()));
        updateFinalScore();
    }

    private void setupMission02Listeners() {
        mission02TopsoilSections.textProperty().addListener((obs, old, newVal) -> {
            try {
                int count = newVal.isEmpty() ? 0 : Integer.parseInt(newVal);
                scoresheet.getMission02().setTopsoilSectionsCleared(count);
                mission02Score.setText(String.valueOf(count * 10));
                mission02Total.setText(String.valueOf(scoresheet.getMission02().calculateScore()));
                updateFinalScore();
            } catch (NumberFormatException e) {
                mission02Score.setText("0");
                mission02Total.setText("0");
            }
        });
    }

    private void setupMission03Listeners() {
        mission03YourMinecart.selectedProperty().addListener((obs, old, newVal) -> {
            scoresheet.getMission03().setYourMinecartOnOpposingField(newVal);
            updateMission03Score();
        });

        mission03OpposingMinecart.selectedProperty().addListener((obs, old, newVal) -> {
            scoresheet.getMission03().setOpposingMinecartOnYourField(newVal);
            updateMission03Score();
        });
    }

    private void updateMission03Score() {
        mission03Total.setText(String.valueOf(scoresheet.getMission03().calculateScore()));
        updateFinalScore();
    }

    private void setupMission04Listeners() {
        mission04PreciousArtifact.selectedProperty().addListener((obs, old, newVal) -> {
            scoresheet.getMission04().setPreciousArtifactNotTouchingMine(newVal);
            updateMission04Score();
        });

        mission04SupportStructures.selectedProperty().addListener((obs, old, newVal) -> {
            scoresheet.getMission04().setBothSupportStructuresStanding(newVal);
            updateMission04Score();
        });
    }

    private void updateMission04Score() {
        mission04Total.setText(String.valueOf(scoresheet.getMission04().calculateScore()));
        updateFinalScore();
    }

    private void setupMission05Listeners() {
        mission05StructureFloor.selectedProperty().addListener((obs, old, newVal) -> {
            scoresheet.getMission05().setStructureFloorCompletelyUpright(newVal);
            mission05Total.setText(String.valueOf(scoresheet.getMission05().calculateScore()));
            updateFinalScore();
        });
    }

    private void setupMission06Listeners() {
        mission06OreBlocks.textProperty().addListener((obs, old, newVal) -> {
            try {
                int count = newVal.isEmpty() ? 0 : Integer.parseInt(newVal);
                scoresheet.getMission06().setOreBlocksNotTouchingForge(count);
                mission06Score.setText(String.valueOf(count * 10));
                mission06Total.setText(String.valueOf(scoresheet.getMission06().calculateScore()));
                updateFinalScore();
            } catch (NumberFormatException e) {
                mission06Score.setText("0");
                mission06Total.setText("0");
            }
        });
    }

    private void setupMission07Listeners() {
        mission07Millstone.selectedProperty().addListener((obs, old, newVal) -> {
            scoresheet.getMission07().setMillstoneNotTouchingBase(newVal);
            mission07Total.setText(String.valueOf(scoresheet.getMission07().calculateScore()));
            updateFinalScore();
        });
    }

    private void setupMission08Listeners() {
        mission08PreservedPieces.textProperty().addListener((obs, old, newVal) -> {
            try {
                int count = newVal.isEmpty() ? 0 : Integer.parseInt(newVal);
                scoresheet.getMission08().setPreservedPiecesOutsideSilo(count);
                mission08Score.setText(String.valueOf(count * 10));
                mission08Total.setText(String.valueOf(scoresheet.getMission08().calculateScore()));
                updateFinalScore();
            } catch (NumberFormatException e) {
                mission08Score.setText("0");
                mission08Total.setText("0");
            }
        });
    }

    private void setupMission09Listeners() {
        mission09Roof.selectedProperty().addListener((obs, old, newVal) -> {
            scoresheet.getMission09().setRoofCompletelyRaised(newVal);
            updateMission09Score();
        });

        mission09MarketWares.selectedProperty().addListener((obs, old, newVal) -> {
            scoresheet.getMission09().setMarketWaresRaised(newVal);
            updateMission09Score();
        });
    }

    private void updateMission09Score() {
        mission09Total.setText(String.valueOf(scoresheet.getMission09().calculateScore()));
        updateFinalScore();
    }

    private void setupMission10Listeners() {
        mission10ScaleTipped.selectedProperty().addListener((obs, old, newVal) -> {
            scoresheet.getMission10().setScaleTippedAndTouchingMat(newVal);
            updateMission10Score();
        });

        mission10PanRemoved.selectedProperty().addListener((obs, old, newVal) -> {
            scoresheet.getMission10().setScalePanCompletelyRemoved(newVal);
            updateMission10Score();
        });
    }

    private void updateMission10Score() {
        mission10Total.setText(String.valueOf(scoresheet.getMission10().calculateScore()));
        updateFinalScore();
    }

    private void setupMission11Listeners() {
        mission11ArtifactsRaised.selectedProperty().addListener((obs, old, newVal) -> {
            scoresheet.getMission11().setArtifactsRaisedAboveGroundLayer(newVal);
            updateMission11Score();
        });

        mission11CraneFlag.selectedProperty().addListener((obs, old, newVal) -> {
            scoresheet.getMission11().setCraneFlagAtLeastPartlyLowered(newVal);
            updateMission11Score();
        });
    }

    private void updateMission11Score() {
        mission11Total.setText(String.valueOf(scoresheet.getMission11().calculateScore()));
        updateFinalScore();
    }

    private void setupMission12Listeners() {
        mission12SandCleared.selectedProperty().addListener((obs, old, newVal) -> {
            scoresheet.getMission12().setSandCompletelyCleared(newVal);
            updateMission12Score();
        });

        mission12ShipRaised.selectedProperty().addListener((obs, old, newVal) -> {
            scoresheet.getMission12().setShipCompletelyRaised(newVal);
            updateMission12Score();
        });
    }

    private void updateMission12Score() {
        mission12Total.setText(String.valueOf(scoresheet.getMission12().calculateScore()));
        updateFinalScore();
    }

    private void setupMission13Listeners() {
        mission13StatueRaised.selectedProperty().addListener((obs, old, newVal) -> {
            scoresheet.getMission13().setStatueCompletelyRaised(newVal);
            mission13Total.setText(String.valueOf(scoresheet.getMission13().calculateScore()));
            updateFinalScore();
        });
    }

    private void setupMission14Listeners() {
        mission14Artifacts.textProperty().addListener((obs, old, newVal) -> {
            try {
                int count = newVal.isEmpty() ? 0 : Integer.parseInt(newVal);
                scoresheet.getMission14().setArtifactsInForum(count);
                mission14Score.setText(String.valueOf(count * 5));
                mission14Total.setText(String.valueOf(scoresheet.getMission14().calculateScore()));
                updateFinalScore();
            } catch (NumberFormatException e) {
                mission14Score.setText("0");
                mission14Total.setText("0");
            }
        });
    }

    private void setupMission15Listeners() {
        mission15Sites.textProperty().addListener((obs, old, newVal) -> {
            try {
                int count = newVal.isEmpty() ? 0 : Integer.parseInt(newVal);
                scoresheet.getMission15().setSitesWithFlags(count);
                mission15Score.setText(String.valueOf(count * 10));
                mission15Total.setText(String.valueOf(scoresheet.getMission15().calculateScore()));
                updateFinalScore();
            } catch (NumberFormatException e) {
                mission15Score.setText("0");
                mission15Total.setText("0");
            }
        });
    }

    private void setupPrecisionTokensListeners() {
        precisionTokensCombo.valueProperty().addListener((obs, old, newVal) -> {
            if (newVal != null && !newVal.isEmpty()) {
                int tokens = Integer.parseInt(newVal);
                scoresheet.getPrecisionTokens().setTokensRemaining(tokens);
                precisionTokensScore.setText(String.valueOf(scoresheet.getPrecisionTokens().calculateScore()));
                updateFinalScore();
            }
        });
    }

    private void setupGraciousProfessionalismListeners() {
        gpToggleGroup.selectedToggleProperty().addListener((obs, old, newVal) -> {
            if (newVal == gpDeveloping) {
                scoresheet.getGraciousProfessionalism().setLevel(GraciousProfessionalism.Level.DEVELOPING);
            } else if (newVal == gpAccomplished) {
                scoresheet.getGraciousProfessionalism().setLevel(GraciousProfessionalism.Level.ACCOMPLISHED);
            } else if (newVal == gpExceeds) {
                scoresheet.getGraciousProfessionalism().setLevel(GraciousProfessionalism.Level.EXCEEDS);
            }
            updateFinalScore();
        });
    }

    private void updateAllScores() {
        // Initialize all score displays
        mission01Total.setText("0");
        mission02Total.setText("0");
        mission03Total.setText("0");
        mission04Total.setText("0");
        mission05Total.setText("0");
        mission06Total.setText("0");
        mission07Total.setText("0");
        mission08Total.setText("0");
        mission09Total.setText("0");
        mission10Total.setText("0");
        mission11Total.setText("0");
        mission12Total.setText("0");
        mission13Total.setText("0");
        mission14Total.setText("0");
        mission15Total.setText("0");
        precisionTokensScore.setText(String.valueOf(scoresheet.getPrecisionTokens().calculateScore()));
        updateFinalScore();
    }

    private void updateFinalScore() {
        finalScoreLabel.setText(String.valueOf(scoresheet.calculateTotalScore()));
    }

    @FXML
    protected void openWebcamScanner() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ScoresheetController.class.getResource("scan-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 900, 800);
            stage.setTitle("Scan Sheet");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
