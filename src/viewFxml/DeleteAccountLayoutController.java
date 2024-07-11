package viewFxml;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import model.User;
import service.AccountService;

public class DeleteAccountLayoutController {
    @FXML
    private ImageView cancel;

    @FXML
    private ImageView confirm;

    public void initialDeletingAccountPage(MainController parentController, AccountService accountService, User user,ProfileLayoutController profileLayoutController) {
        confirm.setOnMouseClicked(e->{
            accountService.deleteUserAccount(user);
            parentController.handleSignOut();
        });

        cancel.setOnMouseClicked(e->{
            profileLayoutController.handleAchievementButton();
        });
    }



    
}
