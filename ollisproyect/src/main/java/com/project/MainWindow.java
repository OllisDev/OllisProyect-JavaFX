package com.project;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.project.model.User;
import com.project.repository.UserRepository;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainWindow extends Application {

    private UserRepository userRepository;

    public MainWindow() {
        this.userRepository = new UserRepository();
    }

    @Override
    public void start(Stage primaryStage) {

        Label lblWelcome = new Label("¡Bienvenido a OllisLauncher!");
        lblWelcome.setAlignment(Pos.TOP_CENTER);

        Button btnRegister = new Button("Registrarse");

        btnRegister.setOnAction(e -> RegisterWindow());

        Button btnLogin = new Button("Iniciar sesión");

        btnLogin.setOnAction(e -> LogInWindow());

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(lblWelcome, btnRegister, btnLogin);

        Scene scene = new Scene(layout, 1000, 800);
        scene.getStylesheets().add(getClass().getResource("styles/mainWindow.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("OllisLauncher - Menu principal");
        primaryStage.show();

    }

    public void RegisterWindow() {
        Stage stage = new Stage();

        Label lblName = new Label("Nombre:");
        lblName.setFont(new Font("Arial", 12));

        TextField txtName = new TextField();
        txtName.setFont(new Font("Arial", 12));

        Label errorName = createErrorLabel();

        HBox layoutName = new HBox(10);
        layoutName.getChildren().addAll(lblName, txtName, errorName);

        Label lblLastName = new Label("Apellidos:");
        lblLastName.setFont(new Font("Arial", 12));

        TextField txtLastName = new TextField();
        txtLastName.setFont(new Font("Arial", 12));

        Label errorLastName = createErrorLabel();

        HBox layoutLastName = new HBox(10);
        layoutLastName.getChildren().addAll(lblLastName, txtLastName, errorLastName);

        Label lblUserName = new Label("Nombre de usuario:");
        lblUserName.setFont(new Font("Arial", 12));

        TextField txtUserName = new TextField();
        txtUserName.setFont(new Font("Arial", 12));

        Label errorUserName = createErrorLabel();

        HBox layoutUserName = new HBox(10);
        layoutUserName.getChildren().addAll(lblUserName, txtUserName, errorUserName);

        Label lblPassword = new Label("Contraseña:");
        lblPassword.setFont(new Font("Arial", 12));

        PasswordField txtPassword = new PasswordField();
        txtPassword.setFont(new Font("Arial", 12));

        Label errorPassword = createErrorLabel();

        HBox layoutPassword = new HBox(10);
        layoutPassword.getChildren().addAll(lblPassword, txtPassword, errorPassword);

        Label lblEmail = new Label("Email:");
        lblEmail.setFont(new Font("Arial", 12));

        TextField txtEmail = new TextField();
        txtEmail.setFont(new Font("Arial", 12));

        Label errorEmail = createErrorLabel();

        HBox layoutEmail = new HBox(10);
        layoutEmail.getChildren().addAll(lblEmail, txtEmail, errorEmail);

        Label lblBirthday = new Label("Fecha de nacimiento:");
        lblBirthday.setFont(new Font("Arial", 12));

        DatePicker datePicker = new DatePicker();

        Label errorBirthday = createErrorLabel();

        HBox layoutBirthday = new HBox(10);
        layoutBirthday.getChildren().addAll(lblBirthday, datePicker, errorBirthday);

        Button btnCancel = new Button("Cancelar");
        btnCancel.setFont(new Font("Arial", 12));

        btnCancel.setOnAction(e -> stage.close());

        Button btnAccept = new Button("Aceptar");
        btnAccept.setFont(new Font("Arial", 12));
        btnAccept.setOnAction(e -> {
            boolean isValid = true;

            if (txtName.getText().trim().isEmpty()) {
                errorName.setText("El nombre no puede estar vacío");
                isValid = false;
            } else if (!txtName.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
                errorName.setText("El nombre solo debe contener letras");
            } else {
                errorName.setText("");
            }

            if (txtLastName.getText().trim().isEmpty()) {
                errorLastName.setText("Los apellidos no pueden estar vacíos");
                isValid = false;
            } else if (!txtLastName.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
                errorLastName.setText("Los apellidos solo deben contener letras");

            } else {
                errorLastName.setText("");
            }

            if (txtUserName.getText().trim().isEmpty()) {
                errorUserName.setText("El nombre de usuario no puede estar vacío");
                isValid = false;
            } else {
                errorUserName.setText("");
            }

            if (txtPassword.getText().trim().isEmpty()) {
                errorPassword.setText("La contraseña no puede estar vacía");
                isValid = false;
            } else if (txtPassword.getText().length() < 8) {
                errorPassword.setText("La contraseña debe contener 8 caracteres");
                isValid = false;
            } else {
                errorPassword.setText("");
            }

            if (txtEmail.getText().trim().isEmpty()) {
                errorEmail.setText("El email no puede estar vacío");
                isValid = false;
            } else if (!txtEmail.getText().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                errorEmail.setText("Formato de email no válido");
                isValid = false;
            } else {
                errorEmail.setText("");
            }

            if (datePicker.getValue() == null) {
                errorBirthday.setText("La fecha de nacimiento es obligatorio");
                isValid = false;
            } else {
                errorBirthday.setText("");
            }

            if (isValid) {

                LocalDate birthdayLocalDate = datePicker.getValue();
                LocalDateTime birthday = birthdayLocalDate.atStartOfDay();

                User user = new User(txtName.getText(), txtLastName.getText(), txtUserName.getText(), txtPassword.getText(), txtEmail.getText(), birthday);

                boolean success = userRepository.CreateUser(user);

                if (success) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Registro exitoso");
                    alert.setTitle("Información");
                    alert.showAndWait();
                    stage.close();
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Error al registrar el usuario, debido a que ya existe");
                    alert.setTitle("Error");
                    alert.showAndWait();
                    stage.close();
                }
            }

        });

        HBox layoutButtons = new HBox(10);
        layoutButtons.getChildren().addAll(btnAccept, btnCancel);

        VBox mainLayout = new VBox(15);
        mainLayout.getChildren().addAll(layoutName, layoutLastName, layoutUserName, layoutPassword, layoutEmail, layoutBirthday, layoutButtons);

        Scene scene = new Scene(mainLayout, 400, 400);
        scene.getStylesheets().add(getClass().getResource("styles/registerWindow.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("OllisLauncher - Registrarse");
        stage.showAndWait();
    }

    public void LogInWindow() {
        Stage stage = new Stage();

        Label lblUserName = new Label("Nombre de usuario:");
        lblUserName.setFont(new Font("Arial", 12));

        TextField txtUserName = new TextField();
        txtUserName.setFont(new Font("Arial", 12));

        Label errorUsername = createErrorLabel();

        HBox layoutUserName = new HBox(10);
        layoutUserName.getChildren().addAll(lblUserName, txtUserName, errorUsername);

        Label lblPassword = new Label("Contraseña:");
        lblPassword.setFont(new Font("Arial", 12));

        PasswordField txtPassword = new PasswordField();
        txtPassword.setFont(new Font("Arial", 12));

        Label errorPassword = createErrorLabel();

        HBox layoutPassword = new HBox(10);
        layoutPassword.getChildren().addAll(lblPassword, txtPassword, errorPassword);

        Button btnCancel = new Button("Cancelar");
        btnCancel.setFont(new Font("Arial", 12));

        btnCancel.setOnAction(e -> stage.close());

        Button btnAccept = new Button("Aceptar");
        btnAccept.setFont(new Font("Arial", 12));

        btnAccept.setOnAction(e -> {
            boolean isValid = true;

            if (txtUserName.getText().trim().isEmpty()) {
                errorUsername.setText("El nombre de usuario no debe estar vacío");
                isValid = false;
            } else {
                errorUsername.setText("");
            }

            if (txtPassword.getText().trim().isEmpty()) {
                errorPassword.setText("La contraseña no debe estar vacío");
                isValid = false;
            } else if (txtPassword.getText().length() < 8) {
                errorPassword.setText("La contraseña debe tener mínimo 8 caracteres");
                isValid = false;
            } else {
                errorPassword.setText("");
            }

            if (isValid) {
                boolean success = userRepository.ValidateUserLogIn(txtUserName, txtPassword);

                if (success) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Inicio de sesión exitoso");
                    alert.setTitle("Información");
                    alert.showAndWait();
                    stage.close();
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Error al iniciar sesión, debido a que el usuario no existe");
                    alert.setTitle("Error");
                    alert.showAndWait();
                    stage.close();
                }
            }
        });

        HBox layoutButtons = new HBox(10);
        layoutButtons.getChildren().addAll(btnAccept, btnCancel);

        VBox mainLayout = new VBox(15);
        mainLayout.getChildren().addAll(layoutUserName, layoutPassword, layoutButtons);

        Scene scene = new Scene(mainLayout, 600, 800);
        stage.setScene(scene);
        stage.setTitle("OllisLauncher - Iniciar sesión");
        stage.showAndWait();
    }

    private Label createErrorLabel() {
        Label label = new Label();
        label.setFont(new Font("Arial", 10));
        label.setTextFill(Color.RED);
        return label;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
