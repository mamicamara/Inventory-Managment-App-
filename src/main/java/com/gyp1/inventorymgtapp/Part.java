package com.gyp1.inventorymgtapp;

/**
 * The abstract class Part encapsulates the attributes that make up a Product. It is
 * inherited by concrete classes: Outsourced and InHouse parts.
 *
 * @author Mami Camara
 * @version April 2023
 */
public abstract class Part {
    /**
     * The identifier of this Part.
     */
    private int id;
    /**
     * The name of this Part.
     */
    private String name;
    /**
     * The price of this Part.
     */
    private double price;
    /**
     * The inventory level of this Part.
     */
    private int stock;
    /**
     * The minimum inventory level for this Part.
     */
    private int min;
    /**
     * The maximum inventory level for this Part.
     */
    private int max;

    /**
     * Default constructor. Initializes an object of this class with the values of given parameters.
     *
     * @param id - the id to assign to this Part.
     * @param name - the name to assign to this Part.
     * @param price - the price of this Part.
     * @param stock - the inventory level of this Part.
     * @param min - the minimum inventory level allowed for this Part.
     * @param max - the maximum inventory level allowed for this Part.
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Assigns the value of the parameter to this Part's id.
     *
     * @param id - has the value to assign to the id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Assigns the value of the parameter to this Part's name.
     *
     * @param name has the value to assign to the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Assigns the value of the parameter to this Part's price.
     *
     * @param price has the value to assign to the price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Assigns the value of the parameter to this Part's stock.
     *
     * @param stock has the value to assign to the stock.
     */
    public void setStock(int stock) {
        this.min = min;
    }

    /**
     * Assigns the value of the parameter to this Part's min.
     *
     * @param min has the value to assign to the min.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Assigns the value of the parameter to this Part's max.
     *
     * @param max has the value to assign to the max.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Returns this Part's id.
     *
     * @return this Part's id.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns this Part's name.
     *
     * @return this Part's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns this Part's price/cost.
     *
     * @return this Part's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns this Part's current inventory level.
     *
     * @return this Part's current inventory level.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Returns the minimum value allowed for this Part's inventory level.
     *
     * @return the minimum value for this Part's inventory level.
     */
    public int getMin() {
        return min;
    }

    /**
     * Returns the maximum value allowed for this Part's inventory level.
     *
     * @return the maximum value for this Part's inventory level.
     */
    public int getMax() {
        return max;
    }
}
