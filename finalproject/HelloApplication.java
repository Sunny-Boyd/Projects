package com.example.finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private Player player;
    private Room currentRoom;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adventure-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Adventure Game!");
        stage.setScene(scene);


        HelloController controller = fxmlLoader.getController();
        controller.initialize();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}