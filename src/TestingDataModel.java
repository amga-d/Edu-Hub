import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TestingDataModel extends Application {
    private static final double WIDTH = 200;
    private static final double HEIGHT = 200;
    private static final double RADIUS = 80;
    private static final double START_ANGLE = 0; // Starting angle

    private Arc progressCircle;
    private Text progressText;
    private DoubleProperty progress = new SimpleDoubleProperty(0);

    @Override
    public void start(Stage primaryStage) {
        Arc outerCircle = new Arc(WIDTH / 2, HEIGHT / 2, RADIUS -10, RADIUS -10, 0, 360);
        outerCircle.setFill(Color.TRANSPARENT);
        outerCircle.setStroke(Color.valueOf("059379"));
        outerCircle.setStrokeWidth(1);

        // Arc backgroundCircle = new Arc(WIDTH / 2, HEIGHT / 2, RADIUS, RADIUS, START_ANGLE, 360);
        // backgroundCircle.setFill(Color.TRANSPARENT);
        // backgroundCircle.setStroke(Color.LIGHTGRAY);
        // backgroundCircle.setStrokeWidth(10);

        progressCircle = new Arc(WIDTH / 2, HEIGHT / 2, RADIUS, RADIUS, START_ANGLE, 0);
        progressCircle.setFill(Color.TRANSPARENT);
        progressCircle.setStroke(Color.valueOf("059379"));
        progressCircle.setStrokeWidth(10);
        progressCircle.setType(ArcType.OPEN);

        progressText = new Text("0%");
        progressText.setFont(new Font(30));
        progressText.setFill(Color.BLACK);

        Pane root = new Pane();
        root.getChildren().addAll(outerCircle, progressCircle, progressText);

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        primaryStage.setTitle("Circular Progress Bar");
        primaryStage.setScene(scene);
        primaryStage.show();

        progress.addListener((obs, oldVal, newVal) -> {
            int percentage = (int) (newVal.doubleValue() * 100);
            progressText.setText(percentage + "%");
            progressCircle.setLength(360 * newVal.doubleValue());
        });

        // Center the text in the progress circle
        // progressText.layoutXProperty().bind((root.widthProperty().subtract(progressText.prefWidth(-1)).divide(2)));
        // progressText.layoutYProperty().bind(root.heightProperty().subtract(progressText.prefHeight(-1)).divide(2));
        progressText.setX(75);
        progressText.setY(110);
        // Initialize with a small non-zero value to avoid starting from the middle
        progress.set(0.001);

        // Simulate setting progress from a database value with animation
        animateProgress(0.30); // Example: 30%
    }

    public void setProgress(double progress) {
        this.progress.set(progress);
    }

    public void animateProgress(double targetProgress) {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(progress, 0.001)),
            new KeyFrame(Duration.seconds(1), new KeyValue(progress, targetProgress))
        );
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
