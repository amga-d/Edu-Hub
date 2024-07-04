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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class MainController implements Initializable {
   
    @FXML
    private BorderPane borderPane;

    @FXML
    private Pane calender;

    @FXML
    private ImageView chatImage;

    @FXML
    private Pane contactButton;

    @FXML
    private ImageView coursImage;

    @FXML
    private Pane courseButton;

    @FXML
    private Label dateLabel1;

    @FXML
    private Label dateLabel2;

    @FXML
    private Label dateLabel3;

    @FXML
    private Label dateLabel4;

    @FXML
    private Label dateLabel5;

    @FXML
    private Label dateLabel6;

    @FXML
    private Label dateLabel7;

    @FXML
    private VBox day1;

    @FXML
    private VBox day2;

    @FXML
    private VBox day3;

    @FXML
    private VBox day4;

    @FXML
    private VBox day5;

    @FXML
    private VBox day6;

    @FXML
    private VBox day7;

    @FXML
    private Label dayLabel1;

    @FXML
    private Label dayLabel2;

    @FXML
    private Label dayLabel3;

    @FXML
    private Label dayLabel4;

    @FXML
    private Label dayLabel5;

    @FXML
    private Label dayLabel6;

    @FXML
    private Label dayLabel7;

    @FXML
    private Pane dayPane1;

    @FXML
    private Pane dayPane2;

    @FXML
    private Pane dayPane3;

    @FXML
    private Pane dayPane4;

    @FXML
    private Pane dayPane5;

    @FXML
    private Pane dayPane6;

    @FXML
    private Pane dayPane7;

    @FXML
    private HBox daysBox;

    @FXML
    private Pane forumButton;

    @FXML
    private ImageView forumImage;

    @FXML
    private Pane homeButton;

    @FXML
    private ImageView homeImage;

    @FXML
    private Label monthLabel;

    @FXML
    private ImageView notificationButton;

    @FXML
    private Pane notificationPane;

    @FXML
    private ImageView profile;

    @FXML
    private Pane profileButton;

    @FXML
    private Pane profileButton1;

    @FXML
    private ImageView profileImage;

    @FXML
    private Label username;

    private Account account;
    private CourseService courseService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // initialize the courseService
        courseService = new CourseServiceImpl();
        setCalender();

        
        // handleBoxClick(homeButton);
        homeButton.getChildren().get(0).getStyleClass().add("selected");
        homeImage.setVisible(true);
        openPage("Home.fxml");
        
        courseButton.setOnMouseClicked(e -> {
            Pane clickedBox = (Pane) e.getSource();
            handleBoxClick(clickedBox);
            coursImage.setVisible(true);
            courseButton.getChildren().get(0).getStyleClass().add("selected");
            
        });
        homeButton.setOnMouseClicked(e -> {
            Pane clickedBox = (Pane) e.getSource();
            handleBoxClick(clickedBox);
            homeImage.setVisible(true);
            openPage("Home.fxml");
            homeButton.getChildren().get(0).getStyleClass().add("selected");
            
        });
        
        forumButton.setOnMouseClicked(e -> {
            Pane clickedBox = (Pane) e.getSource();
            handleBoxClick(clickedBox);
            forumImage.setVisible(true);
            forumButton.getChildren().get(0).getStyleClass().add("selected");
        });
        profileButton.setOnMouseClicked(e -> {
            Pane clickedBox = (Pane) e.getSource();
            handleBoxClick(clickedBox);
            profileImage.setVisible(true);
            profileButton.getChildren().get(0).getStyleClass().add("selected");
        });
        contactButton.setOnMouseClicked(e -> {
            Pane clickedBox = (Pane) e.getSource();
            handleBoxClick(clickedBox);
            chatImage.setVisible(true);
            contactButton.getChildren().get(0).getStyleClass().add("selected");

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

        courseButton.getChildren().get(0).getStyleClass().remove("selected");
        homeButton.getChildren().get(0).getStyleClass().remove("selected");
        forumButton.getChildren().get(0).getStyleClass().remove("selected");
        profileButton.getChildren().get(0).getStyleClass().remove("selected");
        contactButton.getChildren().get(0).getStyleClass().remove("selected");

        coursImage.setVisible(false);
        homeImage.setVisible(false);
        chatImage.setVisible(false);
        profileImage.setVisible(false);
        forumImage.setVisible(false);



    }

    public void setAccount(Account account, AccountService accountService) {
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

    private void openPage(String fileName) {
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
