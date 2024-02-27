/*==========================================================

 Title:     Assignment 10 (Final) - C++ Currency Translation Machine
 Course:  	CIS 2252
 Author:  	<Sun Young Boyd>
 Date:    	<11/21/23>
 Description: C++ Currency Translation Machine

 ==========================================================
*/
// Define necessary directives

// create your classes here

#include <iostream>
#include <fstream>
#include <iomanip>
#include <limits>

using namespace std;


class ctm {
    protected:
    int currencySelection;
    double conversionFactor;
    string currencyName;
    
    public:
    void intro() {
        while (true) {
            cin >> currencySelection;
            if (cin.fail() || currencySelection < 1 || currencySelection > 3) {
                cin.clear();
                cout << "Invalid entry, Please enter a nummber between 1-3:";
            } else {
                break;
            }
        }
        
        switch (currencySelection) {
            case 1:
            conversionFactor = 6.77;
            currencyName = "Yuan";
            break;
            
            case 2:
            conversionFactor = 0.98;
            currencyName = "Euro";
            break;
            
            case 3:
            conversionFactor = 0.83;
            currencyName = "Pounds";
            break;
            default:
            break;
        }
    }
};


class amount : public ctm {
    protected:
    double exchangeAmount;
    
    public:
    void calculations() {
        cin >> exchangeAmount;
        
        double conversionAmount = exchangeAmount / conversionFactor;
        double conversionFee = conversionAmount * 0.05;
        double totalAmountDue = conversionAmount + conversionFee;
        
        ofstream outputFile("receipt.txt");
        if (outputFile.is_open()) {
            outputFile << fixed << setprecision(2);
            outputFile << exchangeAmount << " " << currencyName << endl;
            outputFile << conversionAmount << " " << "dollars" << endl;
            outputFile << conversionFee << " " << "dollars" << endl;
            outputFile << totalAmountDue << " " << "dollars" << endl;
            outputFile.close();
        } else {
            
        }
    }
};



class rc : public amount {
    public:
    void rctype() {
        ifstream inputFile("receipt.txt");
        if (inputFile.is_open()) {
            string line;
            while (getline(inputFile, line)) {
                cout << line << endl;
            }
            inputFile.close();
        } else {
            
        }
    }
};

int main()
{
    //No need to modify main as everything should be handled in the classes
    rc obj; 
    obj.ctm::intro();
    obj.amount::calculations();
    obj.rctype();
    
    return 0;
}

