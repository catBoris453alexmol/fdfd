package ru.sapteh;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Reg.fxml"));
        stage.setTitle("SignIn");
        stage.setScene(new Scene(root));
        stage.getIcons().addAll(new Image("/image/unnamed.png"));
        stage.show();

    }
    public static void main(String[] args) {
        launch(args);

    }
}

