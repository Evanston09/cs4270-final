package me.evankim.cs4270final.model.missions;

import me.evankim.cs4270final.model.Mission;

public class Mission08Silo implements Mission {
    private int preservedPiecesOutsideSilo; // 10 points each

    public Mission08Silo() {
        this.preservedPiecesOutsideSilo = 0;
    }

    public int getPreservedPiecesOutsideSilo() {
        return preservedPiecesOutsideSilo;
    }

    public void setPreservedPiecesOutsideSilo(int preservedPiecesOutsideSilo) {
        this.preservedPiecesOutsideSilo = Math.max(0, preservedPiecesOutsideSilo);
    }

    @Override
    public int calculateScore() {
        return preservedPiecesOutsideSilo * 10;
    }

    @Override
    public int getMissionNumber() {
        return 8;
    }

    @Override
    public String getMissionName() {
        return "Silo";
    }
}
