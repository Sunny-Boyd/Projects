/*==========================================================

 Title:     Assignment 4 - C++ Care Unit
 Course:  	CIS 2252
 Author:  	<Sun Young Boyd>
 Date:    	<09/20/23>
 Description: C++ Care Unit

 ==========================================================
*/

#include <iostream>
using namespace std;


// Write functions here

double conversionMeters(double heightinfeet) {
    return heightinfeet / 3.281;
}

double BMIcalculator(double kg, double meter = 1.8) {
    return kg / (meter * meter);
}




int main() {
    //DO NOT MODIFY MAIN()!!!
    
    double w1, w2, h;
    cin >> w1 >> w2 >> h;

    float index1 = BMIcalculator(w1);
    float index2 = BMIcalculator(w2, conversionMeters(h));

    cout << index1 << " " << index2 << endl;

    swap(w1, w2);

    index1 = BMIcalculator(w1);
    index2 = BMIcalculator(w2, conversionMeters(h));
    cout << index1 << " " << index2 << endl;
}
