/*==========================================================

 Title:     Assignment 9 - C++ Wizard Creature Department
 Course:  	CIS 2252
 Author:  	<Sun Young Boyd>
 Date:    	<11/02/23>
 Description: C++ Wizard Creature Department

 ==========================================================
*/

#include <iostream>
#include <string>

using namespace std;

//Write classes here...
class Arena {
    protected:
    int radius;
    
    public:
    Arena() {
        radius = 0;
    }
};

class ArenaArea : public Arena {
    public:
    void scanInput() {
        cin >> radius;
    }
};


class Creature {
    protected:
    string name;
    int quantity;
    string type;
    
    public:
    Creature() {
        name = "unknown";
        quantity = 0;
        type = "unknown";
    }
    
    Creature(string n, int q) {
        name = n;
        quantity = q;
        type = "unknown";
    }
    
    void printStatement() {
        cout << "There's " << quantity << " creatures named " << name << endl;
    }
};

class Phoenix : public Creature {
    private:
    string color;
    
    public:
    Phoenix() : color("unknown") {}
    
    Phoenix(string n, int q, string c) : Creature(n, q) {
        type = "Phoenix";
        color = c;
    }
    
    void printStatement() {
        cout << "There's " << quantity << " " << color << " phoenixes named " << name << endl;
    }
    
    void setName(string n) {
        name = n;
    }
    
    void setQuantity(int q) {
        quantity = q;
    }
    
    void setColor(string c) {
        color = c;
    }
};

class Basilisk : public Creature {
    public:
    Basilisk() {}
    
    Basilisk(string n) : Creature(n, 1) {
        type = "basilisk";
    }
    
    void printStatement() {
        cout << "There's 1 basilisk named " << name << endl;
    }
    
    void setName(string n) {
        name = n;
    }
};


int main()
{
	//create empty array of size 3 for storing name
    string nameArr[3];

	// Write loop here to record user input as names
    // and set third name to “Clover”
    for (int i = 0; i < 2; i++) {
        cin >> nameArr[i];
    }
    
    nameArr[2] = "Clover";
    
    

	Creature c(nameArr[0], 2);
	//print statement from class
	c.printStatement();

	Phoenix p;
	//sets name, quantity and color
	p.setName(nameArr[1]);
	p.setQuantity(3);
	p.setColor("golden");
    //print statement from class
	p.printStatement();

	Basilisk b;
    //sets name
	b.setName(nameArr[2]);
    //prints statement from class
	b.printStatement();

    
}

