package sample.controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import sample.Model;
import sample.connection.ConnectionUtil;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerUserWindow {
    Connection con;
    public ControllerUserWindow(){
        con = ConnectionUtil.connDB();
    }


    @FXML
    private ComboBox<String> tableComboBox;

    @FXML
    private TextField txtPath;

    @FXML
    private Label lblInfo;

    Model model = new Model();

    ObservableList<String> queryTable;

    ObservableList<String> tableList = FXCollections.observableArrayList();

    //private String strComboBox = "";

    @FXML
    void onActionCreate(ActionEvent event) {
        lblInfo.setText("");
        model.createFile(tableComboBox.getValue(), txtPath.getText(), queryTable);
        lblInfo.setTextFill(Color.BLUE);
        lblInfo.setText("Резервная копис таблицы " + tableComboBox.getValue() + " создалась");
    }

    @FXML
    void onActionComboBox(ActionEvent event){
        if(!tableComboBox.isDisable()) {
            int countTableField = model.quantityTableField(tableComboBox.getValue(), con);
            queryTable = model.createQuery(tableComboBox.getValue(), countTableField, con);
            lblInfo.setTextFill(Color.GREEN);
            lblInfo.setText("SQL запрос по выборке данных таблицы " + tableComboBox.getValue() + "\nуспешно сформировался");
        }
    }

    @FXML
    void onActionDirectoryChooser(ActionEvent event) {
        model.directoryChooser(txtPath);
    }

    @FXML
    public void initialize(){

        tableComboBox.setStyle("-fx-font-size: 20");
        tableComboBox.setItems(tableList);

        try {
            DatabaseMetaData tableMetaData = con.getMetaData();
            ResultSet courses = tableMetaData.getTables("courses", null, null, null);
            while(courses.next()){
                tableList.add(courses.getString(3));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
