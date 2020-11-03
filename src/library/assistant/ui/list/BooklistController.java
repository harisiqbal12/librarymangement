package library.assistant.ui.list;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import library.assistant.database.DatabaseHandler;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BooklistController implements Initializable {
    ObservableList<Book> list = FXCollections.observableArrayList();
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableColumn<Book, String> titleCol;
    @FXML
    private TableColumn<Book, String> idCol;
    @FXML
    private TableColumn<Book, String> authorCol;
    @FXML
    private TableColumn<Book, String> publisherCol;
    @FXML
    private TableColumn<Book, Boolean> availableCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        try {
            loadData();
        } catch (SQLException e) {
            System.out.println(e);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText(e.toString());
            alert.showAndWait();
        }

    }

    private void loadData() throws SQLException {
        DatabaseHandler handler =  DatabaseHandler.getInstance();
        String qu = "SELECT * FROM BOOK";
        ResultSet rs = handler.execQuery(qu);
        while (rs.next()) {
            String title = rs.getString("title");
            String id = rs.getString("id");
            String author = rs.getString("author");
            String publisher = rs.getString("publisher");
            boolean available = rs.getBoolean("isAvail");

            list.add(new Book(title, id, author, publisher, available));
        }

        tableView.getItems().setAll(list);
    }

    private void initCol() {
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        availableCol.setCellValueFactory(new PropertyValueFactory<>("available"));

    }

    public static class Book {
        private final SimpleStringProperty title;
        private final SimpleStringProperty id;
        private final SimpleStringProperty author;
        private final SimpleStringProperty publisher;
        private final SimpleBooleanProperty available;

        Book(String title, String id, String author, String publisher, boolean available) {
            this.title = new SimpleStringProperty(title);
            this.id = new SimpleStringProperty(id);
            this.author = new SimpleStringProperty(author);
            this.publisher = new SimpleStringProperty(publisher);
            this.available = new SimpleBooleanProperty(available);
        }

        public String getTitle() {
            return title.get();
        }

        public String getPublisher() {
            return publisher.get();
        }

        public String getId() {
            return id.get();
        }

        public String getAuthor() {
            return author.get();
        }

        public boolean getAvailable() {
            return available.get();
        }
    }

}
