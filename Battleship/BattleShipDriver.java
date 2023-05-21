/**
driver to the battleship game
*/
import java.util.*;
import java.io.*;
public class BattleShipDriver
{
   public static void main(String[]args)throws IOException
   {  //variables
      int turn;
      String strMove, s;
      Move move;
      String[] moves;
      Game game = new Game();
      Random rand = new Random();
      Scanner input = new Scanner(System.in);
      //choosing who goes first
      System.out.println("Welcome to Battleship!\n\n");
      if(rand.nextInt(2)==0)
      {
         turn = 1;
         System.out.println("You lost the coin toss. Computer starts.\n");
      }
      else
      {
         turn = 0;
         System.out.println("You won the coin toss! Your turn to start.\n");
      }
      //run game till someone wins
      while(!(game.computerDefeated())&&!(game.playerDefeated()))
      {
         if(turn%2 == 0)
         {
            do {
               System.out.print("Move? ");
               strMove = input.nextLine().toUpperCase();
               move = new Move(strMove);
               }
               while(!game.getComputer().moveAvailable(move));
            s = game.getComputer().makePlayerMove(move);
            if(s != null)
               System.out.println("Computer says: " + s + "\n");
         }
         else
         {
           System.out.print("Computer's turn. Press enter to continue:");
           strMove = input.nextLine();
           System.out.println();
           moves = game.getPlayer().makeComputerMove();
           System.out.println("computer played "+moves[0]+"\n");
           if(moves[1] != null)
            System.out.println(moves[1]);
         }
         System.out.println(game);
         turn++;
      }
      //output
      System.out.println("game over");
      if(game.computerDefeated())
         System.out.println("you won!");
      else
         System.out.println("you lost.");
         
   }
}