package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import se.hkr.e7.Singleton;

import java.io.IOException;
import java.net.URL;

public class PatientLoginController {


    public TextField ssnText;


    public void Back(ActionEvent actionEvent) throws IOException {

        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("Welcome.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    public void Cancel(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void patientLogin(ActionEvent actionEvent) throws IOException {


        //this will set the primary key of tha patient to singleton .
        Singleton.getInstance().setSsn(ssnText.getText());


//        moving to the Dashboard of the patient ....

        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("PatientDashboard.fxml");
        assert resource != null;
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
}

