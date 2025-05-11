package com.project;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.jnativehook.GlobalScreen;

import com.project.model.Game;
import com.project.model.User;
import com.project.repository.GameRepository;
import com.project.repository.GameSessionRepository;
import com.project.repository.UserRepository;
import com.project.utils.GlobalMouseListener;
import com.project.utils.WindowsChecker;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Clase principal de la ventana PixelCoinsLauncher. Gestiona las
 * funcionalidades principales de la aplicación, como la ejecución de juegos, la
 * gestión de usuarios, y el sistema anti-AFK.
 */
public class PixelCoinsWindow extends Application {

    private GlobalMouseListener mouseListener;

    public String windowTittle;

    private AtomicLong lastActivityTime = new AtomicLong(System.currentTimeMillis());

    private Timeline antiAfkTimeline;

    private boolean active = true;

    public GameSessionRepository gameSessionRepository;

    public UserRepository userRepository;

    public GameRepository gameRepository;

    public MainWindow mainWindow;

    /**
     * Constructor de la clase PixelCoinsWindow. Inicializa los repositorios y
     * la referencia a la ventana principal.
     *
     * @param mainWindow La ventana principal de la aplicación.
     */
    public PixelCoinsWindow(MainWindow mainWindow) {
        this.gameSessionRepository = new GameSessionRepository();
        this.mainWindow = mainWindow;
        this.gameRepository = new GameRepository();
        this.userRepository = new UserRepository();

    }

    private BorderPane rightPane;

