/*
 * This will create the deck of cards, and deal out two hands, and choose a cut
 * by evan rohan
 */
import java.util.*;
public class cribbageFrontEnd
{
    public static void main(String[] args)
    {
       //❤️ ♣️ ♠️ ♦️
       int game = 0;
       int points = 0;
       int Opoints = 0;
       int side;
       int total_points;
       int round_points=0;
       int Oround_points = 0;
       boolean end_game = true;
       Random rand = new Random();
       Scanner input = new Scanner(System.in);
       ArrayList<String> deckStart = shuffle();
       System.out.println("Hello and welcome to cribbage made by Evan Rohan.");
       int start = rand.nextInt(52);
       String test = deckStart.get(start);
       System.out.println();
       System.out.println("Your draw was " + test);
       int value1 =0;
       int value2 =0;
       int value4 =-1;
       int value5 =-1;
       int tie;
       int tie1;
       String flush;
       String flush1;
       if(test.substring(0,2).equals("10"))
                {
                    value1 = 10;
                    flush = test.substring(2,3);
                }
       else
           flush = test.substring(1,2);
            String test1 = test.substring(0,1);
            if(value1!=10)
                if(test1.equals("J"))
                    {
                    value1 = 10;
                    value4 = 11;
                    }
                else if(test1.equals("Q"))
                {
                    value1 = 10;
                    value4 = 12;
                }
                else if(test1.equals("K"))
                {
                    value1 = 10;
                    value4 = 13;
                }
                else
                    value1 = Integer.valueOf(test1);
       int Ostart = rand.nextInt(52);
       String Test = deckStart.get(Ostart);
       System.out.println();
       System.out.println("The oppenent's draw was " + Test);
       System.out.println();
       if(Test.substring(0,2).equals("10"))
                    {
                        value2 = 10;
                        flush1 = Test.substring(2,3);
                    }
       else
        flush1 = Test.substring(1,2);
                String Test3 = Test.substring(0,1);
                if(value2!=10)
                    if(Test3.equals("J"))
                    {
                    value2 = 10;
                    value5 = 11;
                    }
                else if(Test3.equals("Q"))
                {
                    value2 = 10;
                    value5 = 12;
                }
                else if(Test3.equals("K"))
                {
                    value2 = 10;
                    value5 = 13;
                }
                    else
                        value2 = Integer.valueOf(Test3);
       if(value4 == -1)
        value1 = value1;
       else
        value1 = value4;
       if(value5 == -1)
        value2 = value2;
       else
        value2 = value5;
       if(flush.equals("❤️"))
        tie = 3;
       else if(flush.equals("♣️️"))
         tie = 1;
       else if(flush.equals("♠"))
        tie = 4;
       else
        tie = 2;
       if(flush1.equals("❤️"))
        tie1 = 3;
       else if(flush1.equals("♣️️"))
         tie1 = 1;
       else if(flush1.equals("♠"))
        tie1 = 4;
       else
        tie1 = 2;
       if(value1 < value2)
            {
                side = 0;
                System.out.println("You will start with the crib.");
            }
       else if(value2 < value1)
       {
         side = 1;
         System.out.println("The opponent will start with the crib.");
       }
       else
       {
           if(tie < tie1)
           {
               side = 0;
               System.out.println("You will start with the crib.");
           }
           else
           {
               side = 1;
               System.out.println("The opponent will start with the crib.");
           }
       }
       System.out.println();
       while(end_game){
       if(side%2 == 0)
        System.out.println("It is your crib.");
       else
        System.out.println("It is your opponent's crib.");
       System.out.println();
       ArrayList<String> deck = shuffle();
       ArrayList<String> hand = new ArrayList<String>(4);
       int count = 52;
       for(int d = 1; d <=6; d++)
       {
           int num = rand.nextInt(count);
           hand.add(deck.get(num));
           deck.remove(num);
           count--;
       }
       Collections.sort(hand);
       System.out.println(hand);
       ArrayList<String> Ohand = new ArrayList<String>(4);
       for(int i = 1; i <=6; i++)
       {
           int num = rand.nextInt(count);
           Ohand.add(deck.get(num));
           deck.remove(num);
           count--;
       }
       int randcut = rand.nextInt(count);
       String cut = deck.get(randcut);
       //your hand
       System.out.println("please pick the position of the first card you want to be in the crib: ");
       String next = input.nextLine();
       ArrayList<String> arr = new ArrayList<String>(Arrays.asList(next.split(" ")));
       Collections.sort(arr);
       int crib = Integer.valueOf(arr.get(0));
       int crib1 = Integer.valueOf(arr.get(1));
       String card = hand.get(crib-1);
       String card1 = hand.get(crib1-1);
       hand.remove(crib-1);
       hand.remove(crib1-2);
       ArrayList<String> Crib = new ArrayList<String>(4);
       Crib.add(card);
       Crib.add(card1);
       System.out.println("Your Hand: " + hand);
       System.out.println();
       System.out.println("The cut is "+cut);
       System.out.println();
       if(cut.substring(0,1).equals("J"))
        if(side%2 == 0)
        {
            round_points+=2;
            System.out.println("Cut is a jack! +2");
        }
        else
        {
            Oround_points+=2;
            System.out.println("Cut is a jack! +2");
        }
       // opponents hand
       Collections.sort(Ohand);
        int max_points = 0;
        int crib_choice = 0;
        int crib_choice1 = 1;
        String choice_one;
        String choice_two;
       for(int u = 0; u < Ohand.size()-2;u++)
       {
           choice_one = Ohand.get(u);
           Ohand.remove(u);
           for(int y = 0;y+u <Ohand.size()-1;y++)
           {
               int temp_points = 0;
               choice_two = Ohand.get(y+u);
               Ohand.remove(y+u);
               Ohand.add(cut);
               Cribbage Ohandchoice = new Cribbage(Ohand,0);
               temp_points += Ohandchoice.points2();
               temp_points += Ohandchoice.points3();
               temp_points += Ohandchoice.points4();
               temp_points += Ohandchoice.points5();
               if(temp_points >= max_points)
               {
                   crib_choice = u;
                   crib_choice1 = y;
               }
               Ohand.add(choice_two);
               Ohand.remove(cut);
               Collections.sort(Ohand);
           }
               Ohand.add(choice_one);
               Collections.sort(Ohand);
        }
        choice_one = Ohand.get(crib_choice);
        choice_two = Ohand.get(crib_choice1);
        Crib.add(choice_one);
        Crib.add(choice_two);
        Ohand.remove(choice_one);
        Ohand.remove(choice_two);
       //pointing
        String Ocard;
        Boolean pointing = true;
        int turn = side;
        ArrayList <String> pointing_cards = new ArrayList<String>();
        Pointing points_total = new Pointing(pointing_cards);
        ArrayList <String>phand = new ArrayList<String>(hand);
        ArrayList <String>pOhand = new ArrayList<String>(Ohand);
        int Oppo;
        int User;     
        while(pointing)
        {
            Oppo = test(pOhand,pointing_cards);
            User = test(phand,pointing_cards);
            System.out.println("Oppo "+Oppo);
            System.out.println("User "+User);
            if(turn%2==0 && phand.size()!=0 && User <= 31)
           {
            System.out.println("MY hand " +phand);
            System.out.println("please select the card you want to play 1-"+phand.size());
            int point1 = input.nextInt();
            pointing_cards.add(phand.get(point1-1));
            System.out.println("The card you chose is "+phand.get(point1-1));
            points_total = new Pointing(pointing_cards);
            while (points_total.checkForEnd()>31)
            {
              pointing_cards.remove(phand.get(point1-1));
              System.out.println("card would cause total to be over 31, pick again: 1-"+phand.size());
              point1 = input.nextInt();
              pointing_cards.add(phand.get(point1-1));
              points_total = new Pointing(pointing_cards);
            }
            System.out.println("In play "+pointing_cards);
            Pointing pointing_total = new Pointing(pointing_cards);
            phand.remove(phand.get(point1-1));
            round_points += points_total.points();
            System.out.println("total " + pointing_total.checkForEnd());
            System.out.println("my points "+round_points);
           }
           else if(turn%2 == 1 && pOhand.size()!=0 && Oppo <= 31)
           {
               Ocard = Opointing(pOhand,pointing_cards);
               pointing_cards.add(Ocard);
               pOhand.remove(Ocard);
               System.out.println("opponents hand "+pOhand);
               Pointing pointing_total = new Pointing(pointing_cards);
               Oround_points+= points_total.points();
               System.out.println("The Opponent played: "+Ocard);
               System.out.println("total " + pointing_total.checkForEnd());
               System.out.println("In play "+pointing_cards);
               System.out.println("O points "+Oround_points);
           }
           else
           {
               if(turn%2==0)
               {
                   round_points++;
                   pointing_cards.clear();
                }
                else
                {
                    Oround_points++;
                    pointing_cards.clear();
                    
                }
            }
            
           turn++;
           if (phand.size() == 0)
           {
               if(pOhand.size()==0)
               {
                   pointing = false;
                }
            }
            if (pOhand.size() == 0)
           {
               if(phand.size()==0)
               {
                   pointing = false;
                }
            }  
        }
       //handcount
       hand.add(cut);
       Ohand.add(cut);
       Crib.add(cut);
       Cribbage hand_count = new Cribbage(hand,0);
       Cribbage Ohand_count = new Cribbage(Ohand,0);
       Cribbage crib_count = new Cribbage(Crib,1);
       
       round_points += hand_count.points();
       
       Oround_points += Ohand_count.points();
       
       System.out.println("The Crib: " + Crib);
       if(side%2 == 0)
        {
            round_points += crib_count.points();
        }
       else
       {
           Oround_points += crib_count.points();
       }
       if(side%2==0)
       {
           Opoints+= Oround_points;
           if(Opoints >=121)
           {
             System.out.println("You have lost.");
           }
       }
       else
       {
           points+= round_points;
           if(points >= 121)
           {
              System.out.println("You have won."); 
           }
           else
            Opoints+=Oround_points;
       }
       if(points <121 && Opoints <121)
       {
       System.out.println();
       System.out.println("You had " + round_points + " points this round");
       System.out.println();
       System.out.println(Ohand);
       System.out.println("The opponent had " + Oround_points + " points this round");
       System.out.println();
       System.out.println("Total Points: " +points);
       System.out.println();
       System.out.println("Opponents Points: " +Opoints);
       System.out.println();
    }
       side++;
       Oround_points = 0;
       round_points = 0;
    }
        
}




public static ArrayList<String> shuffle()
    {
        ArrayList<String> deck = new ArrayList<String>(52);
        
        for(int i = 0; i<10 ; i++)
        {
            deck.add(i+1 + "❤️");
            
        } 
        for(int a = 0; a<10 ; a++)
        {
            deck.add(a+1 + "♣️");
            
        } 
        for(int b = 0; b<10 ; b++)
        {
            deck.add(b+1 + "♠️");
            
        } 
        for(int c = 0; c<10 ; c++)
        {
            deck.add(c+1 + "♦️");
            
        } 
        deck.add("K❤️");
        deck.add("K♣");
        deck.add("K♠");
        deck.add("K♦️");
        deck.add("J❤️");
        deck.add("J♣");
        deck.add("J♠");
        deck.add("J♦️");
        deck.add("Q❤️");
        deck.add("Q♣");
        deck.add("Q♠");
        deck.add("Q♦️");
        return deck;
    }
public static String Opointing(ArrayList<String>Ohand,ArrayList<String> points)
{
    String final_card = Ohand.get(0);
    String card;
    for(String i:Ohand)
    {
       card = i;
       int max = -1;
       points.add(card);
       Pointing p = new Pointing(points);
       if (p.checkForEnd() <= 31)
       {
           if (p.points() > max)
           {
               final_card = i;
            }
       }
       points.remove(card);
    }
    
    return final_card;
}
public static int test(ArrayList<String> h,ArrayList<String>pointing)
{
    int total = 32;
    int temp;
    ArrayList<String> hand = new ArrayList<String>(h);
    ArrayList<String> points = new ArrayList<String>(pointing);
    for (int i=0;i < hand.size();i++)
    {
        points.add(hand.get(i));
        Pointing p = new Pointing(points);
        temp = p.checkForEnd();
        points.remove(hand.get(i));
        if (temp < total)
        {
            total = temp;
        }
    }
    return total;
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
}