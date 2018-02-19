package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class UserController implements Initializable {
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, Integer> tableColumn1;
    @FXML
    private TableColumn<User, String> tableColumn2;
    @FXML
    private TableColumn<User, String> tableColumn3;
    @FXML
    private Button button;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonUpdate;

    private static int id;
    private static User selectedItem;


    private ObservableList<User> userList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            init();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        userTable.getSelectionModel().getSelectedIndex();
        button.setOnAction(this::add);
        userTable.getSelectionModel().getSelectedIndex();
        buttonDelete.setOnAction(this::deleteUser);
        buttonUpdate.setOnAction(this::update);

        userTable.setRowFactory(new Callback<TableView<User>, TableRow<User>>() {
            @Override
            public TableRow<User> call(TableView<User> param) {
                TableRow<User> row = new TableRow<>();
                MenuItem remove = new MenuItem("Remove");
                remove.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            delete(userTable.getSelectionModel().getSelectedItem().getId());
                            load();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
                ContextMenu menu = new ContextMenu(remove);
                row.contextMenuProperty().setValue(menu);
                return row;
            }
        });
    }



    private void init() throws SQLException {
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("about"));
        load();
    }

    private void load() throws SQLException {
        userList.clear();
        UserService userService = new UserServiceImpl();
        List<User> all = userService.findAll();
        userList.addAll(all);
        userTable.setItems(userList);
    }

    private void delete(int id) throws SQLException {
        User user = new User();
        UserService userService = new UserServiceImpl();
        user.setId(id);
        userService.delete(id);

    }
//    private void update(User user) throws SQLException {
//        user.setAbout(getTextArea().getText());
//        user.setName(getTextFieldName().getText());
//        UserService userService = new UserServiceImpl();
//        userService.update(user);
//    }


    private void add(ActionEvent event) {
        final FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/add_user.fxml"));

        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Stage stage = new Stage();
        assert parent != null;
        Scene value = new Scene(parent);
        value.getStylesheets().add("css/main.css");
        stage.setScene(value);
        stage.initModality(Modality.WINDOW_MODAL);
        Window window = ((Node) event.getSource()).getScene().getWindow();
        stage.initOwner(window);
        stage.show();
        stage.setOnHidden(e -> {
            try {
                load();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void deleteUser(ActionEvent event) {
        int selectedIndex = userTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            id = userTable.getSelectionModel().getSelectedItem().getId();
            selectedItem = userTable.getSelectionModel().getSelectedItem();
            userTable.getItems().remove(selectedIndex);
            final FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/delete_user.fxml"));
            Parent parent = null;
            try {
                parent = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            final Stage stage = new Stage();
            assert parent != null;
            Scene value = new Scene(parent);
            value.getStylesheets().add("css/main.css");
            stage.setScene(value);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setOnHiding(e -> {
                try {
                    load();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            });
            Window window = ((Node) event.getSource()).getScene().getWindow();
            stage.initOwner(window);
            stage.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No selection");
            alert.setHeaderText("No selected");
            alert.setContentText("Please select the row in the table to delete.");
            alert.showAndWait();
        }

    }

    private void update(ActionEvent event) {
        int selectedIndex = userTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            selectedItem = userTable.getSelectionModel().getSelectedItem();
            userTable.getItems().remove(selectedIndex);
            final FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/update.fxml"));
            Parent parent = null;
            try {
                parent = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            final Stage stage = new Stage();
            assert parent != null;
            Scene value = new Scene(parent);
            value.getStylesheets().add("css/main.css");
            stage.setScene(value);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setOnHiding(e -> {
                try {
                    load();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            });
            Window window = ((Node) event.getSource()).getScene().getWindow();
            stage.initOwner(window);
            stage.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("no row selected");
            alert.setContentText("Please select a row in the table.");
            alert.showAndWait();
        }
    }

    public static int getId() {
        return id;
    }

    public static User getSelectedItem() {
        return selectedItem;
    }
}
