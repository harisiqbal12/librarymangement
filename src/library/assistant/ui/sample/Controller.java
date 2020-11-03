package library.assistant.ui.sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import library.assistant.database.DatabaseHandler;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    DatabaseHandler databaseHandler;
    @FXML
    private TextField title;
    @FXML
    private TextField id;
    @FXML
    private TextField author;
    @FXML
    private TextField publisher;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        databaseHandler = DatabaseHandler.getInstance();

        try {
            checkData();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addButton(ActionEvent event) {
        String bookID = id.getText();
        String bookTitle = title.getText();
        String bookAuthor = author.getText();
        String bookPublisher = publisher.getText();

        if (bookID.isEmpty() || bookTitle.isEmpty() || bookAuthor.isEmpty() || bookPublisher.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter All Values");
            alert.show();
            return;
        }

        String qu = "INSERT INTO BOOK VALUES (" +
                "'" + bookID + "'," +
                "'" + bookTitle + "'," +
                "'" + bookAuthor + "'," +
                "'" + bookPublisher + "'," +
                "" + "true" + "" +
                ")";

        System.out.println(qu);
        if (databaseHandler.execAction(qu)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Data Added Successfully");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed");
            alert.showAndWait();
        }
    }

    public void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
        System.out.println("Application has been closed");
    }

    private void checkData() throws SQLException {
        String qu = "SELECT title FROM BOOK";
        ResultSet res = databaseHandler.execQuery(qu);

        while (res.next()) {
            String titleex = res.getString("title");
            System.out.println(titleex);
        }
    }

}
