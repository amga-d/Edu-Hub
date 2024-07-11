package viewFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import model.Account;
import model.User;
import service.AccountService;

public class FinishSignUpController implements Initializable {

    @FXML
    private Label academic;

    @FXML
    private Label academicDescription;

    @FXML
    private Pane academicPane;

    @FXML
    private Label personal;

    @FXML
    private Label personalDescription;

    @FXML
    private Pane personalPane;

    @FXML
    private Label skillDescription;

    @FXML
    private Label skillDevelopment;

    @FXML
    private Pane skillPane;
    @FXML
    private Pane pane;

    private Stage stage;

    private Account account;
    private AccountService accountService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        applyGradientText(skillDescription, skillDevelopment, "#2C4175", "#DE8BB6");
        applyGradientText(academic, academicDescription, "#0080F0", "#00E7F0");
        applyGradientText(personal, personalDescription, "#2BB782", "#EFE767");

        skillPane.setOnMouseClicked(event -> {
            setOpacity(skillPane);
        });
        personalPane.setOnMouseClicked(event -> {
            setOpacity(personalPane);
        });
        academicPane.setOnMouseClicked(event -> {
            setOpacity(academicPane);
        });
    }

    private void setOpacity(Pane pane) {
        skillPane.setStyle("-fx-opacity : 0.8");
        personalPane.setStyle("-fx-opacity : 0.8");
        academicPane.setStyle("-fx-opacity : 0.8");
        pane.setStyle("-fx-opacity : 1");
    }

    public static void applyGradientText(Label label, Label description, String color1, String color2) {
        // Create the LinearGradient
        LinearGradient linearGradient = new LinearGradient(
                0, 0, 1, 1, // startX, startY, endX, endY
                true, // proportional
                CycleMethod.NO_CYCLE,
                new Stop(-0.0115, Color.web(color1)),
                new Stop(0.9963, Color.web(color2)));
        // Apply the LinearGradient to the label's text fill
        label.setTextFill(linearGradient);
        description.setTextFill(linearGradient);
    }

    public void openMainPage(ActionEvent e) {
        
        try {
            FXMLLoader loader = new FXMLLoader (getClass().getResource("Main.fxml"));
            Parent mainPage = loader.load();
            MainController mainController = loader.getController();
            mainController.initialMain((User)account,accountService);
            Scene scene = new Scene(mainPage);
            Stage stage = new Stage();
            stage.setFullScreen(true);
            stage.setTitle("Edu-Squad");
            stage.getIcons().add(getStage().getIcons().get(0));
            // System.out.println(stage.getFullScreenExitHint());
            stage.setScene(scene);
            getStage().close();
            stage.show();

        } catch (IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        Stage stage = (Stage) pane.getScene().getWindow();
        return stage;
    }

    public void setAccount(Account account, AccountService accountService){
        this.account= account;
        this.accountService = accountService;
    }

}
