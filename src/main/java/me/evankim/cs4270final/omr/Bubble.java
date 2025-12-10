package me.evankim.cs4270final.omr;

import org.bytedeco.opencv.opencv_core.Point;

/**
 * Represents a bubble on a scoresheet page with a position and value.
 * Positions are stored as percentages (0.0-1.0) that scale with the warped page size.
 */
public class Bubble {
    private final double percentX;
    private final double percentY;
    private final String value;

    /**
     * Creates a new bubble at the specified relative position with the given value.
     *
     * @param percentX the X position as a percentage of page width (0.0-1.0)
     * @param percentY the Y position as a percentage of page height (0.0-1.0)
     * @param value the value this bubble represents (e.g., "Y", "N", "0", "1")
     */
    public Bubble(double percentX, double percentY, String value) {
        this.percentX = percentX;
        this.percentY = percentY;
        this.value = value;
    }

    /**
     * Converts the relative bubble position to pixel coordinates.
     *
     * @return the pixel position as an OpenCV Point
     */
    public Point toPixelPosition() {
        int x = (int) (percentX * BubbleFieldTemplate.WARPED_WIDTH);
        int y = (int) (percentY * BubbleFieldTemplate.WARPED_HEIGHT);
        return new Point(x, y);
    }

    /**
     * Gets the value represented by this bubble.
     *
     * @return the bubble's value
     */
    public String getValue() { return value; }
}
