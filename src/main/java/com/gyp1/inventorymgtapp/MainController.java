package com.gyp1.inventorymgtapp;

import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The MainController controls the main view and provides linkage to the PartController
 *  and ProductController.
 *
 * @author Mami Camara
 * @version April 2023
 */
public class MainController implements Initializable {

    /* This section initializes the components in the main-view.fxml file. */

    /**
     * The TableView component that lists all parts. It is linked to the allParts
     * ObserverbleList in the inventory object.
     */
    @FXML
    private TableView<Part> tblParts;

    /**
     * The TableView component that lists all products. It is linked to the allProducts
     * ObserverbleList in the inventory object.
     */
    @FXML
    private TableView<Product> tblProducts;

    /**
     * The Part ID column of the tblParts TableView.
     */
    @FXML
    private TableColumn<Part, Integer> tbcolPrtID;

    /**
     * The Part Name column of the tblParts TableView.
     */
    @FXML
    private TableColumn<Part, String> tbcolPrtName;

    /**
     * The Inv column of the tblParts TableView.
     */
    @FXML
    private TableColumn<Part, Integer> tbcolPrtInv;

    /**
     * The Price column of the tblParts TableView.
     */
    @FXML
    private TableColumn<Part, Double> tbcolPrtPrice;

    /**
     * The Product ID column of the tblProducts TableView.
     */
    @FXML
    private TableColumn<Product, Integer> tbcolPrdtID;

    /**
     * The Name column of the tblProducts TableView.
     */
    @FXML
    private TableColumn<Product, String> tbcolPrdtName;

    /**
     * The Inv column of the tblProducts TableView.
     */
    @FXML
    private TableColumn<Product, Integer> tbcolPrdtInv;

    /**
     * The Price column of the tblProducts TableView.
     */
    @FXML
    private TableColumn<Product, Double> tbcolPrdtPrice;

    /**
     * The Exit button in the main view screen.
     */
    @FXML
    private Button btnExit;

    /**
     * The Search, Add, Modify and Delete buttons related to the tblParts Table View
     * of the main-view screen.
     */
    @FXML
    private Button btnPrtSearch, btnPrtAdd, btnPrtModify, btnPrtDelete;

    /**
     * The Search, Add, Modify and Delete buttons related to the tblProducts Table View
     * of the main-view screen.
     */
    @FXML
    private Button btnPrdtSearch, btnPrdtAdd, btnPrdtModify, btnPrdtDelete;

    /**
     * The Search TextField components in the main-view screen.
     */
    @FXML
    private TextField tbxPrtSearch, tbxPrdtSearch;

    /**
     * A reference to the main view scene (screen). Useful for returning back to
     * the screen without recreating it.
     */
    private Scene mainView;

    /**
     * A reference to the PartController.
     */
    private PartController partController;

    /**
     * A reference to the ProductController.
     */
    private ProductController productController;

    /**
     * Stores the only instance of the Inventory object that will store all the Parts
     * and Products information obtained from the screens.
     */
    private Inventory inventory;

    /**
     * A reference to the Part objects in the inventory allParts list that have been
     * filtered by the word in the tbxPrtSearch TextField.
     */
    private FilteredList<Part> filteredParts;

    /**
     * A reference to the Product objects in the inventory allProducts list that have been
     * filtered by the word in the tbxPrdtSearch TextField.
     */
    private FilteredList<Product> filteredProducts;


    /**
     * Initializes objects of the Inventory, PartController, ProductController classes, and
     * creates instances of the ObservableLists storing filtered part and Product objects.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inventory = new Inventory();
        // Wrap the parts observable list in a filtered list.
        filteredParts = new FilteredList<>(inventory.getAllParts(),  b->true);
        // Wrap the products observable list in a filtered list.
        filteredProducts = new FilteredList<>(inventory.getAllProducts(),  b->true);

        partController = new PartController();
        productController = new ProductController();

        partController.setInventory(inventory);
        productController.setInventory(inventory);

        bindPartsLisToTableView();
        bindProductsLisToTableView();
    }

    /**
     * Handler of the ActionEvent for the part Search button.
     *
     * @param event an instance of ActionEvent.
     */
    @FXML
    protected void handlePrtSearchBtn(ActionEvent event) {
        filterParts(tbxPrtSearch.getText());
    }

