package me.evankim.cs4270final.omr;

import org.bytedeco.opencv.opencv_core.Point;

public class Bubble {
    private final double percentX;
    private final double percentY;
    private final String value;

    public Bubble(double percentX, double percentY, String value) {
        this.percentX = percentX;
        this.percentY = percentY;
        this.value = value;
    }

    public Point toPixelPosition() {
        int x = (int) (percentX * BubbleFieldTemplate.WARPED_WIDTH);
        int y = (int) (percentY * BubbleFieldTemplate.WARPED_HEIGHT);
        return new Point(x, y);
    }

    public double getPercentX() { return percentX; }
    public double getPercentY() { return percentY; }
    public String getValue() { return value; }
}
