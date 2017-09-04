package edu.maranatha.controller;

import edu.maranatha.entity.Department;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Robby
 */
public class SecondFormController implements Initializable {

    @FXML
    private TableColumn<Department, String> colName;
    private MainFormController mainController;
    @FXML
    private TableView<Department> tableDepartment;

    public void setMainController(MainFormController mainController) {
        this.mainController = mainController;
        tableDepartment.setItems(mainController.getDepartments());
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

}
