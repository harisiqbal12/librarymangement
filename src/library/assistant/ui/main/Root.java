package library.assistant.ui.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.sun.javafx.scene.control.behavior.TabPaneBehavior;
import library.assistant.database.DatabaseHandler;

public class Root extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
            stage.setTitle("root");
            stage.setScene(new Scene(root));
            stage.show();

            DatabaseHandler.getInstance();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error occured");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
