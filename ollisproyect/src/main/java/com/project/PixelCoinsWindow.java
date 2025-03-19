package com.project;

import com.project.model.User;
import com.project.repository.UserRepository;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PixelCoinsWindow extends Application {

    private UserRepository userRepository;

    private MainWindow mainWindow;

    public PixelCoinsWindow(MainWindow mainWindow) {
        this.userRepository = new UserRepository();
        this.mainWindow = mainWindow;

    }

    private BorderPane rightPane;

    @Override
    public void start(Stage pixelCoinWindow) {

        Button btnAccount = createImageButton("Cuenta", "/com/project/account_pixel.png");
        Button btnLaunch = createImageButton("Lanzador", "/com/project/rocket_pixel.png");
        Button btnShop = createImageButton("Tienda", "/com/project/shop_pixel.png");

        btnAccount.setOnAction(e -> showAccountScene());

        setCursorChange(btnAccount);
        setCursorChange(btnLaunch);
        setCursorChange(btnShop);

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

        rightPane = new BorderPane();
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
        label.setStyle("-fx-text-fill: black; -fx-font-size: 12px; -fx-border-color: black; -fx-border-width: 5px; -fx-background-color: orange; -fx-font-family: 'Press Start 2P';");

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

    public void showAccountScene() {

        Label lblAccount = new Label("Cuenta");
        VBox layoutAccount = new VBox(10);

        layoutAccount.setAlignment(Pos.TOP_CENTER);
        layoutAccount.getChildren().addAll(lblAccount);

        Label lblUserName = new Label("Nombre de usuario: ");
        TextField txtUserName = new TextField();
        txtUserName.setEditable(false);

        HBox layoutUserName = new HBox(10);
        layoutUserName.getChildren().addAll(lblUserName, txtUserName);
        layoutUserName.setAlignment(Pos.CENTER);

        Label lblPassword = new Label("Contraseña: ");
        PasswordField txtPassword = new PasswordField();
        txtPassword.setEditable(false);

        TextField txtPasswordVisible = new TextField();

        txtPasswordVisible.setManaged(false);
        txtPasswordVisible.setVisible(false);

        // Sincronización de ambos campos
        txtPasswordVisible.textProperty().bindBidirectional(txtPassword.textProperty());

        Button btnViewPassword = new Button("👁");
        btnViewPassword.getStyleClass().add("button-password");

        btnViewPassword.setOnAction(e -> {
            showDisguisePassword(txtPassword, txtPasswordVisible);
        });

        HBox layoutPassword = new HBox(10);
        layoutPassword.getChildren().addAll(lblPassword, txtPassword, txtPasswordVisible, btnViewPassword);
        layoutPassword.setAlignment(Pos.CENTER);

        Label lblEmail = new Label("Email: ");
        TextField txtEmail = new TextField();
        txtEmail.setEditable(false);

        HBox layoutEmail = new HBox(10);
        layoutEmail.getChildren().addAll(lblEmail, txtEmail);
        layoutEmail.setAlignment(Pos.CENTER);

        VBox layoutUser = new VBox(10);
        layoutUser.getChildren().addAll(layoutUserName, layoutPassword, layoutEmail);
        layoutUser.setAlignment(Pos.CENTER);

        showUserInfo(txtUserName, txtEmail, txtPassword);

        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(layoutAccount, layoutUser);
        mainLayout.setAlignment(Pos.CENTER);

        rightPane.setCenter(new StackPane(mainLayout));
    }

    private void setCursorChange(Button button) {
        button.setOnMouseEntered(e -> button.setCursor(Cursor.HAND));
        button.setOnMouseExited(e -> button.setCursor(Cursor.DEFAULT));
    }

    public void showUserInfo(TextField txtName, TextField txtEmail, PasswordField txtPassword) {
        if (mainWindow.getCurrentUser() != null) {
            User user = mainWindow.getCurrentUser();
            txtName.setText(user.getUserName());
            txtEmail.setText(user.getEmail());
            txtPassword.setText(user.getPassword());
        } else {
            txtName.setText("No hay usuario autenticado");
            txtEmail.setText("");
            txtPassword.setText("");
        }
    }

    private void showDisguisePassword(PasswordField passwordField, TextField textField) {
        if (passwordField.isVisible()) {
            textField.setText(passwordField.getText());
            textField.setManaged(true);
            textField.setVisible(true);
            passwordField.setManaged(false);
            passwordField.setVisible(false);
        } else {
            passwordField.setText(textField.getText());
            passwordField.setManaged(true);
            passwordField.setVisible(true);
            textField.setManaged(false);
            textField.setVisible(false);
        }
    }

}
