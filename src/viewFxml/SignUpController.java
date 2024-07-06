package viewFxml;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;
import service.AccountService;

public class SignUpController implements Initializable {
    @FXML
    private ImageView profilePic;
    @FXML
    private Label birthDate;

    @FXML
    private DatePicker birthMenu;

    @FXML
    private Label email;

    @FXML
    private TextField emailField;

    @FXML
    private Label gender;

    @FXML
    private ComboBox<String> genderBox;

    @FXML
    private Label name;

    @FXML
    private TextField nameField;

    @FXML
    private Label password;

    @FXML
    private TextField passwordField;

    @FXML
    private Label role;

    @FXML
    private ComboBox<String> jobOption;

    @FXML
    private VBox vBox;

    @FXML
    private Label mentorLabel;
    @FXML
    private CheckBox mentorCheckbox;

    private Boolean[] isInformationFilled;
    private Stage stage;
    private AccountService accountService;
    private String absolutePath;
    private Boolean isUserAccount;

    String[] jobOptions = { "Software Developer/Engineer",
            "Data Scientist",
            "Project Manager",
            "Teacher/Educator",
            "Student",
            "Researcher",
            "Business Analyst",
            "Graphic Designer",
            "Marketing Specialist",
            "Sales Representative",
            "Human Resources Manager",
            "Healthcare Professional",
            "Finance/Accounting Professional",
            "Consultant",
            "Entrepreneur/Startup Founder",
            "Legal Professional",
            "Administrator",
            "IT Support Specialist",
            "Customer Service Representative",
            "Engineer (Other)",
            "Architect",
            "Journalist/Writer",
            "Operations Manager",
            "Product Manager",
            "Executive/Director",
            "Logistics/Supply Chain Manager",
            "Trainer/Coach",
            "Scientist",
            "Artist",
            "Other" };
    Image temporary = new Image(getClass().getResourceAsStream("/Resources/account_images/UploadProfile.png"));

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isInformationFilled = new Boolean[6];
        isUserAccount = true;

        for (int i = 0; i < 6; i++) {
            isInformationFilled[i] = false;
        }

        profilePic.setImage(temporary);

        genderBox.getItems().addAll("Male", "Female");
        jobOption.getItems().addAll(jobOptions);

        setChange(nameField, name, 0);
        setChange(emailField, email, 1);
        setChange(passwordField, password, 2);
        setChange(birthMenu, birthDate, 3);
        setChange(jobOption, role, 4);
        setChange(genderBox, gender, 5);
        setChange(mentorCheckbox, mentorLabel);

        profilePic.setOnMouseClicked(event -> {
            changeProfile();
        });
    }

    @FXML
    public void handleMentorCheckBox(ActionEvent e) {
        if (mentorCheckbox.isSelected()) {
            isUserAccount = false;
        } else {
            isUserAccount = true;
        }
    }

    private void changeProfile() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        fileChooser.setInitialDirectory(new File("src/Resources"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {

            absolutePath = selectedFile.toURI().toString();
            setImage(absolutePath);
        }

    }

    private void setImage(String absolutePath) {
        Image image = new Image(absolutePath);
        profilePic.setImage(image);
        double radius = (Math.min(profilePic.getFitHeight(), profilePic.getFitWidth())) / 2;
        Circle clipCircle = new Circle(radius, radius, radius);
        profilePic.setClip(clipCircle);

    }

    private void setChange(CheckBox checkBox, Label label) {
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                checkBox.setStyle("-fx-opacity: 1.0;");
                label.setStyle("-fx-opacity: 1.0;");
            } else {
                checkBox.setStyle("-fx-opacity: 0.5;");
                label.setStyle("-fx-opacity: 0.5;");
            }
        });

        // Adding listener to checkbox focused property
        checkBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                checkBox.setStyle("-fx-opacity: 1.0;");
                label.setStyle("-fx-opacity: 1.0;");
            } else {
                if (!checkBox.isSelected()) {
                    checkBox.setStyle("-fx-opacity: 0.5;");
                    label.setStyle("-fx-opacity: 0.5;");
                }
            }
        });
    }

    private void setChange(DatePicker field, Label lable, int index) {
        field.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                field.setStyle("-fx-opacity: 0.5;");
                lable.setStyle("-fx-opacity: 0.5;");
            } else {
                field.setStyle("-fx-opacity: 1.0;");
                lable.setStyle("-fx-opacity: 1.0;");
            }
        });

        field.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                field.setStyle("-fx-opacity: 1.0;");
                lable.setStyle("-fx-opacity: 1.0;");
                isInformationFilled[index] = true;
            } else {
                if (field.getValue() == null) {
                    field.setStyle("-fx-opacity: 0.5;");
                    lable.setStyle("-fx-opacity: 0.5;");
                    isInformationFilled[index] = false;

                }
            }
        });

    }

    private void setChange(ComboBox box, Label lable, int index) {

        box.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                box.setStyle("-fx-opacity: 0.5;");
                lable.setStyle("-fx-opacity: 0.5;");
            } else {
                box.setStyle("-fx-opacity: 1.0;");
                lable.setStyle("-fx-opacity: 1.0;");
            }
        });

        box.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                box.setStyle("-fx-opacity: 1.0;");
                lable.setStyle("-fx-opacity: 1.0;");
                isInformationFilled[index] = true;
            } else {
                if (box.getValue() == null) {
                    box.setStyle("-fx-opacity: 0.5;");
                    lable.setStyle("-fx-opacity: 0.5;");
                    isInformationFilled[index] = false;
                }
            }
        });
    }

    private void setChange(TextField field, Label lable, int index) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                field.setStyle("-fx-opacity: 0.5;");
                lable.setStyle("-fx-opacity: 0.5;");
            } else {
                field.setStyle("-fx-opacity: 1.0;");
                lable.setStyle("-fx-opacity: 1.0;");
            }
        });
        field.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                field.setStyle("-fx-opacity: 1.0;");
                lable.setStyle("-fx-opacity: 1.0;");
                isInformationFilled[index] = true;

            } else {
                if (field.getText().isEmpty()) {
                    field.setStyle("-fx-opacity: 0.5;");
                    lable.setStyle("-fx-opacity: 0.5;");
                    isInformationFilled[index] = false;
                }
            }
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void SignUp(ActionEvent e) {

        for (Boolean boolean1 : isInformationFilled) {
            if (boolean1 == false) {
                return;
            }
        }

        String fullname = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        Boolean gender = (genderBox.getSelectionModel().getSelectedItem().equals("Male"));
        String job = jobOption.getSelectionModel().getSelectedItem();
        LocalDate date = birthMenu.getValue();

        Account account;
        VBox newVBox;

        if (isUserAccount) {

            account = new User(password, email, fullname, gender, date, job, absolutePath);
            accountService.createAccount(account);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FinishSignUp.fxml"));
                newVBox = loader.load();

                FinishSignUpController controller = loader.getController();
                controller.setAccount(account, accountService);

                vBox.getChildren().removeAll();
                vBox.getChildren().setAll(newVBox);

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            account = new Instructor(password, email, fullname, gender, date, job, absolutePath);
            accountService.createAccount(account);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MentorMenu.fxml"));
                Parent mainPage = loader.load();
                Scene scene = new Scene(mainPage);
                Stage stage = new Stage();
                stage.setFullScreen(true);
                stage.setScene(scene);
                ((Stage)vBox.getScene().getWindow()).close();
                stage.show();
                

                MentorMenuController controller = loader.getController();
                controller.setAccount(account);

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }



    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
