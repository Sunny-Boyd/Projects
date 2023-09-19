/*==========================================================

 Title:       Assignment 1 - Dragon Years
 Course:      CIS 2252
 Author:      <Sun Young Boyd>
 Date:        <09/05/23>
 Description: This program ....

 ==========================================================
*/
#include <iostream>
#include <string>

using namespace std;

int main() {
    
  //write your solution here
  string dragonName;
  double myNumber;
  double sum;
  

  cin >> dragonName;
  cin >> myNumber;
  
  sum = (myNumber - 2)*4 + 21;
  
  cout << "The dragon named " << dragonName << " is " << sum << " years old in wizard years.";
  
  
 
 
  
  return 0;
}

