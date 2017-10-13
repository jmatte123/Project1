import java.util.*;
public class Port
{
    private String name = ""; //Order by name
    private ArrayList<Cargo> local = new ArrayList<Cargo>(); //Local Cargo
    private ArrayList<Cargo> outbound = new ArrayList<Cargo>(); //Outbound Cargo
    private Random gen = new Random();
    private int myID; //equals()
    private static int nextID = 0;
     
    public boolean equals(Port other)
     {
          if (getID() == other.getID())
               return true;
          return false;
     }
     
     public int compareTo(Port other)
     {
          return getName().compareTo(other.getName()); 
     }
     
    public Port(String inName)
    {
         setName(inName);
         setID();
    }
    
    public Port()
    {
         setID();
    }
    
    private void setID()
    {
         myID = nextID++;
    }
    
    public int getID()
    {
         return myID;
    }
    
    public String toString()
    {
         String output = "";
         output = "This is " +getName()+ ":\n";
         output = output + "\tPort Number: " + getID() + "\n*****LOCAL CARGO*********\n";
         for (Cargo unit : local)
         {
              output = output + unit;
         }
         output = output + "*****END LOCAL CARGO*********\n\tTotal Tonnage of Local Cargo: " + getLocalTonnage() + "\n*****OUTBOUND CARGO*********\n";
         for (Cargo unit : outbound)
         {
              output = output + unit;
         }
         output = output + "*****END OUTBOUND CARGO*********\n\tTotal Tonnage of Outbound Cargo: " + getOutboundTonnage() + "\n";
         return output;
    }
    
    public ArrayList<Cargo> getLocal()
    {
         return local; 
    }

    public ArrayList<Cargo> getOutbound()
    {
         return outbound; 
    }

    public String getName()
    {
         return name;
    }

    /*
      This is stars inside
      it does multiple lines
     */

    public void addOutbound(Cargo inCargo)
    {
         if (inCargo.getTonnage() >= 0)
         {
              outbound.add(inCargo);
         }
    }
    
    public double getLocalTonnage()
    {
         double total = 0.0;
         for (Cargo unit : local)
         {
              total += unit.getTonnage();
         }
         return total;
    }
    
    public double getOutboundTonnage()
    {
         double total = 0.0;
         for (Cargo unit : outbound)
         {
              total += unit.getTonnage();
         }
         return total;
    }

    public void setName(String inName)
    {
         name = inName;
    }
    
    public void load(Ship targetShip)
    {
         for (int x = outbound.size() - 1; x >=0 ; x--)
         {
              if (targetShip.load(outbound.get(x)))
              {
                   outbound.remove(x);
              }
         }
    }

    public void unload(Ship targetShip)
    {
         ArrayList<Cargo> unloaded = targetShip.unload(getName());
         for (Cargo unit : unloaded)
         {
              if (getName().equals(unit.getDest()))
              {
                   local.add(unit);
              }
              else
              {
                   outbound.add(unit);
              }
         }
         
    }

    
    public void unloadAll(Ship targetShip)
    {
         ArrayList<Cargo> unloaded = targetShip.unloadAll();
         for (Cargo unit : unloaded)
         {
              if (getName().equals(unit.getDest()))
              {
                   local.add(unit);
              }
              else
              {
                   outbound.add(unit);
              }
         }
         
    }
}