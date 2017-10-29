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
    private Stack<Cargo> outbound  = new Stack<>(); //Outbound Cargo
    private Random gen = new Random();
    private int myID; //equals()
    private int numShipsInPort=0;
    private Queue<Ship>  inBoundShips = new Queue<>();
    private Queue<Ship>  outBoundShips = new Queue<>();
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
        if(local.toArray(Cargo.class)==null)
            return total;
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
        if(outbound.toArray(Cargo.class)==null)
            return total;
        for (Cargo unit : outbound.toArray(Cargo.class)) {
            total += unit.getTonnage();
        }
        return total;
    }

    /**
     * this will get the inBound of the ships
     *
     * @return - the ship that in the port
     */
    public Queue<Ship> getInBound() {
        return inBoundShips;
    }

    /**
     * this will put a ship at the end of the line a ship is put in the port
     * it will also increment for every ships there is in the port.
     *
     * @param shipComingIn - take in the ship that in coming into the port
     */
    public void inBoundShips(Ship shipComingIn) {
            inBoundShips.Enqueue(shipComingIn);
            numShipsInPort++;
    }

    /**
     * get the ships that are out of port
     *
     * @return - the ships going out of the port
     */
    public Queue<Ship> getOutBoundShips() {
        return outBoundShips;
    }

    /**
     * the inBoundShips.Dequeue has the data from the ship that you are trying to Dequeue from the Queue class
     * this will then set that ship to an outboundShip
     *
     * deincrament number of ships in a port beucase a ship left
     */
    public void outBoundShips() {
        outBoundShips.Enqueue(inBoundShips.Dequeue());
        numShipsInPort--;
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
     */
    public void load() {
        for (int i = 0; i < outbound.size(); i++) {
            if (inBoundShips.peek().load(outbound.toArray(Cargo.class)[i])) {
                outbound.pop();
            }
        }
        outBoundShips();
    }

    /**
     * unloads certain cargo from the ship.
     */
    public void unload() {
        ArrayList<Cargo> unloaded = inBoundShips.peek().unload(getName());
        if (inBoundShips.peek().getCct() == null){
            return;
        }
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
        output = output + "\tPort Number: " + getID() + "\n**LOCAL CARGO**\n\t";
        if (local.toArray(Cargo.class) != null) {
            for (Cargo unit : local.toArray(Cargo.class)) {
                output = output + unit + "\t";
            }
        } else
            output += 0.0;
        output += "\n";
        output = output + "**END LOCAL CARGO**\n\tTotal Tonnage of Local Cargo: " + getLocalTonnage() + "\n**OUTBOUND CARGO**\n";
        if (outbound.toArray(Cargo.class) != null) {
            for (Cargo unit : outbound.toArray(Cargo.class)) {
                output = output + unit;
            }
        } else
            output += 0;
        output += "**END OUTBOUND CARGO**\n\tTotal Tonnage of Outbound Cargo: " + getOutboundTonnage() + "\n";
        return output;
    }
}
