package sample;

import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            FileWriter fw = new FileWriter(file);
            fw.write(list.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
