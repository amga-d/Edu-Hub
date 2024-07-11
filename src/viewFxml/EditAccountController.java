package viewFxml;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import model.User;
import service.AccountService;

public class EditAccountController implements Initializable {

    @FXML
    private Label birthDate;

    @FXML
    private DatePicker birthMenu;

    @FXML
    private Label email;

    @FXML
    private TextField emailField;

    @FXML
    private Label name;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField newpasswordField1;

    @FXML
    private Label password1;

    @FXML
    private ImageView profileImage;
    @FXML
    private ImageView changeProfileIcon;
    @FXML
    private Label alertLabel;
    @FXML
    private Button saveButton;

    private User user;
    private AccountService accountService;
    private ProfileLayoutController parentController;
    private boolean isImageChanged;
    private String absolutePath;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initailEditPage(User user, AccountService accountService) {

        this.user = user;
        this.accountService = accountService;

        isImageChanged = false;
        clipImage(user.getImage());
        birthMenu.setValue(user.getDate());
        nameField.setText(user.getName());
        emailField.setText(user.getEmail());
    

        changeProfileIcon.setOnMouseClicked(e -> {
            changeProfile();
        });

    }

    @FXML
    private void handleEditAccountbutton() {

        if (!(newpasswordField1.getText().isEmpty() || newpasswordField1.getText().equals(""))) {
            user.setName(nameField.getText());
            user.setEmail(emailField.getText());
            user.setDate(birthMenu.getValue());
            user.setPassword(newpasswordField1.getText());
            if (isImageChanged) {
                user.setImagePath(absolutePath);
            }
            accountService.saveAccounts();
            parentController.updateUser();

        } else {
            alertLabel.setText("Please Enter  New Password");
        }

    }


    private void changeProfile() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        fileChooser.setInitialDirectory(new File("src/Resources"));
        File selectedFile = fileChooser.showOpenDialog(nameField.getScene().getWindow());
        if (selectedFile != null) {

            absolutePath = selectedFile.toURI().toString();
            clipImage(new Image(absolutePath));

            isImageChanged = true;
        }

    }

    private void clipImage(Image image) {

        profileImage.setImage(image);
        double radius = (Math.min(profileImage.getFitHeight(), profileImage.getFitWidth())) / 2;
        Circle clipCircle = new Circle(radius, radius, radius);
        profileImage.setClip(clipCircle);
    }


    public void setParentController(ProfileLayoutController profileLayoutController){
        this.parentController = profileLayoutController;
    }

}
