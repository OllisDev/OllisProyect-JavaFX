package com.project.utils;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

/**
 * Clase de utilidad para interactuar con las ventanas activas en el sistema
 * operativo Windows. Utiliza la biblioteca JNA para acceder a las funciones
 * nativas de Windows.
 */
public class WindowsChecker {

    /**
     * Obtiene el título de la ventana activa en el sistema operativo Windows.
     *
     * @return Una cadena que representa el título de la ventana activa. Si no
     * hay una ventana activa, devuelve una cadena vacía.
     */
    public static String GetActiveWindowTitle() {
        // Obtener el identificador de la ventana activa
        HWND hwnd = User32.INSTANCE.GetForegroundWindow();

        // Obtener el título de la ventana activa
        char[] windowText = new char[512];
        User32.INSTANCE.GetWindowText(hwnd, windowText, 512);

        return Native.toString(windowText);
    }

    /**
     * Comprueba si una ventana específica está activa.
     *
     * @param windowName El nombre de la ventana que se desea comprobar.
     * @return {@code true} si la ventana especificada está activa,
     * {@code false} en caso contrario.
     */
    public static boolean isWindowActive(String windowName) {
        String activeWindowTitle = GetActiveWindowTitle();
        if (activeWindowTitle == null) {
            return false;
        }
        return activeWindowTitle.equalsIgnoreCase(windowName);
    }

    /**
     * Método principal para probar la funcionalidad de la clase. Muestra el
     * título de la ventana activa en la consola.
     *
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        // Llamar al método estático y mostrar el título de la ventana activa
        String activeWindowTitle = WindowsChecker.GetActiveWindowTitle();
        System.out.println("Ventana activa: " + activeWindowTitle);
    }
}
