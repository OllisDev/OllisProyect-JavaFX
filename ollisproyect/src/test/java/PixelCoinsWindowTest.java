
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import com.project.MainWindow;
import com.project.PixelCoinsWindow;
import com.project.model.Game;
import com.project.model.User;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Clase de pruebas unitarias para la clase {@link PixelCoinsWindow}. Utiliza
 * TestFX para simular interacciones con la interfaz gráfica y JUnit 5 para
 * realizar las verificaciones.
 *
 * <p>
 * Incluye pruebas para las siguientes funcionalidades:</p>
 * <ul>
 * <li>Mostrar y ocultar contraseñas</li>
 * <li>Mostrar escenas específicas (lanzador, tienda, cuenta)</li>
 * <li>Registrar nuevos juegos</li>
 * <li>Ejecutar juegos</li>
 * </ul>
 *
 * @see PixelCoinsWindow
 * @see ApplicationTest
 */
class PixelCoinsWindowTest extends ApplicationTest {

    private PixelCoinsWindow pixelCoinsWindow;

    /**
     * Inicializa la ventana principal antes de ejecutar las pruebas.
     *
     * @param stage La instancia de {@link Stage} proporcionada por TestFX.
     */
    @Override
    public void start(Stage stage) {
        pixelCoinsWindow = new PixelCoinsWindow(null);
        pixelCoinsWindow.start(stage);
    }

    /**
     * Prueba unitaria para verificar la funcionalidad de mostrar y ocultar
     * contraseñas. Simula la interacción del usuario con los campos de
     * contraseña y texto visible. Verifica que los valores se transfieren
     * correctamente entre los campos y que la visibilidad cambia.
     */
    @Test
    void testShowDisguisePassword() {
        PixelCoinsWindow pixelCoinsWindow = new PixelCoinsWindow(null);

        PasswordField passwordField = new PasswordField();
        TextField textField = new TextField();

        passwordField.setText("password123");
        textField.setText("password123");

        // Alternar a mostrar la contraseña
        pixelCoinsWindow.showDisguisePassword(passwordField, textField);
        assertEquals("password123", textField.getText());
        assertFalse(passwordField.isVisible());
        assertTrue(textField.isVisible());

        // Alternar a ocultar la contraseña
        pixelCoinsWindow.showDisguisePassword(passwordField, textField);
        assertEquals("password123", passwordField.getText());
        assertTrue(passwordField.isVisible());
        assertFalse(textField.isVisible());
    }

    /**
     * Prueba unitaria para verificar que la escena del lanzador de juegos se
     * muestra correctamente. Verifica que los elementos de la interfaz, como la
     * tabla de juegos y los botones, están presentes.
     */
    @Test
    void testShowLaunchScene() throws Exception {
        FxToolkit.setupStage(stage -> {
            PixelCoinsWindow pixelCoinsWindow = new PixelCoinsWindow(new MainWindow());
            pixelCoinsWindow.start(stage);
            pixelCoinsWindow.showLaunchScene();
        });

        verifyThat(".table-view", (TableView<Game> table) -> table.getColumns().size() == 3);

        verifyThat("#btnAddGame", (Button btn) -> btn.getText().equals("Añadir juego"));
        verifyThat("#btnDeleteGame", (Button btn) -> btn.getText().equals("Borrar juego"));
        verifyThat("#btnExecuteGame", (Button btn) -> btn.getText().equals("Ejecutar juego"));
    }

    /**
     * Prueba unitaria para verificar que la escena de la tienda se muestra
     * correctamente. Verifica que los elementos de la interfaz, como el saldo
     * de monedas y los mensajes, están presentes.
     */
    @Test
    void testShowShopScene() throws Exception {
        FxToolkit.setupStage(stage -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setCurrentUser(new User("OLLIS950", "password123", "iker123@example.com"));
            PixelCoinsWindow pixelCoinsWindow = new PixelCoinsWindow(mainWindow);
            pixelCoinsWindow.start(stage);
            pixelCoinsWindow.showShopScene();
        });

