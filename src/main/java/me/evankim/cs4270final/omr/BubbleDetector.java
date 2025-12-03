package me.evankim.cs4270final.omr;

import org.bytedeco.opencv.opencv_core.*;
import java.util.List;

import static org.bytedeco.opencv.global.opencv_core.mean;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGR2GRAY;

public class BubbleDetector {

    public static final int ROI_SIZE = 30;
    public static final double INTENSITY_THRESHOLD = 150.0;

    public String detectFilledBubble(Mat capturedImage, List<Bubble> bubbles) {
        String detectedValue = null;
        double darkestIntensity = Double.MAX_VALUE;

        for (Bubble bubble : bubbles) {
            double intensity = calculateBubbleIntensity(capturedImage, bubble);

            if (intensity < INTENSITY_THRESHOLD) {
                if (intensity < darkestIntensity) {
                    darkestIntensity = intensity;
                    detectedValue = bubble.getValue();
                }
            }
        }

        return detectedValue;
    }

    private double calculateBubbleIntensity(Mat image, Bubble bubble) {
        Point position = bubble.toPixelPosition();
        int x = position.x();
        int y = position.y();

        int halfSize = ROI_SIZE / 2;
        int roiX = Math.max(0, x - halfSize);
        int roiY = Math.max(0, y - halfSize);
        int roiWidth = Math.min(ROI_SIZE, image.cols() - roiX);
        int roiHeight = Math.min(ROI_SIZE, image.rows() - roiY);

        Mat roi = new Mat(image, new Rect(roiX, roiY, roiWidth, roiHeight));

        Mat gray = new Mat();
        cvtColor(roi, gray, COLOR_BGR2GRAY);

        Scalar meanValue = mean(gray);
        double intensity = meanValue.get(0);

        gray.release();
        roi.release();

        return intensity;
    }
}