    /**
     * Handler of the ActionEvent for the part Add button.
     *
     * @param event an instance of ActionEvent.
     * @throws IOException if the 'part-view.fxml' cannot be loaded.
     */
    @FXML
    protected void handlePrtAddBtn(ActionEvent event) throws IOException {
        goToPartsPane(event, UserAction.ADD);
    }

    /**
     * Handler of the ActionEvent for the part Modify button.
     *
     * @param event an instance of ActionEvent.
     * @throws IOException if the 'part-view.fxml' cannot be loaded.
     */
    @FXML
    protected void handlePrtModifyBtn(ActionEvent event) throws IOException {
        goToPartsPane(event, UserAction.MODIFY);
    }

    /**
     * Handler of the ActionEvent for the part Delete button.
     *
     * @param event an instance of ActionEvent.
     * @throws IOException if the 'part-view.fxml' cannot be loaded.
     */
    @FXML
    protected void handlePrtDeleteBtn(ActionEvent event) throws IOException {
        if (ConfirmDialog.showDialog("delete")){
            Part selectedPart = tblParts.getSelectionModel().getSelectedItem();
            inventory.deletePart(selectedPart);
        }
    }

    /**
     * Handler of the ActionEvent for the product Search button.
     *
     * @param event an instance of ActionEvent.
     */
    @FXML
    protected void handlePrdtSearchBtn(ActionEvent event) {
        filterProducts(tbxPrdtSearch.getText());
    }

    /**
     * Handler of the ActionEvent for the product Add button.
     *
     * @param event an instance of ActionEvent.
     * @throws IOException if the 'part-view.fxml' cannot be loaded.
     */
    @FXML
    protected void handlePrdtAddBtn(ActionEvent event) throws IOException {
//        selectedProductID++;
        goToProductPane(event, UserAction.ADD);
    }

    /**
     * Handler of the ActionEvent for the product Modify button.
     *
     * @param event an instance of ActionEvent.
     * @throws IOException if the 'part-view.fxml' cannot be loaded.
     */
    @FXML
    protected void handlePrdtModifyBtn(ActionEvent event) throws IOException {
        goToProductPane(event, UserAction.MODIFY);
    }


    /**
     * Handler of the ActionEvent for the product Delete button.
     *
     * @param event an instance of ActionEvent.
     * @throws IOException if the 'part-view.fxml' cannot be loaded.
     */
    @FXML
    protected void handlePrdtDeleteBtn(ActionEvent event) throws IOException {
        Product selectedProduct = tblProducts.getSelectionModel().getSelectedItem();

        if (selectedProduct.getAllAssociatedParts().isEmpty()) {
            // Prompt user to confirm or cancel deletion
            if (ConfirmDialog.showDialog("delete")) {
                // Get from the products ObservableList the Product object that was
                // selected on the products TableView.
                inventory.deleteProduct(selectedProduct);
            }
        }
        else{
            MessageDialog.showDialog("You cannot delete a product with associated parts.",
                    "Please remove all parts first to be able to delete this product.",
                    true);
        }
    }


