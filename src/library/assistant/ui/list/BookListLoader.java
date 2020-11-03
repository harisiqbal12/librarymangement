package library.assistant.ui.list;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class BookListLoader extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("booklist.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR OCCURED");
            alert.setHeaderText(null);
            alert.setContentText("error occured during loading of booklist scene");
            alert.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
