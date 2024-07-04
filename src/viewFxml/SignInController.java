package viewFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import model.Account;
import service.AccountService;

public class SignInController implements Initializable {
    @FXML
    private Label hello;
    @FXML
    private Label welcome;
    @FXML
    private Label email;
    @FXML
    private Label password;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label errorLabel;
    @FXML
    private AccountService accountService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LinearGradient linearGradient = new LinearGradient(
                0, 0, 1, 0, // startX, startY, endX, endY
                true, // proportional
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#0D1925")),
                new Stop(0.4972, Color.web("#059379")));

        hello.setTextFill(linearGradient);
        welcome.setTextFill(linearGradient);
        password.setTextFill(linearGradient);
        email.setTextFill(linearGradient);

        setChange(emailField, email);
        setChange(passwordField, password);
    }

    private void setChange(TextField field, Label lable){
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
            } else {
                if (field.getText().isEmpty()) {
                    field.setStyle("-fx-opacity: 0.5;");
                    lable.setStyle("-fx-opacity: 0.5;");
                }
            }
        });
        
    }

    public void openMainPage(Account account, AccountService accountService) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent mainPage = loader.load();
            MainController mainController = loader.getController();
            mainController.setAccount(account,accountService);
            
            Scene scene = new Scene(mainPage);
            Stage stage = new Stage();
            stage.setFullScreen(true);
            // System.out.println(stage.getFullScreenExitHint());
            stage.setScene(scene);
            getStage().close();
            stage.show();

        } catch (IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    
    private Stage getStage() {
        Stage stage = (Stage) password.getScene().getWindow();
        return stage;
    }

    @FXML
    public void signIn(ActionEvent e) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Username and password must not be empty.");
        }

        else if (accountService.authenticate(email, password)) {
            errorLabel.setText("");
            try {
                openMainPage(accountService.getAccountByUsername(email),accountService);
            } catch (Exception e1) {
                System.err.println("an error occur 'Account not found'"+e1.getMessage());
            }
        } else {
            errorLabel.setText("Invalid username or password.");
        }
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
