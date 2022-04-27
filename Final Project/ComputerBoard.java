/**computer board class
extends board
*/
import java.util.*;
import java.io.*;
public class ComputerBoard extends Board
{
   /**computer board constructor
   calls board constructor
   @param filename to load board
   */
   public ComputerBoard(String fileName) throws IOException
   {
      super(fileName);
   }
   /**method for player move, takes in move
   and applies against own board
   @param m as the move
   @return String if any ships sank, null otherwise
   */
   public String makePlayerMove(Move m)
   {
      String str = null;
      CellStatus s = super.applyMoveToLayout(m);
      if(s==CellStatus.AIRCRAFT_CARRIER)
         if(super.getFleet().getAircraftCarrier().getSunk())
         {
            str = "You Sank My Aircraft Carrier!";
         }
      if(s==CellStatus.BATTLESHIP)
         if(super.getFleet().getBattleship().getSunk())
         {
            str = "You Sank My Battleship!";
         }
      if(s==CellStatus.CRUISER)
         if(super.getFleet().getCruiser().getSunk())
         {
            str = "You Sank My Cruiser!";
         }
      if(s==CellStatus.DESTROYER)
         if(super.getFleet().getDestroyer().getSunk())
         {
            str = "You Sank My Destroyer!";
         }
      if(s==CellStatus.SUB)
         if(super.getFleet().getSub().getSunk())
         {
            str = "You Sank My Submarine!";
         }
      updateLayout();
      return str;
   }
   /**toString method
   @return String board
   */
   @Override
   public String toString()
   {
      String s = "  1 2 3 4 5 6 7 8 9 10\n";
      for(int r = 0; r<Board.SIZE; r++)
      {
         if(r==0)
            s+="A ";
         else if(r==1)
            s+="B ";
         else if(r==2)
            s+="C ";
         else if(r==3)
            s+="D ";
         else if(r==4)
            s+="E ";
         else if(r==5)
            s+="F ";
         else if(r==6)
            s+="G ";
         else if(r==7)
            s+="H ";
         else if(r==8)
            s+="I ";
         else
            s+="J ";
            
         for(int c = 0; c<Board.SIZE; c++)
         {
            s+=super.getLayout().get(r).get(c).toString().charAt(0)+" ";
         }
         s+="\n";
      }
      return s;
   }
  /**update layout
  checks the entire board if any ship is sunk
  and updates it accordingly
  */
  private void updateLayout()
   {
      Fleet fleet = super.getFleet();
      ArrayList<ArrayList<CellStatus>> layout = super.getLayout();
      for(int r = 0; r<SIZE; r++)
         for(int c = 0; c<SIZE;c++)
         {
            if(fleet.getAircraftCarrier().getSunk())
            {
               if(layout.get(r).get(c)==CellStatus.AIRCRAFT_CARRIER_HIT)
                  layout.get(r).set(c,CellStatus.AIRCRAFT_CARRIER_SUNK);
            }
            if(fleet.getBattleship().getSunk())
            {
               if(layout.get(r).get(c)==CellStatus.BATTLESHIP_HIT)
                  layout.get(r).set(c,CellStatus.BATTLESHIP_SUNK);
            }
            if(fleet.getCruiser().getSunk())
            {
               if(layout.get(r).get(c)==CellStatus.CRUISER_HIT)
                  layout.get(r).set(c,CellStatus.CRUISER_SUNK);
            }
            if(fleet.getDestroyer().getSunk())
            {
               if(layout.get(r).get(c)==CellStatus.DESTROYER_HIT)
                  layout.get(r).set(c,CellStatus.DESTROYER_SUNK);
            }
            if(fleet.getSub().getSunk())
            {
               if(layout.get(r).get(c)==CellStatus.SUB_HIT)
                  layout.get(r).set(c,CellStatus.SUB_SUNK);
            }
         }
   }
}