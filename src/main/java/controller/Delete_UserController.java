package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Delete_UserController implements Initializable {

    @FXML
    public Label label;
    @FXML
    public Button butCancel;
    @FXML
    public Button butOK;
    @FXML
    public Text text;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        butOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int id = UserController.getId();
                try {
                    delete(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                Stage stage = (Stage) butCancel.getScene().getWindow();
                stage.close();

            }
        });
        text.setText(UserController.getSelectedItem().getName());

        butCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) butCancel.getScene().getWindow();
                stage.close();
            }
        });
    }
    public void delete(int id) throws SQLException {
        User user = new User();
        UserService userService = new UserServiceImpl();
        user.setId(id);
        userService.delete(id);

    }


    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Button getButCancel() {
        return butCancel;
    }

    public void setButCancel(Button butCancel) {
        this.butCancel = butCancel;
    }

    public Button getButOK() {
        return butOK;
    }

    public void setButOK(Button butOK) {
        this.butOK = butOK;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }
}