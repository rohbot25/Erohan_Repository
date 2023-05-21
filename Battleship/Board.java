/**
board class
*/
import java.util.*;
import java.io.*;
public class Board
{
   //instance variables
   private ArrayList<ArrayList<CellStatus>> layout = new ArrayList<ArrayList<CellStatus>>();
   private Fleet fleet;
   public static final int SIZE = 10;
   
   /**Board constructor
   @param fileName to load the board
   */
   public Board(String fileName)throws IOException
   {
         for(int i=0; i<SIZE; i++)
         {
            layout.add(new ArrayList<CellStatus>());
         }
         for(int r=0; r<SIZE; r++)
         {
            for(int c=0; c<SIZE; c++)
            {
               layout.get(r).add(CellStatus.NOTHING);
            }
         }
      Scanner file = new Scanner(new File(fileName));
      String ship;
      Move start;
      Move end;
      while(file.hasNext())
      {
         ship = file.nextLine();
         if(ship.charAt(0)=='D')
         {
            start = new Move(ship.substring(2,4));
            end = new Move(ship.substring(5));
            if(start.row()==end.row())
            {
               for(int c = start.col()-1; c < end.col(); c++)
               {
                  layout.get(start.row()-1).set(c,CellStatus.DESTROYER);
               }
            }
            else
            {
               for(int r = start.row()-1; r < end.row(); r++)
               {
                  layout.get(r).set(start.col()-1,CellStatus.DESTROYER);  
               }
            }
               
         }
         else if(ship.charAt(0)=='A')
         {
            start = new Move(ship.substring(2,4));
            end = new Move(ship.substring(5));
            if(start.row()==end.row())
            {
               for(int c = start.col()-1; c < end.col(); c++)
               {
                  layout.get(start.row()-1).set(c,CellStatus.AIRCRAFT_CARRIER);
               }
            }
            else
            {
               for(int r = start.row()-1; r < end.row(); r++)
               {
                  layout.get(r).set(start.col()-1,CellStatus.AIRCRAFT_CARRIER);  
               }
            }
         }
         else if(ship.charAt(0)=='S')
         {
            start = new Move(ship.substring(2,4));
            end = new Move(ship.substring(5));
            if(start.row()==end.row())
            {
               for(int c = start.col()-1; c < end.col(); c++)
               {
                  layout.get(start.row()-1).set(c,CellStatus.SUB);
               }
            }
            else
            {
               for(int r = start.row()-1; r < end.row(); r++)
               {
                  layout.get(r).set(start.col()-1,CellStatus.SUB);  
               }
            }
         }
         else if(ship.charAt(0)=='C')
         {
            start = new Move(ship.substring(2,4));
            end = new Move(ship.substring(5));
            if(start.row()==end.row())
            {
               for(int c = start.col()-1; c < end.col(); c++)
               {
                  layout.get(start.row()-1).set(c,CellStatus.CRUISER);
               }
            }
            else
            {
               for(int r = start.row()-1; r < end.row(); r++)
               {
                  layout.get(r).set(start.col()-1,CellStatus.CRUISER);  
               }
            }
         }
         else
         {
            start = new Move(ship.substring(2,4));
            end = new Move(ship.substring(5));
            if(start.row()==end.row())
            {
               for(int c = start.col()-1; c < end.col(); c++)
               {
                  layout.get(start.row()-1).set(c,CellStatus.BATTLESHIP);
               }
            }
            else
            {
               for(int r = start.row()-1; r < end.row(); r++)
               {
                  layout.get(r).set(start.col()-1,CellStatus.BATTLESHIP);  
               }
            }
         }
            
      }
      fleet = new Fleet();
   }
   /**this method takes in a amove, has it hit its spot
   count it in the fleet, and return original value
   @param m the move being made
   @return s the CellStatus before the move
   */
   public CellStatus applyMoveToLayout(Move m)
   {
      CellStatus s = layout.get(m.row()-1).get(m.col()-1);
      if(s==CellStatus.NOTHING)
         layout.get(m.row()-1).set(m.col()-1,CellStatus.NOTHING_HIT);
      else if(s==CellStatus.AIRCRAFT_CARRIER)
      {
         layout.get(m.row()-1).set(m.col()-1,CellStatus.AIRCRAFT_CARRIER_HIT);
         fleet.updateFleet(ShipType.ST_AIRCRAFT_CARRIER);
      }
      else if(s==CellStatus.BATTLESHIP)
      {
         layout.get(m.row()-1).set(m.col()-1,CellStatus.BATTLESHIP_HIT);
         fleet.updateFleet(ShipType.ST_BATTLESHIP);
      }
      else if(s==CellStatus.CRUISER)
      {
         layout.get(m.row()-1).set(m.col()-1,CellStatus.CRUISER_HIT);
         fleet.updateFleet(ShipType.ST_CRUISER);
      }
      else if(s==CellStatus.DESTROYER)
      {
         layout.get(m.row()-1).set(m.col()-1,CellStatus.DESTROYER_HIT);
         fleet.updateFleet(ShipType.ST_DESTROYER);
      }
      else
      {
         layout.get(m.row()-1).set(m.col()-1,CellStatus.SUB_HIT);
         fleet.updateFleet(ShipType.ST_SUB);
      }
      return s;
   }
   /**checks if the move entered is already been done
   @param m as the move checked
   @return true if good, false otherwise
   */
   public boolean moveAvailable(Move m)
   {
      CellStatus s = layout.get(m.row()-1).get(m.col()-1);
      if(s.toString().charAt(1)=='x' || s.toString().charAt(1)=='X')
         return false;
      return true;
   }
   /**calls fleet method gameover
   @return true if all ships are sunk, false otherwise
   */
   public boolean gameOver()
   {
      return(fleet.gameOver());
   }
   /**getter for layout
   @return layout for game
   */
   public ArrayList<ArrayList<CellStatus>> getLayout()
   {
      return layout;
   }
   /**getter for fleet
   @return Fleet of ships
   */
   public Fleet getFleet()
   {
      return fleet;
   }
   
   public CellStatus getStatus(int row, int col)
    {
      return layout.get(row).get(col);
    }
   }
   