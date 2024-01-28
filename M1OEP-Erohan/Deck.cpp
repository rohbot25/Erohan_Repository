//
// Created by Evan Rohan on 1/24/23.
//

#include "Deck.h"
#include <time.h>
#include <sstream>
using namespace std;

Deck::Deck() {
    srand(time(NULL));
    hand = vector<Card>();
    deck = vector<Card>();
    house = vector<Card>();
    money = START_CASH;
}

vector<Card> Deck::getHouse() {
    return house;
}

double Deck::getMoney() {
    return money;
}

Card Deck::selectCard() {
    int randomIndex;
    Card randomCard;

    if(deck.size()!=0) {
        randomIndex = rand() % deck.size();
        //select random card
        randomCard = deck[randomIndex];
        //remove it
        deck.erase(deck.begin() + randomIndex);
    }

    return randomCard;
}

void Deck::shuffle() {
    Card card;
    //for all suits
    for(int a = 1; a < MAX_SUIT; ++a){
        if(a==1)
            card.suit = HEART;
        else if(a==2)
            card.suit = DIAMOND;
        else if(a==3)
            card.suit = CLUB;
        else
            card.suit = SPADE;
        //all 13 cards
        for(int i = 1; i <= MAX_CARDS; ++i){
            if(i <= 10){
                card.value = i;
                if(i==1) {
                    card.type = "Ace";
                    card.value = 11;
                }
                else if(i==2)
                    card.type="Two";
                else if(i==3)
                    card.type="Three";
                else if(i==4)
                    card.type="Four";
                else if(i==5)
                    card.type="Five";
                else if(i==6)
                    card.type="Six";
                else if(i==7)
                    card.type="Seven";
                else if(i==8)
                    card.type="Eight";
                else if(i==9)
                    card.type="Nine";
                else
                    card.type="Ten";

                deck.push_back(card);
            }else{
                card.value = 10;
                if(i == 11)
                    card.type="Jack";
                else if(i==12)
                    card.type="Queen";
                else
                    card.type="King";

                deck.push_back(card);
            }
        }
    }
}

void Deck::startRound(ostream &outs) {
    //clear hands
    hand.clear();
    house.clear();
    //shuffle deck
    shuffle();
    //add new starting cards to hand
    hand.push_back(selectCard());
    hand.push_back(selectCard());
    house.push_back(selectCard());
    house.push_back(selectCard());
    //display money of user
    outs << "You have $" << money << endl;
}

int Deck::handSum() {
    int sum = 0;
    //sum hand value
    for(int i=0;i<hand.size();++i){
        sum += hand[i].value;
    }
    //if over, check if an ace needs to be counted as 1 instead of 11
    if(sum > MAX_POINTS){
        sum = 0;
        for(int i=0;i<hand.size();++i){
            if(hand[i].type == "Ace")
                ++sum;
            else
                sum += hand[i].value;
        }
    }
    return sum;
}

int Deck::houseSum() {
    int sum = 0;
    //sum cards
    for(int i=0;i<house.size();++i){
        sum += house[i].value;
    }
    //if bust, check if an ace needs to be counted as 1 instead of 11
    if(sum > MAX_POINTS){
        sum = 0;
        for(int i=0;i<house.size();++i){
            if(house[i].type == "Ace")
                ++sum;
            else
                sum += house[i].value;
        }
    }
    return sum;
}

void Deck::getBet(std::ostream &outs, std::istream &ins) {
    bet = MIN_BET;
    stringstream ss;
    string line;
    bool good = true;
    int count =0;
    //prompt user
    outs << "How much would you like the bet?(minimum of $25.00):  $";
    getline(ins,line);
    for (int i = 0; i < line.length(); ++i) {
        if (isspace(line.at(i))||!isdigit(line.at(i))) {
            if (line.at(i) == '.' && count < 1) {
                ++count;
            }else
                good=false;
        }

    }
    ss << line;
    ss >> bet;
    //validate
    while(!ss || line.empty() || bet < MIN_BET || bet > money || !good){
        count = 0;
        good = true;
        if(line.empty())
            outs << "No input. How much would you like the bet?(minimum of $25.00): $";
        else if(!ss)
            outs << "Invalid input. Please enter your input with a number of at least $25.00: $";
        else if(bet < MIN_BET)
            outs << "Bet is too low. Please bet at least $25.00: $";
        else if(bet > money)
            outs << "You do not have that much money. Please bet an amount of money you have: $";
        else
            outs << "You're doing way too much at this point. Enter a bet buddy(minimum of $25): $";

        ins.clear();
        getline(ins,line);
        ss.str("");
        ss.clear();
        for (int i = 0; i < line.length(); ++i) {
            if (isspace(line.at(i))||!isdigit(line.at(i))) {
                if (line.at(i) == '.' && count < 1) {
                    ++count;
                }else
                    good=false;
            }

        }
        ss << line;
        ss >> bet;
    }
    money -= bet;

}

