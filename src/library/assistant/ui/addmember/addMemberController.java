package library.assistant.ui.addmember;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import library.assistant.database.DatabaseHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class addMemberController implements Initializable {
    DatabaseHandler databaseHandler;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField mobile;
    @FXML
    private JFXTextField email;
    @FXML
    private AnchorPane memberPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        databaseHandler = DatabaseHandler.getInstance();


    }

    private void blankAllFields() {
        name.setText(" ");
        email.setText(" ");
        mobile.setText(" ");
        id.setText(" ");
    }

    private int throwError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Please Fill All Values");
        alert.showAndWait();

        return 0;
    }

    private int throwSuccess(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
        return 0;
    }

    public void addButton(ActionEvent event) {
        String name = this.name.getText();
        String id = this.id.getText();
        String mobile = this.mobile.getText();
        String email = this.email.getText();
        System.out.println("Save button click");

        boolean flag = name.isBlank() || id.isEmpty() || mobile.isEmpty() || email.isEmpty();
        if (flag) { throwError(); return; }

        String st = "INSERT INTO MEMBER VALUES (" +
                "'" + id + "'," +
                "'" + name + "'," +
                "'" + mobile + "',"+
                "'" + email + "'" +
                ")";
        System.out.println(st);
        if(!databaseHandler.execAction(st)) { throwError(); return;};

        throwSuccess("Data added successfully");
        blankAllFields();

    }

    public void cancel(ActionEvent event) {
        Stage stage = (Stage) memberPane.getScene().getWindow();
        stage.close();
        System.out.println("Add Member Screen has Been closed");

    }
}
