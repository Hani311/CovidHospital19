package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import se.hkr.e7.model.*;

public class AdminDashboardController extends Controller {

    public Button addPatientButton;
    public Button addEmployeeButton;
    public Button viewResultsButton;
    public Button viewPatientButton;
    public Button viewStaffButton;
    public Button removeStaffButton;
    public Button removeAdminButton;
    public Button searchButton;
    public TextField searchText;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AdminDashboard.fxml");
        addEmployeeButton.setOnAction(actionEvent -> loadScene("view/AddEmployee.fxml", actionEvent));
        addPatientButton.setOnAction(actionEvent -> loadScene("view/AddPatient.fxml", actionEvent));
        viewResultsButton.setOnAction(actionEvent -> loadScene("view/ViewResults.fxml", actionEvent));
        viewPatientButton.setOnAction(actionEvent -> loadScene("view/ViewPatients.fxml", actionEvent));
        viewStaffButton.setOnAction(actionEvent -> loadScene("view/ViewStaff.fxml", actionEvent));
        removeStaffButton.setOnAction(actionEvent -> loadScene("view/RemoveStaff.fxml", actionEvent));
        removeAdminButton.setOnAction(actionEvent -> loadScene("view/RemoveAdmin.fxml", actionEvent));
    }

    public void search(ActionEvent actionEvent) {
        if (Person.isValidSsn(searchText.getText())) {
            Patient patient = DatabaseHandler.load(Patient.class, searchText.getText());
            Employee employee = DatabaseHandler.load(Employee.class, searchText.getText());

            if (patient == null && employee == null) {
                showError("There is no one with that ssn in the system.");
                return;
            }

            Singleton singleton = Singleton.getInstance();
            if (patient == null) {
                singleton.setEmployee(employee);
                singleton.setPatient(null);
            } else {
                singleton.setPatient(patient);
                singleton.setEmployee(null);
            }

            loadScene("view/Search.fxml", actionEvent);
        } else {
            showError("input in YYMMDDXXXX form");
        }
    }
}



