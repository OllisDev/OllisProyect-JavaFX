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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación PixelCoinsLauncher. Representa la ventana
 * principal del programa y gestiona las acciones relacionadas con el registro e
 * inicio de sesión de usuarios.
 */
public class MainWindow extends Application {

    private UserRepository userRepository;

    private User currentUser;

    /**
     * Obtiene el usuario actualmente autenticado.
     *
     * @return El usuario actual.
     */
    public User getCurrentUser() {
        return this.currentUser;
    }

    /**
     * Establece el usuario actualmente autenticado.
     *
     * @param user El usuario a establecer como actual.
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    private PixelCoinsWindow pixelCoinsWindow;

    /**
     * Constructor de la clase MainWindow. Inicializa el repositorio de
     * usuarios, la ventana de PixelCoins y el usuario actual.
     */
    public MainWindow() {
        this.userRepository = new UserRepository();
        this.pixelCoinsWindow = new PixelCoinsWindow(this);
        this.currentUser = new User();
    }

    /**
     * Método principal de inicio de la aplicación. Configura y muestra la
     * ventana principal.
     *
     * @param primaryStage El escenario principal de la aplicación.
     */
    @Override
    public void start(Stage primaryStage) {

        Label lblWelcome = new Label("¡Bienvenido a PixelCoins!");

        Button btnRegister = new Button("Registrarse");

        btnRegister.setOnAction(e -> RegisterWindow());

        Button btnLogin = new Button("Iniciar sesión");

        btnLogin.setOnAction(e -> LogInWindow());

        Pane background = new Pane();
        background.setStyle("-fx-background-color:rgb(54, 54, 54);");

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(lblWelcome, btnRegister, btnLogin);

        StackPane root = new StackPane();
        root.getChildren().addAll(background, layout);

        Scene scene = new Scene(root, 1000, 800);
        scene.getStylesheets().add(getClass().getResource("styles/mainWindow.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("PixelCoinsLauncher - Menu principal");
        primaryStage.show();

    }

    /**
     * Muestra la ventana de registro de usuarios. Permite al usuario ingresar
     * sus datos para registrarse en el sistema.
     */
    public void RegisterWindow() {
        Stage stage = new Stage();

        Label lblRegister = new Label("REGISTRO");
        HBox layoutRegister = new HBox(10);
        layoutRegister.getChildren().addAll(lblRegister);
        layoutRegister.setAlignment(Pos.TOP_CENTER);

        Label lblName = new Label("Nombre:");

        TextField txtName = new TextField();

        Label errorName = createErrorLabel();

        HBox layoutName = new HBox(10);
        layoutName.getChildren().addAll(lblName, txtName, errorName);
        layoutName.setAlignment(Pos.CENTER);

        Label lblLastName = new Label("Apellidos:");

        TextField txtLastName = new TextField();

        Label errorLastName = createErrorLabel();

        HBox layoutLastName = new HBox(10);
        layoutLastName.getChildren().addAll(lblLastName, txtLastName, errorLastName);
        layoutLastName.setAlignment(Pos.CENTER);

        Label lblUserName = new Label("Nombre de usuario:");

        TextField txtUserName = new TextField();

        Label errorUserName = createErrorLabel();

        HBox layoutUserName = new HBox(10);
        layoutUserName.getChildren().addAll(lblUserName, txtUserName, errorUserName);
        layoutUserName.setAlignment(Pos.CENTER);

        Label lblPassword = new Label("Contraseña:");

        PasswordField txtPassword = new PasswordField();

        TextField txtPasswordVisible = new TextField();

        txtPasswordVisible.setManaged(false);
        txtPasswordVisible.setVisible(false);

        // Sincronización de ambos campos
        txtPasswordVisible.textProperty().bindBidirectional(txtPassword.textProperty());

        Button btnViewPassword = new Button("👁");

        btnViewPassword.setOnAction(e -> {
            showDisguisePassword(txtPassword, txtPasswordVisible);
        });

        Label errorPassword = createErrorLabel();

        HBox layoutPassword = new HBox(10);
        layoutPassword.getChildren().addAll(lblPassword, txtPassword, txtPasswordVisible, btnViewPassword, errorPassword);
        layoutPassword.setAlignment(Pos.CENTER);

        Label lblEmail = new Label("Email:");

        TextField txtEmail = new TextField();

        Label errorEmail = createErrorLabel();

        HBox layoutEmail = new HBox(10);
        layoutEmail.getChildren().addAll(lblEmail, txtEmail, errorEmail);
        layoutEmail.setAlignment(Pos.CENTER);

        Label lblBirthday = new Label("Fecha de nacimiento:");

        DatePicker datePicker = new DatePicker();

        Label errorBirthday = createErrorLabel();

        HBox layoutBirthday = new HBox(10);
        layoutBirthday.getChildren().addAll(lblBirthday, datePicker, errorBirthday);
        layoutBirthday.setAlignment(Pos.CENTER);

        Button btnCancel = new Button("Cancelar");

        btnCancel.setOnAction(e -> stage.close());

        Button btnAccept = new Button("Aceptar");

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
        layoutButtons.setAlignment(Pos.BOTTOM_CENTER);

        VBox mainLayout = new VBox(15);
        mainLayout.getChildren().addAll(layoutRegister, layoutName, layoutLastName, layoutUserName, layoutPassword, layoutEmail, layoutBirthday, layoutButtons);
        mainLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainLayout, 800, 800);
        scene.getStylesheets().add(getClass().getResource("styles/registerWindow.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("PixelCoinsLauncher - Registrarse");
        stage.setResizable(false);
        stage.showAndWait();
    }

    /**
     * Muestra la ventana de inicio de sesión. Permite al usuario ingresar sus
     * credenciales para acceder al sistema.
     */
    public void LogInWindow() {
        Stage stage = new Stage();

        Label lblLogIn = new Label("INICIO SESIÓN");
        HBox layoutLogIn = new HBox(10);
        layoutLogIn.setAlignment(Pos.TOP_CENTER);
        layoutLogIn.getChildren().addAll(lblLogIn);

        Label lblUserName = new Label("Nombre de usuario:");

        TextField txtUserName = new TextField();

        Label errorUsername = createErrorLabel();

        HBox layoutUserName = new HBox(10);
        layoutUserName.setAlignment(Pos.CENTER);
        layoutUserName.getChildren().addAll(lblUserName, txtUserName, errorUsername);

        Label lblPassword = new Label("Contraseña:");

        PasswordField txtPassword = new PasswordField();

        TextField txtPasswordVisible = new TextField();

        Button btnViewPassword = new Button("👁");

        txtPasswordVisible.setManaged(false);
        txtPasswordVisible.setVisible(false);

        // Sincronización de ambos campos
        txtPasswordVisible.textProperty().bindBidirectional(txtPassword.textProperty());

        btnViewPassword.setOnAction(e -> {
            showDisguisePassword(txtPassword, txtPasswordVisible);
        });

        Label errorPassword = createErrorLabel();

        HBox layoutPassword = new HBox(10);
        layoutPassword.setAlignment(Pos.CENTER);
        layoutPassword.getChildren().addAll(lblPassword, txtPassword, txtPasswordVisible, btnViewPassword, errorPassword);

        Button btnCancel = new Button("Cancelar");

        btnCancel.setOnAction(e -> stage.close());

        Button btnAccept = new Button("Aceptar");

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
                    User user = userRepository.getUserByUsername(txtUserName.getText());

                    if (user != null) {
                        this.setCurrentUser(user);
                    }

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Inicio de sesión exitoso");
                    alert.setTitle("Información");
                    alert.showAndWait();
                    stage.close();

                    PixelCoinsWindow pixelCoinsWindow = new PixelCoinsWindow(this);
                    Stage pStage = new Stage();
                    pixelCoinsWindow.start(pStage);
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
        layoutButtons.setAlignment(Pos.BOTTOM_CENTER);
        layoutButtons.getChildren().addAll(btnAccept, btnCancel);

        VBox mainLayout = new VBox(15);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(layoutLogIn, layoutUserName, layoutPassword, layoutButtons);

        Scene scene = new Scene(mainLayout, 800, 800);
        scene.getStylesheets().add(getClass().getResource("styles/loginWindow.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("PixelCoinsLauncher - Iniciar sesión");
        stage.setResizable(false);
        stage.showAndWait();
    }

    /**
     * Crea un Label para mostrar mensajes de error.
     *
     * @return Un Label vacío para mensajes de error.
     */
    private Label createErrorLabel() {
        Label label = new Label();
        return label;
    }

    /**
     * Alterna entre mostrar y ocultar la contraseña.
     *
     * @param passwordField El campo de contraseña.
     * @param textField El campo de texto visible.
     */
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

    /**
     * Método principal para iniciar la aplicación.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
