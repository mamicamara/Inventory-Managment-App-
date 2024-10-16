package com.gyp1.inventorymgtapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The ProductController contains the logic for updating the products list in the inventory
 * object from user input via the part-view.fxml based view, and also updating the same
 * view with data from the model.
 *
 * @author Mami Camara
 * @version April 2023
 */
public class ProductController implements Initializable {
    /**
     * Stores a reference of the Scene object associated with the main-view.fxml.
     */
    private Scene parentView;

    /**
     * A reference of the Inventory object instantiated in the MainController.
     */
    private Inventory inventory;

    /**
     * Used for generating product id when adding new Product object.
     */
    private int maxProductID;

    /**
     * The current user action - either Add or Modify.
     */
    private UserAction action;

    /**
     * The index in the alProducts ObservableList in the Inventory object 'inventory'
     * of the Product selected in the tblProduct Table View of the main view screen.
     */
    private int selectedIndex = -1;

    private FilteredList<Part> filteredParts;

    /**
     * The title label
     */
    @FXML
    Label lblTitle;

    /**
     * The Search, Save, Cancel, Add and Delete Button components in  the product-view screen.
     */
    @FXML
    Button btnSearch, btnSave, btnCancel, btnAdd, btnDelete;

    /**
     * TextFields components in the product-view.fxml file.
     */
    @FXML
    TextField tbxId, tbxName, tbxInv, tbxPrice, tbxMax, tbxMin, tbxSearch;

    /**
     * The TableView component that lists all parts in the inventory's allParts
     * ObserverbleList.
     */
    @FXML
    private TableView<Part> tblviewParts;

    /**
     * The TableView component that lists all parts in a Product's associated ObserverbleList.
     */
    @FXML
    private TableView<Part> tblviewPrdctParts;

    /**
     * The Part ID column of the tblviewParts TableView.
     */
    @FXML
    private TableColumn<Part, Integer> tblcolPartID;

    /**
     * The Name column of the tblviewParts TableView.
     */
    @FXML
    private TableColumn<Part, String> tblcolPartName;

    /**
     * The Inv column of the tblviewParts TableView.
     */
    @FXML
    private TableColumn<Part, Integer> tblcolPartInv;

    /**
     * The Price column of the tblviewParts TableView.
     */
    @FXML
    private TableColumn<Part, Double> tblcolPartPrice;


    /**
     * The Part ID column of the tblviewPrdctParts TableView.
     */
    @FXML
    private TableColumn<Product, Integer> tblcolPrdctPartID;

    /**
     * The Name column of the tblviewPrdctParts TableView.
     */
    @FXML
    private TableColumn<Product, String> tblcolPrdctPartName;

    /**
     * The Inv column of the tblviewPrdctParts TableView.
     */
    @FXML
    private TableColumn<Product, Integer> tblcolPrdctPartInv;

    /**
     * The Price column of the tblviewPrdctParts TableView.
     */
    @FXML
    private TableColumn<Product, Double> tblcolPrdctPartPrice;

    /**
     * An ObservableList for temporarily storing parts associated to a product.
     */
    private ObservableList<Part> associatedParts;

    /**
     * Handler of the ActionEvent for the Add button in the product-view.fxml. It adds
     * the selected part from the tblviewParts Table View into the associatedParts
     * ObservableList.
     *
     * @param event an instance of ActionEvent.
     */
    @FXML
    protected void handleAddButton(ActionEvent event) {
        Part selectedPart = tblviewParts.getSelectionModel().getSelectedItem();
        if (selectedPart != null){
            associatedParts.add(selectedPart);
        }
    }

    /**
     * Handler of the ActionEvent for the Delete button in the product-view.fxml.
     *
     * @param event an instance of ActionEvent.
     */
    @FXML
    protected void handleDeleteButton(ActionEvent event) {
        if (ConfirmDialog.showDialog("delete")) {
            Part selectedPart = tblviewPrdctParts.getSelectionModel().getSelectedItem();
            if (selectedPart != null) {
                associatedParts.remove(selectedPart);
            }
        }
    }

    /**
     * Handler of the ActionEvent for the Search button in the product-view.fxml.
     *
     * @param event an instance of ActionEvent.
     */
    @FXML
    protected void handleSearchButton(ActionEvent event) {
        filterParts(tbxSearch.getText());
    }

