package edu.maranatha.controller;

import edu.maranatha.MainApp;
import edu.maranatha.entity.Department;
import edu.maranatha.entity.Student;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Robby
 */
public class MainFormController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private TableColumn<Student, String> colDepartment;
    @FXML
    private TableColumn<Student, String> colId;
    @FXML
    private TableColumn<Student, String> colName;
    @FXML
    private ComboBox<Department> comboDepartment;
    private Stage departmentStage;
    private ObservableList<Department> departments;
    private ObservableList<Student> students;
    @FXML
    private TableView<Student> tableStudent;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtLastName;

    @FXML
    private void btnResetAction(ActionEvent event) {
        txtId.clear();
        txtFirstName.clear();
        txtLastName.clear();
    }

    @FXML
    private void btnSaveAction(ActionEvent event) {
        //  Action for Save Button
    }

    @FXML
    private void btnUpdateAction(ActionEvent event) {
        //  Action for Update Button
    }

    public ObservableList<Department> getDepartments() {
        if (departments == null) {
            departments = FXCollections.observableArrayList();
            departments.add(new Department("Kedokteran"));
            departments.add(new Department("Teknik Sipil"));
            departments.add(new Department("Teknik Elektro"));
            departments.add(new Department("Teknik Industri"));
            departments.add(new Department("Teknik Informatika"));
            departments.add(new Department("Sistem Informasi"));
            departments.add(new Department("Desain Komunikasi Visual"));
            departments.add(new Department("Seni Rupa Murni"));
        }
        return departments;
    }

    public ObservableList<Student> getStudents() {
        if (students == null) {
            students = FXCollections.observableArrayList();
        }
        return students;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboDepartment.setItems(getDepartments());
        tableStudent.setItems(getStudents());
        colDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory((
                TableColumn.CellDataFeatures<Student, String> param) -> new SimpleStringProperty(param.getValue().
                getFirstName() + " " + param.getValue().getLastName()));
        //  Make sure only number that can be accepted in this field
        txtId.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d+")) {
                    if (((StringProperty) observable).getValue() == null
                            || ((StringProperty) observable).getValue().isEmpty()) {
                        oldValue = "";
                    }
                    ((StringProperty) observable).setValue(oldValue);
                }
            }
        });
    }

    @FXML
    private void mnAboutAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Created by Robby");
        alert.setTitle(Alert.AlertType.INFORMATION.toString());
        alert.showAndWait();
    }

    @FXML
    private void mnCloseAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void mnShowDepAction(ActionEvent event) {
        if (departmentStage == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("view/SecondForm.fxml"));
                AnchorPane root = loader.load();
                Scene scene = new Scene(root);
                departmentStage = new Stage();
                departmentStage.setScene(scene);
                departmentStage.setTitle("Department Management");
                departmentStage.initOwner(borderPane.getScene().getWindow());
                departmentStage.initModality(Modality.WINDOW_MODAL);
                departmentStage.show();
            } catch (IOException ex) {
                Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (departmentStage.isFocused()) {
            departmentStage.toFront();
        } else {
            departmentStage.show();
        }
    }

}
