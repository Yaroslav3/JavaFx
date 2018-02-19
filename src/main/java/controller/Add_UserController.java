package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Add_UserController implements Initializable {
    @FXML
    private TextField fieldName;
    @FXML
    private TextArea areaAbout;
    @FXML
    private Button buttonAddUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonAddUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    save();
                    Stage stage = (Stage) buttonAddUser.getScene().getWindow();
                    stage.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void save() throws SQLException {
        User user = new User();
        user.setName(getFieldName().getText());
        user.setAbout(getAreaAbout().getText());
        UserService userService = new UserServiceImpl();
        userService.save(user);
    }

    public TextField getFieldName() {
        return fieldName;
    }

    public void setFieldName(TextField fieldName) {
        this.fieldName = fieldName;
    }

    public TextArea getAreaAbout() {
        return areaAbout;
    }

    public void setAreaAbout(TextArea areaAbout) {
        this.areaAbout = areaAbout;
    }
}
