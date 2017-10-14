import java.util.*; // Need Random
import java.io.*; //Need File IO Tools
/**
 * @author Joe Matteson
 * @author Edgar Centeno
 */
public class Driver
{


    public static void main(String[] args) throws FileNotFoundException
    {
         //Create a link to an input file
         File portFile = new File("Ports.txt");
         File shipFile = new File("Ships.txt");
         
         //Open input file
         Scanner inputPorts = new Scanner(portFile);
         Scanner inputShips = new Scanner(shipFile);

         //Setup rest of reading tools and Array Lists
         ArrayList<Port> myPorts = new ArrayList<Port>();
         ArrayList<Ship> myShips = new ArrayList<Ship>();
         String line;
         String[] parts;
         
         //Okay, now lets get the loop going
         //We can use Scanner's hasNext() method to let us know when we run out of data
         while(inputPorts.hasNext())
         {
              //Read line
              line = inputPorts.nextLine();
              //Split line into parts
              parts = line.split("%");
              //Store Data
              myPorts.add(new Port(parts[0]));
         }
         //Close input file, we are done with the input file
         inputPorts.close();
         
         while(inputShips.hasNext())
         {
              //Read line
              line = inputShips.nextLine();
              //Split line into parts
              parts = line.split("%");
              //Store Data
              myShips.add(new Ship(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3])));
         }
         //Close input file, we are done with the input file
         inputShips.close();

         //Add some cargo to each port
         Random rng = new Random();
         for (Port eachPort : myPorts)
         {
              //Generate between 1 and 10 cargo objects per Port
              int objects = rng.nextInt(10);
              for (int x = 0; x <= objects; x++)
              {
                   //Generate tonnage
                   double tonnage = rng.nextDouble() * 100; //0 and 100ish
                   
                   //Generate destination
                   //A Port's outbound cargo can't be for that same Port
                   int dest = rng.nextInt(myPorts.size());
                   while(eachPort.getName().equals(myPorts.get(dest).getName()))
                   {
                        dest = rng.nextInt(myPorts.size());
                   }
                   String target = myPorts.get(dest).getName();
                   
                   //Generate Cargo 
                   //(will need to use full parameter constructor once you write that method)
                   Cargo temp = new Cargo(target, tonnage);
                   

                   //Add Cargo to Port's Outbound load.
                   //You'll need a method in Port to do this
                   eachPort.addOutbound(temp);
              }

         }
         
         //So, I've got:
         // A List of Ships (without cargo)
         //   and
         // A List of Ports (with cargo)
         
         //Section 1
         //I decide I want another list of Ships that only has ships that are able to hold more than 100 tonnes of cargo
         ArrayList<Ship> bigShips = new ArrayList<Ship>();
         for (Ship curr : myShips)
         {
              if (curr.getMax() > 100)
              {
                   //Ship temp = new Ship(curr.getName(), curr.getMax(), curr.getSpeed(), curr.getDistance());
                   //I just need a shallow copy as I want to keep the same object, just have it be in 2 different lists
                   bigShips.add(curr);
              }
         }
         
         //I Should now have a List of all Ships {myShips} and a list of all ships with more than 100 ton capacity {bigShips}...
         //Given my input files...I should have 6 ships in myShips and 3 ships in bigShips...And 3 ships in both!
         //Let's make sure that is the case
         System.out.println("Total of ALL ships (Should be 6): " + myShips.size());
         System.out.println("Total of BIG ships (Should be 3): " + bigShips.size());         
         int both = 0;
         for (Ship all : myShips)
         {
              for (Ship big : bigShips)
              {
                   if (all.equals(big))
                   {
                        both++;
                   }
              }
         }
         System.out.println("Ships in both lists (Should be 3): " + both);
         
         //End Section 1
         
         //Second 2
         //I decide I want to create a new port
         //I want my new port to have the same amount and kind of Cargo as myPort.get(0) has.
         //So if myPort.get(0) has a Cargo massing 10.2 tonnes and heading for Winnipeg, then the
         //   new port should have a Cargo massing 10.2 tonnes and heading for Winnipeg
         
         //Creating a new port, using myPort.get(0) as a template
         //I want a deep copy instead
         Port newPort = new Port(myPorts.get(0).getName());
         for (Cargo unit : myPorts.get(0).getOutbound())
         {
              newPort.addOutbound(new Cargo(unit.getDest(), unit.getTonnage()));
         }
         
         //Verifying that both Ports have same general cargo
         //Checking outbound
         System.out.println("Checking Outbound");
         System.out.println("\tTotal Outbound in original: " + myPorts.get(0).getOutbound().size());
         System.out.println("\tTotal Outbound in newPort (should match above): " + newPort.getOutbound().size());
         int match = 0;
         for (int x = 0; x < newPort.getOutbound().size(); x++)
         {
              //Check Attributes
              if (newPort.getOutbound().get(x).getDest().equals(myPorts.get(0).getOutbound().get(x).getDest()) && newPort.getOutbound().get(x).getTonnage() == myPorts.get(0).getOutbound().get(x).getTonnage())
              {
                   match++;
              }
         }
         System.out.println("\tNumber of matched outbound cargo (should match above): " + match);
         int dupes = 0;
         for (Cargo newO : newPort.getOutbound())
         {
              for (Cargo oldO : myPorts.get(0).getOutbound())
              {
                   if (newO.equals(oldO))
                   {
                        dupes++;
                   }
              }
         }
         System.out.println("\tOutbound cargo that is at both Ports (should be zero since that is impossible): " + dupes);
         
        
         //End Section 2
         
         
         //Section 3
         //Well, that didn't work, trying a different strat
         //Creating a new port, using myPort.get(0) as a template
         //I want a deep copy instead (same as Section 2)
         newPort = new Port(myPorts.get(0).getName());
         for (Cargo unit : myPorts.get(0).getOutbound())
         {
              newPort.addOutbound(new Cargo(unit.getDest(), unit.getTonnage()));
         }
         
         //Verifying that both Ports have same general cargo
         //Checking outbound
         System.out.println("Checking Outbound");
         System.out.println("\tTotal Outbound in original: " + myPorts.get(0).getOutbound().size());
         System.out.println("\tTotal Outbound in newPort (should match above): " + newPort.getOutbound().size());
         match = 0;
         for (int x = 0; x < newPort.getOutbound().size(); x++)
         {
              //Check Attributes
              if (newPort.getOutbound().get(x).getDest().equals(myPorts.get(0).getOutbound().get(x).getDest()) && newPort.getOutbound().get(x).getTonnage() == myPorts.get(0).getOutbound().get(x).getTonnage())
              {
                   match++;
              }
         }
         System.out.println("\tNumber of matched outbound cargo (should match above): " + match);
         dupes = 0;
         for (Cargo newO : newPort.getOutbound())
         {
              for (Cargo oldO : myPorts.get(0).getOutbound())
              {
                   if (newO.equals(oldO))
                   {
                        dupes++;
                   }
              }
         }
         System.out.println("\tOutbound cargo that is at both Ports (should be zero since that is impossible): " + dupes);
         
 
         
         //End Section 3
    }


}