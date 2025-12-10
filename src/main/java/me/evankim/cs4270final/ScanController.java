package me.evankim.cs4270final;


import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.stage.Stage;
import me.evankim.cs4270final.omr.ScoresheetProcessor;
import org.bytedeco.javacpp.indexer.FloatIndexer;
import org.bytedeco.javacpp.indexer.IntIndexer;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_objdetect.ArucoDetector;
import org.bytedeco.opencv.opencv_objdetect.Dictionary;
import org.bytedeco.opencv.opencv_objdetect.DetectorParameters;
import org.bytedeco.opencv.opencv_objdetect.RefineParameters;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.bytedeco.opencv.global.opencv_calib3d.*;
import static org.bytedeco.opencv.global.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.bytedeco.opencv.global.opencv_objdetect.getPredefinedDictionary;
import static org.bytedeco.opencv.global.opencv_objdetect.drawDetectedMarkers;
import static org.bytedeco.opencv.global.opencv_objdetect.DICT_4X4_50;

public class ScanController {

    @FXML
    ImageView videoView;

    @FXML
    Label statusLabel;

   private Mat javaCVMat = new Mat();

    private Dictionary dict = getPredefinedDictionary(DICT_4X4_50);

    private DetectorParameters detectorParams = new DetectorParameters();
    private RefineParameters refineParams = new RefineParameters();
    private ArucoDetector arucoDetector = new ArucoDetector(dict, detectorParams, refineParams);

    /**
     * create buffer only once saves much time!
     */
    private final WritablePixelFormat<ByteBuffer> formatByte = PixelFormat.getByteBgraPreInstance();

    private final OpenCVFrameConverter<Mat> javaCVConv = new OpenCVFrameConverter.ToMat();

    /**
     * controls if application closes
     */
    private final SimpleBooleanProperty cameraActiveProperty = new SimpleBooleanProperty(true);

    // Camera index: 0=internal, 4=first USB camera
    OpenCVFrameGrabber frameGrabber = new OpenCVFrameGrabber(4);

    ByteBuffer buffer;

    // Used in making sure image is stable before processing
    private final int HISTORY_SIZE = 5; // Track last 5 frames
    private List<HashMap<Integer, Point2f>> positionHistory = new ArrayList<>();
    private int stableFrameCount = 0;
    private static final int REQUIRED_STABLE_FRAMES = 3;
    private static final float STABILITY_THRESHOLD = 5.0f; // pixels

    // Processor and stage for transferring captured frames
    private ScoresheetProcessor processor;
    private Stage scannerStage;

    // Page scan tracking
    private boolean page1Scanned = false;
    private boolean page2Scanned = false;

    protected void updateView(Frame frame) {
        Mat mat = javaCVConv.convert(frame);

        MatVector corners = new MatVector();
        Mat ids = new Mat();

        arucoDetector.detectMarkers(mat, corners, ids);

        if (!ids.empty()) {
            drawDetectedMarkers(mat, corners, ids, new Scalar(0, 255, 0, 255));

            mat = tryWarpToRectangle(mat, corners, ids);
        }

        opencv_imgproc.cvtColor(mat, javaCVMat, COLOR_BGR2BGRA);

        int w = javaCVMat.cols();
        int h = javaCVMat.rows();

        buffer = javaCVMat.createBuffer();

        PixelBuffer<ByteBuffer> pb =
                new PixelBuffer<>(w, h, buffer, formatByte);

        WritableImage wi = new WritableImage(pb);
        Platform.runLater(() -> videoView.setImage(wi));
    }

