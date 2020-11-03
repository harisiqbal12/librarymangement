package library.assistant.ui.listmember;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.assistant.database.DatabaseHandler;

import javax.xml.crypto.Data;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class memberListController implements Initializable {
    ObservableList<Member> list = FXCollections.observableArrayList();

    @FXML
    private TableView<Member> tableView;
    @FXML
    private TableColumn<Member, String> nameCol;
    @FXML
    private TableColumn<Member, String> idCol;
    @FXML
    private TableColumn<Member, String> emailCol;
    @FXML
    private TableColumn<Member, String> mobileCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        try {
            loadDate();
        }catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void loadDate() throws SQLException{
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String qu = "SELECT * FROM MEMBER";
        ResultSet rs = handler.execQuery(qu);
        while (rs.next()) {
            String name = rs.getString("name");
            String id = rs.getString("id");
            String email = rs.getString("email");
            String mobile = rs.getString("mobile");
            list.add(new Member(name, id, email, mobile));
        }
        tableView.getItems().setAll(list);
    }

    private void initCol() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    }


    public static class Member {
        private final SimpleStringProperty name;
        private final SimpleStringProperty id;
        private final SimpleStringProperty email;
        private final SimpleStringProperty mobile;

        public Member(String name, String id, String email, String mobile) {
            this.name = new SimpleStringProperty(name);
            this.id = new SimpleStringProperty(id);
            this.email = new SimpleStringProperty(email);
            this.mobile = new SimpleStringProperty(mobile);
        }

        public String getName() { return name.get(); }
        public String getId() { return id.get(); }
        public String getEmail() { return email.get(); }
        public String getMobile() { return mobile.get(); }
    }
}
