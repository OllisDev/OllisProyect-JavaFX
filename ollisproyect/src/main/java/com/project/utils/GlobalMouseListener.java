package com.project.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public class GlobalMouseListener implements NativeMouseInputListener {

    private final String targetWindowName; // Nombre de la ventana objetivo
    private boolean isWindowActive;
    private final Runnable onActivityDetected; // Variable global para indicar si la ventana está activa

    // Constructor que recibe el nombre de la ventana objetivo
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

    @Override
    public void nativeMouseClicked(NativeMouseEvent e) {
        checkActiveWindow();
        if (onActivityDetected != null) {
            onActivityDetected.run(); // Ejecutar la acción si la ventana está activa
        }
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent e) {
        checkActiveWindow();
        if (onActivityDetected != null) {
            onActivityDetected.run(); // Ejecutar la acción si la ventana está activa
        }
    }

    // Método para comprobar si la ventana activa coincide con el título esperado
    private void checkActiveWindow() {
        String activeWindowTitle = WindowsChecker.GetActiveWindowTitle();
        isWindowActive = targetWindowName.equals(activeWindowTitle);
    }

    // Método para obtener el estado de la ventana activa
    public boolean isWindowActive() {
        return isWindowActive;
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent e) {
        checkActiveWindow();
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {
        checkActiveWindow();
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent e) {
        checkActiveWindow();
    }
}
