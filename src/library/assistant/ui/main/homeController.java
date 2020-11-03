package library.assistant.ui.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class homeController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void loadAddMember(ActionEvent event) {
        loadWindow("/library/assistant/ui/addmember/addmember.fxml", "Add Member");
    }
    @FXML
    private void loadAddBook(ActionEvent event) {
        loadWindow("/library/assistant/ui/sample/main.fxml", "Add Books");
    }
    @FXML
    private void loadMemberList(ActionEvent event) {
        loadWindow("/library/assistant/ui/listmember/memberList.fxml", "Member List");
    }
    @FXML
    private void loadBookList(ActionEvent event) {
        loadWindow("/library/assistant/ui/list/booklist.fxml", "Book List");
    }

    void loadWindow(String loc, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
