package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model {

    public void directoryChooser(TextField textField){
        Desktop desktop = Desktop.getDesktop();
        DirectoryChooser dir = new DirectoryChooser();
        dir.setInitialDirectory(new File("c:/Users/user/Desktop"));
        dir.setTitle("Резервное копирование БД");
        File file = dir.showDialog(new Stage());
        if(!(file == null)){
            textField.setText(file.getAbsolutePath());
        } else textField.setText("");
    }

    public void createFile(String fileName, String path, List<String> list){
        File file = new File(path + "/" + fileName + ".txt");
        try {
            boolean isCreateFile = file.createNewFile();
            if(isCreateFile){
                fileWriter(file, list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fileWriter (File file, List<String> list){
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(list.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<String> createQuery(String tableName, int quantityTableField, Connection connection){
        ObservableList<String> list = FXCollections.observableArrayList();

        String sql = "select * from " + tableName;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                for (int i = 1; i <= quantityTableField; i++) {
                    list.add(resultSet.getString(i));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return list;
    }



    public int quantityTableField(String table, Connection connection){

        int result = 0;

        String sql = "SELECT * from " + table;

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            result = rsmd.getColumnCount();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }
}