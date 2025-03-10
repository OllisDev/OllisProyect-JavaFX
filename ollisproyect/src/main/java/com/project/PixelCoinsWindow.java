package com.project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PixelCoinsWindow extends Application {

    @Override
    public void start(Stage pixelCoinWindow) {
        BorderPane bpButtons = new BorderPane();
        Button btnAccount = new Button("Cuenta");
        HBox layoutAccount = new HBox(10);
        layoutAccount.getChildren().addAll(btnAccount);

        VBox layoutButtons = new VBox();
        layoutButtons.getChildren().addAll(layoutAccount);
        bpButtons.setLeft(layoutButtons);
        bpButtons.setAlignment(layoutButtons, Pos.CENTER);

        Scene scene = new Scene(bpButtons, 800, 800);

        pixelCoinWindow.setScene(scene);
        pixelCoinWindow.setTitle("PixelCoinsLauncher");
        pixelCoinWindow.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
