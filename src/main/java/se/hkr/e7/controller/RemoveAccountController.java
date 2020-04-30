package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import se.hkr.e7.model.*;

import java.util.Objects;

public class RemoveAccountController extends Controller {

    public TextField ssnField;
    public Button removeButton;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/RemoveAccount.fxml");
        removeButton.setOnAction(this::remove);
    }

    public void remove(ActionEvent actionEvent) {
        try {
            if (!Person.isValidSsn(ssnField.getText())) {
                showError("Please input a valid SSN.");
                return;
            }

            Employee employee = DatabaseHandler.load(Employee.class, ssnField.getText());
            Patient patient = DatabaseHandler.load(Patient.class, ssnField.getText());

            if (employee == null && patient == null) {
                showError("No one with that SSN exists.");
                return;
            }

            try {
                DatabaseHandler.delete(Objects.requireNonNullElse(employee, patient));
                showConfirmation("Deleted", "Done");
            } catch (Exception exception) {
                if (employee != null) {
                    employee.clear();
                    DatabaseHandler.save(employee);
                }
                if (patient != null) {
                    patient.clear();
                    DatabaseHandler.save(patient);
                }
            } finally {
                showConfirmation("Deleted", "Done");
            }
        } catch (Exception exception) {
            showError("The account could not be deleted.");
        }

    }
}