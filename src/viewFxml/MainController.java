package viewFxml;

import model.Account;
import model.Course;
import model.Instructor;
import model.User;
import service.AccountService;
import service.CourseService;
import service.CourseServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Pane calender;

    @FXML
    private ImageView chatImage;

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
    @FXML
    private Pane signOutPane;
    @FXML
    private Pane contactUsPane;
    @FXML
    private ImageView contactUsState2Icon;
    @FXML
    private Label contactUSlabel;
    @FXML
    private ImageView myLearningImage;
    @FXML
    private Pane myLearningButton;
    @FXML
    private Pane learningProcessPane;
    @FXML
    private Pane learningProcessPane2;
    @FXML
    private Pane learningProcessPane3;
    @FXML
    private Pane mentor1;
    @FXML
    private Pane mentor2;
    @FXML
    private Pane mentor3;
    @FXML
    private Pane mentor4;
    @FXML
    private Label course1;
    @FXML
    private Label course2;
    @FXML
    private Label course3;

    private User user;
    private CourseService courseService;
    private AccountService accountService;

    private Node rightSide;

    private CircularProgressBar circularProgressBar;
    private CircularProgressBar circularProgressBar2;
    private CircularProgressBar circularProgressBar3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        circularProgressBar = new CircularProgressBar();
        circularProgressBar.addToPane(learningProcessPane);
    
        circularProgressBar2 = new CircularProgressBar();
        circularProgressBar2.addToPane(learningProcessPane2);
    
        circularProgressBar3 = new CircularProgressBar();
        circularProgressBar3.addToPane(learningProcessPane3);
        rightSide = borderPane.getRight();

        homeButton.setOnMouseClicked(e -> {

            handleBoxClick();
            homeImage.setVisible(true);
            homeButton.getChildren().get(0).getStyleClass().add("selected");

            HomeController homeController1 = openPage("Home.fxml", false).getController();
            homeController1.setCourseService(courseService);
            homeController1.setAccount(user);
            homeController1.intiialHomePage();
        });

        courseButton.setOnMouseClicked(e -> {
            handleBoxClick();
            coursImage.setVisible(true);
            courseButton.getChildren().get(0).getStyleClass().add("selected");

            CoursesContorller couresesContorller = openPage("Courses.fxml", false).getController();
            couresesContorller.setCourseService(courseService);
            couresesContorller.setAccount(user);
            couresesContorller.initialCoursePage();

        });

        forumButton.setOnMouseClicked(e -> {
            handleBoxClick();
            forumImage.setVisible(true);
            forumButton.getChildren().get(0).getStyleClass().add("selected");
            openPage("FourmLayout.fxml", true).getController();

        });

        profileButton.setOnMouseClicked(e -> {
            handleOpenProfilePage();
        });

        profile.setOnMouseClicked(e->{
            handleOpenProfilePage();
        });

        contactUsPane.setOnMouseClicked(e -> {
            handleBoxClick();
            contactUsState2Icon.setVisible(true);
            contactUSlabel.getStyleClass().add("selected");
            openPage("ContactUsLayout.fxml", true);
        });

        signOutPane.setOnMouseClicked(e -> {
            handleSignOut();
        });

        myLearningButton.setOnMouseClicked(e -> {
            handleBoxClick();
            myLearningImage.setVisible(true);
            myLearningButton.getChildren().get(0).getStyleClass().add("selected");
            MyLearningController controller = openPage("MyLearingLayout.fxml", false).getController();
            List<Course> mycourses = courseService.getCoursesByUser(user);
            controller.initialMyLearningPage(mycourses, user, courseService);
        });

        notificationButton.setOnMouseClicked(e -> {
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

    private void handleOpenProfilePage() {
        handleBoxClick();
        profileImage.setVisible(true);
        profileButton.getChildren().get(0).getStyleClass().add("selected");
        ProfileLayoutController controller= openPage("ProfileLayout.fxml", true).getController();

        List<Course> completeCourses = new ArrayList<>();
        for (Course course : courseService.getCoursesByUser(user)) {
            if (course.isCourseCompleteByUser(user)) {
                completeCourses.add(course);
            }
        }
        
        controller.initialProfile(user,completeCourses,accountService);
        controller.setParentController(this);
        
    }

    private void setMontors() {
        List<Instructor> instructors = courseService.getAccountService().geInstructors();

        setMentorInformation(instructors.get(0), mentor1);
        setMentorInformation(instructors.get(1), mentor2);
        setMentorInformation(instructors.get(2), mentor3);
        setMentorInformation(instructors.get(3), mentor4);
        for (int i = 0; i < 3; i++) {
            Instructor instructor = instructors.get(i);

        }
    }

    private void setMentorInformation(Instructor instructor, Pane mentorPane) {
        ((ImageView) mentorPane.getChildren().get(0)).setImage(instructor.getImage());
        ((Label) mentorPane.getChildren().get(1)).setText(instructor.getName());
        ((Label) mentorPane.getChildren().get(2)).setText(instructor.getSpecializatoin());

    }

    private void setProgress() {
        List<Course> courses = courseService.getCoursesByUser(user);
    double progress1 = 0;
    double progress2 = 0;
    double progress3 = 0;

    if (!courses.isEmpty()) {
        progress1 = courses.get(0).calculateProgress(user);
        course1.setText(courses.get(0).getCourseName());
        if (courses.size() > 1) {
            progress2 = courses.get(1).calculateProgress(user);
            course2.setText(courses.get(1).getCourseName());
            if (courses.size() > 2) {
                progress3 = courses.get(2).calculateProgress(user);
                course3.setText(courses.get(2).getCourseName());
            }
        }
    }
    circularProgressBar.animateProgress(progress1);
    circularProgressBar2.animateProgress(progress2);
    circularProgressBar3.animateProgress(progress3);
        // List<Course> courses = courseService.getCoursesByUser(user);
        // double progress1 = 0;
        // double progress2 = 0;
        // double progress3 = 0;

        // if (!courses.isEmpty()) {
        //     progress1 = courses.get(0).calculateProgress(user);
        //     course1.setText(courses.get(0).getCourseName());
        //     if (courses.size() > 1) {
        //         progress2 = courses.get(1).calculateProgress(user);
        //         course2.setText(courses.get(1).getCourseName());
        //         if (courses.size() > 2) {
        //             progress3 = courses.get(2).calculateProgress(user);
        //             course3.setText(courses.get(2).getCourseName());
        //         }
        //     }
        // }
        // CircularProgressBar circularProgressBar = new CircularProgressBar();
        // circularProgressBar.addToPane(learningProcessPane);
        // circularProgressBar.animateProgress(progress1);

        // CircularProgressBar circularProgressBar2 = new CircularProgressBar();
        // circularProgressBar2.addToPane(learningProcessPane2);
        // circularProgressBar2.animateProgress(progress2);

        // CircularProgressBar circularProgressBar3 = new CircularProgressBar();
        // circularProgressBar3.addToPane(learningProcessPane3);
        // circularProgressBar3.animateProgress(progress3);

    }

    public void handleSignOut() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        try {
            Parent root = loader.load();
            loginController logincontroller = loader.getController();
            logincontroller.setAccountService(courseService.getAccountService());
            logincontroller.load();

            root.setClip(createRoundedRectangle(900, 600, 30));

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);

            Image icon = new Image(getClass().getResourceAsStream("/Resources/logo.jpeg"));
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("edu-Hub");
            stage.getIcons().add(icon);

            ((Stage) signOutPane.getScene().getWindow()).close();

            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private javafx.scene.shape.Rectangle createRoundedRectangle(double width, double height, double radius) {
        javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(width, height);
        rectangle.setArcWidth(radius);
        rectangle.setArcHeight(radius);
        return rectangle;
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

    public void handleBoxClick() {

        courseButton.getChildren().get(0).getStyleClass().remove("selected");
        homeButton.getChildren().get(0).getStyleClass().remove("selected");
        forumButton.getChildren().get(0).getStyleClass().remove("selected");
        profileButton.getChildren().get(0).getStyleClass().remove("selected");
        contactUsPane.getChildren().get(0).getStyleClass().remove("selected");
        myLearningButton.getChildren().get(0).getStyleClass().remove("selected");

        coursImage.setVisible(false);
        homeImage.setVisible(false);
        profileImage.setVisible(false);
        forumImage.setVisible(false);
        myLearningImage.setVisible(false);
        contactUsState2Icon.setVisible(false);

    }

    private void setProfile(Image image) {
        profile.setImage(image);
        double radius = (Math.min(profile.getFitHeight(), profile.getFitWidth())) / 2;
        Circle clipCircle = new Circle(radius, radius, radius);
        profile.setClip(clipCircle);
    }

    private FXMLLoader openPage(String fileName, Boolean removeSideBar) { // open page and return FXMLLOADER
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));

        Parent root;
        try {
            root = loader.load();
            loader.getController();
            if (removeSideBar) {
                borderPane.setRight(null);
            } else {
                borderPane.setRight(rightSide);
            }
            borderPane.setCenter(root);
            setProgress();
            return loader; // return the loader to be to get the controller class
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateUser(){
        setProfile(user.getImage());
        username.setText(user.getName());
    }

    public void initialMain(User account, AccountService accountService) {
        this.user =  account;
        this.accountService = accountService;
        
        username.setText(user.getName());
        this.courseService = new CourseServiceImpl(accountService);
        setProfile(user.getImage());

        homeButton.getChildren().get(0).getStyleClass().add("selected");
        homeImage.setVisible(true);

        HomeController homeController = openPage("Home.fxml", false).getController();
        homeController.setCourseService(courseService);
        homeController.setAccount(user);
        homeController.intiialHomePage();

        setMontors();
        setProgress();
        setCalender();
    }
}
