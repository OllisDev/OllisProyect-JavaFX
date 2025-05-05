package com.project.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

/**
 * Clase que implementa un listener global para eventos del ratón. Utiliza la
 * biblioteca JNativeHook para capturar eventos globales del ratón y comprobar
 * si la ventana activa coincide con una ventana objetivo.
 */
public class GlobalMouseListener implements NativeMouseInputListener {

    private final String targetWindowName; // Nombre de la ventana objetivo
    private boolean isWindowActive;
    private final Runnable onActivityDetected; // Variable global para indicar si la ventana está activa

    /**
     * Constructor que inicializa el listener global del ratón.
     *
     * @param targetWindowName El nombre de la ventana objetivo que se debe
     * monitorear.
     * @param onActivityDetected Una acción que se ejecutará cuando se detecte
     * actividad del ratón.
     * @throws IllegalArgumentException Si el nombre de la ventana objetivo es
     * nulo o está vacío.
     */
    public GlobalMouseListener(String targetWindowName, Runnable onActivityDetected) {
        if (targetWindowName == null || targetWindowName.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la ventana no puede ser nulo o vacío.");
        }
        this.onActivityDetected = onActivityDetected;
        this.targetWindowName = targetWindowName;
        this.isWindowActive = false;

        try {
            // Desactivar los logs de JNativeHook
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.OFF);
            logger.setUseParentHandlers(false);

            // Registrar el hook global
            GlobalScreen.registerNativeHook();
            GlobalScreen.getInstance().addNativeMouseListener(this);
            //GlobalScreen.addNativeMouseMotionListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método llamado cuando se detecta un clic del ratón. Comprueba si la
     * ventana activa coincide con la ventana objetivo y ejecuta la acción
     * asociada si se detecta actividad.
     *
     * @param e El evento del ratón.
     */
    @Override
    public void nativeMouseClicked(NativeMouseEvent e) {
        checkActiveWindow();
        if (onActivityDetected != null) {
            onActivityDetected.run(); // Ejecutar la acción si la ventana está activa
        }
    }

    /**
     * Método llamado cuando se detecta movimiento del ratón. Comprueba si la
     * ventana activa coincide con la ventana objetivo y ejecuta la acción
     * asociada si se detecta actividad.
     *
     * @param e El evento del ratón.
     */
    @Override
    public void nativeMouseMoved(NativeMouseEvent e) {
        checkActiveWindow();
        if (onActivityDetected != null) {
            onActivityDetected.run(); // Ejecutar la acción si la ventana está activa
        }
    }

    /**
     * Comprueba si la ventana activa coincide con el título de la ventana
     * objetivo.
     */
    private void checkActiveWindow() {
        String activeWindowTitle = WindowsChecker.GetActiveWindowTitle();
        isWindowActive = targetWindowName.equals(activeWindowTitle);
    }

    /**
     * Devuelve el estado de la ventana activa.
     *
     * @return {@code true} si la ventana activa coincide con la ventana
     * objetivo, {@code false} en caso contrario.
     */
    public boolean isWindowActive() {
        return isWindowActive;
    }

    /**
     * Método llamado cuando se presiona un botón del ratón. Comprueba si la
     * ventana activa coincide con la ventana objetivo.
     *
     * @param e El evento del ratón.
     */
    @Override
    public void nativeMousePressed(NativeMouseEvent e) {
        checkActiveWindow();
    }

    /**
     * Método llamado cuando se suelta un botón del ratón. Comprueba si la
     * ventana activa coincide con la ventana objetivo.
     *
     * @param e El evento del ratón.
     */
    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {
        checkActiveWindow();
    }

    /**
     * Método llamado cuando se arrastra el ratón. Comprueba si la ventana
     * activa coincide con la ventana objetivo.
     *
     * @param e El evento del ratón.
     */
    @Override
    public void nativeMouseDragged(NativeMouseEvent e) {
        checkActiveWindow();
    }
}
