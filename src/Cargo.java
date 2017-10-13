import java.util.*;
public class Cargo
{
     private static int trackNumber = 0;
     private int orderNum; //using for equals()
     private String dest = "";
     private double tons = 0.0; //Ordering by tonnage!
     
     public boolean equals(Cargo other)
     {
          if (getOrderNumber() == other.getOrderNumber())
               return true;
          return false;
     }
     
     public int compareTo(Cargo other)
     {
          //return getTonnage() - other.getTonnage(); //Can't use because tons is a double! (rounding errors)
          //return getDest().compareTo(other.getDest()); //Would work if I wanted to order by destination
          if (getTonnage() < other.getTonnage())
               return -1;
          else if (getTonnage() > other.getTonnage())
               return 1;
          else
               return 0;
     }
     
     public Cargo(String inDest, double inTons)
     {
          setDest(inDest);
          setTonnage(inTons);
          setOrderNumber();
     }
     
     private void setOrderNumber()
     {
          orderNum = trackNumber++;
     }
     
     public int getOrderNumber()
     {
          return orderNum;
     }
     
     public String getDest()
     {
          return dest;
     }
     
     public double getTonnage()
     {
          return tons;
     }
     
     private void setDest(String inDest)
     {
          dest = inDest;
     }
     
     private void setTonnage(double inTons)
     {
          if (inTons >= 0)
          {
               tons = inTons;
          }
     }
     
     public String toString()
     {
          String output = "Cargo object #" + getOrderNumber() + " has the following information:\n";
          output = output + "\t" + getTonnage() + " tons\n\tTo be delivered to: " + getDest() + "\n";
          return output;
     }
     
}