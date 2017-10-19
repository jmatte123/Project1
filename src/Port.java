import java.util.*;

/**
 * @author Joe Matteson
 * @author Edgar Centeno
 * <p>
 * this is the Port class which represents a port for ships and thier cargo.
 */
public class Port {

    // --- Fields --- //
    private static int nextID = 0;
    private String name = ""; //Order by name
    private Stack<Cargo> local = new Stack<>(); //Local Cargo
    private Stack<Cargo> outbound = new Stack<>(); //Outbound Cargo
    private Random gen = new Random();
    private int myID; //equals()

    /**
     * Default Port constructor.  Creates a new port with a new id.
     */
    public Port() {
        setID();
    }

    /**
     * This is a constructor  that takes in a name for the new port.
     *
     * @param inName name of the port.
     */
    public Port(String inName) {
        setName(inName);
        setID();
    }

    /**
     * increment the id to get a new id for a new port.
     */
    private void setID() {
        myID = nextID++;
    }

    /**
     * get the id of the port.
     *
     * @return the ports id.
     */
    public int getID() {
        return myID;
    }

    /**
     * get the local cargo at the port.
     *
     * @return the cargo.
     */
    public Stack<Cargo> getLocal() {
        return local;
    }

    /**
     * get the cargo that is being shipped oit somewhere else.
     *
     * @return the cargo that is being shipped.
     */
    public Stack<Cargo> getOutbound() {
        return outbound;
    }

    /**
     * get the name of the port.
     *
     * @return the name of the port.
     */
    public String getName() {
        return name;
    }

    /**
     * set the name of the port.
     *
     * @param inName the new name of the port.
     */
    public void setName(String inName) {
        name = inName;
    }

    /**
     * get the local tonnage.
     *
     * @return the total amount of tonnage in the local stock.
     */
    public double getLocalTonnage() {
        double total = 0.0;
        for (Cargo unit : local.toArray(Cargo.class)) {
            total += unit.getTonnage();
        }
        return total;
    }

    /**
     * get the amount of tonnage that the outbound has.
     *
     * @return the total amount of tonnage.
     */
    public double getOutboundTonnage() {
        double total = 0.0;
        for (Cargo unit : outbound.toArray(Cargo.class)) {
            total += unit.getTonnage();
        }
        return total;
    }

    /**
     * adds cargo to the outbound cargo pile.
     *
     * @param inCargo cargo to put on the outbound.
     */
    public void addOutbound(Cargo inCargo) {
        if (inCargo.getTonnage() >= 0) {
            outbound.push(inCargo);
        }
    }

    /**
     * load cargo onto the ship.
     *
     * @param targetShip the ship that will take the cargo.
     */
    public void load(Ship targetShip) {
        for (int i = 0; i < outbound.size(); i++) {
            if (targetShip.load(outbound.toArray(Cargo.class)[i])) {
                outbound.pop();
            }
        }
    }

    /**
     * unloads certain cargo from the ship.
     *
     * @param targetShip the ship that will be unloaded.
     */
    public void unload(Ship targetShip) {
        ArrayList<Cargo> unloaded = targetShip.unload(getName());
        for (Cargo unit : unloaded) {
            if (getName().equals(unit.getDest())) {
                local.push(unit);
            } else {
                outbound.push(unit);
            }
        }

    }

    /**
     * unloads all the cargo from the ship and sorts it out given the cargo's destination.
     *
     * @param targetShip the ship that will be unloaded.
     */
    public void unloadAll(Ship targetShip) {
        ArrayList<Cargo> unloaded = targetShip.unloadAll();
        for (Cargo unit : unloaded) {
            if (getName().equals(unit.getDest())) {
                local.push(unit);
            } else {
                outbound.push(unit);
            }
        }

    }

    /**
     * returns true or false depending upon if two ports have the same id.
     *
     * @param other the other port for testing if it is equal.
     * @return true if it is the same otherwise false.
     */
    public boolean equals(Port other) {
        if (getID() == other.getID())
            return true;
        return false;
    }

    /**
     * compares the names of two ports.
     *
     * @param other the other port to compare it to
     * @return negative if the string is smaller than the other, positive if the string is bigger,
     * and 0 if the string is the same size.
     */
    public int compareTo(Port other) {
        return getName().compareTo(other.getName());
    }

    /**
     * string representation of the class.
     *
     * @return the output format.
     */
    public String toString() {
        String output = "";
        output = "This is " + getName() + ":\n";
        output = output + "\tPort Number: " + getID() + "\n*****LOCAL CARGO*********\n";
        for (Cargo unit : local.toArray(Cargo.class)) {
            output = output + unit;
        }
        output = output + "*****END LOCAL CARGO*********\n\tTotal Tonnage of Local Cargo: " + getLocalTonnage() + "\n*****OUTBOUND CARGO*********\n";
        for (Cargo unit : outbound.toArray(Cargo.class)) {
            output = output + unit;
        }
        output = output + "*****END OUTBOUND CARGO*********\n\tTotal Tonnage of Outbound Cargo: " + getOutboundTonnage() + "\n";
        return output;
    }
}
