package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.connection.ConnectionUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerSignIn {

    Connection conn = null;

    public ControllerSignIn() {
        conn = ConnectionUtil.connDB();
    }

    @FXML
    private Label lblInfo;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button buttonInput;

    @FXML
    public void onActionInput(ActionEvent event) {

        boolean start = true;

        String login = txtLogin.getText();
        String password = txtPassword.getText();

        String sql = "SELECT login, password FROM users WHERE login = ? AND password = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                if (login.equals(resultSet.getString("login")) &&
                        password.equals(resultSet.getString("password"))) {
                    start = false;
                    buttonInput.getScene().getWindow().hide();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/sample/view/userWindow.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle("User Window");
                    assert root != null;
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                }
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        if (start) {
            lblInfo.setTextFill(Color.RED);
            lblInfo.setText("login or password incorrect");
        }
    }
}
