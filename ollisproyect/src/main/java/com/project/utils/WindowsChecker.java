package com.project.utils;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

public class WindowsChecker {

    // Método estático para obtener el título de la ventana activa
    public static String GetActiveWindowTitle() {
        // Obtener el identificador de la ventana activa
        HWND hwnd = User32.INSTANCE.GetForegroundWindow();

        // Obtener el título de la ventana activa
        char[] windowText = new char[512];
        User32.INSTANCE.GetWindowText(hwnd, windowText, 512);

        return Native.toString(windowText);
    }

    public static void main(String[] args) {
        // Llamar al método estático y mostrar el título de la ventana activa
        String activeWindowTitle = WindowsChecker.GetActiveWindowTitle();
        System.out.println("Ventana activa: " + activeWindowTitle);
    }
}
