package me.evankim.cs4270final.omr;

import org.bytedeco.opencv.opencv_core.*;
import java.util.List;

import static org.bytedeco.opencv.global.opencv_core.mean;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGR2GRAY;

/**
 * Detects which bubble is filled in a set of bubbles using image analysis.
 * Uses dynamic thresholding based on page intensity to identify darkened bubbles.
 */
public class BubbleDetector {

    /**
     * Size of the region of interest (ROI) around each bubble center in pixels.
     */
    public static final int ROI_SIZE = 30;

    /**
     * Threshold ratio for dynamic thresholding relative to page average intensity.
     */
    public static final double THRESHOLD_RATIO = 0.75;

    /**
     * Detects which bubble is filled by finding the one with lowest intensity.
     *
     * @param capturedImage the scoresheet page image
     * @param bubbles list of bubble locations to check
     * @return the value of the filled bubble, or null if none are filled
     */
    public String detectFilledBubble(Mat capturedImage, List<Bubble> bubbles) {
        String detectedValue = null;
        double darkestIntensity = Double.MAX_VALUE;

        Mat grayImage = new Mat();
        cvtColor(capturedImage, grayImage, COLOR_BGR2GRAY);

        Scalar globalMean = mean(grayImage);
        double pageAverageIntensity = globalMean.get(0);

        double dynamicThreshold = pageAverageIntensity * THRESHOLD_RATIO;

        for (Bubble bubble : bubbles) {
            double intensity = calculateBubbleIntensity(grayImage, bubble);

            if (intensity < dynamicThreshold) {
                if (intensity < darkestIntensity) {
                    darkestIntensity = intensity;
                    detectedValue = bubble.getValue();
                }
            }
        }

        grayImage.release();

        return detectedValue;
    }

    private double calculateBubbleIntensity(Mat grayImage, Bubble bubble) {
        Point position = bubble.toPixelPosition();
        int x = position.x();
        int y = position.y();

        int halfSize = ROI_SIZE / 2;

        int roiX = Math.max(0, x - halfSize);
        int roiY = Math.max(0, y - halfSize);
        int roiWidth = Math.min(ROI_SIZE, grayImage.cols() - roiX);
        int roiHeight = Math.min(ROI_SIZE, grayImage.rows() - roiY);

        Mat roi = new Mat(grayImage, new Rect(roiX, roiY, roiWidth, roiHeight));

        Scalar meanValue = mean(roi);
        double intensity = meanValue.get(0);

        roi.release();

        return intensity;
    }
}