bool Deck::hitOrStand(std::ostream &outs, std::istream &ins) const{
    char character;
    stringstream ss;
    string line;
    bool good = false;
    //prompt user
    outs << "Would you like to hit or stand?(h for hit/s for stand): ";
    ins.clear();
    getline(ins,line);
    ss << line;
    ss >> character;
    if(line.length() == CHARACTER) {
        if (character == 'h' || character == 'H')
            return true;
        else if (character == 's' || character == 'S')
            return false;
        else
            good = false;
    }
    //validate
    while(line.empty() || line.length()>CHARACTER || !good){
        if(line.empty())
            outs << "No input. Enter a single character(h for hit/s for stand): ";
        else if(line.length() > CHARACTER)
            outs << "Enter a single character(h for hit/s for stand): ";
        else
            outs << "Please only enter h for hit, or s for stand: ";

        ins.clear();
        getline(ins,line);
        ss.str("");
        ss.clear();
        ss << line;
        ss >> character;
        if(line.length()==CHARACTER) {
            if (character == 'h' || character == 'H')
                return true;
            else if (character == 's' || character == 'S')
                return false;
            else
                good = false;
        }
    }

}

bool Deck::goAgain(std::ostream &outs, std::istream &ins) const{
    char input;
    string line;
    stringstream ss;
    bool good = false;
    //prompt user
    outs << "Would you like to play another hand?(y for yes/n for no) ";
    ins.clear();
    getline(ins,line);
    ss.str("");
    ss.clear();
    ss << line;
    ss >> input;
    if(line.length()==CHARACTER) {
        if (input == 'y' || input == 'Y')
            return true;
        else if (input == 'n' || input == 'N')
            return false;
        else
            good = false;
    }
    //validate
    while(line.empty() || line.length()>CHARACTER || !good){
        if(line.empty())
            outs << "No input. Enter a single character(y for yes/n for no): ";
        else if(line.length() > CHARACTER)
            outs << "Enter a single character(y for yes/n for no): ";
        else
            outs << "Please only enter y for yes, or n for no: ";

        ins.clear();
        getline(ins,line);
        ss.str("");
        ss.clear();
        ss << line;
        ss >> input;
        if(line.length()==CHARACTER) {
            if (input == 'y' || input == 'Y')
                return true;
            else if (input == 'n' || input == 'N')
                return false;
            else
                good = false;
        }
    }

}

void Deck::runHouse(std::ostream &outs) {
    //if user bust, end game
    if(handSum()>MAX_POINTS){
        outs << "You Lost." << endl;
    }else {
        //run house till over 17
        while (houseSum() < END) {
            house.push_back(selectCard());
        }
       //display house hand
        outs << "The house hand is: ";
        for (int i = 0; i < house.size(); ++i) {
            outs << printCard(house[i]) << ", ";
        }
        outs << endl;
        //check if house busted, or display points
        if(houseSum() <= MAX_POINTS)
            outs << "The house hand totals to " << houseSum() << endl;
        else
            outs << "The house busted.\n" << endl;

        //decide winner, and give bet back accordingly
        if (houseSum() <= MAX_POINTS && handSum() <= MAX_POINTS) {
            if (houseSum() > handSum()) {
                outs << "You Lost." << endl;
            } else if (houseSum() < handSum()) {
                outs << "You Won! You have gained $" << 2 * bet << endl;
                money += 2 * bet;
            } else {
                outs << "Draw. Your bet of $" << bet << " has been returned to you." << endl;
                money += bet;
            }
        } else if (houseSum() > MAX_POINTS) {
            outs << "You Won! You have gained $" << 2 * bet << endl;
            money += 2 * bet;
        } else {
            outs << "You Lost." << endl;
        }
    }
}

string Deck::printCard(Card card) {
    string suit;
    //switch enum to string
    if(card.suit==HEART)
        suit = "Hearts";
    else if(card.suit==DIAMOND)
        suit = "Diamonds";
    else if(card.suit==CLUB)
        suit = "Clubs";
    else
        suit = "Spades";
    //make string
    string out= card.type + " of " + suit;

    return out;

}

void Deck::addCardToHand() {
    //add selected card
    hand.push_back(selectCard());
}

void Deck::showHand(std::ostream &outs) {
    outs << "Your hand: ";
    //loop through hand and print each card
    for(int i=0; i<hand.size();++i){
        outs << printCard(hand[i]) << ", ";
    }
    //hand total
    outs << endl;
    outs << "Your hand totals to " << handSum() << endl;
}
