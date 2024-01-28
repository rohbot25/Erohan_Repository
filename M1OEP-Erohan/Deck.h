//
// Created by Evan Rohan on 1/26/23.
//

#ifndef M1OEP_EROHAN_DECK_H
#define M1OEP_EROHAN_DECK_H

#include <vector>
#include <iostream>

//suits enum
enum suits {DIAMOND,HEART,SPADE,CLUB};

//card structure
struct Card{
    suits suit;
    std::string type;
    int value;
};
class Deck{
private:
    //variables
    std::vector<Card> deck;
    std::vector<Card> hand;
    std::vector<Card> house;
    double money;
    double bet;
    //constants
    const int MAX_CARDS = 13;
    const int MAX_SUIT = 4;
    const double START_CASH = 250;
    const int END = 17;
    const int MAX_POINTS = 21;
    const double MIN_BET = 25;
    const int CHARACTER = 1;

public:
    /*
     * Default Constructor
     * Requires: nothing
     * Modifies: money
     * Effects: gives user starting money
     */
    Deck();

    //Getters
    std::vector<Card> getHouse();
    double getMoney();

    /*
     * add card to hand
     * Requires: nothing
     * Modifies: deck and hand
     * Effects: calls selectCard() and adds a card to the users hand
     */
    void addCardToHand();

    /*
     * Randomly selects a card
     * Requires: nothing
     * Modifies: deck
     * Effects: returns a random card from the deck
     * removes card after selected
     */
    Card selectCard();

    /*
     * shuffle
     * Requires: nothing
     * Modifies: deck
     * Effects: creates a new full deck
     */
    void shuffle();

    /*
     * picks cards for house and hand
     * requires: ostream
     * modifies: hand, deck, and house
     * Effects: picks two cards from the deck for hand and two for house, print out how much money the player has. also prints out starting details
     */
    void startRound(std::ostream &outs);

    /*
     * Get bet
     * Requires: ostream and istream
     * Modifies: outs and ins, money
     * Effects: print options to outs and get user input. Check for validity. take money out of money
     */
    void getBet(std::ostream &outs, std::istream &ins);

    /*
     * Get Option
     * Requires: ostream and istream
     * Modifies: outs and ins
     * Effects: prints hit or stand options and gets user input. Check for validity.
     */
    bool hitOrStand(std::ostream &outs, std::istream &ins)const;

    /*
     * get player choice
     * requires: ostream and istream
     * Modifies: outs and ins
     * Effects: print options and check if they want to continue the game or not
     */
    bool goAgain(std::ostream &outs, std::istream &ins)const;
    /*
     * run house
     * requires: outs
     * Modifies: house and money
     * Effects: reveals house cards and flips until over 17 or busts claims winner as well, and reports if you have won your money
     */
    void runHouse(std::ostream &outs);

    /*
     * adds up hand and returns an int
     * Requires: nothing
     * Modifies: nothing
     * Effects: return int of cards sum
     */
    int handSum();

    /*
     * sums house hand and returns that value
     * requires: nothing
     * modifies: nothing
     * Effects: return an int of the cards sum
     */
    int houseSum();

    /*
     * show hand
     * requires: outs
     * Modifies: nothing
     * Effects: sends hand data to outs to be displayed to user.
     */
    void showHand(std::ostream &outs);

    /*
     * card to a string
     * requires: card
     * modifies nothing
     * Effects: makes a card struct into a string and returns it
     */
    std::string printCard(Card card);




};
#endif //M1OEP_EROHAN_DECK_H
