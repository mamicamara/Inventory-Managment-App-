package com.gyp1.inventorymgtapp;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.util.Optional;

/**
 * The ConfirmDialog class creates a custom confirm dialog using JavaFX's Dialog
 * class. It contains only the showDialog method that can be called without
 * creating an instance of the ConfirmDialog class.
 *
 * @author Mami Camara
 * @version April 2023
 */
public class ConfirmDialog {

    /**
     * Shows a confirm dialog and return true if the Yes button is clicked,
     * and returns false when the No button is clicked. It can be used to
     * confirm a Cancellation and Deletion action, which is specified via
     * the parameter variable.
     *
     * @param action a string specifying whether it's a cancel or delete action.
     * @return true if the Yes button is clicked and false if the No button is clicked.
     */
    public static boolean showDialog(String action) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirm " + action);
        dialog.setContentText("Are you sure you want to " + action);
        ButtonType cancelButtonType = new ButtonType("No", ButtonBar.ButtonData.NO);
        ButtonType deleteButtonType = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);
        dialog.getDialogPane().getButtonTypes().add(deleteButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        return result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.YES;
    }
}