    private Mat tryWarpToRectangle(Mat mat, MatVector corners, Mat ids) {
        long markerCount = corners.size();
        if (markerCount < 4) {
            return mat;
        }

        HashMap<Integer, Point2f> markerMap = new HashMap<>();

        // Calculate center for each marker and store in HashMap
        for (int i = 0; i < markerCount; i++) {
            Mat c = corners.get(i);
            FloatIndexer idx = c.createIndexer();
            int rows = c.rows();
            int cols = c.cols();

            float sumX = 0f;
            float sumY = 0f;
            int count = 0;

            for (int r = 0; r < rows; r++) {
                for (int col = 0; col < cols; col++) {
                    float x = idx.get(r, col, 0);
                    float y = idx.get(r, col, 1);
                    sumX += x;
                    sumY += y;
                    count++;
                }
            }

            if (count > 0) {
                IntIndexer idxIds = ids.createIndexer();
                int markerId = idxIds.get(i, 0);
                idxIds.release();
                Point2f center = new Point2f(sumX / count, sumY / count);
                markerMap.put(markerId, center);
            }
        }

        if (markerMap.size() < 4) {
            return mat;
        }

        // Sort IDs
        List<Integer> sortedIds = new ArrayList<>(markerMap.keySet());
        sortedIds.sort(Integer::compareTo);

        Point2f tl = markerMap.get(sortedIds.get(0));  // Lowest ID = top-left
        Point2f tr = markerMap.get(sortedIds.get(1));  // 2nd lowest ID = top-right
        Point2f bl = markerMap.get(sortedIds.get(2));  // 3rd lowest ID = bottom-left
        Point2f br = markerMap.get(sortedIds.get(3));  // 4th lowest ID = bottom-right

        Mat srcMat = new Mat(4, 1, CV_32FC2);
        FloatIndexer srcIdx = srcMat.createIndexer();
        srcIdx.put(0, 0, 0, tl.x());
        srcIdx.put(0, 0, 1, tl.y());
        srcIdx.put(1, 0, 0, tr.x());
        srcIdx.put(1, 0, 1, tr.y());
        srcIdx.put(2, 0, 0, br.x());
        srcIdx.put(2, 0, 1, br.y());
        srcIdx.put(3, 0, 0, bl.x());
        srcIdx.put(3, 0, 1, bl.y());
        srcIdx.release();

        // Around the size of paper
        int width = 1700;
        int height = 2200;

        Mat dstMat = new Mat(4, 1, CV_32FC2);
        FloatIndexer dstIdx = dstMat.createIndexer();
        dstIdx.put(0, 0, 0, 0);          // top-left x
        dstIdx.put(0, 0, 1, 0);          // top-left y
        dstIdx.put(1, 0, 0, width);      // top-right x
        dstIdx.put(1, 0, 1, 0);          // top-right y
        dstIdx.put(2, 0, 0, width);      // bottom-right x
        dstIdx.put(2, 0, 1, height);     // bottom-right y
        dstIdx.put(3, 0, 0, 0);          // bottom-left x
        dstIdx.put(3, 0, 1, height);     // bottom-left y
        dstIdx.release();

        Mat H = findHomography(srcMat, dstMat);
        if (H == null || H.empty()) {
            return mat;
        }

        Mat warped = new Mat();
        warpPerspective(mat, warped, H, new Size(width, height));

        if (isFrameStable(markerMap)) {
            stableFrameCount++;
            if (stableFrameCount >= REQUIRED_STABLE_FRAMES) {
                transferToProcessor(warped, markerMap);
                stableFrameCount = 0;
            }
        } else {
            stableFrameCount = 0;
        }

        return warped;
    }

    private boolean isFrameStable(HashMap<Integer, Point2f> currentPositions) {
        positionHistory.add(currentPositions);
        if (positionHistory.size() > HISTORY_SIZE) {
            positionHistory.removeFirst();
        }

        if (positionHistory.size() < 2) return false;

        HashMap<Integer, Point2f> prevPositions = positionHistory.get(positionHistory.size() - 2);
        float maxMovement = 0;

        for (Integer key : currentPositions.keySet()) {
            if (!prevPositions.containsKey(key)) return false;

            Point2f curr = currentPositions.get(key);
            Point2f prev = prevPositions.get(key);

            float dx = curr.x() - prev.x();
            float dy = curr.y() - prev.y();
            float distance = (float) Math.sqrt(dx * dx + dy * dy);

            maxMovement = Math.max(maxMovement, distance);
        }

        return maxMovement < STABILITY_THRESHOLD;
    }

    private void transferToProcessor(Mat warpedMat, HashMap<Integer, Point2f> markerMap) {
        int[] markerIds = markerMap.keySet().stream()
                .mapToInt(Integer::intValue)
                .toArray();

        processor.processPage(warpedMat, markerIds);
    }

    public void setCameraActive(Boolean isActive) {
        cameraActiveProperty.set(isActive);
    }

    public Boolean getCameraActive() {
        return cameraActiveProperty.get();
    }

    public void setProcessorAndStage(ScoresheetProcessor processor, Stage stage) {
        this.processor = processor;
        this.scannerStage = stage;
    }

    public void showPageScannedNotification(int pageNumber) {
        Platform.runLater(() -> {
            if (pageNumber == 1) {
                page1Scanned = true;
            } else if (pageNumber == 2) {
                page2Scanned = true;
            }
            updateStatusLabel();
        });
    }

    private void updateStatusLabel() {
        String status = "";
        if (page1Scanned && page2Scanned) {
            status = "BOTH PAGES SCANNED - Processing Complete!";
        } else if (page1Scanned) {
            status = "PAGE 1 SCANNED - Please scan Page 2";
        } else if (page2Scanned) {
            status = "PAGE 2 SCANNED - Please scan Page 1";
        }

        if (!status.isEmpty()) {
            statusLabel.setText(status);
            statusLabel.setVisible(true);
        }
    }

    public void shutdown() {
        setCameraActive(false);
        if (javaCVMat != null) {
            javaCVMat.release();
        }
    }

    private void setVideoView(Frame mat) {
        updateView(mat);
    }

    @FXML
    public void initialize() {
        try {
            frameGrabber.setImageWidth(1280);
            frameGrabber.setImageHeight(720);
            frameGrabber.start();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            while (getCameraActive()) {
                try {
                    Frame frame = frameGrabber.grab();
                    setVideoView(frame);
                } catch (FrameGrabber.Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                frameGrabber.release();
            } catch (FrameGrabber.Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

}
