package viewFxml;

import javafx.animation.KeyFrame;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class CircularProgressBar {
    private static final double WIDTH = 80;
    private static final double HEIGHT = 80;
    private static final double RADIUS = 35;
    private static final double START_ANGLE = 0; // Starting angle

    private Arc progressCircle;
    private Text progressText;
    private DoubleProperty progress = new SimpleDoubleProperty(0);

    public CircularProgressBar() {
        progressCircle = new Arc(WIDTH / 2, HEIGHT / 2, RADIUS, RADIUS, START_ANGLE, 0);
        progressCircle.setFill(Color.TRANSPARENT);
        progressCircle.setStroke(Color.valueOf("059379"));
        progressCircle.setStrokeWidth(5);
        progressCircle.setType(ArcType.OPEN);

        progressText = new Text("0%");

        progressText.setFill(Color.BLACK);
        progressText.setFont(Font.font("Poppins", FontWeight.MEDIUM, 14));
        progressText.setX(WIDTH / 2 - 11);
        progressText.setY(HEIGHT / 2 + 5);

        progress.addListener((obs, oldVal, newVal) -> {
            int percentage = (int) (newVal.doubleValue() * 100);
            progressText.setText(percentage + "%");
            progressCircle.setLength(360 * newVal.doubleValue());
        });

        // Initialize with a small non-zero value to avoid starting from the middle
        progress.set(0.001);
    }

    public void setProgress(double progress) {
        this.progress.set(progress);
    }

    public void animateProgress(double targetProgress) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progress, 0.001)),
                new KeyFrame(Duration.seconds(1), new KeyValue(progress, targetProgress)));
        timeline.play();
    }

    public void addToPane(Pane pane) {
        Arc outerCircle = new Arc(WIDTH / 2, HEIGHT / 2, RADIUS - 5, RADIUS - 5, 0, 360);
        outerCircle.setFill(Color.TRANSPARENT);
        outerCircle.setStroke(Color.valueOf("059379"));
        outerCircle.setStrokeWidth(1);

        pane.getChildren().addAll(outerCircle, progressCircle, progressText);
    }
}
