package me.evankim.cs4270final.model.missions;

import me.evankim.cs4270final.model.Mission;

public class Mission14Forum implements Mission {
    // Artifacts: Brush, Topsoil, Precious Artifact, Opposing Team's Minecart,
    // Ore with Fossilized Artifact, Millstone, & Scale Pan
    private int artifactsInForum; // 5 points each

    public Mission14Forum() {
        this.artifactsInForum = 0;
    }

    public int getArtifactsInForum() {
        return artifactsInForum;
    }

    public void setArtifactsInForum(int artifactsInForum) {
        this.artifactsInForum = Math.max(0, Math.min(artifactsInForum, 7)); // Max 7 artifacts
    }

    @Override
    public int calculateScore() {
        return artifactsInForum * 5;
    }

    @Override
    public int getMissionNumber() {
        return 14;
    }

    @Override
    public String getMissionName() {
        return "Forum";
    }
}
