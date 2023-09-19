/*==========================================================

 Title:       Assignment 2 - Numerical Representation
 Course:      CIS 2252
 Author:      <Sun Young Boyd>
 Date:        <09/07/23>
 Description: Numerical Representation

 ==========================================================
*/
#include <cmath>
#include <iostream>
#include <string>

using namespace std;


    
  //write your solution here
  
  
string numWords(int numbers) { 
 string words[9] = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
 
    if (numbers >=1 && numbers <=9) {
        return words[numbers - 1];
    } else if (numbers % 2 ==0) {
        return "even";
    } else {
        return "odd";
    }
}    
 
 void myFunction(int first, int second) {
     for (int i = first; i <=second; ++i) {
         string result = numWords(i);
         cout << result <<endl;
     }
 }
 
 
int main() {
    
    int first, second;
    
    cin >> first;
    cin >> second;
    
myFunction(first, second);   
    


              
  return 0;
}


