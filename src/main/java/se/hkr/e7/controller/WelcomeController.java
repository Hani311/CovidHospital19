package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import se.hkr.e7.model.Singleton;

public class WelcomeController extends Controller {

    public Button loginButton;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/Welcome.fxml");
        loginButton.setOnAction(actionEvent -> loadScene("view/Login.fxml", actionEvent));
    }
}
