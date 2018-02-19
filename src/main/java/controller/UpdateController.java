package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateController implements Initializable {
    @FXML
    public Label labelName;
    @FXML
    public Label labelAbout;
    @FXML
    public Button buttonOK;
    @FXML
    public Button buttonCancel;
    @FXML
    public TextArea textArea;
    @FXML
    public TextField textFieldName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        textFieldName.setText(String.valueOf(UserController.getSelectedItem().getName()));
        textArea.setText(String.valueOf(UserController.getSelectedItem().getAbout()));

        buttonOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                User selectedItem = UserController.getSelectedItem();
                try {
                    update(selectedItem);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Stage stage = (Stage) buttonOK.getScene().getWindow();
                stage.close();
            }
        });

        buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) buttonCancel.getScene().getWindow();
                stage.close();
            }
        });
    }

    private void update(User user) throws SQLException {
        user.setAbout(getTextArea().getText());
        user.setName(getTextFieldName().getText());
        UserService userService = new UserServiceImpl();
        userService.update(user);
    }

    public TextField getTextFieldName() {
        return textFieldName;
    }

    public void setTextFieldName(TextField textFieldName) {
        this.textFieldName = textFieldName;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }
}
