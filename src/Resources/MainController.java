package viewFxml;

import model.Account;
import model.User;
import service.AccountService;
import service.CourseService;
import service.CourseServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class MainController implements Initializable {
    @FXML
    private BorderPane borderPane;

    @FXML private Label monthLabel;

    @FXML
    private VBox day1, day2, day3, day4, day5, day6, day7;

    @FXML
    private Label dayLabel1, dayLabel2, dayLabel3, dayLabel4, dayLabel5, dayLabel6, dayLabel7;

    @FXML
    private Label dateLabel1, dateLabel2, dateLabel3, dateLabel4, dateLabel5, dateLabel6, dateLabel7;

    @FXML
    private Pane dayPane1, dayPane7, dayPane2, dayPane3, dayPane4, dayPane5, dayPane6;

    @FXML
    private Pane QuizzButton;

    @FXML
    private Pane contactButton;

    @FXML
    private Pane courseButton;

    @FXML
    private Pane forumButton;

    @FXML
    private Pane homeButton;

    @FXML
    private ImageView profile;

    @FXML
    private Pane profileButton;

    @FXML
    private Label username;
    @FXML
    private Pane calender;
    @FXML
    private ImageView notificationButton;
    @FXML
    private Pane notificationPane;

    private Account account;
    private CourseService courseService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // initialize the courseService
        courseService = new CourseServiceImpl();
        setCalender();

        handleBoxClick(homeButton);
        openPage("Home.fxml");

        courseButton.setOnMouseClicked(e -> {
            Pane clickedBox = (Pane) e.getSource();
            handleBoxClick(clickedBox);
        });
        homeButton.setOnMouseClicked(e -> {
            Pane clickedBox = (Pane) e.getSource();
            handleBoxClick(clickedBox);
            openPage("Home.fxml");
        });
        QuizzButton.setOnMouseClicked(e -> {
            Pane clickedBox = (Pane) e.getSource();
            handleBoxClick(clickedBox);
        });
        forumButton.setOnMouseClicked(e -> {
            Pane clickedBox = (Pane) e.getSource();
            handleBoxClick(clickedBox);
        });
        profileButton.setOnMouseClicked(e -> {
            Pane clickedBox = (Pane) e.getSource();
            handleBoxClick(clickedBox);
        });
        contactButton.setOnMouseClicked(e -> {
            Pane clickedBox = (Pane) e.getSource();
            handleBoxClick(clickedBox);
        });
        notificationButton.setOnMouseEntered(e -> {
            notificationPane.setVisible(true);
        });
        notificationButton.setOnMouseExited(e -> {
            notificationPane.setVisible(false);
        });
        notificationPane.setOnMouseEntered(e -> {
            notificationPane.setVisible(true);
        });
        notificationPane.setOnMouseExited(e -> {
            notificationPane.setVisible(false);
        });

    }

    private void setCalender() {
        LocalDate currentDate = LocalDate.now();
        int currentDayOfWeek = currentDate.getDayOfWeek().getValue();

        LocalDate startOfWeek = currentDate.minusDays(currentDayOfWeek - 1);

        monthLabel.setText(
                currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + currentDate.getYear());

        Label[] dayLabels = { dayLabel1, dayLabel2, dayLabel3, dayLabel4, dayLabel5, dayLabel6, dayLabel7 };
        Label[] dateLabels = { dateLabel1, dateLabel2, dateLabel3, dateLabel4, dateLabel5, dateLabel6, dateLabel7 };
        Pane[] dayPanes = { dayPane1, dayPane2, dayPane3, dayPane4, dayPane5, dayPane6, dayPane7 };

        for (int i = 0; i < 7; i++) {
            LocalDate date = startOfWeek.plusDays(i);
            dayLabels[i].setText(date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase());
            dateLabels[i].setText(String.valueOf(date.getDayOfMonth()));
            if (date.equals(currentDate)) {
                dayLabels[i].getStyleClass().add("selected");
                dateLabels[i].getStyleClass().add("selected");
                dayPanes[i].getStyleClass().add("selected-pane");
            } else {
                dayLabels[i].getStyleClass().remove("selected");
                dateLabels[i].getStyleClass().remove("selected");
                dayPanes[i].getStyleClass().remove("selected-pane");
            }
        }
    }

    public void handleBoxClick(Pane clickedBox) {

        courseButton.getStyleClass().remove("selected");
        homeButton.getStyleClass().remove("selected");
        QuizzButton.getStyleClass().remove("selected");
        forumButton.getStyleClass().remove("selected");
        profileButton.getStyleClass().remove("selected");
        contactButton.getStyleClass().remove("selected");

        // Apply selected style to the clicked box
        clickedBox.getStyleClass().add("selected");

    }

    public void setAccount(Account account,AccountService accountService) {
        this.account = account;
        User user = (User) account;
        username.setText(user.getFullName());
        String imagePath = user.getProfilePath();

        courseService.setAccountService(accountService);
        if (imagePath != null) {
            
            setImage(user.getProfilePath(), profile);
        }
    }

    private void setImage(String filePath, ImageView imageview) {
        Image image = new Image(filePath);
        imageview.setImage(image);
        double radius = (Math.min(imageview.getFitHeight(), imageview.getFitWidth())) / 2;
        Circle clipCircle = new Circle(radius, radius, radius);
        imageview.setClip(clipCircle);
    }

    private void openPage(String fileName){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));

        Parent root;
        try {
            root = loader.load();
            borderPane.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
