package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Patient;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class ViewPatientsDoctorController extends Controller {

    public TableView patientTableView;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/ViewPatientsDoctor.fxml");
        List<Patient> patients = DatabaseHandler.loadAll(Patient.class);

        for (Map.Entry<String, String> entry : Map.ofEntries(
                new AbstractMap.SimpleEntry<>("name", "Name"),
                new AbstractMap.SimpleEntry<>("ssn", "SSN"),
                new AbstractMap.SimpleEntry<>("email", "Email"),
                new AbstractMap.SimpleEntry<>("address", "Address")
        ).entrySet()) {
            TableColumn<Patient, String> tableColumn = new TableColumn<>(entry.getValue());
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(entry.getKey()));
            patientTableView.getColumns().add(tableColumn);
        }

        patientTableView.getItems().addAll(patients);
    }
}

