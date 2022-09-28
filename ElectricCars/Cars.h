//
// Created by Evan Rohan on 9/28/22.
//

#ifndef ELECTRICCARS_CARS_H
#define ELECTRICCARS_CARS_H
#include <fstream>
#include <string>
#include <vector>
#include <iostream>
#include <iomanip>
using std::string, std::vector, std::ifstream, std::cout, std::endl, std::ostream, std::left, std::setw,std::right;

class Cars{
private:
    string brand;
    string model;
    double acceleration;
    int speed;
    int range;
    int efficiency;
    int charge;
    string powerTrain;
    string bodyStyle;
    int seats;
    double price;
public:
    //constructor
    Cars(string b, string m,double a, int s, int r, int e,int c, string p, string bs, int seats, double price){
        brand = b;
        model = m;
        acceleration = a;
        speed = s;
        range = r;
        efficiency = e;
        charge = c;
        powerTrain = p;
        bodyStyle = bs;
        this->seats = seats;
        this->price = price * .97;
    }
    //getters
    string getBrand() const{
        return brand;
    }
    string getModel() const{
        return model;
    }
    double getAcceleration() const{
        return acceleration;
    }
    int getSpeed() const{
        return speed;
    }
    int getRange() const{
        return range;
    }
    int getEfficiency() const{
        return efficiency;
    }
    int getCharge() const{
        return charge;
    }
    string getPowerTrain() const{
        return powerTrain;
    }
    string getBodyStyle() const{
        return bodyStyle;
    }
    int getSeats() const{
        return seats;
    }
    double getPrice() const{
        return price;
    }
    //setters

    friend ostream& operator << (ostream& outs, const Cars& car){
        outs << setw(11) << car.getBrand();
        outs << setw(31) << car.getModel();
        outs << setw (10 ) << car.getBodyStyle();
        outs << setw(5) << car.getAcceleration();
        outs << setw(4) << car.getSpeed();
        outs << setw(4) << car.getRange();
        outs << setw(4) << car.getEfficiency();
        outs << setw(4) << car.getCharge();
        outs << setw(4) << car.getPowerTrain();
        outs << setw(2) << car.getSeats();
        outs << setw(7) << car.getPrice();
        return outs;
}
};

void getDataFromFile(string filename, vector<Cars>& cars){
    ifstream inFile;
    inFile.open(filename);

    //get heading
    string heading;
    if(inFile){
        getline(inFile, heading);
        cout << heading << endl;
    }else{
        cout << "Could not open file." << endl;
    }

    string brand = "", model = "", bodyStyle = "", powerTrain = "";
    int seats = 0, efficiency = 0, range = 0, speed = 0,charge = 0;
    double price = 0, acceleration = 0;
    char comma;
    //while loop to store data
    while(inFile && inFile.peek() != EOF){

        getline(inFile, brand, ',');

        getline(inFile, model, ',');

        inFile >> acceleration;
        inFile >> comma;

        inFile >> speed;
        inFile >> comma;

        inFile >> range;
        inFile >> comma;

        inFile >> efficiency;
        inFile >> comma;

        inFile >> charge;
        inFile >> comma;

        getline(inFile, powerTrain, ',');

        getline(inFile, bodyStyle, ',');

        inFile >> seats;
        inFile >> comma;

        inFile >> price;
    }
    inFile.close();
}


#endif //ELECTRICCARS_CARS_H