    /**
     * Método principal de inicio de la ventana PixelCoinsLauncher. Configura y
     * muestra la interfaz principal de la aplicación.
     *
     * @param pixelCoinWindow El escenario principal de la ventana.
     */
    @Override
    public void start(Stage pixelCoinWindow) {

        Button btnAccount = createImageButton("Cuenta", "/com/project/account_pixel.png");
        Button btnLaunch = createImageButton("Lanzador", "/com/project/rocket_pixel.png");
        Button btnShop = createImageButton("Tienda", "/com/project/shop_pixel.png");

        btnAccount.setOnAction(e -> showAccountScene());
        btnLaunch.setOnAction(e -> showLaunchScene());
        btnShop.setOnAction(e -> showShopScene());

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

    /**
     * Crea un botón con una imagen y texto.
     *
     * @param text El texto del botón.
     * @param imagePath La ruta de la imagen del botón.
     * @return Un botón configurado con la imagen y el texto.
     */
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

    /**
     * Muestra la escena de la cuenta del usuario. Permite visualizar
     * información del usuario autenticado.
     */
    public void showAccountScene() {

        Label lblAccount = new Label("Cuenta");
        VBox layoutAccount = new VBox(10);

        layoutAccount.setAlignment(Pos.TOP_CENTER);
        layoutAccount.getChildren().addAll(lblAccount);

        Label lblUserName = new Label("Nombre de usuario: ");
        TextField txtUserName = new TextField();
        txtUserName.setId("txtUserNameAccount");
        txtUserName.setEditable(false);

        HBox layoutUserName = new HBox(10);
        layoutUserName.getChildren().addAll(lblUserName, txtUserName);
        layoutUserName.setAlignment(Pos.CENTER);

        Label lblPassword = new Label("Contraseña: ");
        PasswordField txtPassword = new PasswordField();
        txtPassword.setId("txtPasswordAccount");
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
        txtEmail.setId("txtEmailAccount");
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

    /**
     * Cambia el cursor al pasar el ratón sobre un botón.
     *
     * @param button El botón al que se aplicará el cambio de cursor.
     */
    private void setCursorChange(Button button) {
        button.setOnMouseEntered(e -> button.setCursor(Cursor.HAND));
        button.setOnMouseExited(e -> button.setCursor(Cursor.DEFAULT));
    }

    /**
     * Muestra la escena del lanzador de juegos. Permite gestionar y ejecutar
     * juegos desde la aplicación.
     */
    public void showLaunchScene() {
        Label lblLaunch = new Label("Lanzador");
        VBox layoutLaunch = new VBox(10);

        layoutLaunch.setAlignment(Pos.TOP_CENTER);
        layoutLaunch.getChildren().addAll(lblLaunch);

        TableView<Game> table = new TableView<>();

        TableColumn<Game, String> colGenre = new TableColumn<>("Genero");
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Game, String> colName = new TableColumn<>("Nombre");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Game, String> colExePath = new TableColumn<>("Ejecutable");
        colExePath.setCellValueFactory(new PropertyValueFactory<>("exePath"));

        table.getColumns().addAll(colGenre, colName, colExePath);
        table.setPrefHeight(300);
        table.setPrefWidth(100);

        List<Game> games = GameRepository.showListGames();

        table.getItems().addAll(games);

        VBox layoutTable = new VBox(10);
        layoutTable.getChildren().addAll(table);

        Button btnAddGame = new Button("Añadir juego");
        btnAddGame.setId("btnAddGame");
        Button btnDeleteGame = new Button("Borrar juego");
        btnDeleteGame.setId("btnDeleteGame");
        Button btnExecuteGame = new Button("Ejecutar juego");
        btnExecuteGame.setId("btnExecuteGame");

        btnAddGame.getStyleClass().add("buttons");
        btnDeleteGame.getStyleClass().add("buttons");
        btnExecuteGame.getStyleClass().add("buttons");

        btnAddGame.setOnAction(e -> {
            RegisterGamesWindow();
            table.getItems().setAll(GameRepository.showListGames());
        });

        btnDeleteGame.setOnAction(e -> {
            Game selectedGame = table.getSelectionModel().getSelectedItem();
            if (selectedGame != null) {
                gameRepository.deleteListGames(selectedGame);
                table.getItems().remove(selectedGame);
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", null, "Seleccione un juego para eliminar.");
            }
        });

        btnExecuteGame.setOnAction(e -> {
            executeGame(table);
        });

        HBox layoutButtons = new HBox(10);
        layoutButtons.setAlignment(Pos.CENTER);
        layoutButtons.getChildren().addAll(btnAddGame, btnDeleteGame, btnExecuteGame);

        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(layoutLaunch, layoutTable, layoutButtons);
        mainLayout.setAlignment(Pos.CENTER);

        rightPane.setCenter(new StackPane(mainLayout));

    }

    /**
     * Muestra la escena de la tienda. Permite visualizar el saldo de monedas
     * del usuario y productos disponibles.
     */
    public void showShopScene() {
        Label lblShop = new Label("Tienda");
        VBox layoutShop = new VBox(10);

        layoutShop.setAlignment(Pos.TOP_CENTER);
        layoutShop.getChildren().addAll(lblShop);

        User currentUser = mainWindow.getCurrentUser();
        int balance = userRepository.showCoins(currentUser.getUserName());
        Label lblBalance = new Label("Monedas: " + balance);
        lblBalance.setId("lblBalance");

        lblBalance.setStyle("-fx-font-size: 10px; -fx-font-family: 'Press Start 2P';");

        VBox layoutBalance = new VBox(10);
        layoutBalance.setAlignment(Pos.TOP_RIGHT);
        layoutBalance.getChildren().add(lblBalance);

        Label lblCommingSoon = new Label("Proximamente productos en la tienda...");
        lblCommingSoon.setId("lblCommingSoon");
        lblCommingSoon.setStyle("-fx-font-size: 10px; -fx-font-family: 'Press Start 2P'; -fx-text-fill: red;");

        VBox layoutCommingSoon = new VBox(10);
        layoutCommingSoon.setAlignment(Pos.CENTER);
        layoutCommingSoon.getChildren().add(lblCommingSoon);

        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(layoutShop, layoutBalance, layoutCommingSoon);
        mainLayout.setAlignment(Pos.CENTER);

        rightPane.setCenter(new StackPane(mainLayout));
    }

    /**
     * Muestra la información del usuario autenticado.
     *
     * @param txtName Campo de texto para el nombre de usuario.
     * @param txtEmail Campo de texto para el email del usuario.
     * @param txtPassword Campo de texto para la contraseña del usuario.
     */
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
     * Muestra la ventana para registrar un nuevo juego. Permite al usuario
     * agregar un juego a la lista de juegos disponibles.
     */
    public void RegisterGamesWindow() {
        Stage stage = new Stage();

        Label lblName = new Label("Nombre del juego: ");

        TextField txtName = new TextField();
        txtName.setId("txtNameGame");

        HBox layoutName = new HBox(10);
        layoutName.setAlignment(Pos.CENTER);
        layoutName.getChildren().addAll(lblName, txtName);

        Label lblGenre = new Label("Genero: ");

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setId("comboBox");
        comboBox.getItems().addAll("Aventura", "RPG", "Shooter", "Terror", "Plataforma de juegos", "Emulador");

        HBox layoutGenre = new HBox(10);
        layoutGenre.setAlignment(Pos.CENTER);
        layoutGenre.getChildren().addAll(lblGenre, comboBox);

        Label lblPath = new Label("Ruta del juego: ");

        TextField txtPath = new TextField();
        txtPath.setId("txtPath");
        txtPath.setEditable(false);

        Button btnBrowse = new Button("Buscar");
        btnBrowse.setOnAction(e -> openFileExplorer(txtPath));

        HBox layoutPath = new HBox(10);
        layoutPath.setAlignment(Pos.CENTER);
        layoutPath.getChildren().addAll(lblPath, txtPath, btnBrowse);

        Button btnAddGame = new Button("Añadir juego");
        btnAddGame.setId("btnAddGameRegisterGame");
        Button btnCancel = new Button("Cancelar");

        btnAddGame.setOnAction(e -> {
            String gameName = txtName.getText().trim();
            String genre = comboBox.getValue();
            String exePath = txtPath.getText().trim();

            if (gameName.isEmpty() || genre == null || exePath.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", null, "Todos los campos deben completarse");
                return;
            }

            // Verificar si el juego ya existe en la base de datos o lista
            List<Game> games = GameRepository.showListGames();
            boolean exists = games.stream().anyMatch(game -> game.getExePath().equalsIgnoreCase(exePath));

            if (exists) {
                showAlert(AlertType.ERROR, "Error", null, "Este juego ya está en la lista");
                return;
            } else {
                Game newGame = new Game(gameName, genre, exePath);
                gameRepository.addListGames(newGame);
                showAlert(Alert.AlertType.INFORMATION, "Éxito", null, "Juego agregado correctamente");
                stage.close(); // Cierra la ventana de registro
            }
        });
        btnCancel.setOnAction(e -> stage.close());

        HBox layoutButtons = new HBox(10);
        layoutButtons.setAlignment(Pos.CENTER);
        layoutButtons.getChildren().addAll(btnAddGame, btnCancel);

        VBox mainLayout = new VBox(10);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(layoutName, layoutGenre, layoutPath, layoutButtons);

        Scene scene = new Scene(mainLayout, 600, 600);
        scene.getStylesheets().add(getClass().getResource("styles/registerGamesWindow.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("PixelCoinsLauncher - Añadir juego");
        stage.setResizable(false);
        stage.showAndWait();

    }

    /**
     * Abre un explorador de archivos para seleccionar un archivo ejecutable.
     *
     * @param txtPath Campo de texto donde se mostrará la ruta seleccionada.
     */
    private void openFileExplorer(TextField txtPath) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona un archivo ejecutable (.exe)");

        FileChooser.ExtensionFilter exeFilter = new FileChooser.ExtensionFilter("Archivos ejecutables (*.exe)", "*.exe");
        fileChooser.getExtensionFilters().add(exeFilter);

        Stage fileStage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            txtPath.setText(selectedFile.getAbsolutePath());
        }
    }

    /**
     * Ejecuta un juego seleccionado desde la lista. Inicia el proceso del juego
     * y muestra un temporizador de tiempo jugado.
     *
     * @param table La tabla que contiene la lista de juegos.
     */
    public void executeGame(TableView<Game> table) {
        System.out.println(mainWindow.getCurrentUser());

        User currentUser = mainWindow.getCurrentUser();
        Game selectedGame = table.getSelectionModel().getSelectedItem();

        if (currentUser == null || currentUser.getId() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", null, "No hay usuario autenticado o el ID es nulo.");
            return;
        }

        if (selectedGame != null) {
            try {
                LocalDateTime startTime = LocalDateTime.now();

                Alert gameAlert = new Alert(AlertType.INFORMATION);
                gameAlert.setResizable(true);
                gameAlert.setWidth(500);
                gameAlert.setHeight(500);
                gameAlert.setTitle("Juego en ejecución");
                gameAlert.setHeaderText("Jugando: " + selectedGame.getName());

                Label timeLabel = new Label("Tiempo jugado: 00:00:00");
                timeLabel.setId("timeLabel");
                AtomicLong secondsPlayed = new AtomicLong(0);

                Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), e -> {
                    secondsPlayed.incrementAndGet();
                    long hours = secondsPlayed.get() / 3600;
                    long minutes = (secondsPlayed.get() % 3600) / 60;
                    long seconds = secondsPlayed.get() % 60;
                    // Comprobar si la ventana está activa
                    boolean isWindowActive = mouseListener.isWindowActive();
                    timeLabel.setText(String.format("Tiempo jugado: %02d:%02d:%02d\nVentana activa: %s", hours, minutes, seconds, windowTittle));
                }));
                timeline.setCycleCount(Timeline.INDEFINITE);

                ProcessBuilder pb = new ProcessBuilder(selectedGame.getExePath());
                Process process = pb.start();

                Thread.sleep(2000);

                windowTittle = WindowsChecker.GetActiveWindowTitle();
                System.out.println("[Debug]: Título de la ventana del juego: " + windowTittle);

                afkSystem(timeline);

                VBox content = new VBox(10, timeLabel);
                content.setAlignment(Pos.CENTER);
                gameAlert.getDialogPane().setContent(content);

                ButtonType stopButtonType = new ButtonType("Detener juego", ButtonBar.ButtonData.OK_DONE);
                gameAlert.getButtonTypes().setAll(stopButtonType);

                new Thread(() -> {
                    try {
                        // Crear una instancia de GlobalMouseListener con el título de la ventana principal
                        mouseListener = new GlobalMouseListener(WindowsChecker.GetActiveWindowTitle(), () -> {
                            lastActivityTime.set(System.currentTimeMillis());
                        });
                        process.waitFor();
                        Platform.runLater(() -> {
                            timeline.stop();
                            if (gameAlert.isShowing()) {
                                gameAlert.close();
                            }
                        });
                        try {
                            GlobalScreen.unregisterNativeHook();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();

                timeline.play();
                Optional<ButtonType> result = gameAlert.showAndWait();

                if (result.isPresent() || !gameAlert.isShowing()) {
                    timeline.stop();
                    process.destroy();
                    LocalDateTime endTime = LocalDateTime.now();
                    long totalSeconds = secondsPlayed.get();

                    // Guardar la sesión
                    gameSessionRepository.saveGameSession(
                            selectedGame.getId(),
                            mainWindow.getCurrentUser().getId(),
                            startTime,
                            endTime,
                            totalSeconds
                    );

                    // Mostrar resumen
                    showGameSessionSummary(selectedGame, totalSeconds);
                }

            } catch (IOException | InterruptedException e) {
                Alert alert = new Alert(AlertType.ERROR);
                showAlert(Alert.AlertType.ERROR, "Error", null, "No se pudo ejecutar el juego.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Advertencia", null, "Selecciona un juego antes de ejecutar");
        }
    }

    /**
     * Muestra un resumen de la sesión de juego. Incluye el tiempo jugado y las
     * monedas ganadas.
     *
     * @param game El juego jugado.
     * @param totalSeconds El tiempo total jugado en segundos.
     */
    private void showGameSessionSummary(Game game, long totalSeconds) {
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        String timePlayed = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        int coinsEarned = (int) (totalSeconds / 60) * 10; // 10 monedas por minuto

        Alert summaryAlert = new Alert(AlertType.INFORMATION);
        summaryAlert.setTitle("Resumen de juego");
        summaryAlert.setHeaderText("Sesión terminada: " + game.getName());
        summaryAlert.setContentText(String.format(
                "Tiempo jugado: %s\n"
                + "Monedas ganadas: %d",
                timePlayed, coinsEarned
        ));
        summaryAlert.showAndWait();
    }

    /**
     * Sistema anti-AFK que pausa el juego si no hay actividad o si la ventana
     * no está activa.
     *
     * @param timeline El temporizador del juego.
     */
    public void afkSystem(Timeline timeline) {
        // Bandera para controlar si el temporizador ya está en pausa
        boolean[] isGamePaused = {false};

        if (windowTittle == null || windowTittle.isEmpty()) {
            throw new IllegalArgumentException("El título de la ventana no puede ser nulo o vacío.");
        }

        antiAfkTimeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), event -> {
            boolean isWindowActive = WindowsChecker.isWindowActive(windowTittle);
            long currentTime = System.currentTimeMillis();
            long inactivityTime = currentTime - lastActivityTime.get();

            // Verificamos si la ventana no está activa o si ha pasado el tiempo de inactividad
            if (!isWindowActive || inactivityTime > 60000) {
                if (timeline.getStatus() == Timeline.Status.RUNNING && !isGamePaused[0]) {
                    // Pausamos el juego solo si no está en pausa
                    timeline.pause();
                    isGamePaused[0] = true;  // Actualizamos el estado a "pausado"
                    System.out.println("[Anti-AFK]: Juego pausado por inactividad o ventana no activa.");
                }
            } else {
                if (timeline.getStatus() == Timeline.Status.PAUSED && isGamePaused[0]) {
                    // Reanudamos el juego solo si estaba en pausa
                    timeline.play();
                    isGamePaused[0] = false;  // Actualizamos el estado a "jugando"
                    System.out.println("[Anti-AFK]: Juego reanudado.");
                }
            }
        }));

        antiAfkTimeline.setCycleCount(Timeline.INDEFINITE);
        antiAfkTimeline.play();

        // Listener de mouse para actualizar el tiempo de actividad
        mouseListener = new GlobalMouseListener(windowTittle, () -> {
            lastActivityTime.set(System.currentTimeMillis());
        });
    }

    public Alert showAlert(AlertType type, String title, String headerText, String contentText) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
        return alert;
    }

}
