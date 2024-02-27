/*==========================================================

 Title:       Assignment 8 - Arena Area Inheritance
 Course:      CIS 2252
 Author:      <Sun Young Boyd>
 Date:        <10/28/23>
 Description: Arena Area Inheritance

 ==========================================================
*/

#include <iostream>

using namespace std;

//Create classes Arena and ArenaArea here
class Arena {
    private:
    int radius;
    
    public:
    void setRadius(int rad) {
        radius = rad;
    }
    
    int getRadius() {
        return radius;
    }
    
    void display() {
        cout << getRadius() * getRadius();
    }
};


class ArenaArea:public Arena {
    public:
    void scan_input() {
        int rad;
        cin >> rad;
        cout << endl;
        setRadius(rad);
    }
    
    void display() {
        cout << 3.14 * getRadius() * getRadius() << endl;
    }
};


int main()
{
    //DO NOT MODIFY ANYTHING IN THE main() !!!
    
    ArenaArea stadium; //Declaring ArenaArea object stadium
    
    stadium.scan_input(); //Read the radius input
    
    stadium.Arena::display(); //Print the radius squared
    
    cout << endl;
    
    stadium.display(); //Print the area of the Arena (stadium) object
    
    return 0;
}
