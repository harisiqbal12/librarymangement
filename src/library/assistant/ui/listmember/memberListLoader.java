package library.assistant.ui.listmember;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class memberListLoader extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("memberList.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Member list");
            stage.show();

        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
