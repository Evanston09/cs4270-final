package me.evankim.cs4270final.model;

import me.evankim.cs4270final.model.missions.*;

public class Scoresheet {
    // Header information
    private String teamNumber;
    private String match;
    private String referee;
    private String table;
    private String teamInitials;

    // Scoring components using polymorphism
    private EquipmentInspection equipmentInspection;
    private Mission[] missions; // Array of all 15 missions
    private PrecisionTokens precisionTokens;
    private GraciousProfessionalism graciousProfessionalism;

    public Scoresheet() {
        // Initialize header fields
        this.teamNumber = "";
        this.match = "";
        this.referee = "";
        this.table = "";
        this.teamInitials = "";

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
    }

    // Header getters and setters
    public String getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(String teamNumber) {
        this.teamNumber = teamNumber;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTeamInitials() {
        return teamInitials;
    }

    public void setTeamInitials(String teamInitials) {
        this.teamInitials = teamInitials;
    }

    // Component getters
    public EquipmentInspection getEquipmentInspection() {
        return equipmentInspection;
    }

    public Mission[] getMissions() {
        return missions;
    }

    public Mission getMission(int missionNumber) {
        return missions[missionNumber - 1];
    }

    public Mission01SurfaceBrushing getMission01() {
        return (Mission01SurfaceBrushing) missions[0];
    }

    public Mission02MapReveal getMission02() {
        return (Mission02MapReveal) missions[1];
    }

    public Mission03MineshaftExplorer getMission03() {
        return (Mission03MineshaftExplorer) missions[2];
    }

    public Mission04CarefulRecovery getMission04() {
        return (Mission04CarefulRecovery) missions[3];
    }

    public Mission05WhoLivedHere getMission05() {
        return (Mission05WhoLivedHere) missions[4];
    }

    public Mission06Forge getMission06() {
        return (Mission06Forge) missions[5];
    }

    public Mission07HeavyLifting getMission07() {
        return (Mission07HeavyLifting) missions[6];
    }

    public Mission08Silo getMission08() {
        return (Mission08Silo) missions[7];
    }

    public Mission09WhatsOnSale getMission09() {
        return (Mission09WhatsOnSale) missions[8];
    }

    public Mission10TipTheScales getMission10() {
        return (Mission10TipTheScales) missions[9];
    }

    public Mission11AnglerArtifacts getMission11() {
        return (Mission11AnglerArtifacts) missions[10];
    }

    public Mission12SalvageOperation getMission12() {
        return (Mission12SalvageOperation) missions[11];
    }

    public Mission13StatueRebuild getMission13() {
        return (Mission13StatueRebuild) missions[12];
    }

    public Mission14Forum getMission14() {
        return (Mission14Forum) missions[13];
    }

    public Mission15SiteMarking getMission15() {
        return (Mission15SiteMarking) missions[14];
    }

    public PrecisionTokens getPrecisionTokens() {
        return precisionTokens;
    }

    public GraciousProfessionalism getGraciousProfessionalism() {
        return graciousProfessionalism;
    }

    // Calculate total score using polymorphism
    public int calculateTotalScore() {
        int total = 0;

        total += equipmentInspection.calculateScore();

        for (Mission mission : missions) {
            total += mission.calculateScore();
        }

        total += precisionTokens.calculateScore();
        total += graciousProfessionalism.calculateScore();

        return total;
    }
}