package me.evankim.cs4270final.model.missions;

import me.evankim.cs4270final.model.Mission;

public class Mission05WhoLivedHere extends Mission {
    private boolean structureFloorCompletelyUpright; // 30 points

    public Mission05WhoLivedHere() {
        this.structureFloorCompletelyUpright = false;
    }

    public boolean isStructureFloorCompletelyUpright() {
        return structureFloorCompletelyUpright;
    }

    public void setStructureFloorCompletelyUpright(boolean structureFloorCompletelyUpright) {
        this.structureFloorCompletelyUpright = structureFloorCompletelyUpright;
    }

    @Override
    public int calculateScore() {
        return structureFloorCompletelyUpright ? 30 : 0;
    }

    @Override
    public int getMissionNumber() {
        return 5;
    }

    @Override
    public String getMissionName() {
        return "Who Lived Here?";
    }
}
