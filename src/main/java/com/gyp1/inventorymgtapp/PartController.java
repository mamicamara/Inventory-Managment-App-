package com.gyp1.inventorymgtapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The PartController contains the logic for updating the parts list in the inventory
 * object from user input via the part-view.fxml based view, and also updating the same
 * view with data from the model.
 *
 * @author Mami Camara
 * @version April 2023
 */
public class PartController implements Initializable {
    /**
     * Stores a reference of the Scene object associated with the main-view.fxml.
     */
    private Scene parentView;

    /**
     * Used for generating part id when adding new Part objects.
     */
    private int maxPartID = 1;

    /**
     * A reference of the Inventory object instantiated in the MainController.
     */
    private Inventory inventory;

    /**
     * The current user action - either Add or Modify.
     */
    private UserAction action;

    /**
     * The index in the allParts ObservableList in the Inventory object 'inventory'
     * of the Part selected in the tblPart Table View of the main view screen.
     */
    private int selectedIndex = -1;

    /**
     * A Label component declared in the part-view.fxml file.
     */
    @FXML
    private Label lblCompanyMachine, lblTitle;

    /**
     * The Save and Cancel buttons declared in the part-view.fxml file.
     */
    @FXML
    private Button btnSave, btnCancel;

    /**
     * A ToggleGroup for grouping the RadioButtons rbtnInHouse and rbtnOutsourced.
     */
    @FXML
    private ToggleGroup toggleGrpSource;

    /**
     * The RadioButtons components in the part-view.fxml file for specifying an
     * InHouse or Outsourced part.
     */
    @FXML
    private RadioButton rbtnInHouse, rbtnOutsourced;

    /**
     * TextFields components in the part-view.fxml file.
     */
    @FXML
    private TextField tbxId, tbxName, tbxInv, tbxPrice, tbxMax, tbxMin, tbxCompanyOrMachine;

    /**
     * The Save button click event handler that adds into or updates a Part
     * object in the allParts list of the Inventory object.
     *
     * @param event an instance of ActionEvent.
     */
    @FXML
    protected void handleSaveButton(ActionEvent event) {
        Part newPart = getPartInput();

        if (newPart != null) {
            if (action == UserAction.ADD) {
                inventory.addPart(newPart);
            } else if (action == UserAction.MODIFY) {
                inventory.updatePart(selectedIndex, newPart);
            } else {
                MessageDialog.showDialog("Unknown operation", true);
            }
            backToMain(event);
        }
    }

    /**
     * The Cancel button click event handler that shows a dialog
     * and returns to the main screen when user clicks the Yes button
     * of the dialog. The part view screen is retained when the No button
     * is clicked.
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
     * The InHouseRadio RadionButton click event handler. It sets the
     * text of the lblCompanyMachine label to 'Machine ID'.
     *
     * @param event an instance of ActionEvent.
     */
    @FXML
    protected void handleInHouseRadio(ActionEvent event) {
        lblCompanyMachine.setText("Machine ID");
    }