    /**
     * The Save button click event handler that adds into or updates a Product
     * object in the allProducts list of the Inventory object.
     *
     * @param event an instance of ActionEvent.
     */
    @FXML
    protected void handleSaveButton(ActionEvent event) {
        Product newProduct = getProductInput();

        if (newProduct != null) {
            if (action == UserAction.ADD) {
                inventory.addProduct(newProduct);
            } else if (action == UserAction.MODIFY) {
                inventory.updateProduct(selectedIndex, newProduct);
            } else {
                MessageDialog.showDialog("Unknown operation", true);
            }
            backToMain(event);
        }

    }

    /**
     * The Cancel button click event handler that shows a dialog and returns
     * to the main screen when user clicks the Yes button of the dialog. The
     * part view screen is retained when the No button is clicked.
     *
     * @param event an instance of ActionEvent.
     */
    @FXML
    protected void handleCancelButton(ActionEvent event) {
        if (ConfirmDialog.showDialog("cancel")){
            backToMain(event);
        }
    }

    /**
     * A helper method that loads the main screen (main-view) to
     * the application's primary stage.
     *
     * @param event an instance of the ActionEvent.
     */
    private void backToMain(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(parentView);
    }

    /**
     *Assign to this class' action attribute the value of the given parameter variable.
     *
     * @param action the value to assign to the action attribute.
     */
    public void setAction(UserAction action) {
        this.action = action;
    }

    /**
     * Assign to this class' index attribute the value of the given parameter variable.
     *
     * @param index the value to assign to the index attribute.
     */
    public void setSelectedIndex(int index) {
        this.selectedIndex = index;
    }

    /**
     * Assign to this class' inventory attribute the value of the given parameter inv.
     *
     * @param inventory the value to assign to the inventory attribute.
     */
    void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Assign to this class' parentView attribute the value of the given parameter inv.
     *
     * @param parentView the value to assign to the parentView attribute.
     */
    public void setParentView(Scene parentView) {
        this.parentView = parentView;
    }

    /**
     * Populates the TextField components with the values of the Product selected from the
     * main view, initializes an ObservableList for temporarily storing parts associated to
     * a Product, and links the TableViews in the Product-view to their respective ObservableLists.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        associatedParts = FXCollections.observableArrayList();

        if (action == UserAction.ADD) {
            tbxId.setText("Auto-Generated");
            lblTitle.setText("Add Product");
        }
        // Population the fields if modifying the product
         else if (action == UserAction.MODIFY && selectedIndex >= 0) {
            lblTitle.setText("Modify Product");
            Product product = inventory.getAllProducts().get(selectedIndex);
            tbxId.setText(Integer.toString(product.getId()));
            tbxName.setText(product.getName());
            tbxInv.setText(Integer.toString(product.getStock()));
            tbxPrice.setText(Double.toString(product.getPrice()));
            tbxMax.setText(Integer.toString(product.getMax()));
            tbxMin.setText(Integer.toString(product.getMin()));

            associatedParts.addAll(product.getAllAssociatedParts());
        }

        // Bind the Parts list and Product parts list to respective tables.
        bindPartsLisToTableView();
    }

    /**
     * Generates and returns a new Product ID.
     *
     * @return an integer with the value of the new Product ID
     */
    private int generateID() {
        if (inventory.getAllProducts().isEmpty()) {
            maxProductID = 1;
        }
        else{
            if (maxProductID < 1) {
                maxProductID = 0;
                for (Product product : inventory.getAllProducts()) {
                    if (product.getId() > maxProductID) {
                        maxProductID = product.getId();
                    }
                }
            }
            maxProductID++;
        }
        return maxProductID;
    }

