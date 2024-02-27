/*==========================================================

 Title:       Assignment 7 - Wizard Identification using Classes
 Course:      CIS 2252
 Author:      <Sun Young Boyd>
 Date:        <10/19/2023>
 Description: Wizard Identification using Classes

 ==========================================================
*/

#include <iostream>
#include <string>

using namespace std;

//Create your class Wizard here.
class Wizard {
    private: 
    int wizardAge;
    string wizardFirstName;
    string wizardLastName;
    int wizard_ID;
    
    public:
    void set_wizardAge (int age) {
        wizardAge = age;
    }
    
    void set_wizard_ID (int id) {
        wizard_ID = id;
    }
    
    void set_wizardFirstName (string firstName) {
        wizardFirstName = firstName;
    }
    
    void set_wizardLastName (string lastName) {
        wizardLastName = lastName;
    }



    int get_wizardAge() {
        return wizardAge;
    }

    int get_wizard_ID() {
        return wizard_ID;
    }

    string get_wizardFirstName() {
        return wizardFirstName;
    }

    string get_wizardLastName() {
        return wizardLastName;
    }
};





int main() {
//DO NOT MODIFY THE MAIN()!!!!
    int wizardAge, wizard_ID;
    string wizardFirstName, wizardLastName;
    
    cin >> wizardAge >> wizardFirstName >> wizardLastName >> wizard_ID;
    
    Wizard wiz;
    wiz.set_wizardAge(wizardAge);
    wiz.set_wizard_ID(wizard_ID);
    wiz.set_wizardFirstName(wizardFirstName);
    wiz.set_wizardLastName(wizardLastName);
    
    cout << wiz.get_wizardAge() << "\n";
    cout << wiz.get_wizardLastName() << ", " << wiz.get_wizardFirstName() << "\n";
    cout << wiz.get_wizard_ID();
    
    return 0;
}
