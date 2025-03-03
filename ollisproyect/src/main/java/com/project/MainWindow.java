package com.project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label lblWelcome = new Label("¡Bienvenido!");
        lblWelcome.setFont(new Font("Arial", 20));
        lblWelcome.setAlignment(Pos.TOP_CENTER);

        Button btnRegister = new Button("Registrarse");
        btnRegister.setFont(new Font("Arial", 12));

        btnRegister.setOnAction(e -> RegisterWindow());

        Button btnLogin = new Button("Iniciar sesión");
        btnLogin.setFont(new Font("Arial", 12));

        btnLogin.setOnAction(e -> LogInWindow());

        VBox layout = new VBox(15);
        layout.getChildren().addAll(lblWelcome, btnRegister, btnLogin);

        Scene scene = new Scene(layout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("OllisLauncher - Menu principal");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private void RegisterWindow() {
        Stage stage = new Stage();

        Label lblName = new Label("Nombre:");
        lblName.setFont(new Font("Arial", 12));

        TextField txtName = new TextField();
        txtName.setFont(new Font("Arial", 12));

        HBox layoutName = new HBox(10);
        layoutName.getChildren().addAll(lblName, txtName);

        Label lblLastName = new Label("Apellidos:");
        lblLastName.setFont(new Font("Arial", 12));

        TextField txtLastName = new TextField();
        txtLastName.setFont(new Font("Arial", 12));

        HBox layoutLastName = new HBox(10);
        layoutLastName.getChildren().addAll(lblLastName, txtLastName);

        Label lblUserName = new Label("Nombre de usuario:");
        lblUserName.setFont(new Font("Arial", 12));

        TextField txtUserName = new TextField();
        txtUserName.setFont(new Font("Arial", 12));

        HBox layoutUserName = new HBox(10);
        layoutUserName.getChildren().addAll(lblUserName, txtUserName);

        Label lblPassword = new Label("Contraseña:");
        lblPassword.setFont(new Font("Arial", 12));

        PasswordField txtPassword = new PasswordField();
        txtPassword.setFont(new Font("Arial", 12));

        HBox layoutPassword = new HBox(10);
        layoutPassword.getChildren().addAll(lblPassword, txtPassword);

        Label lblEmail = new Label("Email:");
        lblEmail.setFont(new Font("Arial", 12));

        TextField txtEmail = new TextField();
        txtEmail.setFont(new Font("Arial", 12));

        HBox layoutEmail = new HBox(10);
        layoutEmail.getChildren().addAll(lblEmail, txtEmail);

        Label lblBirthday = new Label("Fecha de nacimiento:");
        lblBirthday.setFont(new Font("Arial", 12));

        DatePicker datePicker = new DatePicker();

        HBox layoutBirthday = new HBox(10);
        layoutBirthday.getChildren().addAll(lblBirthday, datePicker);

        Button btnCancel = new Button("Cancelar");
        btnCancel.setFont(new Font("Arial", 12));

        btnCancel.setOnAction(e -> stage.close());

        Button btnAccept = new Button("Aceptar");
        btnAccept.setFont(new Font("Arial", 12));

        HBox layoutButtons = new HBox(10);
        layoutButtons.getChildren().addAll(btnAccept, btnCancel);

        VBox mainLayout = new VBox(15);
        mainLayout.getChildren().addAll(layoutName, layoutLastName, layoutUserName, layoutPassword, layoutEmail, layoutBirthday, layoutButtons);

        Scene scene = new Scene(mainLayout, 400, 400);
        stage.setScene(scene);
        stage.setTitle("OllisLauncher - Registrarse");
        stage.showAndWait();
    }

    private void LogInWindow() {
        Stage stage = new Stage();

        Label lblUserName = new Label("Nombre de usuario:");
        lblUserName.setFont(new Font("Arial", 12));

        TextField txtUserName = new TextField();
        txtUserName.setFont(new Font("Arial", 12));

        HBox layoutUserName = new HBox(10);
        layoutUserName.getChildren().addAll(lblUserName, txtUserName);

        Label lblPassword = new Label("Contraseña:");
        lblPassword.setFont(new Font("Arial", 12));

        PasswordField txtPassword = new PasswordField();
        txtPassword.setFont(new Font("Arial", 12));

        HBox layoutPassword = new HBox(10);
        layoutPassword.getChildren().addAll(lblPassword, txtPassword);

        Button btnCancel = new Button("Cancelar");
        btnCancel.setFont(new Font("Arial", 12));

        btnCancel.setOnAction(e -> stage.close());

        Button btnAccept = new Button("Aceptar");
        btnAccept.setFont(new Font("Arial", 12));

        HBox layoutButtons = new HBox(10);
        layoutButtons.getChildren().addAll(btnAccept, btnCancel);

        VBox mainLayout = new VBox(15);
        mainLayout.getChildren().addAll(layoutUserName, layoutPassword, layoutButtons);

        Scene scene = new Scene(mainLayout, 400, 400);
        stage.setScene(scene);
        stage.setTitle("OllisLauncher - Iniciar sesión");
        stage.showAndWait();
    }
}
