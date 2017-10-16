import java.util.*;

/**
 * @author Joe Matteson
 * @author Edgar Centeno
 * we used github! yay
 *
 * this is the Ship class, which represents a cargo ship.
 */
public class Ship {

    // --- Fields --- //
    private static int nextID = 0;
    private String name = "";
    private double mct = 0.0; //Max Cargo Tonnage; using this for compareTo()
    private Stack<Cargo> cct = new Stack<>();
    private int speed = 0; //Speed of Ship
    private int dist = 0; //Distance between Ship and its current destination
    private Random gen = new Random();
    private int myID; //equals()

    /**
     * this is the constructor
     * the user will add in the name, max, speed and distance of the ship
     *
     * @param inName  - user inputs name of Ship
     * @param inMax   - user inputs the max tonnage
     * @param inSpeed - user inputs seed of Ship
     * @param inDist  - user inputs the distance of the Ship
     */
    public Ship(String inName, double inMax, int inSpeed, int inDist) {
        setName(inName);
        setMax(inMax);
        setSpeed(inSpeed);
        setDistance(inDist);
        setID();
    }

    /**
     * this is another Ship constructor that will take in only the name, max tonnage and speed for Ship
     * this will also set a random distance between.
     *
     * @param inName- takes in the of Ship.
     * @param inMax   - takes in the max tonnage -  Ship.
     * @param inSpeed - takes in the speed of Ship.
     */
    public Ship(String inName, double inMax, int inSpeed) {
        setName(inName);
        setMax(inMax);
        setSpeed(inSpeed);
        int tmp = gen.nextInt(900) + 101;
        setDistance(tmp);
        setID();
    }

    /**
     * This constructor only takes in the Ship name and manx tonnage.
     * this will also generate a random distance and a random speed.
     *
     * @param inName - user inputs Ship name.
     * @param inMax  - user inputs Max tonnage.
     */
    public Ship(String inName, double inMax) {
        setName(inName);
        setMax(inMax);
        int tmp = gen.nextInt(51) + 10;
        setSpeed(tmp);
        tmp = gen.nextInt(900) + 101;
        setDistance(tmp);
        setID();
    }

    /**
     * This constructor only takes in the name of the ship.
     * It will also randomly set the distance, max tonnage and speed of Ship.
     *
     * @param inName - user inputs Ship name.
     */
    public Ship(String inName) {
        setName(inName);
        double temp = (gen.nextDouble() * 700) + 50;
        setMax(temp);
        int tmp = gen.nextInt(51) + 10;
        setSpeed(tmp);
        tmp = gen.nextInt(900) + 101;
        setDistance(tmp);
        setID();
    }

    /**
     * This constructor will not take in anything.
     * It will also randomly set the distance, max tonnage and speed of Ship.
     * The ship name will be changed to a  ID incrementing as each ship is made
     */
    public Ship() {
        double temp = (gen.nextDouble() * 700) + 50;
        setMax(temp);
        int tmp = gen.nextInt(51) + 10;
        setSpeed(tmp);
        tmp = gen.nextInt(900) + 101;
        setDistance(tmp);
        setID();
    }

    /**
     * this gets the Id of Ship.
     *
     * @return the id of the ship.
     */
    public int getID() {
        return myID;
    }

    /**
     * this sets Id of ship incremented
     */
    private void setID() {
        myID = nextID++;
    }

    /**
     * get speed of Ship.
     *
     * @return the speed of the ship.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * set speed of Ship.
     *
     * @param inSpeed - user sets the speed and checks if speed is not negative.
     */
    public void setSpeed(int inSpeed) {
        if (inSpeed >= 0) {
            speed = inSpeed;
        }
    }

    /**
     * gets distance.
     *
     * @return - distance.
     */
    public int getDistance() {
        return dist;
    }

    /**
     * sets distance.
     *
     * @param inDist - takes in  distance and checks to make sure its not negative.
     */
    public void setDistance(int inDist) {
        if (inDist >= 0) {
            dist = inDist;
        }
    }

    /**
     * gets the current cargo.
     *
     * @return - cct.
     */
    public Stack<Cargo> getCurrentCargo() {
        return cct;
    }

    /**
     * (
     * gets max tonnage.
     *
     * @return - mct (Max Cargo Tonnage).
     */
    public double getMax() {
        return mct;
    }

