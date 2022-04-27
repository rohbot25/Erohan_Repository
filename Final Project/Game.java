/**game class
*/
import java.io.*;
public class Game
{
   //instance variables
   private ComputerBoard computer;
   private UserBoard player;
   
   /**game constructor
   creates the boards
   */
   public Game()   
   {
      try{
      computer = new ComputerBoard("compFleet.txt");
      player = new UserBoard("userFleet.txt");
      }
      catch(IOException e){}
      
   }
   /**calls the computer move
   @return array of move, and if any ship was sunk
   */
   public String[] makeComputerMove()
   {
      String[] array = player.makeComputerMove();
      return array;
   }
   /**calls the player move
   @return String if any ships sunk, null otherwise
   */
   public String makePlayerMove(String s)
   {
      String str = computer.makePlayerMove(new Move(s));
      return str;
   }
   public String makePlayerMove(Move m)
   {
      String str = computer.makePlayerMove(m);
      return str;
   }
   /**check if computer ships all sunk
   @return true if so, false otherwise
   */
   public boolean computerDefeated()
   {
      return(computer.gameOver());
   }
   /**check is user ships are sunk
   @true if so, false otherwise
   */
   public boolean playerDefeated()
   {
      return(player.gameOver());
   }
   /**toString method
   @return string of both boards
   */
   @Override
   public String toString()
   {
      String s =String.format("User Board\n%s\nComputer Board\n%s",player,computer);
      return s;
   }
   /**getter for computerboard
   @return computerboard
   */
   public ComputerBoard getComputer()
   {
      return computer;
   }
   /**getter for userboard
   @return player
   */
   public UserBoard getPlayer()
   {
      return player;
   }
   
   public CellStatus getComputerStatus(int row, int col)
   {
      return computer.getStatus(row,col);
   }
   
   public CellStatus getUserStatus(int row, int col)
   {
      return player.getStatus(row,col);
   }
}