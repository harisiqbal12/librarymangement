package library.assistant.database;

import javax.swing.*;
import java.sql.*;

public final class DatabaseHandler {

    private static final String DB_URL = "jdbc:derby:database;create=true";
    private static DatabaseHandler handler = null;
    private static Connection conn = null;
    private static Statement stmt = null;

    private DatabaseHandler() {
        createConnection();
        setupBookTable();
        setupMemberTable();
    }

    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }

        return handler;
    }


    void createConnection() {

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            conn = DriverManager.getConnection(DB_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void setupBookTable() {
        String TABLE_NAME = "BOOK";
        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + " already exists. Ready for go!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "("
                        + "    id varchar(200) primary key, \n"
                        + "    title varchar(200),\n"
                        + "    author varchar(200),\n"
                        + "    publisher varchar(100),\n"
                        + "    isAvail boolean default true"
                        + " )");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage() + "  --- setupDatabase");
        }
    }

    void setupMemberTable() {
        String TABLE_NAME = "MEMBER";
        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + " already exists. Ready for go!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "("
                        + "    id varchar(200) primary key, \n"
                        + "    name varchar(200),\n"
                        + "    mobile varchar(20),\n"
                        + "    email varchar(100) \n"
                        + " )");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage() + " ---setupMember Table");
        }
    }

    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler " + ex.getLocalizedMessage());
            return null;
        }
        return result;
    }

    public boolean execAction(String qu) {
        try {
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage(), "ERROR OCCURED", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execAction:dataHandler " + e.getLocalizedMessage());
            return false;
        }
    }


}
