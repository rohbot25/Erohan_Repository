/**
fleet class
*/
public class Fleet
{
   //instance variables
   private Ship battleship;
   private Ship aircraftcarrier;
   private Ship cruiser;
   private Ship sub;
   private Ship destroyer;
   
   /**Fleet constructor
   Creates new ship objects
   */
   public Fleet()
   {
      battleship = new BattleShip();
      aircraftcarrier = new AircraftCarrier();
      cruiser = new Cruiser();
      sub = new Sub();
      destroyer = new Destroyer();
   }
   /**adds a hit to whatever shiptype entered
   @param s as the ship hit
   @return true if ship sunk, false otherwise
   */
   public boolean updateFleet(ShipType s)
   {
      if(s == ShipType.ST_BATTLESHIP)
         return(battleship.hit());
      else if(s == ShipType.ST_AIRCRAFT_CARRIER)
         return(aircraftcarrier.hit());
      else if(s == ShipType.ST_CRUISER)
         return(cruiser.hit());
      else if(s == ShipType.ST_SUB)
         return(sub.hit());
      else
         return(destroyer.hit());
   }
   /**tests if all ships have been sunk
   @return true if sunk, false otherwise
   */
   public boolean gameOver()
   {
      return(battleship.getSunk()&&aircraftcarrier.getSunk()&&cruiser.getSunk()&&sub.getSunk()&&destroyer.getSunk());
   }
   /**getter for battleship
   @return battlehsip
   */
   public Ship getBattleship()
   {
      return battleship;
   }
   /**getter for aircraftcarrier
   @return aircraftcarrier
   */
   public Ship getAircraftCarrier()
   {
      return aircraftcarrier;
   }
   /**getter for cruiser
   @return cruiser
   */
   public Ship getCruiser()
   {
      return cruiser;
   }
   /**getter for sub
   @return sub
   */
   public Ship getSub()
   {
      return sub;
   }
   /**getter for destroyer
   @return destroyer
   */
   public Ship getDestroyer()
   {
      return destroyer;
   }
}