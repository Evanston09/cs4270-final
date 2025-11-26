package me.evankim.cs4270final.model.missions;

import me.evankim.cs4270final.model.Mission;

public class Mission07HeavyLifting implements Mission {
    private boolean millstoneNotTouchingBase; // 30 points

    public Mission07HeavyLifting() {
        this.millstoneNotTouchingBase = false;
    }

    public boolean isMillstoneNotTouchingBase() {
        return millstoneNotTouchingBase;
    }

    public void setMillstoneNotTouchingBase(boolean millstoneNotTouchingBase) {
        this.millstoneNotTouchingBase = millstoneNotTouchingBase;
    }

    @Override
    public int calculateScore() {
        return millstoneNotTouchingBase ? 30 : 0;
    }

    @Override
    public int getMissionNumber() {
        return 7;
    }

    @Override
    public String getMissionName() {
        return "Heavy Lifting";
    }
}