    /**
     * Handler of the ActionEvent for the Exit button.
     *
     * @param event an instance of ActionEvent.
     */
    @FXML
    protected void handleExitButton(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Assigns the value of the parameter to this controller's mainView variable.
     *
     * @param scene the scene to set as the reference to the main view scene.
     */
    public void setView(Scene scene) {
        this.mainView = scene;
        partController.setParentView(mainView);
        productController.setParentView(mainView);
    }

    /**
     * Adds an event to the application's stage so that Part and Product views (screens)
     * do not terminate the application when the close button is clicked.
     *
     * @param stage an object of the application's stage .
     */
    private void setOnCloseAction(Stage stage) {
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Stage stage =  ((Stage) e.getSource());
                if (mainView != stage.getScene()){
                    e.consume();
                }
                stage.setScene(mainView);
            }
        });
    }

    /**
     * Loads the part-view (screen) into the primary stage of the application
     * when either the Add or Delete buttons in the Part section are clicked.
     *
     * @param event the event associated with the Add or Delete button.
     * @param action either Add or Delete action.
     * @throws IOException when loading the part-view.fxml fails.
     */
    private void goToPartsPane(ActionEvent event, UserAction action) throws IOException {
        // Save the action in the PartController's action attribute.
        partController.setAction(action);

        if (action == UserAction.MODIFY) {
            // Retrieve the index in the parts Table View to identify the selected Part.
            int index = tblParts.getSelectionModel().getSelectedIndex();
            if (index < 0) {
                MessageDialog.showDialog("You must select the part to modify", true);
                return;
            }

            //Save the selected index to the PartController's selectedIndex attribute.
            partController.setSelectedIndex(index);
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("part-view.fxml"));
        loader.setController(partController);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        setOnCloseAction(stage);
        stage.setScene(new Scene(loader.load()));
    }

    /**
     * Loads the product-view (screen) into the primary stage of the application
     * when either the Add or Delete buttons in the product section are clicked.
     *
     * @param event the event associated with the Add or Delete button.
     * @param action either Add or Delete action.
     * @throws IOException when loading the product-view.fxml fails.
     */
    private void goToProductPane(ActionEvent event, UserAction action) throws IOException {
        productController.setAction(action);

        if (action == UserAction.MODIFY) {
            int index = tblProducts.getSelectionModel().getSelectedIndex();
            if (index < 0) {
                MessageDialog.showDialog("You must select the part to modify", true);
                return;
            }
            productController.setSelectedIndex(index);
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("product-view.fxml"));
        loader.setController(productController);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        setOnCloseAction(stage);
        stage.setScene(new Scene(loader.load()));
    }

    /**
     * Sets a predicate on the filteredParts Observable list. The predicate callback
     * function returns true for each Part object that has an id or name matching the
     * value in the given searchKey.
     *
     * @param searchKey the search item to lookup in the list.
     */
    private void filterParts(String searchKey) {
        filteredParts.setPredicate(part -> {
            if (searchKey == null || searchKey.isEmpty()) {
                return true;
            }
            String filter = searchKey.toLowerCase();

            if (part.getName().toLowerCase().indexOf(filter) != -1) {
                return true;
            } else if (String.valueOf(part.getId()).indexOf(filter) != -1) {
                return true;
            }
            else{
                return false;
            }
        });
    }

    /**
     * Sets a predicate on the filteredProducts Observable list. The predicate callback
     * function returns true for each Product object that has an id or name matching the
     * value in the given searchKey.
     *
     * @param searchKey the search item to lookup in the list.
     */
    private void filterProducts(String searchKey){
        filteredProducts.setPredicate(product -> {
            if (searchKey == null || searchKey.isEmpty()) {
                return true;
            }

            String filter = searchKey.toLowerCase();

            if (product.getName().toLowerCase().indexOf(filter) != -1) {
                return true;
            } else if (String.valueOf(product.getId()).indexOf(filter) != -1) {
                return true;
            }
            else{
                return false;
            }
        });
    }

    /**
     * Set the CellValueFactory for columns of the tblParts TableView in the part-view.fxml,
     * and links the Table View to the allParts ObservableList in the inventory object
     * via the parts FilteredList and SortedList objects.
     */
    private void bindPartsLisToTableView() {
        //Set the CellValueFactory for all columns of the tblParts TableView in main-view.fxml.
        tbcolPrtID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        tbcolPrtName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        tbcolPrtInv.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        tbcolPrtPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        // Wrap filtered parts in a SortedList
        SortedList<Part> sortedParts = new SortedList<>(filteredParts);
        // Add sorted and/or filtered parts to the parts table.
        sortedParts.comparatorProperty().bind(tblParts.comparatorProperty());
        // Add sorted and/or filtered products to the products  table.
        tblParts.setItems(sortedParts);

        tbxPrtSearch.textProperty().addListener((Observable, oldValue, newValue) -> {
            filterParts(newValue);
        });

    }

    /**
     * Set the CellValueFactory for columns of the tblProducts TableView in the product-view.fxml,
     * and links the TableView to the allProducts ObservableList in the inventory object via the
     * products FilteredList and SortedList objects.
     */
    private void bindProductsLisToTableView() {

        //Set the CellValueFactory for all columns of the tblProducts TableView in main-view.fxml.
        tbcolPrdtID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        tbcolPrdtName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        tbcolPrdtInv.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        tbcolPrdtPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));

        tbxPrdtSearch.textProperty().addListener((Observable, oldValue, newValue) -> {
            filterProducts(newValue);
        });

        //Wrap filtered products in a SortedList
        SortedList<Product> sortedProducts = new SortedList<>(filteredProducts);

        // Bind the SortedList comparator to the parts tableview comparator to effect sorting on tableview.
        sortedProducts.comparatorProperty().bind(tblProducts.comparatorProperty());

        // Add sorted and/or filtered products to the products  table.
        tblProducts.setItems(sortedProducts);
    }


}
