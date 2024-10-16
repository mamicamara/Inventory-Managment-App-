package com.gyp1.inventorymgtapp;

/**
 * The Outsourced class is a child of the Part class. An outsourced part is a part
 * that is obtained from an external company. It encapsulates all attributes
 * of the Part class in addition to the attribute machineID.
 *
 * @author Mami Camara
 * @version April 2023
 */
public class Outsourced extends Part {
    /**
     * Stores the company from which this part was outsourced.
     */
    private String companyName;

    /**
     * The default constructor, which initializes an Outsourced object with the values given
     * as parameters.
     *
     * @param id the identifier to set for this Outsourced part.
     * @param name the name to set for this Outsourced part.
     * @param price the price to set for this Outsourced part.
     * @param stock the current number of Outsourced part available as inventory.
     * @param min the minimum number of this Outsourced part that must be stocked.
     * @param max the maximum number of this Outsourced part that can be stocked.
     * @param companyName the company from which this part was outsourced.
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Assigns the value of the parameter companyName to this Outsourced part's
     * companyName attribute.
     *
     * @param companyName has the value to set as that of this Outsourced part's companyName.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Returns the name of the company from which this Outsourced part originated.
     *
     * @return a string representing the name of the company from which
     * this outsourced part was sourced.
     */
    public String getCompanyName() {
        return companyName;
    }


}
