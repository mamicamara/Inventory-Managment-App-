package com.gyp1.inventorymgtapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The InventoryMgtApp class is the starting point of the app (system). It
 * contains the main method of the program.
 *
 * @author  Mami Camara
 * @version April 2023
 *
 * FUTURE ENHANCEMENT - In future modifications, the application's Product model will have
 * a quantity attribute, and the application will prompt users to specify the quantity of
 * an associated part for a product when adding or modifying the product. Subsequently,
 * the application will prevent specifying multiple associated parts for a product.
 */
public class InventoryMgtApp extends Application {

    /**
     * Creates an instance of the controller that manages the main view.
     */
    MainController controller = new MainController();

    /**
     * The main entry point of this application.
     *
     * @param stage the main stage (window) for this application's
     *              GUI (Graphical User Interface).
     * @throws IOException when there is an error reading the fxml file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        //Load the main-view fxml to create the main scene.
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryMgtApp.class.getResource("main-view.fxml"));

        //Associate MainController to the main view.
        fxmlLoader.setController(controller);

        //Initialize the main scene using the main-view fxml.
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);

        //Initialize the MainController's variable that point to the scene created.
        controller.setView(scene);

        //Show the GUI.
        stage.show();
    }

    /**
     * The entry point of the application; useful in some cases such as when the
     * JavaFX launcher is not embedded in the executable file (JAR file), typical
     * when using an IDE.
     *
     * NOTE: The Javadoc for this application are included in the docs folder within
     * the root directory.
     *
     * @param args stores commandline arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}