    /**
     * sets the max tonnage and makes sure its not negative.
     *
     * @param inMax - takes in max tonnage as a double.
     */
    public void setMax(double inMax) {
        if (inMax >= 0) {
            mct = inMax;
        }
    }

    /**
     * gets name.
     *
     * @return - name of the ship.
     */
    public String getName() {
        return name;
    }

    /**
     * takes in name.
     *
     * @param inName - sets a new name for the ship.
     */
    public void setName(String inName) {
        name = inName;
    }

    /**
     * get current cargo tonnage.
     *
     * @return -
     */
    public double getCurrentCargoTonnage() {
        double total = 0.0;
        for (Cargo unit : cct.toArray()) {
            total += unit.getTonnage();
        }
        return total;
    }

    /**
     * grabs a Ship and another ship and checks if they are equal in name
     *
     * @param other -  grabs the other ship
     * @return boolean
     */
    public boolean equals(Ship other) {
        if (getID() == other.getID())
            return true;
        return false;
    }

    /**
     * compares ship with another ship to see if max tonnage.
     *
     * @param other grabs other ship.
     * @return - -1 if original max tonnage is less than other max tonnage.
     * - 1 if original max tonnage is more than other max tonnage.
     * - 0 if both original and max tonnage equal.
     */
    public int compareTo(Ship other) {
        if (getMax() < other.getMax())
            return -1;
        else if (getMax() > other.getMax())
            return 1;
        else
            return 0;
    }

    /**
     * load cargo into cargo space.
     *
     * @param inCargo - takes in cargo.
     * @return boolean | false if cargo tonnage is negative or if get tonnage + current
     * cargo tonnage is more than max tonnage.
     * true if cargo is positive and is less than max tonnage.
     */
    public boolean load(Cargo inCargo) {
        if (inCargo.getTonnage() < 0)
            return false;
        if (inCargo.getTonnage() + getCurrentCargoTonnage() > getMax())
            return false;

        cct.push(inCargo);
        return true;

    }

    /**
     * unloads cargo.
     *
     * @param port takes in the port that it is going to unload at.
     * @return list of cargo.
     */
    public ArrayList<Cargo> unload(String port) {
        ArrayList<Cargo> toUnload = new ArrayList<Cargo>();
        for (int x = cct.size() - 1; x >= 0; x--) {
            if (cct.toArray()[x].getDest().equals(port)) {
                toUnload.add(cct.toArray()[x]);
                cct.pop();
            }
        }
        return toUnload;
    }

    /**
     * unloads all the cargo from the ship and sorts it out given the cargo's destination.
     *
     * @return the list of the cargo.
     */
    public ArrayList<Cargo> unloadAll() {
        ArrayList<Cargo> toUnload = new ArrayList<Cargo>();
        for (Cargo unit : cct.toArray()) {
            toUnload.add(unit);
        }
        cct.clear();
        return toUnload;
    }

    /**
     * looks at how far the ship has gone with the speed and distance of said ship.
     */
    public void travel() {
        int traveled = 0;

        if (getDistance() == 0) {
            System.out.println("Ship is already in port or has no destination");
        } else if (getDistance() > getSpeed()) {
            traveled = getDistance() - getSpeed();
            setDistance(traveled);
            System.out.println("Ship traveled " + getSpeed() + " units and is now " + getDistance() + " units from destination");
        } else if (getDistance() <= getSpeed()) {
            setDistance(traveled);
            System.out.println("The Ship: " + getName() + " has arrived at its destination");
        }
    }

    /**
     * toString will format so it will output in a readable form.
     *
     * @return - the output format.
     */
    public String toString() {
        String output = "";
        output = "This is the good ship " + getName() + ":\n";
        output = output + "\tShip Number: " + getID() + "\n";
        output = output + "\tCurrent Distance to Next Destination: " + getDistance() + "\n";
        output = output + "\tTravelling at a Speed of : " + getSpeed() + "\n";
        output = output + "\tMax Tonnage Able to Carry: " + getMax() + "\n*****CARGO ONBOARD*********\n";
        for (Cargo unit : cct.toArray()) {
            output = output + unit;
        }
        output = output + "*****END CARGO LIST*********\n\tTotal Tonnage of Cargo: " + getCurrentCargoTonnage() + "\n";
        return output;
    }


}