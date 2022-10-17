import java.util.*;
/*
 * cribbge class for recording points 2,3,4,5
 * by evan rohan
 * woof
 */

public class Cribbage
{
    public ArrayList <String> hand;
    public int crib_flush;
    public Cribbage(ArrayList <String> h,int cf)
    {
        hand = new ArrayList <String>(h);
        crib_flush = cf;
    }
    public int points()
    {
        int points = 0;
        points += points2();
        points += points3();
        points += points4();
        points += points5();
        return points;
    }
    public int points2()
    {
        int points=0;
        String cut = hand.get(4);
        for(int i = 0; i<hand.size()-1;i++)
        {
            String card_one = hand.get(i);
            //System.out.println("card1 "+card_one);
            points+=jackOfTheSuit(hand.get(i),cut);
            for(int a = i+1;a < hand.size();a++)
            {
                String card_two = hand.get(a);
                //System.out.println("card2 "+card_two);
                points+=pair(card_one,card_two);
                points+=fifteentwo(card_one,card_two);
            }
        }
        return points;
    }
    public int points3()
    {
        int points = 0;
        points+=runs(hand);
        for(int a = 0; a<hand.size()-2;a++)
        {
            String card_one = hand.get(a);
            for(int b = a+1;b<hand.size()-1;b++)
            {
                String card_two = hand.get(b);
                for(int c = b+1; c<hand.size();c++)
                {
                    String card_three = hand.get(c);
                    points+=fifteenthree(card_one,card_two,card_three);
                }
            }
        }
        return points;
    }
    public int points4()
    {
        int points = 0;
        points+=flushCheck(hand);
        for(int a = 0; a<hand.size() -3; a++)
        {
            String card_one = hand.get(a);
            for(int b = a+1; b<hand.size() -2; b++)
            {
                String card_two = hand.get(b);
                for(int c =b+1; c<hand.size()-1;c++)
                {
                     String card_three = hand.get(c);
                     for(int d = c+1; d<hand.size();d++)
                     {
                         String card_four=hand.get(d);
                         points+=fifteenfour(card_one,card_two,card_three,card_four);
                     }         
                }
            }
        }
        return points;
    }
    public int points5()
    {
        int points = 0;
        points+=fifteenfive(hand);
        return points;
    }
    
    
    //convert cards to integer values
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
    //check for jack of the suit
    static int jackOfTheSuit(String card,String cut)
    {
        int points = 0;
         if(card.substring(0,1).equals("J"))
            {
                if(card.substring(1,2).equals(cut.substring(1,2)))
                    {
                            points++;
                        //System.out.println("Jack of the Suit");
                    }
            }
           return points;
    }
    //checks for pairs
    static int pair(String card_one,String card_two)
    {
        int one = convert(card_one);
        int two = convert(card_two);
        int points = 0;
        if(one != 10 || two != 10)
        {
            if(one==two)
            {
                points+=2;
                //System.out.println("Pair for 2");
            }
        }
        else
        {
            if(card_one.substring(0,1).equals(card_two.substring(0,1)))
                {
                    points+=2;
                    //System.out.println("Pair for 2");
                }
        }
        return points;
    }
    //fifteens
    static int fifteentwo(String card_one,String card_two)
    {
        int points = 0;
        int one = convert(card_one);
        //System.out.println("card1 "+one);
        int two = convert(card_two);
        //System.out.println("card2 "+two);
        if(one+two == 15)
        {
            points+=2;
            //System.out.println("fifteen for 2");
        }
        return points;
    }
    static int fifteenthree(String card_one, String card_two, String card_three)
    {
        int points = 0;
        int one = convert(card_one);
        int two = convert(card_two);
        int three = convert(card_three);
        if(one+two+three == 15)
          {  
        points+=2;
        //System.out.println("fifteen for 2");
    }
        return points;
    }
    static int fifteenfour(String card_one, String card_two, String card_three,String card_four)
    {
        int points = 0;
        int one = convert(card_one);
        int two = convert(card_two);
        int three = convert(card_three);
        int four = convert(card_four);
        if(one+two+three+four==15)
        {
            points+=2;
            //System.out.println("fifteen for 2");
        }
        return points;
    }
    static int fifteenfive(ArrayList<String>hand)
    {
        int points = 0;
        int one = convert(hand.get(0));
        int two = convert(hand.get(1));
        int three = convert(hand.get(2));
        int four = convert(hand.get(3));
        int five = convert(hand.get(4));
        if(one+two+three+four+five==15)
        {
            points+=2;
            //System.out.println("fifteen for 2");
        }
        return points;
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
    //flushconvert
    static String flushConvert(String card)
    {
        int flush_value = runConvert(card);
        String flush;
        if(flush_value != 10 )
            flush = card.substring(1,2);
        else
            flush = card.substring(2,3);
        return flush;
    }
    //points for flush
    public int flushCheck(ArrayList<String> hand)
    {
        int points = 0;
        String flush1 = flushConvert(hand.get(0));
        String flush2 = flushConvert(hand.get(1));
        String flush3 = flushConvert(hand.get(2));
        String flush4 = flushConvert(hand.get(3));
        String flush5 = flushConvert(hand.get(4));
        if(flush1.equals(flush2) && flush2.equals(flush3) && flush3.equals(flush4))
        {
            if(flush4.equals(flush5))
            {
                points+=5;
                //System.out.println("Flush for 5");
            }
            else
            {
                if(crib_flush==0)
                {
                points+=4;
                //System.out.println("Flush for 4");
            }
            }
        }
        return points;
    }
    public int runs(ArrayList<String>hand)
    {
        int points=0;
        int count=0;
        int x = 0;
        int y = 0;
        ArrayList <Integer> numHand = new ArrayList<Integer>(Arrays.asList(runConvert(hand.get(0)),runConvert(hand.get(1)),runConvert(hand.get(2)),runConvert(hand.get(3)),runConvert(hand.get(4))));
        ArrayList <String> runHand = new ArrayList<String>();
        runHand.add(hand.get(0));runHand.add(hand.get(1));runHand.add(hand.get(2));runHand.add(hand.get(3));runHand.add(hand.get(4));
        ArrayList<Integer> extra = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4));
        ArrayList<String> check =new ArrayList<String>(Arrays.asList("a","b","c","d"));
        ArrayList<String> check1 = new ArrayList(4);
        ArrayList<Integer> runs = new ArrayList();
        Collections.sort(numHand);
        Collections.sort(runHand);
        for(int a = 0;a<runHand.size()-2;a++)
        {
            int one = numHand.get(a);
            String h1 = runHand.get(a);
            
            int remove1 = extra.get(a);
            extra.remove(a);
            
            for(int b = a+1;b<runHand.size()-1;b++)
            {
                 int two = numHand.get(b);
                String h2 = runHand.get(b);
                
                int remove2 = extra.get(a+count);
                extra.remove(a+count);
                for(int c = b+1;c<runHand.size();c++)
                {
                    int three = numHand.get(c);
                    String h3 = runHand.get(c);
                    int remove3 = extra.get(a+count);
                    extra.remove(a+count);
                     if(one == two-1 && two == three-1)
                     {
                           //System.out.println("run for 3");
                           points+=3;
                           runs.add(one);
                           runs.add(two);
                           runs.add(three);
                           check1.add(h1);
                           check1.add(h2);
                           check1.add(h3);
                           check1.add(hand.get(extra.get(0)));
                           runs.add(numHand.get(extra.get(0)));
                           runs.add(numHand.get(extra.get(1)));
                           Collections.sort(runs);
                           Collections.sort(check1);
                           if(runs.get(0) == runs.get(1)-1 && runs.get(1) == runs.get(2)-1 && runs.get(2) == runs.get(3)-1 && runs.get(3) == runs.get(4)-1&&y==0)
                           {
                               //System.out.println("run for five");
                               points+=5;
                               y++;
                               x=2;
                           }
                           else
                           {
                               runs.remove(new Integer(numHand.get(extra.get(0))));
                               check1.remove(new String(runHand.get(extra.get(0))));
                               check1.add(runHand.get(extra.get(1)));
                               runs.add(numHand.get(extra.get(1)));
                               Collections.sort(check1);
                               Collections.sort(runs);
                               if(runs.get(0) == runs.get(1)-1 && runs.get(1) == runs.get(2)-1 && runs.get(2) == runs.get(3)-1)
                               {
                                    if(!(check1.equals(check))&&x==0)
                                   {
                                   //System.out.println("run for four");
                                   check.clear();
                                   check.add(check1.get(0));
                                   check.add(check1.get(1));
                                   check.add(check1.get(2));
                                   check.add(check1.get(3));
                                   x++;
                                   points+=4;
                                   } 
                                   if(!(check1.equals(check)) && x==1)
                                   {
                                       //System.out.println("run for four");
                                       x++;
                                       points+=4;
                                    }
                               }
                               else
                                {
                                    runs.remove(new Integer(numHand.get(extra.get(0))));
                                    check1.remove(new String(runHand.get(extra.get(0))));
                                    check1.add(runHand.get(extra.get(1)));
                                    runs.add(numHand.get(extra.get(1)));
                                    Collections.sort(check1);
                                    Collections.sort(runs);
                                   
                                   
                                    if(runs.get(0) == runs.get(1)-1 && runs.get(1) == runs.get(2)-1 && runs.get(2) == runs.get(3)-1)
                                    {
                                        if(!(check1.equals(check)) && x==0)
                                   {
                                       //System.out.println("run for four");
                                       check.clear();
                                       check.add(check1.get(0));
                                       check.add(check1.get(1));
                                       check.add(check1.get(2));
                                       check.add(check1.get(3));
                                       x++;
                                       points+=4;
                                   }
                                       if(!(check1.equals(check)) && x==1)
                                       {
                                           //System.out.println("run for four");
                                           x++;
                                           points+=4;
                                       }
                                    }
                                }
                               
                           }
                          runs.clear();
                          check1.clear(); 
                     }
                     extra.add(remove3);
                }
                extra.add(count,remove2);
                count += 1;  
            }
             extra.add(count,remove1);
            Collections.sort(extra);
            count = 0;
        }
        if(points == 20)
        {
            //System.out.println("Double run of 4");
            points=8;
        }
        else if(points==17)
        {
            //System.out.println("Double run of 4");
            points=8;
        }
        else if(points==14)
        {
            //System.out.println("run of 5");
            points=5;
        }
        else
        {
            points=points;
        }
        return points;
    }
    
}