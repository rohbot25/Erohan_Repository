/**
move class
4-12-2022
*/
public class Move
{
   //instance variables
   private int row;
   private int col;
   /**Move constructor
   @param row
   @param column
   */
   public Move(int r, int c)
   {
      row = r;
      col = c;
   }
   /**Alternate constructor
   takes in string of a letter and integer
   @param String s as that value
   */
   public Move(String s)
   {
      int r;
      if(s.charAt(0)=='A')
         r=1;
      else if(s.charAt(0)=='B')
         r=2;
      else if(s.charAt(0)=='C')
         r=3;
      else if(s.charAt(0)=='D')
         r=4;
      else if(s.charAt(0)=='E')
         r=5;
      else if(s.charAt(0)=='F')
         r=6;
      else if(s.charAt(0)=='G')
         r=7;
      else if(s.charAt(0)=='H')
         r=8;
      else if(s.charAt(0)=='I')
         r=9;
      else
         r=10;
      row = r;
      col = Integer.parseInt(s.substring(1));
   }
   /**getter for row
   @return int row
   */
   public int row()
   {
      return row;
   }
   /**getter for col
   @return int col
   */
   public int col()
   {
      return col;
   }
   /**toString method
   @return String of letter and number of spot back
   */
   @Override
   public String toString()
   {
      if(row==1)
         return "A"+col;
      else if(row==2)
         return "B"+col;
      else if(row==3)
         return "C"+col;
      else if(row==4)
         return "D"+col;
      else if(row==5)
         return "E"+col;
      else if(row==6)
         return "F"+col;
      else if(row==7)
         return "G"+col;
      else if(row==8)
         return "H"+col;
      else if(row==9)
         return "I"+col;
      else
         return "J"+col;
   }
}