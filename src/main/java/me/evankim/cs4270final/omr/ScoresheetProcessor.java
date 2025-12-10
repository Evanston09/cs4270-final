package me.evankim.cs4270final.omr;

import me.evankim.cs4270final.model.Scoresheet;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Scalar;
import javafx.application.Platform;

import java.util.List;
import java.util.Map;

import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import static org.bytedeco.opencv.global.opencv_imgproc.circle;
import static org.bytedeco.opencv.global.opencv_imgproc.putText;
import static org.bytedeco.opencv.global.opencv_imgproc.FONT_HERSHEY_SIMPLEX;

public class ScoresheetProcessor {

    private Mat capturedPage1;
    private Mat capturedPage2;
    private Scoresheet scoresheet;
    private ProcessorCallback callback;
    private BubbleDetector bubbleDetector;
    private FieldMapper fieldMapper;

    public interface ProcessorCallback {
        void onProcessingComplete(Scoresheet scoresheet);
        void onProcessingError(String errorMessage);
        void onPageScanned(int pageNumber);
    }

    public ScoresheetProcessor(ProcessorCallback callback) {
        this.callback = callback;
        this.scoresheet = new Scoresheet();
        this.bubbleDetector = new BubbleDetector();
        this.fieldMapper = new FieldMapper();
    }

    public void processPage(Mat warpedMat, int[] detectedMarkerIds) {
        boolean isPage1 = containsPage1Markers(detectedMarkerIds);
        boolean isPage2 = containsPage2Markers(detectedMarkerIds);

        if (isPage1) {
            capturedPage1 = warpedMat.clone();
            detectBubbles(capturedPage1, 1);
            saveBubbleVisualization(capturedPage1, 1);
            notifyPageScanned(1);
        } else if (isPage2) {
            capturedPage2 = warpedMat.clone();
            detectBubbles(capturedPage2, 2);
            saveBubbleVisualization(capturedPage2, 2);
            notifyPageScanned(2);
        } else {
            notifyError("Unable to determine page type from markers");
            return;
        }

        if (capturedPage1 != null && capturedPage2 != null) {
            notifyComplete();
        }
    }

    private boolean containsPage1Markers(int[] markerIds) {
        int page1Count = 0;
        for (int id : markerIds) {
            if (id >= 0 && id <= 3) {
                page1Count++;
            }
        }
        return page1Count >= 4;
    }

    private boolean containsPage2Markers(int[] markerIds) {
        int page2Count = 0;
        for (int id : markerIds) {
            if (id >= 4 && id <= 7) {
                page2Count++;
            }
        }
        return page2Count >= 4;
    }

    private void notifyComplete() {
        if (callback != null) {
            Platform.runLater(() -> callback.onProcessingComplete(scoresheet));
        }
    }

    private void notifyError(String message) {
        if (callback != null) {
            Platform.runLater(() -> callback.onProcessingError(message));
        }
    }

    private void notifyPageScanned(int pageNumber) {
        if (callback != null) {
            Platform.runLater(() -> callback.onPageScanned(pageNumber));
        }
    }

    private void detectBubbles(Mat capturedImage, int pageNumber) {
        BubbleFieldTemplate template = new BubbleFieldTemplate(pageNumber);
        Map<String, List<Bubble>> fields = template.getFields();

        for (Map.Entry<String, List<Bubble>> entry : fields.entrySet()) {
            String fieldId = entry.getKey();
            List<Bubble> bubbles = entry.getValue();

            String detectedValue = bubbleDetector.detectFilledBubble(capturedImage, bubbles);
            DetectionResult result = new DetectionResult(fieldId, detectedValue);
            fieldMapper.applyDetection(result, scoresheet);
        }
    }

    private void saveBubbleVisualization(Mat capturedImage, int pageNumber) {
        Mat debugImage = capturedImage.clone();
        BubbleFieldTemplate template = new BubbleFieldTemplate(pageNumber);

        Map<String, List<Bubble>> fields = template.getFields();

        for (Map.Entry<String, List<Bubble>> entry : fields.entrySet()) {
            String fieldId = entry.getKey();
            List<Bubble> bubbles = entry.getValue();

            String detectedValue = bubbleDetector.detectFilledBubble(capturedImage, bubbles);

            for (Bubble bubble : bubbles) {
                Point position = bubble.toPixelPosition();

                Scalar color = bubble.getValue().equals(detectedValue)
                        ? new Scalar(0, 0, 255, 255)
                        : new Scalar(0, 255, 0, 255);

                int thickness = bubble.getValue().equals(detectedValue) ? 3 : 2;

                circle(debugImage, position, 10, color, thickness, 0, 0);

                Point textPos = new Point(position.x() + 15, position.y() + 5);
                putText(debugImage, bubble.getValue(), textPos,
                        FONT_HERSHEY_SIMPLEX, 0.4, new Scalar(255, 0, 0, 255), 1, 0, false);
            }
        }

        String filename = "captured_page" + pageNumber + "_with_bubbles.png";
        imwrite(filename, debugImage);
        System.out.println("Saved visualization: " + filename);

        debugImage.release();
    }

    public void release() {
        if (capturedPage1 != null) {
            capturedPage1.release();
        }
        if (capturedPage2 != null) {
            capturedPage2.release();
        }
    }
}
