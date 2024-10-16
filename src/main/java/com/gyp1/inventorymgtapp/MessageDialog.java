package com.gyp1.inventorymgtapp;

import javafx.scene.control.Alert;

/**
 * The MessageDialog displays an Alert dialog without creating a new instance.
 *
 * @author Mami Camara
 * @version April 2023
 */
public class MessageDialog {

    /**
     * Display a message without a title via a pop-up (dialog window).
     *
     * @param message the message to display.
     * @param isError indicates whether it is an error or not.
     */
    public static void showDialog(String message, boolean isError){
        showDialog(message,null, isError);
    }

    /**
     * Display a message with a title via a pop-up (dialog).

     * @param title the title to display.
     * @param message the message to display.
     * @param isError indicates whether it is an error or not.
     */
    public static void showDialog(String title, String message, boolean isError){
        Alert alert;
        String prefix;
        if (isError) {
            prefix = "Error";
            alert = new Alert(Alert.AlertType.WARNING);
        }else{
            prefix = "Success";
            alert = new Alert(Alert.AlertType.INFORMATION);
        }

        alert.setTitle(prefix + " message");
        if (title == null){
            alert.setHeaderText(prefix);
        }else {
            alert.setHeaderText(title);
        }
        if (message != null){
            alert.setContentText(message);
        }
        alert.showAndWait();
    }

}
