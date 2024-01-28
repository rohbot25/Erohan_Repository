//
// Created by Evan Rohan on 1/24/23.
//

#include "Deck.h";
using namespace std;

int main(){
    //constants and variables
    const int MAX_POINTS = 21;
    Deck play = Deck();
    vector<Card> house;
    bool hitOrStand ;
    bool goAgain = true;
    const int MIN_BET=25;

    //welcome text
    cout << "\nWelcome to BlackJack made by Evan Rohan\n" << endl;
    cout << "Please Enjoy and Gamble Responsibly ;)\n" << endl;
    cout << endl;

    //main gameplay while loop
    while(goAgain) {
        //shuffle deck and display your own cards, and one card from the house
        play.startRound(cout);
        house = play.getHouse();
        play.getBet(cout, cin);
        cout << "One Card of the House is " << play.printCard(house[0]) << "\n" << endl;
        play.showHand(cout);
        //ask if they would like to hit or stand
        hitOrStand = play.hitOrStand(cout, cin);
        //run for as many times as they would like to hit or until they bust
        while (hitOrStand) {
            //add a Card to the hand and redisplay the hand
            play.addCardToHand();
            play.showHand(cout);
            //if they have less than or equal to 21 points, allow them to hit again
            if(play.handSum()<= MAX_POINTS)
                hitOrStand = play.hitOrStand(cout, cin);
            else {
                cout << "BUST!\n" << endl;
                hitOrStand = false;
            }
        }
        //run the house cards and display the results of the round
        play.runHouse(cout);
        //check if they have enough money to continue, and prompt if they would like to
        if(play.getMoney() >= MIN_BET)
            goAgain = play.goAgain(cout, cin);
        else {
            goAgain = false;
            cout << "You do not have enough money to continue.\n" << endl;
        }

    }
    //ending text
    cout << "Thank you for playing BlackJack!\n"<<endl;
    cout << "You ended with $" << play.getMoney() << endl;
    return 0;
}