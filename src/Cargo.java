/**
 * @author Edgar Centeno
 * @author Joe Matteson
 */

import java.util.*;

/**
 * this is the cargo class,
 */
public class Cargo {
    private static int trackNumber = 0;
    private int orderNum; //using for equals()
    private String dest = "";
    private double tons = 0.0; //Ordering by tonnage!

    /**
     * this is the constructor where the user can set the destination and weight in tonns for the cargo
     * @param inDest - takes the destination from user
     * @param inTons - takes the tons of cargo from user as a double
     */
    public Cargo(String inDest, double inTons) {
        setDest(inDest);
        setTonnage(inTons);
        setOrderNumber();
    }

    /**
     * sets order number to track number incrementing by 1
     */
    private void setOrderNumber() {
        orderNum = trackNumber++;
    }

    /**
     * this is the gets cargo order Number
     * @return order number
     */
    public int getOrderNumber() {
        return orderNum;
    }

    /**
     * This gets the destination for cargo
     * @return return destination
     */
    public String getDest() {
        return dest;
    }

    /**
     * this sets dest to inDest
     * @param inDest take in inDest from user and set dest to user inDest
     */
    private void setDest(String inDest) {
        dest = inDest;
    }

    /**
     *this gets the tons
     * @return tons
     */
    public double getTonnage() {
        return tons;
    }

    /**
     *this sets the tons
     * @param inTons take in user inTons and set tons to user inTons if inTons is more than or equal to zero
     *               this will not allow negatives
     */
    private void setTonnage(double inTons) {
        if (inTons >= 0) {
            tons = inTons;
        }
    }

    /**
     * this will check two cargo objects to see if they equal in size
     * @param other - checks the other cargo to see if they equal in size
     * @return - boolean
     */
    public boolean equals(Cargo other) {
        if (getOrderNumber() == other.getOrderNumber())
            return true;
        return false;
    }

    /**
     * this will compare one cargo to another cargo in terms of weight
     * @param other - grabs the other cargo object
     * @return - return -1 if original tonnage is less than other cargos tonnage
     *         - returns 1 if original tonnage is more than other cargo tonnage
     *         - returns 0 if original tonnage is equal to other cargos tonnage
     */
    public int compareTo(Cargo other) {
        //return getTonnage() - other.getTonnage(); //Can't use because tons is a double! (rounding errors)
        //return getDest().compareTo(other.getDest()); //Would work if I wanted to order by destination
        if (getTonnage() < other.getTonnage())
            return -1;
        else if (getTonnage() > other.getTonnage())
            return 1;
        else
            return 0;
    }

    /**
     * toString will format so it will output in a readable form
     * @return - the output format
     */
    public String toString() {
        String output = "Cargo object #" + getOrderNumber() + " has the following information:\n";
        output = output + "\t" + getTonnage() + " tons\n\tTo be delivered to: " + getDest() + "\n";
        return output;
    }

}