package com.gyp1.inventorymgtapp;

import com.gyp1.inventorymgtapp.Part;

/**
 * The InHouse class is a child of the Part class. An InHouse part represents those
 * parts that are manufactured in-house by the company stocking it. It encapsulates
 * all attributes of the Part class together with the attribute machineID.
 *
 * @author Mami Camara
 * @version April 2023
 */
public class InHouse extends Part {
    /**
     * Identifies the machine that manufactured this Part.
     */
    private int machineId;

    /**
     * The default constructor, which initializes an InHouse object with the values given
     * as parameters.
     *
     * @param id the identifier to set for this InHouse part.
     * @param name the name to set for this InHouse part.
     * @param price the price to set for this InHouse part.
     * @param stock the current number of InHouse part available as inventory.
     * @param min the minimum number of this InHouse part that must be stocked.
     * @param max the maximum number of this InHouse part that can be stocked.
     * @param machineId the machine identifier to set for this InHouse part.
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id,  name,   price,  stock,  min, max);
        this.machineId= machineId;
    }

    /**
     * Assigns the value of the parameter machineId to this InHouse part's
     * machineId attribute.
     * @param machineId the machine identifier to set for this InHouse part.
     */
    public void setMachineId(int machineId) {
        this.machineId=machineId;
    }

    /**
     * Returns this InHouse part's machineID attribute
     *
     * @return this InHouse part's machineID attribute
     */
    public int getMachineId() {
        return machineId;
    }
}
