package com.project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PixelCoinsWindow extends Application {

    @Override
    public void start(Stage pixelCoinWindow) {

        Button btnAccount = createImageButton("Cuenta", "/com/project/account_pixel.png");
        Button btnLaunch = createImageButton("Lanzador", "/com/project/rocket_pixel.png");
        Button btnShop = createImageButton("Tienda", "/com/project/shop_pixel.png");

        StackPane topPane = new StackPane(btnAccount);
        StackPane centerPane = new StackPane(btnLaunch);
        StackPane bottomPane = new StackPane(btnShop);

        BorderPane.setMargin(topPane, new Insets(20, 10, 10, 10));  // Arriba
        BorderPane.setMargin(bottomPane, new Insets(10, 10, 20, 10)); // Abajo
        BorderPane.setMargin(centerPane, new Insets(10, 10, 10, 10)); // Espaciado general

        BorderPane leftPane = new BorderPane();
        leftPane.getStyleClass().add("border-pane");

        leftPane.setTop(topPane);
        leftPane.setCenter(centerPane);
        leftPane.setBottom(bottomPane);

        leftPane.setPrefWidth(200);

        BorderPane mainPane = new BorderPane();
        mainPane.setLeft(leftPane);

        BorderPane rightPane = new BorderPane();
        rightPane.getStyleClass().add("border-pane2");
        rightPane.setPrefWidth(400);
        mainPane.setCenter(rightPane);

        Scene scene = new Scene(mainPane, 1080, 920);
        scene.getStylesheets().add(getClass().getResource("styles/pixelCoinsWindow.css").toExternalForm());
        pixelCoinWindow.setScene(scene);
        pixelCoinWindow.setTitle("PixelCoinsLauncher");
        pixelCoinWindow.show();

    }

    private Button createImageButton(String text, String imagePath) {
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
        imageView.setFitWidth(50);  // Ajusta el tamaño de la imagen
        imageView.setFitHeight(50);

        // Crear el texto
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: black; -fx-font-size: 12px; -fx-font-family: 'Press Start 2P';");

        // Crear un contenedor VBox con la imagen arriba y el texto abajo
        VBox vbox = new VBox(imageView, label);
        vbox.setAlignment(Pos.CENTER); // Asegurar alineación centrada
        vbox.setSpacing(5); // Espacio entre la imagen y el texto

        // Crear el botón y asignarle el VBox como gráfico
        Button button = new Button();
        button.setGraphic(vbox);
        button.setStyle("-fx-background-color: transparent; -fx-padding: 10px;");

        return button;
    }

}
