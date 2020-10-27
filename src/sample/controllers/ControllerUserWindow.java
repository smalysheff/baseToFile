package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Model;
import sample.connection.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControllerUserWindow {
    Connection con;
    public ControllerUserWindow(){
        con = ConnectionUtil.connDB();
    }

    @FXML
    private TextField txtPath;

    @FXML
    private Label lblInfo;

    private String checkboxTable = "";

    List<String> listCourses = new ArrayList<>();
    List<String> listLesson = new ArrayList<>();
    List<String> listTeachers = new ArrayList<>();
    List<String> listUsers = new ArrayList<>();

    //Query
    private final String sqlCourses = "SELECT * FROM courses";
    private final String sqlLessons = "SELECT * FROM lessons";
    private final String sqlTeachers = "SELECT * FROM teachers";
    private final String sqlUsers = "SELECT * FROM users";

    Model model = new Model();

    @FXML
    void onActionEnd(ActionEvent event) {

        model.createFile(checkboxTable, txtPath.getText(), listCourses);

    }

    @FXML
    void onActionPath(ActionEvent event) {
        model.directoryChooser(txtPath);
    }

    @FXML
    void onActionTableCourses(ActionEvent event) throws SQLException {

        checkboxTable = ((CheckBox)event.getSource()).getText();

        CheckBox source = (CheckBox)event.getSource();
        if(source.isSelected()){
            PreparedStatement statement = con.prepareStatement(sqlCourses);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                listCourses.add(String.format("%s %s %s %s\n", resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4)));

            }
        }

    }
    @FXML
    void onActionTableLessons(ActionEvent event) throws SQLException {

    }
    @FXML
    void onActionTableTeachers(ActionEvent event) throws SQLException {

    }
    @FXML
    void onActionTableUsers(ActionEvent event) throws SQLException {

    }

}
