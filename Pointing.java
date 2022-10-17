import java.util.*;
/*
 * pointing class
 * 
 */
public class Pointing 
{
    public ArrayList <String> hand;
    public Pointing(ArrayList <String> h)
    {
        hand = new ArrayList <String>(h);
        Collections.reverse(hand);
    }
    public int checkForEnd()
    {
        int total = 0;
        int card;
        for(int i = 0; i < hand.size(); i++)
        {
            card = convert(hand.get(i));
            total += card;
        } 
        return total;
    }
    public int points()
    {
        int points=0;
        if (checkForEnd() == 15){
            points += 2;
            System.out.println("Fifteen! +2");
        }
        points+=pairs();
        if (checkForEnd() == 31){
            points +=2;
            System.out.println("Thirty One! +2");
        }
        points+=runs();
        return points;
    }
        static int convert(String card)
    {
        int card_value = 0;
         if(card.substring(0,2).equals("10"))
                card_value = 10;
            card = card.substring(0,1);
          if(card_value!=10)
            if(card.equals("J") || card.equals("Q") || card.equals("K"))
                card_value = 10;
            else
                card_value = Integer.valueOf(card);
        return card_value;
    }
    static int runConvert(String card)
    {
        int run_value;
        int card_value = convert(card);
        String royal = card.substring(0,1);
            if(royal.equals("J"))
                run_value = 11;
            else if(royal.equals("Q"))
                run_value = 12;
            else if(royal.equals("K"))
                run_value = 13;
            else
                run_value = card_value;
                
        return run_value;
            
    }
    public int pairs()
    {
       int points = 0;
       int card1 = 0;
       int card2 = 0;
       int card3 = 0;
       int card4 = 0;
       if(hand.size() >= 2){
       card1 = runConvert(hand.get(0));
       card2 = runConvert(hand.get(1));
       if (card1 == card2)
            points+=2;   
    }
       if(hand.size() >=3)
       {
           card3 = runConvert(hand.get(2));
           if(card3 == card1 && card3 == card2)
                points=6;
        }
       if(hand.size() >=4)
       {
           card4 = runConvert(hand.get(3));
           if(points == 6 && card4 == card3)
                points=12;
        }
        if(points==2)
            System.out.println("Pair! +2");
        else if(points==6)
            System.out.println("Trips! +6");
        else if(points==12)
            System.out.println("Quads! +12");
       return points; 
    }
    public int runs()
    {
       int one,two,three,extra,points=0;
       int count = 0;
       int check = 1;
       ArrayList<Integer> run = new ArrayList<Integer>();
       if (hand.size() >= 3){
           one = runConvert(hand.get(0));
           two = runConvert(hand.get(1));
           three = runConvert(hand.get(2));
           run.add(one);run.add(two);run.add(three);
           Collections.sort(run);
           if(run.get(0) == run.get(1)-1 && run.get(1) == run.get(2)-1)
                points+=3;
           int i = 3;
           boolean runs = true;
       while(i < hand.size() && runs)
       {
           if(points == 3+count){
               extra = runConvert(hand.get(i));
               run.add(extra);
               Collections.sort(run);
               count ++;
               for(int a = 0; a < run.size()-1;a++)
               {
                   if(run.get(a) != run.get(a+1)-1)
                   {
                       runs = false;
                    }
                    
                }
              if (runs)
              {
                  points++;
                }
        }
        i++;
        }
    }
    if (points == 3)
        System.out.println("Run of 3! +3");
    else if(points == 4)
        System.out.println("Run of 4! +4");
    else if(points == 5)
        System.out.println("Run of 5! +5");
    else if(points == 6)
        System.out.println("Run of 6! +6");
    else if(points == 7)
        System.out.println("Run of 7! +7");
    return points;
    }
}