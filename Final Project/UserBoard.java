/**UserBoard class
extends board
*/
import java.util.*;
import java.io.*;
public class UserBoard extends Board
{  
   //instance variables
   private ArrayList<Move> moves = new ArrayList<Move>();
   private Random rand = new Random();
   
   /**UserBoard constructor
   @param fileName to load board
   */
   public UserBoard(String fileName)throws IOException
   {
      super(fileName);
   }
   /**this method
   picks an available random move and preforms it against 
   the player board
   @return array with the move preformed, and then null or a messgae of sunken ship
   */
   public String[] makeComputerMove()
   {
      Move m;
      boolean same;
      String[] array = new String[2];
      do
      {
      same = false;
      m = pickRandomMove();
      for(Move m1:moves)
      {
         if(m1.toString().equals(m.toString()))
            same = true;
      }
      }
      while(same);
      
      moves.add(m);
      array[0] = m.toString();
      array[1] = null;
      CellStatus s = super.applyMoveToLayout(m);
      if(s==CellStatus.AIRCRAFT_CARRIER)
         if(super.getFleet().getAircraftCarrier().getSunk())
         {
            array[1] = "You Sank My Aircraft Carrier!";
         }
      if(s==CellStatus.BATTLESHIP)
         if(super.getFleet().getBattleship().getSunk())
         {
            array[1] = "You Sank My Battleship!";
         }
      if(s==CellStatus.CRUISER)
         if(super.getFleet().getCruiser().getSunk())
         {
            array[1] = "You Sank My Cruiser!";
         }
      if(s==CellStatus.DESTROYER)
         if(super.getFleet().getDestroyer().getSunk())
         {
            array[1] = "You Sank My Destroyer!";
         }
      if(s==CellStatus.SUB)
         if(super.getFleet().getSub().getSunk())
         {
            array[1] = "You Sank My Submarine!";
         }
      updateLayout();
      return array;
   }
   /**picks a random move
   @return Move selected
   */
   public Move pickRandomMove()
   {
      int r = rand.nextInt(10)+1;
      int c = rand.nextInt(10)+1;
      return new Move(r,c);
   }
   /** toString method for the board
   @Return String representation 
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
            s+=super.getLayout().get(r).get(c).toString().charAt(1)+" ";
         }
         s+="\n";
      }
      return s;
   }
   
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