    /**
     * Sets a predicate on the filteredParts Observable list. The predicate callback
     * function returns true for each part object that has an id or name matching the
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
            } else {
                return false;
            }
        });
    }

    /**
     * Links the TableView components int the product-view.fxml file to their respective
     * ObservableLists. The tblviewParts is linked to the allParts ObservableLists of the
     * Inventory object, while the tblviewPrdctParts is linked to the associatedParts
     * ObservableLists that temporarily holds Part objects associated to a product.
     */
    private void bindPartsLisToTableView() {

        //Set the CellValueFactory for all columns of the tblviewParts TableView in products-view.fxml.
        tblcolPartID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        tblcolPartName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        tblcolPartInv.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        tblcolPartPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        // Wrap the parts observable list in a filtered list.
        filteredParts = new FilteredList<>(inventory.getAllParts(), b -> true);

        // Wrap filtered parts in a SortedList
        SortedList<Part> sortedParts = new SortedList<>(filteredParts);
        // Add sorted and/or filtered parts to the parts table.
        sortedParts.comparatorProperty().bind(tblviewParts.comparatorProperty());
        // Add sorted and/or filtered products to the products  table.
        tblviewParts.setItems(sortedParts);

        tbxSearch.textProperty().addListener((Observable, oldValue, newValue) -> {
            filterParts(newValue);
        });

        /// Setup the product's parts tableview

        // Bind the part's table to the inventory's parts list.
        tblcolPrdctPartID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        tblcolPrdctPartName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        tblcolPrdctPartInv.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        tblcolPrdctPartPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));

        tblviewPrdctParts.setItems(associatedParts);
    }

    /**
     * Retrieves the value in the TextFields and creates a new Product object when user action is ADD,
     * or updates the selected Product object when user action is MODIFY.
     *
     * @return return a new Product object or an updated Product object depending on the current user action.
     *
     * RUNTIME ERROR - Runtime errors were encountered before enclosing in try-catch blocks the conversion
     * of the price input to a double and the conversions of min and max inputs to the respective
     * integers when the user typed in string values. The runtime errors were prevented by enclosing the
     * conversion of the inputs in try-catch blocks.
     */
        private Product getProductInput() {
        String name;
        double price;
        int id = 0, inv, min, max;

        if (action == UserAction.ADD) {
            id = generateID();
        } else if (action == UserAction.MODIFY) {
            try {
                id = Integer.parseInt(tbxId.getText());
            } catch (NumberFormatException ex) {
                MessageDialog.showDialog("Product ID must be an integer", true);
                return null;
            }
        } else {
            MessageDialog.showDialog("Unknown action!", "Expected add or modify operation", true);
            return null;
        }

        if (id < 1) {
            MessageDialog.showDialog("Invalid product ID", true);
            return null;
        }

        name = tbxName.getText();
        if (name.trim().isEmpty()) {
            MessageDialog.showDialog("Name is empty", true);
            return null;
        }

        try {
            inv = Integer.parseInt(tbxInv.getText());
            if (inv < 0) {
                MessageDialog.showDialog("Inventory must be greater than or equal to 0", true);
                return null;
            }

        } catch (NumberFormatException ex) {
            MessageDialog.showDialog("Inventory level must be an integer", true);
            return null;
        }

        try {
            price = Double.parseDouble(tbxPrice.getText());

            if (price <= 0) {
                MessageDialog.showDialog("Price cannot be zero or negative", true);
                return null;
            }
            else if (price < getAllPartsCost(associatedParts)) {
                MessageDialog.showDialog("Price cannot be less than cost of parts", true);
                return null;
            }
        } catch (NumberFormatException ex) {
            MessageDialog.showDialog("Price must be a floating number", true);
            return null;
        }

        try {
            max = Integer.parseInt(tbxMax.getText());
            if (inv > max) {
                MessageDialog.showDialog("Inventory must be less than or equal to max", true);
                return null;
            }
        } catch (NumberFormatException ex) {
            MessageDialog.showDialog("Maximum inventory must be an integer", true);
            return null;
        }

        try {
            min = Integer.parseInt(tbxMin.getText());
            if (min < 0) {
                MessageDialog.showDialog("Minimum inventory level cannot be negative", true);
                return null;
            }
            if (max < min) {
                MessageDialog.showDialog("Max must be greater than min", true);
                return null;
            }
            if (min > inv) {
                MessageDialog.showDialog("Inventory level must be greater than or equal to min", true);
                return null;
            }
        } catch (NumberFormatException ex) {
            MessageDialog.showDialog("Minimum inventory must be an integer", true);
            return null;
        }


        if (associatedParts.isEmpty() && action == UserAction.ADD){
            MessageDialog.showDialog("Product must have at least one part", true);
            return null;
        }

        // Create a new product with the values obtained from the user.
        Product newProduct = new Product(id, name, price, inv, min, max);
        newProduct.getAllAssociatedParts().addAll(associatedParts);

        return newProduct;
    }

    /**
     * Calculates and returns the total cost of the parts makeing up
     * a Product.
     *
     * @param partsList an ObservableList with parts associated to a specific object.
     * @return total cost of all the parts in the list.
     */
    private double getAllPartsCost(ObservableList<Part> partsList){
        Double totalCost = 0.0;
        for(Part part : partsList){
            totalCost += part.getPrice();
        }
        return totalCost;
    }
}
