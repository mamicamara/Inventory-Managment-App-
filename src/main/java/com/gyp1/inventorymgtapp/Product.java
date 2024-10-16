package com.gyp1.inventorymgtapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Product class represents a real-life product as an object of
 * seven attributes: id, name, price, stock, minimum and maximum
 * inventory levels permitted, and a list of Part objects.
 *
 * @author Mami Camara
 * @version April 2023
 */
public class Product {
    /**
     * A list of Part objects that make up this product. An ObservableList
     * is used to permit listeners to track changes.
     */
    private final ObservableList<Part> associatedParts;
    /**
     * The identifier of this Product.
     */
    private int id;
    /**
     * The name of this Product.
     */
    private String name;
    /**
     * The price of this Product.
     */
    private double price;
    /**
     * The current inventory level (amount of stock) for this Product.
     */
    private int stock;
    /**
     * The minimum value for this Products inventory level.
     */
    private int min;
    /**
     * The maximum value for this Products inventory level.
     */
    private int max;

    /**
     * Default constructor. Initializes a Product object using the values
     * given as parameters.
     *
     * @param id - the id to assign to this Product.
     * @param name - the name to assign to this Product.
     * @param price - the price of this Product.
     * @param stock - the inventory level of this Product.
     * @param min - the minimum inventory level allowed for this Product.
     * @param max - the maximum inventory level allowed for this Product.
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        associatedParts = FXCollections.observableArrayList();
    }

    /**
     * Assigns the value of the parameter to this Product's id.
     *
     * @param id - has the value to assign to the id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Assigns the value of the parameter to this Product's name.
     *
     * @param name has the value to assign to the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Assigns the value of the parameter to this Product's price.
     *
     * @param price has the value to assign to the price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Assigns the value of the parameter to this Product's stock.
     *
     * @param stock has the value to assign to the stock.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Assigns the value of the parameter to this Product's min.
     *
     * @param min has the value to assign to the min.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Assigns the value of the parameter to this Product's max.
     *
     * @param max has the value to assign to the max.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Returns this Product's id.
     *
     * @return this Product's id.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns this Product's name.
     *
     * @return this Product's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns this Product's price/cost.
     *
     * @return this Product's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns this Product's current inventory level.
     *
     * @return this Product's current inventory level.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Returns the minimum value allowed for this Product's inventory level.
     *
     * @return the minimum value for this Product's inventory level.
     */
    public int getMin() {
        return min;
    }

    /**
     * Returns the maximum value allowed for this Product's inventory level.
     *
     * @return the maximum value for this Product's inventory level.
     */
    public int getMax() {
        return max;
    }


    /**
     * Adds an object of the Part class to the list storing this Product's parts.
     *
     * @param part the Part object to add to the list.
     */
    public void addAssociatedPart(Part part) {
        this.associatedParts.add(part);
    }

    /**
     * Removes from this Product the Part object specified in the parameter.
     *
     * @param selectedAssociatedPart the part to be disassociated from this product.
     * @return true if the associated part is removed, or false if otherwise.
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * Returns all the Part objects that constitute this Product.
     * @return an ObservableList of all parts associated to this product.
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }



}
