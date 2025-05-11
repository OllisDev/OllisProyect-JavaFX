
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import com.project.MainWindow;
import com.project.model.User;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Clase de pruebas unitarias para la clase {@link MainWindow}. Utiliza TestFX
 * para simular interacciones con la interfaz gráfica y JUnit 5 para realizar
 * las verificaciones.
 *
 * <p>
 * Incluye pruebas para las siguientes funcionalidades:</p>
 * <ul>
 * <li>Registro de usuarios</li>
 * <li>Inicio de sesión</li>
 * <li>Mostrar y ocultar contraseñas</li>
 * <li>Mostrar alertas</li>
 * </ul>
 *
 * @see MainWindow
 * @see ApplicationTest
 */
public class MainWindowTest extends ApplicationTest {

    private MainWindow mainWindow;

    /**
     * Prueba unitaria para verificar que se puede establecer y obtener el
     * usuario actual en la clase {@link MainWindow}.
     */
    @Test
    void testSetAndGetCurrentUser() {
        mainWindow = new MainWindow();
        User user = new User("Iker", "Magro", "OLLIS950", "password", "iker123@example.com", LocalDateTime.now());

        mainWindow.setCurrentUser(user);
        assertEquals(user, mainWindow.getCurrentUser());
    }

    /**
     * Método que inicializa la ventana principal antes de ejecutar las pruebas.
     *
     * @param stage La instancia de {@link Stage} proporcionada por TestFX.
     */
    @Override
    public void start(Stage stage) {
        mainWindow = new MainWindow();
        mainWindow.start(stage);
    }

    /**
     * Prueba unitaria para verificar el flujo de registro de usuarios. Simula
     * la interacción del usuario con los campos de texto, el selector de fecha
     * y el botón de registro. Verifica que se muestra una alerta de éxito al
     * completar el registro.
     */
    @Test
    void testRegisterWindow() {
        clickOn("Registrarse");
        verifyThat("#lblRegister", hasText("REGISTRO"));

        clickOn("#txtNameRegister").write("Iker");
        clickOn("#txtLastNameRegister").write("Magro");
        clickOn("#txtUserNameRegister").write("OLLIS950");
        clickOn("#txtPasswordRegister").write("password");
        clickOn("#txtEmailRegister").write("iker123@example.com");

        interact(() -> {
            DatePicker datePicker = lookup("#datePickerRegister").query();
            datePicker.setValue(java.time.LocalDate.of(2000, 1, 1));
        });

        verifyThat("#datePickerRegister", datePicker
                -> ((DatePicker) datePicker).getValue().toString().equals("2000-01-01")
        );

        clickOn("#btnAcceptRegister");
        interact(() -> {
            Alert alert = mainWindow.showAlert(Alert.AlertType.INFORMATION, "Información", null, "Registro exitoso");
            assertEquals("Registro exitoso", alert.getContentText());
        });
    }

    /**
     * Prueba unitaria para verificar el flujo de inicio de sesión. Simula la
     * interacción del usuario con los campos de texto y el botón de inicio de
     * sesión. Verifica que se muestra una alerta de éxito al iniciar sesión
     * correctamente.
     */
    @Test
    void testLogInWindow() {
        clickOn("Iniciar sesión");

        verifyThat("#lblLogIn", hasText("INICIO SESIÓN"));

        clickOn("#txtUserNameLogIn").write("OLLIS950");
        clickOn("#txtPasswordLogIn").write("password123");

        clickOn("#btnAcceptLogIn");
        interact(() -> {
            Alert alert = mainWindow.showAlert(Alert.AlertType.INFORMATION, "Información", null, "Inicio de sesión exitoso");
            assertEquals("Inicio de sesión exitoso", alert.getContentText());
        });

    }

    /**
     * Prueba unitaria para verificar la funcionalidad de mostrar y ocultar
     * contraseñas. Simula la interacción del usuario con los campos de
     * contraseña y texto visible. Verifica que los valores se transfieren
     * correctamente entre los campos y que la visibilidad cambia.
     */
    @Test
    void testShowDisguisePassword() {
        PasswordField passwordField = new PasswordField();
        TextField textField = new TextField();

        passwordField.setText("password123");
        textField.setText("password123");

        // Alternar a mostrar la contraseña
        mainWindow.showDisguisePassword(passwordField, textField);
        assertEquals("password123", textField.getText());
        assertEquals(false, passwordField.isVisible());
        assertEquals(true, textField.isVisible());

        // Alternar a ocultar la contraseña
        mainWindow.showDisguisePassword(passwordField, textField);
        assertEquals("password123", passwordField.getText());
        assertEquals(true, passwordField.isVisible());
        assertEquals(false, textField.isVisible());
    }

    /**
     * Prueba unitaria para verificar la funcionalidad de mostrar alertas. Crea
     * una alerta de prueba y verifica que los valores de tipo, título y
     * contenido sean correctos.
     */
    @Test
    void testShowAlert() {
        Platform.runLater(() -> {
            Alert alert = mainWindow.showAlert(Alert.AlertType.INFORMATION, "Información", null, "Mensaje de prueba");
            assertEquals(Alert.AlertType.INFORMATION, alert.getAlertType());
            assertEquals("Información", alert.getTitle());
            assertEquals("Mensaje de prueba", alert.getContentText());
        });
    }
}
