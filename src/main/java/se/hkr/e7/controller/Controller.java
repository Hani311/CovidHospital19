package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public abstract class Controller {

    public void loadScene(String name, ActionEvent actionEvent) {
        try {
            Node node = (Node) actionEvent.getSource();
            Scene currScene = node.getScene();
            Stage stage = (Stage) currScene.getWindow();
            URL resource = getClass().getClassLoader().getResource(name);
            Parent root = FXMLLoader.load(resource);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            if (!name.equals("view/Welcome.fxml")) {
                System.out.println("not");
                loadScene("view/Welcome.fxml", actionEvent);
            } else {
                new Alert(Alert.AlertType.ERROR, "There was an error loading the default scene.", ButtonType.CLOSE)
                        .showAndWait();
                System.exit(1);
            }
        }
    }
}
