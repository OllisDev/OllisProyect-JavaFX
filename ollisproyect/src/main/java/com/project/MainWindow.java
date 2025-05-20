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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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

        ImageView gifBackground = new ImageView(new Image(getClass().getResourceAsStream("/com/project/images/pixel_blue.gif")));
        gifBackground.setFitWidth(1620);
        gifBackground.setFitHeight(920);
        gifBackground.setPreserveRatio(false);

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(lblWelcome, btnRegister, btnLogin);

        StackPane root = new StackPane();
        root.getChildren().addAll(gifBackground, layout);

        Scene scene = new Scene(root, 1620, 920);

        scene.getStylesheets().add(getClass().getResource("/com/project/styles/mainWindow.css").toExternalForm());

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

        ImageView checkGifName = new ImageView(new Image(getClass().getResourceAsStream("/com/project/images/pixel_coin.gif")));
        checkGifName.setFitWidth(24);
        checkGifName.setFitHeight(24);
        checkGifName.setVisible(false);

        ImageView checkGifLastName = new ImageView(new Image(getClass().getResourceAsStream("/com/project/images/pixel_coin.gif")));
        checkGifLastName.setFitWidth(24);
        checkGifLastName.setFitHeight(24);
        checkGifLastName.setVisible(false);

        ImageView checkGifUserName = new ImageView(new Image(getClass().getResourceAsStream("/com/project/images/pixel_coin.gif")));
        checkGifUserName.setFitWidth(24);
        checkGifUserName.setFitHeight(24);
        checkGifUserName.setVisible(false);

        ImageView checkGifPassword = new ImageView(new Image(getClass().getResourceAsStream("/com/project/images/pixel_coin.gif")));
        checkGifPassword.setFitWidth(24);
        checkGifPassword.setFitHeight(24);
        checkGifPassword.setVisible(false);

        ImageView checkGifEmail = new ImageView(new Image(getClass().getResourceAsStream("/com/project/images/pixel_coin.gif")));
        checkGifEmail.setFitWidth(24);
        checkGifEmail.setFitHeight(24);
        checkGifEmail.setVisible(false);

        ImageView checkGifBirthday = new ImageView(new Image(getClass().getResourceAsStream("/com/project/images/pixel_coin.gif")));
        checkGifBirthday.setFitWidth(24);
        checkGifBirthday.setFitHeight(24);
        checkGifBirthday.setVisible(false);

        ImageView checkErrorName = new ImageView(new Image(getClass().getResourceAsStream("/com/project/images/pixel_coin_red.gif")));
        checkErrorName.setFitWidth(24);
        checkErrorName.setFitHeight(24);
        checkErrorName.setVisible(false);

        ImageView checkErrorLastName = new ImageView(new Image(getClass().getResourceAsStream("/com/project/images/pixel_coin_red.gif")));
        checkErrorLastName.setFitWidth(24);
        checkErrorLastName.setFitHeight(24);
        checkErrorLastName.setVisible(false);

        ImageView checkErrorUserName = new ImageView(new Image(getClass().getResourceAsStream("/com/project/images/pixel_coin_red.gif")));
        checkErrorUserName.setFitWidth(24);
        checkErrorUserName.setFitHeight(24);
        checkErrorUserName.setVisible(false);

        ImageView checkErrorPassword = new ImageView(new Image(getClass().getResourceAsStream("/com/project/images/pixel_coin_red.gif")));
        checkErrorPassword.setFitWidth(24);
        checkErrorPassword.setFitHeight(24);
        checkErrorPassword.setVisible(false);

        ImageView checkErrorEmail = new ImageView(new Image(getClass().getResourceAsStream("/com/project/images/pixel_coin_red.gif")));
        checkErrorEmail.setFitWidth(24);
        checkErrorEmail.setFitHeight(24);
        checkErrorEmail.setVisible(false);

        ImageView checkErrorBirthday = new ImageView(new Image(getClass().getResourceAsStream("/com/project/images/pixel_coin_red.gif")));
        checkErrorBirthday.setFitWidth(24);
        checkErrorBirthday.setFitHeight(24);
        checkErrorBirthday.setVisible(false);

        Label lblRegister = new Label("REGISTRO");
        lblRegister.setId("lblRegister");
        lblRegister.getStyleClass().add("label-register");
        HBox layoutRegister = new HBox(10);
        layoutRegister.getChildren().addAll(lblRegister);
        layoutRegister.setAlignment(Pos.TOP_CENTER);

        Label lblName = new Label("Nombre:");

        TextField txtName = new TextField();
        txtName.setId("txtNameRegister");
        txtName.focusedProperty().addListener((obs, oldVal, newVal) -> {
            checkGifName.setVisible(newVal);
        });

        Label errorName = createErrorLabel();

        HBox layoutName = new HBox(10);
        layoutName.getChildren().addAll(lblName, txtName, checkGifName, errorName, checkErrorName);
        layoutName.setAlignment(Pos.CENTER);

        Label lblLastName = new Label("Apellidos:");

        TextField txtLastName = new TextField();
        txtLastName.setId("txtLastNameRegister");
        txtLastName.focusedProperty().addListener((obs, oldVal, newVal) -> {
            checkGifLastName.setVisible(newVal);
        });
        Label errorLastName = createErrorLabel();

        HBox layoutLastName = new HBox(10);
        layoutLastName.getChildren().addAll(lblLastName, txtLastName, checkGifLastName, errorLastName, checkErrorLastName);
        layoutLastName.setAlignment(Pos.CENTER);

        Label lblUserName = new Label("Nombre de usuario:");

        TextField txtUserName = new TextField();
        txtUserName.setId("txtUserNameRegister");
        txtUserName.focusedProperty().addListener((obs, oldVal, newVal) -> {
            checkGifUserName.setVisible(newVal);
        });

        Label errorUserName = createErrorLabel();

        HBox layoutUserName = new HBox(10);
        layoutUserName.getChildren().addAll(lblUserName, txtUserName, checkGifUserName, errorUserName, checkErrorUserName);
        layoutUserName.setAlignment(Pos.CENTER);

        Label lblPassword = new Label("Contraseña:");

        PasswordField txtPassword = new PasswordField();
        txtPassword.setId("txtPasswordRegister");
        txtPassword.focusedProperty().addListener((obs, oldVal, newVal) -> {
            checkGifPassword.setVisible(newVal);
        });

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
        layoutPassword.getChildren().addAll(lblPassword, txtPassword, txtPasswordVisible, btnViewPassword, checkGifPassword, errorPassword, checkErrorPassword);
        layoutPassword.setAlignment(Pos.CENTER);

        Label lblEmail = new Label("Email:");

        TextField txtEmail = new TextField();
        txtEmail.setId("txtEmailRegister");
        txtEmail.focusedProperty().addListener((obs, oldVal, newVal) -> {
            checkGifEmail.setVisible(newVal);
        });

        Label errorEmail = createErrorLabel();

        HBox layoutEmail = new HBox(10);
        layoutEmail.getChildren().addAll(lblEmail, txtEmail, checkGifEmail, errorEmail, checkErrorEmail);
        layoutEmail.setAlignment(Pos.CENTER);

        Label lblBirthday = new Label("Fecha de nacimiento:");

        DatePicker datePicker = new DatePicker();
        datePicker.setId("datePickerRegister");
        datePicker.focusedProperty().addListener((obs, oldVal, newVal) -> {
            checkGifBirthday.setVisible(newVal);
        });

        Label errorBirthday = createErrorLabel();

        HBox layoutBirthday = new HBox(10);
        layoutBirthday.getChildren().addAll(lblBirthday, datePicker, checkGifBirthday, errorBirthday, checkErrorBirthday);
        layoutBirthday.setAlignment(Pos.CENTER);

        Button btnCancel = new Button("Cancelar");

        btnCancel.setOnAction(e -> stage.close());

        Button btnAccept = new Button("Aceptar");
        btnAccept.setId("btnAcceptRegister");

        btnAccept.setOnAction(e -> {
            boolean isValid = true;

            if (txtName.getText().trim().isEmpty()) {
                errorName.setText("El nombre no puede estar vacío");
                checkErrorName.setVisible(true);
                isValid = false;
            } else if (!txtName.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
                errorName.setText("El nombre solo debe contener letras");
                checkErrorName.setVisible(true);
            } else {
                errorName.setText("");
                checkErrorName.setVisible(false);
            }

            if (txtLastName.getText().trim().isEmpty()) {
                errorLastName.setText("Los apellidos no pueden estar vacíos");
                checkErrorLastName.setVisible(true);
                isValid = false;
            } else if (!txtLastName.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
                errorLastName.setText("Los apellidos solo deben contener letras");
                checkErrorLastName.setVisible(true);

            } else {
                errorLastName.setText("");
                checkErrorLastName.setVisible(false);
            }

            if (txtUserName.getText().trim().isEmpty()) {
                errorUserName.setText("El nombre de usuario no puede estar vacío");
                checkErrorUserName.setVisible(true);
                isValid = false;
            } else {
                errorUserName.setText("");
                checkErrorUserName.setVisible(false);
            }

            if (txtPassword.getText().trim().isEmpty()) {
                errorPassword.setText("La contraseña no puede estar vacía");
                checkErrorPassword.setVisible(true);
                isValid = false;
            } else if (txtPassword.getText().length() < 8) {
                errorPassword.setText("La contraseña debe contener 8 caracteres");
                checkErrorPassword.setVisible(true);
                isValid = false;
            } else {
                errorPassword.setText("");
                checkErrorPassword.setVisible(false);
            }

            if (txtEmail.getText().trim().isEmpty()) {
                errorEmail.setText("El email no puede estar vacío");
                checkErrorEmail.setVisible(true);
                isValid = false;
            } else if (!txtEmail.getText().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                errorEmail.setText("Formato de email no válido");
                checkErrorEmail.setVisible(true);
                isValid = false;
            } else {
                errorEmail.setText("");
                checkErrorEmail.setVisible(false);
            }

            if (datePicker.getValue() == null) {
                errorBirthday.setText("La fecha de nacimiento es obligatorio");
                checkErrorBirthday.setVisible(true);
                isValid = false;
            } else {
                errorBirthday.setText("");
                checkErrorBirthday.setVisible(false);
            }

            if (isValid) {

                LocalDate birthdayLocalDate = datePicker.getValue();
                LocalDateTime birthday = birthdayLocalDate.atStartOfDay();

                User user = new User(txtName.getText(), txtLastName.getText(), txtUserName.getText(), txtPassword.getText(), txtEmail.getText(), birthday);

                boolean success = userRepository.CreateUser(user);

                if (success) {
                    showAlert(AlertType.INFORMATION, "Información", null, "Registro exitoso");
                    stage.close();
                } else {
                    showAlert(AlertType.ERROR, "Error", null, "Error al registrar el usuario, debido a que ya existe");
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

        ImageView gifBackground = new ImageView(new Image(getClass().getResourceAsStream("/com/project/images/videogames.gif")));
        gifBackground.setFitWidth(1620);
        gifBackground.setFitHeight(920);
        gifBackground.setPreserveRatio(false);
        gifBackground.setOpacity(0.3);

        StackPane root = new StackPane();
        root.getChildren().addAll(gifBackground, mainLayout);

        Scene scene = new Scene(root, 1620, 920);
        scene.getStylesheets().add(getClass().getResource("/com/project/styles/registerWindow.css").toExternalForm());
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

        ImageView checkGifUserName = new ImageView(new Image(getClass().getResourceAsStream("/com/project/images/pixel_coin.gif")));
        checkGifUserName.setFitWidth(24);
        checkGifUserName.setFitHeight(24);
        checkGifUserName.setVisible(false);

        ImageView checkGifPassword = new ImageView(new Image(getClass().getResourceAsStream("/com/project/images/pixel_coin.gif")));
        checkGifPassword.setFitWidth(24);
        checkGifPassword.setFitHeight(24);
        checkGifPassword.setVisible(false);

        ImageView checkErrorUserName = new ImageView(new Image(getClass().getResourceAsStream("/com/project/images/pixel_coin_red.gif")));
        checkErrorUserName.setFitWidth(24);
        checkErrorUserName.setFitHeight(24);
        checkErrorUserName.setVisible(false);

        ImageView checkErrorPassword = new ImageView(new Image(getClass().getResourceAsStream("/com/project/images/pixel_coin_red.gif")));
        checkErrorPassword.setFitWidth(24);
        checkErrorPassword.setFitHeight(24);
        checkErrorPassword.setVisible(false);

        Label lblLogIn = new Label("INICIO SESIÓN");
        lblLogIn.getStyleClass().add("label-login");
        lblLogIn.setId("lblLogIn");
        HBox layoutLogIn = new HBox(10);
        layoutLogIn.setAlignment(Pos.TOP_CENTER);
        layoutLogIn.getChildren().addAll(lblLogIn);

        Label lblUserName = new Label("Nombre de usuario:");

        TextField txtUserName = new TextField();
        txtUserName.setId("txtUserNameLogIn");
        txtUserName.focusedProperty().addListener((obs, oldVal, newVal) -> {
            checkGifUserName.setVisible(newVal);
        });

        Label errorUsername = createErrorLabel();

        HBox layoutUserName = new HBox(10);
        layoutUserName.setAlignment(Pos.CENTER);
        layoutUserName.getChildren().addAll(lblUserName, txtUserName, checkGifUserName, errorUsername, checkErrorUserName);

        Label lblPassword = new Label("Contraseña:");

        PasswordField txtPassword = new PasswordField();
        txtPassword.setId("txtPasswordLogIn");
        txtPassword.focusedProperty().addListener((obs, oldVal, newVal) -> {
            checkGifPassword.setVisible(newVal);
        });

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
        layoutPassword.getChildren().addAll(lblPassword, txtPassword, txtPasswordVisible, btnViewPassword, checkGifPassword, errorPassword, checkErrorPassword);

        Button btnCancel = new Button("Cancelar");

        btnCancel.setOnAction(e -> stage.close());

        Button btnAccept = new Button("Aceptar");
        btnAccept.setId("btnAcceptLogIn");

        btnAccept.setOnAction(e -> {
            boolean isValid = true;

            if (txtUserName.getText().trim().isEmpty()) {
                errorUsername.setText("El nombre de usuario no debe estar vacío");
                checkErrorUserName.setVisible(true);
                isValid = false;
            } else {
                errorUsername.setText("");
                checkErrorUserName.setVisible(false);
            }

            if (txtPassword.getText().trim().isEmpty()) {
                errorPassword.setText("La contraseña no debe estar vacío");
                checkErrorPassword.setVisible(true);
                isValid = false;
            } else if (txtPassword.getText().length() < 8) {
                errorPassword.setText("La contraseña debe tener mínimo 8 caracteres");
                checkErrorPassword.setVisible(true);
                isValid = false;
            } else {
                errorPassword.setText("");
                checkErrorPassword.setVisible(false);
            }

            if (isValid) {
                boolean success = userRepository.ValidateUserLogIn(txtUserName, txtPassword);

                if (success) {
                    User user = userRepository.getUserByUsername(txtUserName.getText());

                    if (user != null) {
                        this.setCurrentUser(user);
                    }

                    showAlert(AlertType.INFORMATION, "Información", null, "Inicio de sesión exitoso");
                    stage.close();

                    PixelCoinsWindow pixelCoinsWindow = new PixelCoinsWindow(this);
                    Stage pStage = new Stage();
                    pixelCoinsWindow.start(pStage);
                } else {
                    showAlert(AlertType.ERROR, "Error", null, "Error al iniciar sesión, debido a que el usuario no existe");
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

        ImageView gifBackground = new ImageView(new Image(getClass().getResourceAsStream("/com/project/images/videogames.gif")));
        gifBackground.setFitWidth(1620);
        gifBackground.setFitHeight(920);
        gifBackground.setPreserveRatio(false);
        gifBackground.setOpacity(0.3);

        StackPane root = new StackPane();
        root.getChildren().addAll(gifBackground, mainLayout);

        Scene scene = new Scene(root, 1620, 920);
        scene.getStylesheets().add(getClass().getResource("/com/project/styles/loginWindow.css").toExternalForm());
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
    public void showDisguisePassword(PasswordField passwordField, TextField textField) {
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
     * Muestra una alerta con el tipo, título y mensaje especificados.
     *
     * @param alertType El tipo de alerta (INFORMATION, ERROR, etc.).
     * @param title El título de la alerta.
     * @param message El mensaje de la alerta.
     */
    public Alert showAlert(AlertType type, String title, String headerText, String contentText) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
        return alert;
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
