package com.gyp1.inventorymgtapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Inventory class encapsulates the attributes and behaviors of an inventory object. An
 * Inventory contains a list of Part objects and a list of Product objects with methods that
 * represent the behaviors of the inventory object. Parts and Product objects can be added,
 * removed, searched, updated and retrieved.
 *
 * @author Mami Camara
 * @version April 2023
 */
public class Inventory {
    /**
     * An observable list of stocked Part objects that can be used to make Products. An
     * ObservableList allows an event listener to track changes in it.
     */
    private ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * An observable list of stocked Product objects. An ObservableList allows an event listener
     * to track changes in it.
     */
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();


    /**
     * Inserts a new Part object to the list of this Inventory's parts list.
     *
     * @param newPart the Part object to add to this inventory's parts list.
     */
    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Inserts a new Product object to the list of this Inventory's products list.
     *
     * @param newProduct the Product object to add to this inventory's products list.
     */
    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Searches in the allParts list for a Part with an id that match the
     * value of the parameter variable and returns the matched Part object.
     *
     * @param partId the id of the part to search in the list.
     * @return a part object with an id that match the parameter value.
     */
    public Part lookupPart(int partId) {
        for (Part part : allParts){
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }


    /**
     * Searches in the allProducts list for a Product with an id that match the
     * value of the parameter variable and returns the matched Product object.
     *
     * @param productId the id of the product to search in the list.
     * @return a Product object with an id that match the parameter value.
     */
    public Product lookupProduct(int productId) {
        for (Product product : allProducts){
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    /**
     * Searches in the allParts list for parts with the name given in the parameter
     * variable and returns the matched parts in an ObservableList.
     *
     * @param partName the name of the part to search in the list.
     * @return an ObervableList of part that match the parameter value.
     */
    public ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> foundParts = FXCollections.observableArrayList();;
        for (Part part : allParts){
            if (part.getName().contains(partName)) {
                foundParts.add(part) ;
            }
        }
        return foundParts;
    }

    /**
     * Searches in the allProducts list for products with the name given in the parameter
     * variable and returns the matched products in an ObservableList.
     *
     * @param productName the name of the product to search in the list.
     * @return an ObervableList of products that match the parameter value.
     */
    public ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();;
        for (Product product : allProducts){
            if (productName.equalsIgnoreCase(product.getName())) {
                foundProducts.add(product) ;
            }
        }
        return foundProducts;
    }

    /**
     * Replaces the Part object in the allParts list at index given by the parameter
     * variable 'index' with the Part given by the parameter variable 'selectedPart'.
     *
     * @param index the index in the allParts list at which to replace the Part.
     * @param selectedPart the new part to replace the existing one.
     */
    public void updatePart(int index, Part selectedPart) {
        System.out.println(index);
        allParts.set(index, selectedPart);
    }

    /**
     * Replaces the Product object in the allProducts list at index given by the parameter
     * variable 'index' with the Product given by the parameter variable 'newProduct'.
     *
     * @param index the index in the allProducts list at which to replace the product.
     * @param newProduct the new product to replace the existing one.
     */
    public void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     * Removes from this Inventory the Part given in the parameter variable.
     *
     * @param selectedPart the Part object to remove.
     * @return true if the Part object was removed, or false otherwise.
     */
    public boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * Removes from this Inventory the Product given in the parameter variable.
     *
     * @param selectedProduct the Product object to remove.
     * @return true if Product object was removed, or false otherwise.
     */
    public boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     * Returns all the Part objects in this Inventory.
     *
     * @return all the Part objects in this Inventory.
     */
    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Returns all the Product objects in this Inventory.
     *
     * @return all the Product objects in this Inventory.
     */
    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }


}