    /**
     * The InHouseRadio RadionButton click event handler. It sets the
     * text of the lblCompanyMachine label to 'Company Name'.
     *
     * @param event an instance of ActionEvent.
     */
    @FXML
    protected void handleOutsourcedRadio(ActionEvent event) {
        lblCompanyMachine.setText("Company Name");
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
     * @param inv the value to assign to the inventory attribute.
     */
    public void setInventory(Inventory inv) {
        inventory = inv;
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
     * Generates and returns a new Part ID.
     *
     * @return an integer with the value of the new Part ID
     */
    private int generateID() {

        if (inventory.getAllParts().isEmpty()) {
            maxPartID = 1;
        } else {
            if (maxPartID < 1) {
                maxPartID = 0;
                for (Part part : inventory.getAllParts()) {
                    if (part.getId() > maxPartID) {
                        maxPartID = part.getId();
                    }
                }
            }
            maxPartID++;
        }
        return maxPartID;
    }

    /**
     * Retrieves the value in the TextFields and creates a new Part object when user action is ADD,
     * or update the selected Part object when user action is MODIFY.
     *
     * @return return a new object or an updated object depending on the current user action.
     *
     * RUNTIME ERROR - Runtime errors were encountered before enclosing in try-catch blocks the conversion
     * of the price input to a double and the conversions of min, max and machine id inputs to the respective
     * integers when the user typed in string values. The runtime errors were prevented by enclosing the
     * conversion of the inputs in try-catch blocks.
     */
    private Part getPartInput() {
        String name;
        double price;
        int id = -1, inv, min, max;

        if (action == UserAction.ADD){
            id = generateID();
        }else if (action == UserAction.MODIFY){
            try {
                id = Integer.parseInt(tbxId.getText());
            } catch (NumberFormatException ex) {
                MessageDialog.showDialog("Part ID must be an integer", true);
                return null;
            }
        }else{
            MessageDialog.showDialog("Unknown action!", "Expected add or modify operation", true);
            return null;
        }

        if (id < 1) {
            MessageDialog.showDialog("Invalid part ID", true);
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
                MessageDialog.showDialog("Inventory level must be greater than or equal to 0", true);
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
        } catch (NumberFormatException ex) {
            MessageDialog.showDialog("Price must be a floating number", true);
            return null;
        }

        try {
            max = Integer.parseInt(tbxMax.getText());
            if (inv > max) {
                MessageDialog.showDialog("Inventory level must be less than or equal to max", true);
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

        if (rbtnInHouse.isSelected()) {
            int machineId;

            try {
                machineId = Integer.parseInt(tbxCompanyOrMachine.getText());
                if (machineId < 1) {
                    MessageDialog.showDialog("Machine ID must be a positive integer", true);
                    return null;
                }
            } catch (NumberFormatException ex) {
                MessageDialog.showDialog("Machine ID must be an integer", true);
                return null;
            }

            return new InHouse(id, name, price, inv, min, max, machineId);
        } else if (rbtnOutsourced.isSelected()) {
            String companyName = tbxCompanyOrMachine.getText();
            if (companyName.trim().isEmpty()) {
                MessageDialog.showDialog("Company name is empty", true);
                return null;
            }

            try{
                Integer.parseInt(companyName);
                MessageDialog.showDialog("The company name is not valid",
                        "Please enter a name with alphanumeric characters",
                        true);
                return null;
            }catch (NumberFormatException ex) {}

            return new Outsourced(id, name, price, inv, min, max, companyName);

        } else {
            return null;
        }
    }

    /**
     * Selects the default radio button when user action is ADD, or populates
     * the TextField components with the values of the Part selected from the
     * main view when loading the part-view.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (action == UserAction.ADD) {
            rbtnInHouse.setSelected(true);
            tbxId.setText("Auto-Generated");
            lblTitle.setText("Add Part");
        } else if (action == UserAction.MODIFY && selectedIndex >= 0) {
            lblTitle.setText("Modify Part");
            Part part = inventory.getAllParts().get(selectedIndex);
            tbxId.setText(Integer.toString(part.getId()));
            tbxName.setText(part.getName());
            tbxInv.setText(Integer.toString(part.getStock()));
            tbxPrice.setText(Double.toString(part.getPrice()));
            tbxMax.setText(Integer.toString(part.getMax()));
            tbxMin.setText(Integer.toString(part.getMin()));

            if (part instanceof InHouse) {
                tbxCompanyOrMachine.setText(Integer.toString(((com.gyp1.inventorymgtapp.InHouse) part).getMachineId()));
                lblCompanyMachine.setText("Machine ID");
                rbtnInHouse.setSelected(true);
            } else if (part instanceof Outsourced) {
                tbxCompanyOrMachine.setText(((Outsourced) part).getCompanyName());
                lblCompanyMachine.setText("Company Name");
                rbtnOutsourced.setSelected(true);
            }
        }
    }

}
