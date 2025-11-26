package me.evankim.cs4270final.model.missions;

import me.evankim.cs4270final.model.Mission;

public class Mission15SiteMarking implements Mission {
    private int sitesWithFlags; // 10 points each

    public Mission15SiteMarking() {
        this.sitesWithFlags = 0;
    }

    public int getSitesWithFlags() {
        return sitesWithFlags;
    }

    public void setSitesWithFlags(int sitesWithFlags) {
        this.sitesWithFlags = Math.max(0, sitesWithFlags);
    }

    @Override
    public int calculateScore() {
        return sitesWithFlags * 10;
    }

    @Override
    public int getMissionNumber() {
        return 15;
    }

    @Override
    public String getMissionName() {
        return "Site Marking";
    }
}