        verifyThat("#lblBalance", (Label lbl) -> lbl.getText().startsWith("Monedas:"));
        verifyThat("#lblCommingSoon", (Label lbl) -> lbl.getText().contains("Proximamente productos en la tienda..."));

    }

    /**
     * Prueba unitaria para verificar que la escena de la cuenta del usuario se
     * muestra correctamente. Verifica que los campos de texto contienen la
     * información del usuario autenticado.
     */
    @Test
    void testShowAccountScene() throws Exception {
        FxToolkit.setupStage(stage -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setCurrentUser(new User("OLLIS950", "password123", "iker123@example.com"));
            pixelCoinsWindow = new PixelCoinsWindow(mainWindow);
            pixelCoinsWindow.start(stage);
            pixelCoinsWindow.showAccountScene();
        });

        verifyThat("#txtUserNameAccount", (TextField txt) -> txt.getText().equals("OLLIS950"));
        verifyThat("#txtPasswordAccount", (PasswordField txt) -> txt.getText().equals("password123"));
        verifyThat("#txtEmailAccount", (TextField txt) -> txt.getText().equals("iker123@example.com"));
    }

    /**
     * Prueba unitaria para verificar que un juego se registra correctamente.
     * Simula la interacción del usuario con los campos de texto y botones en la
     * ventana de registro de juegos.
     */
    @Test
    void testRegisterGamesWindow_Success() throws Exception {
        FxToolkit.setupStage(stage -> {
            PixelCoinsWindow pixelCoinsWindow = new PixelCoinsWindow(new MainWindow());
            pixelCoinsWindow.start(stage);

            Platform.runLater(pixelCoinsWindow::RegisterGamesWindow);
        });

        clickOn("#txtNameGame").write("Nuevo Juego");
        clickOn("#comboBox").clickOn("Aventura");
        clickOn("#txtPath").write("C:\\Dolphin-x64\\Dolphin.exe");

        clickOn("#btnAddGameRegisterGame");
        interact(() -> {
            Alert alert = pixelCoinsWindow.showAlert(Alert.AlertType.INFORMATION, "Éxito", null, "Juego agregado correctamente");
            assertEquals("Juego agregado correctamente", alert.getContentText());
        });
    }

    /**
     * Prueba unitaria para verificar que se muestra un error si faltan campos
     * obligatorios al intentar registrar un juego.
     */
    @Test
    void testRegisterGamesWindow_MissingFields() throws Exception {
        FxToolkit.setupStage(stage -> {
            PixelCoinsWindow pixelCoinsWindow = new PixelCoinsWindow(new MainWindow());
            pixelCoinsWindow.start(stage);

            // Abrir la ventana de registro de juegos
            Platform.runLater(pixelCoinsWindow::RegisterGamesWindow);
        });

        clickOn("#btnAddGameRegisterGame");

        interact(() -> {
            Alert alert = pixelCoinsWindow.showAlert(Alert.AlertType.ERROR, "Error", null, "Todos los campos deben completarse");
            assertEquals("Todos los campos deben completarse", alert.getContentText());
        });

    }

    /**
     * Prueba unitaria para verificar que se muestra un error si el juego ya
     * existe en la lista al intentar registrarlo nuevamente.
     */
    @Test
    void testRegisterGamesWindow_GameAlreadyExists() throws Exception {
        FxToolkit.setupStage(stage -> {
            PixelCoinsWindow pixelCoinsWindow = new PixelCoinsWindow(new MainWindow());
            pixelCoinsWindow.start(stage);

            Platform.runLater(pixelCoinsWindow::RegisterGamesWindow);
        });

        clickOn("#txtNameGame").write("Juego Existente");
        clickOn("#comboBox").clickOn("Aventura");
        clickOn("#txtPath").write("C:\\Dolphin-x64\\Dolphin.exe");

        clickOn("#btnAddGameRegisterGame");

        interact(() -> {
            Alert alert = pixelCoinsWindow.showAlert(Alert.AlertType.ERROR, "Error", null, "El juego ya está en la lista");
            assertEquals("El juego ya está en la lista", alert.getContentText());
        });
    }

    /**
     * Prueba unitaria para verificar que se muestra un error si no se
     * selecciona un juego antes de intentar ejecutarlo.
     */
    @Test
    void testExecuteGame_NoGameSelected() throws Exception {
        FxToolkit.setupStage(stage -> {
            PixelCoinsWindow pixelCoinsWindow = new PixelCoinsWindow(new MainWindow());
            pixelCoinsWindow.start(stage);

            pixelCoinsWindow.mainWindow.setCurrentUser(new User("OLLIS950", "password123", "iker@example.com"));
            TableView<Game> table = new TableView<>();
            Platform.runLater(() -> pixelCoinsWindow.executeGame(table));
        });

        interact(() -> {
            Alert alert = pixelCoinsWindow.showAlert(Alert.AlertType.ERROR, "Error", null, "Selecciona un juego antes de ejecutar");
            assertEquals("Selecciona un juego antes de ejecutar", alert.getContentText());
        });
    }

    /**
     * Prueba unitaria para verificar que se muestra un error si ocurre un
     * problema al intentar ejecutar un juego.
     */
    @Test
    void testExecuteGame_ErrorExecutingGame() throws Exception {
        FxToolkit.setupStage(stage -> {
            PixelCoinsWindow pixelCoinsWindow = new PixelCoinsWindow(new MainWindow());
            pixelCoinsWindow.start(stage);

            pixelCoinsWindow.mainWindow.setCurrentUser(new User("OLLIS950", "password123", "iker@example.com"));

            TableView<Game> table = new TableView<>();
            Game game = new Game("Invalid Game", "Aventura", "C:\\invalid\\path.exe");
            table.getItems().add(game);
            table.getSelectionModel().select(game);

            Platform.runLater(() -> pixelCoinsWindow.executeGame(table));
        });

        interact(() -> {
            Alert alert = pixelCoinsWindow.showAlert(Alert.AlertType.ERROR, "Error", null, "No se pudo ejecutar el juego.");
            assertEquals("No se pudo ejecutar el juego.", alert.getContentText());
        });
    }

}
