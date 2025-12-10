package me.evankim.cs4270final.model.missions;

import me.evankim.cs4270final.model.Mission;

public class Mission06Forge extends Mission {
    private int oreBlocksNotTouchingForge; // 10 points each

    public Mission06Forge() {
        this.oreBlocksNotTouchingForge = 0;
    }

    public int getOreBlocksNotTouchingForge() {
        return oreBlocksNotTouchingForge;
    }

    public void setOreBlocksNotTouchingForge(int oreBlocksNotTouchingForge) {
        this.oreBlocksNotTouchingForge = Math.max(0, oreBlocksNotTouchingForge);
    }

    @Override
    public int calculateScore() {
        return oreBlocksNotTouchingForge * 10;
    }

    @Override
    public int getMissionNumber() {
        return 6;
    }

    @Override
    public String getMissionName() {
        return "Forge";
    }
}
