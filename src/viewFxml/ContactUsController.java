package viewFxml;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ContactUsController {

    @FXML
    private Button sendButton;

    @FXML
    private TextField messageTextField;

    @FXML
    private Label contactUsLabel;

    @FXML
    public void sendAction() {
        // Handle the send button click event here
        String message = messageTextField.getText();
        System.out.println("Message: " + message);
        // Send the message to the server or perform any other action
    }
}